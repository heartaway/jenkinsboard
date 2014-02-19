package com.taobao.tae.ewall.service.impl;

import com.alibaba.pt.commons.persistence.page.Page;
import com.taobao.tae.ewall.JenkinsServer;
import com.taobao.tae.ewall.build.BuildProjectDO;
import com.taobao.tae.ewall.client.JenkinsHttpClient;
import com.taobao.tae.ewall.dao.BuildProjectDao;
import com.taobao.tae.ewall.dao.ProjectDao;
import com.taobao.tae.ewall.model.BuildResult;
import com.taobao.tae.ewall.model.BuildStatus;
import com.taobao.tae.ewall.project.ProjectDO;
import com.taobao.tae.ewall.service.BuildProjectService;
import org.apache.tools.ant.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * User: xinyuan.ymm
 * Date: 14-2-14
 * Time: 下午2:17
 */
@Component("buildProjectService")
public class BuildProjectServiceImpl implements BuildProjectService {

    @Autowired
    BuildProjectDao buildProjectDao;

    @Autowired
    ProjectDao projectDao;

    @Override
    public void triggerProjectBuild(Integer projectId, Long buildPipelineId, Boolean isStartProject) {
        ProjectDO projectDO = projectDao.load(projectId);
        BuildProjectDO buildProjectDO = new BuildProjectDO();
        buildProjectDO.setBuildPipelineId(buildPipelineId);
        buildProjectDO.setProjectId(projectId);
        buildProjectDO.setDownStreamProjects(projectDO.getDownStreamProjects());
        buildProjectDO.setProjectName(projectDO.getName());
        try {
            JenkinsHttpClient client = new JenkinsHttpClient(new URI(projectDO.getJenkinsURL()));
            JenkinsServer server = new JenkinsServer(client);
            Integer nextBuildNumber = server.getJob(projectDO.getName()).getNextBuildNumber();
            buildProjectDO.setJenkinsProjectBuildNumber(Long.valueOf(nextBuildNumber.toString()));
            if (isStartProject) {
                // 触发 第一个 工程进行 构建
                server.getJob(projectDO.getName()).build();
                buildProjectDO.setStatus(BuildStatus.BUILDING.getCode().toString());
            } else {
                buildProjectDO.setStatus(BuildStatus.INIT.getCode().toString());
            }
            buildProjectDO.setResult(BuildResult.NOT_BUILT.getCode().toString());
            buildProjectDao.create(buildProjectDO);
        } catch (URISyntaxException e) {
        } catch (IOException e) {
        }

    }

    @Override
    public void stopProjectBuild(Integer projectId, Long buildPipelineId) {
        BuildProjectDO buildProjectDO = new BuildProjectDO();
        buildProjectDO.setBuildPipelineId(buildPipelineId);
        buildProjectDO.setProjectId(projectId);
        List<BuildProjectDO> buildProjectDOs = buildProjectDao.find(buildProjectDO);
        if (buildProjectDOs == null || buildProjectDOs.size() == 0) {
            return;
        } else {
            buildProjectDO = buildProjectDOs.get(0);
            if (buildProjectDO.getStatus().equals(BuildStatus.BUILDING.getCode().toString())) {
                buildProjectDO.setStatus(BuildStatus.STOP.getCode().toString());
                buildProjectDO.setResult(BuildResult.ABORTED.getCode().toString());
                buildProjectDao.update("update", buildProjectDO);
            }
        }
    }

    @Override
    public BuildProjectDO loadByBuildPipelineIdAndProjectName(String projectName, Long buildPipelineId) {
        BuildProjectDO buildProjectDO = new BuildProjectDO();
        buildProjectDO.setBuildPipelineId(buildPipelineId);
        buildProjectDO.setProjectName(projectName);
        List<BuildProjectDO> buildProjectDOs = buildProjectDao.find(buildProjectDO);
        if (buildProjectDOs == null || buildProjectDOs.size() == 0) {
            return null;
        } else {
            return buildProjectDOs.get(0);
        }
    }

    @Override
    public List<BuildProjectDO> getBuildProjectsByBuildPipelineId(Long buildPipelineId) {
        BuildProjectDO buildProjectDO = new BuildProjectDO();
        buildProjectDO.setBuildPipelineId(buildPipelineId);
        List<BuildProjectDO> buildProjectDOs = buildProjectDao.find(buildProjectDO);
        return buildProjectDOs;
    }

    @Override
    public Page<BuildProjectDO> findProjectBuildsByPage(Integer projectId, Integer index) {
        BuildProjectDO buildProjectDO = new BuildProjectDO();
        buildProjectDO.setProjectId(projectId);
        Page<BuildProjectDO> buildProjectDOPage = buildProjectDao.page("page", buildProjectDO, index);
        return buildProjectDOPage;
    }

    @Override
    public Boolean update(BuildProjectDO buildProjectDO) {
        return buildProjectDao.update(buildProjectDO);
    }

    @Override
    public String getJenkinsUrlByProjectId(Integer projectId) {
        ProjectDO projectDO = projectDao.load(projectId);
        if (projectDO == null) {
            return null;
        } else {
            return projectDO.getJenkinsURL();
        }
    }
}
