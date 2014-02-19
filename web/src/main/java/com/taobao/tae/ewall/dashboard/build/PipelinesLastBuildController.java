package com.taobao.tae.ewall.dashboard.build;


import com.taobao.tae.ewall.build.BuildGrid;
import com.taobao.tae.ewall.build.BuildPipelineDO;
import com.taobao.tae.ewall.build.BuildProjectDO;
import com.taobao.tae.ewall.build.PipelineBuild;
import com.taobao.tae.ewall.pipeline.PipelineDO;
import com.taobao.tae.ewall.pipeline.PipelineGroupDO;
import com.taobao.tae.ewall.project.BuildProjectGridImpl;
import com.taobao.tae.ewall.project.ProjectGridImpl;
import com.taobao.tae.ewall.project.ProjectDO;
import com.taobao.tae.ewall.project.ProjectGrid;
import com.taobao.tae.ewall.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * User: xinyuan.ymm
 * Date: 13-12-2
 * Time: 下午7:57
 */
@Controller
@RequestMapping("/dashboard/")
public class PipelinesLastBuildController {

    @Resource
    ProjectService projectService;
    @Resource
    PipelineService pipelineService;
    @Resource
    BuildProjectService buildProjectService;
    @Resource
    BuildPipelineService buildPipelineService;
    @Resource
    PipelineGroupService pipelineGroupService;

    /**
     * 获取 所有 Pipelines ,通过PipelineGroup进行分组 ,按照 display是否展示进行展示最后一次构建信息。
     *
     * @param model
     * @return
     */
    @RequestMapping("build/pipelineslastbuild")
    public String getDisPlayPipelinesLastBuildInfoByGroupView(ModelMap model) {

        HashMap<String, List<PipelineBuild>> displayPipelinesMap = new HashMap<String, List<PipelineBuild>>();
        List<PipelineGroupDO> groupDOs = pipelineGroupService.getAllGroups();
        for (int i = 0; i < groupDOs.size(); i++) {
            if (groupDOs.get(i).getDisplay()) {
                List<PipelineDO> pipelineDOs = pipelineService.findPipelinesByGroupId(groupDOs.get(i).getId());
                List<PipelineBuild> displayPipelines = new ArrayList<PipelineBuild>();
                for (PipelineDO pipeline : pipelineDOs) {
                    if (pipeline.getDisplay()) {
//                        pipelineService.initPipeline(pipeline.getId());
                        BuildPipelineDO buildPipelineDO = buildPipelineService.loadLastBuildPipelineByPipelineId(pipeline.getId());
                        if (buildPipelineDO == null) {
                            PipelineBuild noPipelineBuildHistory = new PipelineBuild();
                            noPipelineBuildHistory.setPipelineDO(pipeline);
                            displayPipelines.add(noPipelineBuildHistory);
                            continue;
                        }
                        List<BuildProjectDO> buildProjectDOs = buildProjectService.getBuildProjectsByBuildPipelineId(buildPipelineDO.getId());
                        BuildProjectDO start = new BuildProjectDO();
                        for (BuildProjectDO project : buildProjectDOs) {
                            if (project.getProjectId().equals(pipeline.getHeadProjectId())) {
                                start = project;
                                break;
                            }
                        }
                        PipelineBuild pipelineBuild = new PipelineBuild();
                        pipelineBuild.setPipelineDO(pipeline);
                        BuildGrid buildGrid = new BuildProjectGridImpl(start, buildProjectDOs);
                        pipelineBuild.setBuildGrid(buildGrid);
                        pipelineBuild.setGridColumnSize(buildGrid.getColumns());
                        pipelineBuild.setGridRowSize(buildGrid.getRows());
                        pipelineBuild.setBuildPipelineDO(buildPipelineDO);
                        displayPipelines.add(pipelineBuild);
                    }
                }
                displayPipelinesMap.put(groupDOs.get(i).getName(), displayPipelines);
            }
        }

        model.addAttribute("displayPipelinesMap", displayPipelinesMap);
        return "dashboard/build/pipelineslastbuild";
    }


}
