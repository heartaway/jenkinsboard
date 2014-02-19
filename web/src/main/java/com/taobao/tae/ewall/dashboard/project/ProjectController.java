package com.taobao.tae.ewall.dashboard.project;


import com.taobao.tae.ewall.pipeline.PipelineDO;
import com.taobao.tae.ewall.project.ProjectGridImpl;
import com.taobao.tae.ewall.project.ProjectDO;
import com.taobao.tae.ewall.project.ProjectType;
import com.taobao.tae.ewall.service.PipelineService;
import com.taobao.tae.ewall.service.ProjectService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: xinyuan.ymm
 * Date: 13-12-2
 * Time: 下午7:57
 */
@Controller
@RequestMapping("/dashboard/project/")
public class ProjectController {

    @Resource
    PipelineService pipelineService;
    @Resource
    ProjectService projectService;

    @RequestMapping("projectlist")
    public String getAllProjects(ModelMap model) {
        Map<String, String> projectType = new HashMap<String, String>();
        for (ProjectType e : ProjectType.values())
            projectType.put(e.value(), e.description());
        List<ProjectDO> projectList = projectService.getAllProjects();
        model.addAttribute("projectlist", projectList);
        model.addAttribute("projectType", projectType);
        return "dashboard/project/projectlist";
    }

    /**
     * Project 的Name 最好唯一
     *
     * @param name
     * @param jenkinsURL
     * @param projectType
     * @param status
     * @param downStreamProjects
     * @param description
     * @param model
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String addProject(String name, String jenkinsURL, String projectType, String status, String downStreamProjects, String description, ModelMap model) {
        if (StringUtils.isNotBlank(name) && StringUtils.isNotBlank(jenkinsURL)) {
            List<ProjectDO> existSameNameProjects = projectService.getProjectByName(name);
            if (existSameNameProjects == null || existSameNameProjects.isEmpty()) {
                ProjectDO projectDO = new ProjectDO();
                projectDO.setName(name);
                projectDO.setJenkinsURL(jenkinsURL);
                projectDO.setProjectType(projectType);
                projectDO.setStatus(status);
                projectDO.setDescription(description);
                Integer result = projectService.addProject(projectDO);
                model.addAttribute("addProjectResult", result);
            } else {
                //TODO 提示Name已存在
            }
        }
        return "redirect:projectlist";
    }

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public String deleteProject(Integer id, ModelMap model) {
        Boolean result = projectService.deleteById(id);
        model.addAttribute("deleteProjectResult", result);
        if (result) {
            return "redirect:projectlist";
        } else {
            return "redirect:projectlist";
        }
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String editProject(Integer id, String name, String jenkinsURL, String projectType, String status, String description, ModelMap model) {
        if (id != null && StringUtils.isNotBlank(name) && StringUtils.isNotBlank(jenkinsURL)) {
            ProjectDO projectDO = new ProjectDO();
            projectDO.setId(id);
            projectDO.setName(name);
            projectDO.setJenkinsURL(jenkinsURL);
            projectDO.setProjectType(projectType);
            projectDO.setStatus(status);
            projectDO.setDescription(description);
            Boolean result = projectService.updateProject(projectDO);
            model.addAttribute("editProjectResult", result);
        }
        return "redirect:projectlist";
    }


    @RequestMapping(value = "projectDependencyAdd", method = RequestMethod.POST)
    public String dependencyAdd(HttpServletRequest request) {
        Integer pipelineId = Integer.valueOf(request.getParameter("pipelineId"));
        Integer projectId = Integer.valueOf(request.getParameter("projectId"));
        StringBuilder dependency = new StringBuilder();
        dependency.append("dependencyGraph");
        dependency.append("_");
        dependency.append(projectId);
        String dependencyGraph = request.getParameter(dependency.toString());
        if (pipelineId == null) {
            return "redirect:/dashboard/pipeline/pipelinelist";
        }
        ProjectUtil projectUtil = new ProjectUtil();
        String downStream = projectUtil.projectNameString2ProjectsIdString(dependencyGraph, projectService);
        PipelineDO pipelineDO = pipelineService.findPipelineById(pipelineId);
        ProjectDO startProject = projectService.getProjectById(pipelineDO.getHeadProjectId());
        List<ProjectDO> allProject = projectService.getAllProjects();
        ProjectGridImpl projectGrid = new ProjectGridImpl(startProject, allProject);
        //判断是否循环依赖
        Boolean isCycleDependency=false;
        for(int i =0;i<projectGrid.getRows();i++){
            for(int j=0;j<projectGrid.getColumns();j++){
                if(dependencyGraph.contains(String.valueOf(projectGrid.get(i, j).getId()))){
                    isCycleDependency=true;
                }
            }
        }
        if(isCycleDependency){
            return "redirect:/dashboard/pipeline/pipelinesetting?pipelineId=".concat(pipelineId.toString());
        } 
        ProjectDO projectDO = projectService.getProjectById(projectId);
        projectDO.setDownStreamProjects(downStream);
        projectService.updateProject(projectDO);
        return "redirect:/dashboard/pipeline/pipelinesetting?pipelineId=".concat(pipelineId.toString());
    }


}
