package com.taobao.tae.ewall.dao;

import com.taobao.itest.ITestSpringContextBaseCase;
import com.taobao.itest.annotation.ITestSpringContext;
import com.taobao.tae.ewall.project.ProjectDO;
import junit.framework.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * User: xinyuan.ymm
 * Date: 13-12-2
 * Time: 下午3:01
 */
@ITestSpringContext(value = {"/ctx-test.xml"})
public class ProjectDaoTest extends ITestSpringContextBaseCase {

    @Resource
    ProjectDao projectDao;

    Integer projectId;

    @Test
    public void testCreate() {
        projectId = createProject();
        ProjectDO result = projectDao.load(projectId);
        projectDao.delete(projectId);
        ProjectDO prototype = generateProject();
        Assert.assertEquals(prototype.getName(), result.getName());
        Assert.assertEquals(prototype.getJenkinsURL(), result.getJenkinsURL());
        Assert.assertEquals(prototype.getProjectType(), result.getProjectType());
    }


    @Test
    public void testDelete() {
        projectId = createProject();
        ProjectDO projectDO = new ProjectDO();
        projectDO.setName("deploycenter");
        projectDO.setJenkinsURL("http://10.20.153.118:8080");
        List<ProjectDO> result = projectDao.find(projectDO);
        Assert.assertEquals(1, result.size());
        projectDao.delete(result.get(0).getId());
        Assert.assertEquals(0, projectDao.find(projectDO).size());
    }

    @Test
    public void testUpdate() {
        projectId = createProject();
        ProjectDO projectNewDO = projectDao.load(projectId);
        ProjectDO projectOrginDo = generateProject();
        projectNewDO.setName("jenkins");
        projectNewDO.setJenkinsURL("http://www.jenkins.com");
        projectDao.update(projectNewDO);
        List<ProjectDO> result = projectDao.find(projectNewDO);
        Assert.assertEquals(1, result.size());
        projectDao.delete(result.get(0).getId());
        Assert.assertEquals(0, projectDao.find(projectNewDO).size());
        List<ProjectDO> orginResult = projectDao.find(projectOrginDo);
        Assert.assertEquals(0, orginResult.size());
    }

    private Integer createProject() {
        ProjectDO projectDO = generateProject();
        return projectDao.create(projectDO);
    }

    private ProjectDO generateProject() {
        ProjectDO projectDO = new ProjectDO();
        projectDO.setName("deploycenter");
        projectDO.setJenkinsURL("http://10.20.153.118:8080");
        projectDO.setProjectType("1");
        return projectDO;
    }
}
