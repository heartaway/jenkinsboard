package com.taobao.tae.ewall.dashboard.pipeline;


import com.taobao.tae.ewall.pipeline.PipelineDO;
import com.taobao.tae.ewall.pipeline.PipelineGroupDO;
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
        List<PipelineGroupDO> groupDOs = pipelineGroupService.getAllGroups();
        for (int i = 0; i < groupDOs.size(); i++) {
            List<PipelineDO> pipelineDOs = pipelineService.findPipelinesByGroupId(groupDOs.get(i).getId());
            pipelinesMap.put(groupDOs.get(i).getName(), pipelineDOs);
        }
        model.addAttribute("pipelinesMap", pipelinesMap);
        return "dashboard/pipeline/pipelinelist";
    }

    @RequestMapping(value = "pipelineadd", method = RequestMethod.POST)
    public String addGroup(String name, String groupName, String description, ModelMap model) {
        PipelineGroupDO pipelineGroupDO = pipelineGroupService.findByName(groupName);
        if (StringUtils.isNotBlank(name)) {
            PipelineDO pipelineDO = new PipelineDO();
            pipelineDO.setName(name);
            pipelineDO.setDescription(description);
            pipelineDO.setGroupId(pipelineGroupDO.getId());
            pipelineService.addPipeline(pipelineDO);
        }
        return "redirect:pipelinelist";
    }

    @RequestMapping(value = "pipelineedit", method = RequestMethod.POST)
    public String editGroup(Integer id, String name,Integer groupId, String description, ModelMap model) {
        if (id != null && StringUtils.isNotBlank(name)) {
            PipelineDO pipelineDO = new PipelineDO();
            pipelineDO.setId(id);
            pipelineDO.setName(name);
            pipelineDO.setGroupId(groupId);
            pipelineDO.setDescription(description);
            Boolean result = pipelineService.updatePipeline(pipelineDO);
            model.addAttribute("editPipelineResult", result);
        }
        return "redirect:pipelinelist";
    }

    @RequestMapping(value = "pipelinedelete", method = RequestMethod.GET)
    public String deletePipeline(Integer id, ModelMap model) {
        Boolean result = pipelineService.deleteById(id);
        model.addAttribute("deletePipelineResult", result);
        if (result) {
            return "redirect:pipelinelist";
        } else {
            return "redirect:pipelinelist";
        }
    }

}
