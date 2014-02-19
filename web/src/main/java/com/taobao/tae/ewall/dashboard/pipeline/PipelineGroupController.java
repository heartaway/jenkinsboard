package com.taobao.tae.ewall.dashboard.pipeline;


import com.taobao.tae.ewall.pipeline.PipelineGroupDO;
import com.taobao.tae.ewall.service.PipelineGroupService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.List;

/**
 * User: xinyuan.ymm
 * Date: 13-12-2
 * Time: 下午7:57
 */
@Controller
@RequestMapping("/dashboard/pipeline/")
public class PipelineGroupController {

    @Resource
    PipelineGroupService pipelineGroupService;

    @RequestMapping("grouplist")
    public String getAllGroups(ModelMap model) {
        List<PipelineGroupDO> groupDOs = pipelineGroupService.getAllGroups();
        model.addAttribute("grouplist", groupDOs);
        return "dashboard/pipeline/grouplist";
    }


    @RequestMapping(value = "groupadd", method = RequestMethod.POST)
    public String addGroup(String name, String description, String display, ModelMap model) {
        if (StringUtils.isNotBlank(name)) {
            PipelineGroupDO groupDO = new PipelineGroupDO();
            groupDO.setName(name);
            groupDO.setDescription(description);
            groupDO.setDisplay(Boolean.valueOf(display));
            Integer result = pipelineGroupService.addGroup(groupDO);
            model.addAttribute("addGroupResult", result);
        }
        return "redirect:grouplist";
    }

    @RequestMapping(value = "groupdelete", method = RequestMethod.GET)
    public String deleteGroup(Integer id, ModelMap model) {
        Boolean result = pipelineGroupService.deleteById(id);
        model.addAttribute("deleteGroupResult", result);
        if (result) {
            return "redirect:grouplist";
        } else {
            return "redirect:grouplist";
        }
    }

    @RequestMapping(value = "groupedit", method = RequestMethod.POST)
    public String editGroup(Integer id, String name, String description, String display, ModelMap model) {
        if (id != null && StringUtils.isNotBlank(name)) {
            PipelineGroupDO groupDO = new PipelineGroupDO();
            groupDO.setId(id);
            groupDO.setName(name);
            groupDO.setDescription(description);
            groupDO.setDisplay(Boolean.valueOf(display));
            Boolean result = pipelineGroupService.updateGroup(groupDO);
            model.addAttribute("editGroupResult", result);
        }
        return "redirect:grouplist";
    }


}
