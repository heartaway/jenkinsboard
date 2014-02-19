package com.taobao.tae.ewall.dashboard.build;

import com.alibaba.pt.commons.persistence.page.Page;
import com.taobao.tae.ewall.build.BuildPipelineDO;
import com.taobao.tae.ewall.build.BuildProjectDO;
import com.taobao.tae.ewall.build.PipelineBuild;
import com.taobao.tae.ewall.dashboard.polling.PipelinePerfomPolling;
import com.taobao.tae.ewall.lock.Resources;
import com.taobao.tae.ewall.pipeline.PipelineDO;
import com.taobao.tae.ewall.project.ProjectGridImpl;
import com.taobao.tae.ewall.project.ProjectDO;
import com.taobao.tae.ewall.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * User: xinyuan.ymm
 * Date: 14-2-14
 * Time: 上午11:48
 */
@Controller
@RequestMapping("/dashboard/build/")
public class BuildPipelineController {

    @Resource
    BuildPipelineService buildPipelineService;

    @Resource
    BuildProjectService buildProjectService;

    @Resource
    ProjectService projectService;

    @Resource
    PipelineService pipelineService;

    @Autowired
    ResourcesLockService resourcesLockService;

    private ExecutorService pipelineExecutor = Executors.newFixedThreadPool(10);

    /**
     * 触发Pipeline构建，并对 pipeline中的所有节点进行初始化
     *
     * @param model
     * @return
     */
    @RequestMapping("triggerpipelinebuild")
    public String triggerPipelineBuild(Integer pipelineId, ModelMap model) {
        Boolean isLock = resourcesLockService.isLock(Resources.PIPELINE.concat("_").concat(pipelineId.toString()));
        if (isLock) {
            return "redirect:/dashboard/build/pipelineslastbuild";
        }
        Long buildPipelineId = buildPipelineService.triggerPipelineBuild(pipelineId);
        List<ProjectDO> allProject = projectService.getAllProjects();
        PipelineDO pipeline = pipelineService.findPipelineById(pipelineId);
        ProjectDO startProject = projectService.getProjectById(pipeline.getHeadProjectId());
        PipelineBuild pipelineBuild = new PipelineBuild();
        pipelineBuild.setPipelineDO(pipeline);
        ProjectGridImpl projectGrid = new ProjectGridImpl(startProject, allProject);

        Iterator<Map.Entry<Integer, Map<Integer, ProjectDO>>> rowKeyIterator = projectGrid.getData().entrySet().iterator();
        while (rowKeyIterator.hasNext()) {
            Map.Entry<Integer, Map<Integer, ProjectDO>> row = rowKeyIterator.next();
            Iterator<Map.Entry<Integer, ProjectDO>> columnIterator = row.getValue().entrySet().iterator();
            while (columnIterator.hasNext()) {
                Map.Entry<Integer, ProjectDO> grid = columnIterator.next();
                ProjectDO projectDO = grid.getValue();
                if (startProject.getId().equals(projectDO.getId())) {
                    buildProjectService.triggerProjectBuild(projectDO.getId(), buildPipelineId, Boolean.TRUE);
                } else {
                    buildProjectService.triggerProjectBuild(projectDO.getId(), buildPipelineId, Boolean.FALSE);
                }
            }
        }

        //polling pipeline building
        PipelinePerfomPolling pipelinePerfomPolling = new PipelinePerfomPolling(buildPipelineId, startProject.getName());
        pipelinePerfomPolling.setBuildPipelineService(buildPipelineService);
        pipelinePerfomPolling.setBuildProjectService(buildProjectService);
        pipelinePerfomPolling.setResourcesLockService(resourcesLockService);
        pipelineExecutor.submit(pipelinePerfomPolling);
        return "redirect:/dashboard/build/pipelineslastbuild";
    }

    /**
     * 强制停止Pipeline的构建
     *
     * @param buildPipelineId
     * @param model
     * @return
     */
    @RequestMapping("stoppipelinebuild")
    public String stopPipelineBuild(Long buildPipelineId, ModelMap model) {
        BuildPipelineDO buildPipelineDO = buildPipelineService.load(buildPipelineId);
        buildPipelineService.stopPipelineBuild(buildPipelineId);
        List<ProjectDO> allProject = projectService.getAllProjects();
        PipelineDO pipeline = pipelineService.findPipelineById(buildPipelineDO.getPipelineId());
        ProjectDO startProject = projectService.getProjectById(pipeline.getHeadProjectId());
        PipelineBuild pipelineBuild = new PipelineBuild();
        pipelineBuild.setPipelineDO(pipeline);
        ProjectGridImpl projectGrid = new ProjectGridImpl(startProject, allProject);

        Iterator<Map.Entry<Integer, Map<Integer, ProjectDO>>> rowKeyIterator = projectGrid.getData().entrySet().iterator();
        while (rowKeyIterator.hasNext()) {
            Map.Entry<Integer, Map<Integer, ProjectDO>> row = rowKeyIterator.next();
            Iterator<Map.Entry<Integer, ProjectDO>> columnIterator = row.getValue().entrySet().iterator();
            while (columnIterator.hasNext()) {
                Map.Entry<Integer, ProjectDO> grid = columnIterator.next();
                ProjectDO projectDO = grid.getValue();
                buildProjectService.stopProjectBuild(projectDO.getId(), buildPipelineId);
            }
        }
        return "redirect:/dashboard/build/pipelineslastbuild";
    }

    @RequestMapping("projectbuildlist")
    public String projectBuildListView(Integer projectId, Integer pageIndex, ModelMap model) {
        if (pageIndex <= 0) {
            pageIndex = 1;
        }
        Integer index = (pageIndex - 1) * Page.DEFAULT_SIZE_PER_PAGE;
        Page<BuildProjectDO> buildProjectDOPage = buildProjectService.findProjectBuildsByPage(projectId, index);
        ProjectDO projectDO = projectService.getProjectById(projectId);
        model.addAttribute("projectName", projectDO.getName());
        model.addAttribute("projectId", projectDO.getId());
        model.addAttribute("buildProjectDOPage", buildProjectDOPage);
        model.addAttribute("pageIndex", pageIndex);
        model.addAttribute("totalPageSize", buildProjectDOPage.getPageSize());
        return "dashboard/build/projectbuildlist";
    }

    @RequestMapping("projectbuilddetail")
    public String projectBuildDetailView(Long buildProjectId, Integer projectId, ModelMap model) {
        model.addAttribute("projectId", projectId);
        return "dashboard/build/projectbuilddetail";
    }

}
