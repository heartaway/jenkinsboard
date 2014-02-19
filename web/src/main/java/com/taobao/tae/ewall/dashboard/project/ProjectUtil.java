package com.taobao.tae.ewall.dashboard.project;

import com.taobao.tae.ewall.project.ProjectDO;
import com.taobao.tae.ewall.service.ProjectService;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * User: xinyuan.ymm
 * Date: 13-12-29
 * Time: 下午1:38
 */
public class ProjectUtil {

    /**
     * 将 依赖关系id 字符串 转换为 工程名称字符串
     * @param projectDownStreamIdString
     * @param projectService
     * @return
     */
    public String covertProjectDownStreamIdString2NameString(String projectDownStreamIdString, ProjectService projectService) {
        StringBuilder projectDownStreamNameString = new StringBuilder();
         if(StringUtils.isBlank(projectDownStreamIdString)){
             return "";
         }
        if (projectDownStreamIdString.endsWith(","))
            projectDownStreamIdString = projectDownStreamIdString.substring(0, projectDownStreamIdString.length() - 1);
        if (projectDownStreamIdString.startsWith(","))
            projectDownStreamIdString = projectDownStreamIdString.substring(1, projectDownStreamIdString.length());
        String[] projectIds = projectDownStreamIdString.split(",");
        for (String projectId : projectIds) {
            ProjectDO projectDO = projectService.getProjectById(Integer.valueOf(projectId));
            if (projectDO != null )
                projectDownStreamNameString.append(projectDO.getName());
            projectDownStreamNameString.append(",");
        }
        String downStreamNameString = projectDownStreamNameString.toString();
        return downStreamNameString.substring(0, downStreamNameString.length() - 1);
    }

    /**
     * 将 工程名称 列表转换为 工程id列表
     *
     * @param projectNameString
     * @return
     */
    public String projectNameString2ProjectsIdString(String projectNameString, ProjectService projectService) {
        StringBuilder projectsIdString = new StringBuilder();
        if(StringUtils.isBlank(projectNameString)){
            return "";
        }
        if (projectNameString.endsWith(","))
            projectNameString = projectNameString.substring(0, projectNameString.length() - 1);
        if (projectNameString.startsWith(","))
            projectNameString = projectNameString.substring(1, projectNameString.length());
        String[] projectNames = projectNameString.split(",");
        for (String projectName : projectNames) {
            List<ProjectDO> projectDOs = projectService.getProjectByName(projectName);
            if (projectDOs != null && !projectDOs.isEmpty())
                projectsIdString.append(projectDOs.get(0).getId());
            projectsIdString.append(",");
        }
        String downStreamIdString = projectsIdString.toString();
        return downStreamIdString.substring(0, downStreamIdString.length() - 1);
    }
}
