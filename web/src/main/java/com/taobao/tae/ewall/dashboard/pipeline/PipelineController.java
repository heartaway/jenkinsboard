package com.taobao.tae.ewall.dashboard.pipeline;


import com.taobao.tae.ewall.dashboard.project.ProjectUtil;
import com.taobao.tae.ewall.project.ProjectGridImpl;
import com.taobao.tae.ewall.project.ProjectDO;
import com.taobao.tae.ewall.pipeline.PipelineDO;
import com.taobao.tae.ewall.pipeline.PipelineGroupDO;
import com.taobao.tae.ewall.service.ProjectService;
import com.taobao.tae.ewall.service.PipelineGroupService;
import com.taobao.tae.ewall.service.PipelineService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * User: xinyuan.ymm
 * Date: 13-12-2
 * Time: 下午7:57
 */
@Controller
@RequestMapping("/dashboard/pipeline/")
public class PipelineController {

    @Resource
    ProjectService projectService;
    @Resource
    PipelineService pipelineService;
    @Resource
    PipelineGroupService pipelineGroupService;

    /**
     * 获取 所有 Pipelines 并通过PipelineGroup进行分组
     *
     * @param model
     * @return
     */
    @RequestMapping("pipelinelist")
    public String getAllPipelinesSortByGroup(ModelMap model) {
        HashMap<String, List<PipelineDO>> pipelinesMap = new HashMap<String, List<PipelineDO>>();
        List<ProjectDO> projectList = projectService.getAllProjects();
        List<PipelineGroupDO> groupDOs = pipelineGroupService.getAllGroups();
        for (int i = 0; i < groupDOs.size(); i++) {
            List<PipelineDO> pipelineDOs = pipelineService.findPipelinesByGroupId(groupDOs.get(i).getId());
            pipelinesMap.put(groupDOs.get(i).getName(), pipelineDOs);
        }
        model.addAttribute("pipelinesMap", pipelinesMap);
        model.addAttribute("projectList", projectList);
        return "dashboard/pipeline/pipelinelist";
    }

    @RequestMapping(value = "pipelineadd", method = RequestMethod.POST)
    public String addPipeline(String name, String groupName, Integer headProjectId, String description, String display, ModelMap model) {
        PipelineGroupDO pipelineGroupDO = pipelineGroupService.findByName(groupName);
        if (StringUtils.isNotBlank(name)) {
            PipelineDO pipelineDO = new PipelineDO();
            pipelineDO.setName(name);
            pipelineDO.setHeadProjectId(headProjectId);
            pipelineDO.setDescription(description);
            pipelineDO.setDisplay(Boolean.valueOf(display));
            pipelineDO.setGroupId(pipelineGroupDO.getId());
            Integer pipelineId = pipelineService.addPipeline(pipelineDO);
            pipelineService.initPipeline(pipelineId);
        }
        return "redirect:pipelinelist";
    }

    @RequestMapping(value = "pipelineedit", method = RequestMethod.POST)
    public String editPipeline(Integer id, String name, Integer groupId, String description, String display, ModelMap model) {
        if (id != null && StringUtils.isNotBlank(name)) {
            PipelineDO pipelineDO = new PipelineDO();
            pipelineDO.setId(id);
            pipelineDO.setName(name);
            pipelineDO.setGroupId(groupId);
            pipelineDO.setDescription(description);
            pipelineDO.setDisplay(Boolean.valueOf(display));
            Boolean result = pipelineService.updatePipeline(pipelineDO);
            model.addAttribute("editPipelineResult", result);
        }
        return "redirect:pipelinelist";
    }

    @RequestMapping(value = "pipelinedelete", method = RequestMethod.GET)
    public String deletePipeline(Integer id, ModelMap model) {
        if (id == null) {
            return "redirect:pipelinelist";
        }
        Boolean result = pipelineService.deleteById(id);
        model.addAttribute("deletePipelineResult", result);
        if (result) {
            return "redirect:pipelinelist";
        } else {
            return "redirect:pipelinelist";
        }
    }

    /**
     * 初始化 pipeline 管道中的 project 关系
     *
     * @param pipelineId
     * @param model
     * @return
     */
    @RequestMapping(value = "pipelinesetting", method = RequestMethod.GET)
    public String pipelineSetting(Integer pipelineId, ModelMap model) {
        if (pipelineId == null) {
            return "redirect:pipelinelist";
        }
        PipelineDO pipelineDO = pipelineService.findPipelineById(pipelineId);
        ProjectDO startProject = projectService.getProjectById(pipelineDO.getHeadProjectId());
        List<ProjectDO> allProject = projectService.getAllProjects();
        ProjectGridImpl projectGrid = new ProjectGridImpl(startProject, allProject);
        ProjectUtil projectUtil = new ProjectUtil();

        model.addAttribute("projectGrid", projectGrid);
        model.addAttribute("columnSize", projectGrid.getColumns());
        model.addAttribute("rowSize", projectGrid.getRows());
        model.addAttribute("allProject", allProject);
        model.addAttribute("pipelineId", pipelineId);
        model.addAttribute("projectService", projectService);
        model.addAttribute("projectUtil", projectUtil);

        return "dashboard/pipeline/pipelinesetting";
    }
}
