package com.taobao.tae.ewall.dashboard.polling;

import com.taobao.tae.ewall.JenkinsServer;
import com.taobao.tae.ewall.build.BuildPipelineDO;
import com.taobao.tae.ewall.build.BuildProjectDO;
import com.taobao.tae.ewall.build.BuildTestReportDO;
import com.taobao.tae.ewall.client.JenkinsHttpClient;
import com.taobao.tae.ewall.dao.ResourcesLockDao;
import com.taobao.tae.ewall.lock.Resources;
import com.taobao.tae.ewall.model.*;
import com.taobao.tae.ewall.service.BuildPipelineService;
import com.taobao.tae.ewall.service.BuildProjectService;
import com.taobao.tae.ewall.service.BuildTestReportService;
import com.taobao.tae.ewall.service.ResourcesLockService;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.*;

/**
 * User: xinyuan.ymm
 * Date: 14-2-18
 * Time: 上午10:11
 */
public class PipelinePerfomPolling implements Callable<Void> {

    private Long buildPipelineId;

    private String startProjectName;

    private ExecutorService pipelineExecutor;

    private BuildProjectService buildProjectService;

    private BuildPipelineService buildPipelineService;

    private ResourcesLockService resourcesLockService;

    private BuildTestReportService buildTestReportService;

    public PipelinePerfomPolling(Long buildPipelineId, String startProjectName) {
        this.buildPipelineId = buildPipelineId;
        this.startProjectName = startProjectName;
        pipelineExecutor = Executors.newCachedThreadPool();
    }

    @Override
    public Void call() throws Exception {
        performPolling(startProjectName);
        updatePipelineBuildResult();
        return null;
    }

    /**
     * 递归执行pipeline中每一个project的构建监听任务
     *
     * @param projectName
     */
    public void performPolling(String projectName) {
        try {
            BuildProjectDO buildProjectDO = buildProjectService.loadByBuildPipelineIdAndProjectName(projectName, buildPipelineId);
            String jenkinsUrl = buildProjectService.getJenkinsUrlByProjectId(buildProjectDO.getProjectId());
            //如果project 在 Jenkins 中配置为  “禁用项目”，则停止对此Job的构建轮询
            Boolean isBuildable = isBuildable(projectName, jenkinsUrl);
            if (!isBuildable) {
                return;
            }
            Thread.sleep(6000);
            Future<BuildWithDetails> future = pipelineExecutor.submit(new ProjectPerformPolling(jenkinsUrl, buildProjectDO.getProjectName(), buildProjectDO.getJenkinsProjectBuildNumber()));
            BuildWithDetails buildWithDetails = future.get(7200, TimeUnit.SECONDS);
            if (future.isDone()) {
                //数据本次构建信息持久化。
                buildResultPersistence(buildProjectDO, buildWithDetails, jenkinsUrl);
                //如果 任务在Jenkins中被终止，则停止构建其后续Job
                if (buildWithDetails.getResult().getCode() == BuildResult.ABORTED.getCode()) {
                    return;
                }
                Boolean isTrigger = isTriggerDownStreamProjectsBuild(buildWithDetails.getResult(), buildProjectDO.getProjectName(), jenkinsUrl);
                //如果构建结果与阀值 不符合停止下游工程构建。
                if (!isTrigger) {
                    return;
                }
                String downStreamProjectsString = buildProjectDO.getDownStreamProjects();
                if (downStreamProjectsString.endsWith(",")) {
                    downStreamProjectsString = downStreamProjectsString.substring(0, downStreamProjectsString.length() - 1);
                }
                if (StringUtils.isBlank(downStreamProjectsString)) {
                    //如果没有下游工程，停止构建。
                    return;
                }
                String[] downStreamProjects = downStreamProjectsString.split(",");
                for (String downStreamProjectName : downStreamProjects) {
                    performPolling(downStreamProjectName);
                }
            }
        } catch (TimeoutException timeoutException) {
            timeoutException.printStackTrace();
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        } catch (ExecutionException executionException) {
            executionException.printStackTrace();
        }
    }

