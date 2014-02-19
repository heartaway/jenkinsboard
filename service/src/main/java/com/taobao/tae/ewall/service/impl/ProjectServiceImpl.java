package com.taobao.tae.ewall.service.impl;

import com.taobao.tae.ewall.dao.ProjectDao;
import com.taobao.tae.ewall.project.ProjectDO;
import com.taobao.tae.ewall.service.ProjectService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * User: xinyuan.ymm
 * Date: 13-12-2
 * Time: 下午9:21
 */
@Component("projectService")
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    ProjectDao projectDao;

    @Override
    public List<ProjectDO> getAllProjects() {
        ProjectDO projectDO = new ProjectDO();
        List<ProjectDO> result = projectDao.find(projectDO);
        return result;
    }

    @Override
    public Boolean deleteById(Integer id) {
        return projectDao.delete(id);
    }

    @Override
    public Integer addProject(ProjectDO projectDO) {
        if (projectDO == null || StringUtils.isBlank(projectDO.getName()) || StringUtils.isBlank(projectDO.getName())) {
            return -1;
        }
        return projectDao.create(projectDO);
    }

    @Override
    public Boolean updateProject(ProjectDO projectDO) {
        if (projectDO == null || StringUtils.isBlank(projectDO.getName()) || StringUtils.isBlank(projectDO.getName())) {
            return false;
        }
        return projectDao.update(projectDO);
    }

    @Override
    public ProjectDO getProjectById(Integer projectId) {
        if (projectId == null) {
            return null;
        }
        return projectDao.load(projectId);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<ProjectDO> getProjectByName(String name) {
        if (name == null) {
            return null;
        }
        ProjectDO projectDO = new ProjectDO();
        projectDO.setName(name);
        return projectDao.find(projectDO);  //To change body of implemented methods use File | Settings | File Templates.
    }
}