    /**
     * 持久化 构建结果信息 ,包括 工程构建信息和测试结果信息。
     *
     * @param buildProjectDO
     * @param buildWithDetails
     * @return
     */
    public Boolean buildResultPersistence(BuildProjectDO buildProjectDO, BuildWithDetails buildWithDetails, String jenkinsUrl) {
        buildProjectDO.setResult(buildWithDetails.getResult().getCode().toString());
        buildProjectDO.setDuration(buildDurationHandle(Integer.valueOf(buildWithDetails.getDuration())));
        buildProjectDO.setStatus(BuildStatus.STOP.getCode().toString());
        buildProjectDO.setDescription(buildWithDetails.getDescription());
        buildProjectService.update(buildProjectDO);
        try {
            JenkinsHttpClient client = new JenkinsHttpClient(new URI(jenkinsUrl));
            JenkinsServer server = new JenkinsServer(client);
            TestReport testReport = server.getTestReport(buildProjectDO.getProjectName(), buildProjectDO.getJenkinsProjectBuildNumber());
            if (testReport == null) {
                return Boolean.FALSE;
            }
            BuildTestReportDO buildTestReportDO = new BuildTestReportDO();
            buildTestReportDO.setBuildProjectId(buildProjectDO.getId());
            buildTestReportDO.setDuration(buildDurationHandle(Integer.valueOf(buildWithDetails.getDuration())));
            buildTestReportDO.setFailCount(testReport.getFailCount());
            buildTestReportDO.setSkipCount(testReport.getSkipCount());
            buildTestReportDO.setTotalCount(testReport.getTotalCount());
            if (testReport.getChildReports() != null && testReport.getChildReports().get(0).getChild() != null) {
                buildTestReportDO.setNumber(testReport.getChildReports().get(0).getChild().getNumber());
            }
            if (testReport.getChildReports() != null && testReport.getChildReports().get(0).getChild() != null) {
                buildTestReportDO.setUrl(testReport.getChildReports().get(0).getChild().getUrl());
            }
            if (testReport.getChildReports() != null) {
                JSONObject jsonObject = JSONObject.fromObject(testReport);
                buildTestReportDO.setTestReport(jsonObject.toString());
            }
            buildTestReportService.create(buildTestReportDO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Boolean.TRUE;
    }

    /**
     * 判断是否触发下游工程继续构建
     *
     * @param buildResult
     * @param projectName
     * @param jenkinsUrl
     * @return
     */
    public Boolean isTriggerDownStreamProjectsBuild(BuildResult buildResult, String projectName, String jenkinsUrl) {
        try {
            JenkinsHttpClient client = new JenkinsHttpClient(new URI(jenkinsUrl));
            JenkinsServer server = new JenkinsServer(client);
            TriggerThreshold triggerThreshold = server.getChildJobTriggerThreshold(projectName);
            if (buildResult.getCode().equals(BuildResult.SUCCESS.getCode()) && triggerThreshold.getCode().equals(TriggerThreshold.SUCCESS.getCode())) {
                return Boolean.TRUE;
            } else if (buildResult.getCode().equals(BuildResult.UNSTABLE.getCode()) && triggerThreshold.getCode().equals(TriggerThreshold.UNSTABLE.getCode())) {
                return Boolean.TRUE;
            } else if (buildResult.getCode().equals(BuildResult.SUCCESS.getCode()) && triggerThreshold.getCode().equals(TriggerThreshold.UNSTABLE.getCode())) {
                return Boolean.TRUE;
            } else if (triggerThreshold.getCode().equals(TriggerThreshold.FAILURE.getCode())) {
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Boolean.FALSE;
    }

    public Boolean isBuildable(String projectName, String jenkinsUrl) {
        try {
            JenkinsHttpClient client = new JenkinsHttpClient(new URI(jenkinsUrl));
            JenkinsServer server = new JenkinsServer(client);
            return server.getJob(projectName).isBuildable();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Boolean.FALSE;
    }

    /**
     * Pipeline构建完成后将pipeline构建结果持久化
     */
    public void updatePipelineBuildResult() {
        BuildPipelineDO buildPipelineDO = buildPipelineService.load(buildPipelineId);
        resourcesLockService.unlock(Resources.PIPELINE.concat("_").concat(buildPipelineDO.getPipelineId().toString()));
        buildPipelineDO.setGmtEnd(new Date());
        buildPipelineDO.setStatus(BuildStatus.STOP.getCode().toString());
        buildPipelineDO.setResult(BuildResult.SUCCESS.getCode().toString());
        buildPipelineService.update(buildPipelineDO);
    }


    public String buildDurationHandle(Integer duration) {
        String time = "";
        if (duration / 3600000 > 0) {
            //大于 1小时
            time = String.valueOf(duration / 3600000);
            time = time + " 小时";
            duration = duration % 3600000;
            if (duration / 60000 > 0) {
                //大于 1 分钟
                time = time + String.valueOf(duration / 60000);
                time = time + " 分";
                duration = duration % 60000;
                time = time + String.valueOf(duration / 1000);
                time = time + " 秒";
            } else {
                time = time + String.valueOf(duration / 1000);
                time = time + " 秒";
            }
        } else {
            if (duration / 60000 > 0) {
                //大于 1 分钟   且 小于等于 1小时
                time = String.valueOf(duration / 60000);
                time = time + " 分";
                duration = duration % 60000;
                time = time + String.valueOf(duration / 1000);
                time = time + " 秒";
            } else {
                time = String.valueOf(duration / 1000);
                time = time + " 秒";
            }
        }
        return time;
    }

    public void setBuildProjectService(BuildProjectService buildProjectService) {
        this.buildProjectService = buildProjectService;
    }

    public void setBuildPipelineService(BuildPipelineService buildPipelineService) {
        this.buildPipelineService = buildPipelineService;
    }

    public void setResourcesLockService(ResourcesLockService resourcesLockService) {
        this.resourcesLockService = resourcesLockService;
    }

    public BuildTestReportService getBuildTestReportService() {
        return buildTestReportService;
    }

    public void setBuildTestReportService(BuildTestReportService buildTestReportService) {
        this.buildTestReportService = buildTestReportService;
    }

}
