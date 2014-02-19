package com.taobao.tae.project;

import com.taobao.tae.ewall.project.ProjectGridImpl;
import com.taobao.tae.ewall.project.ProjectDO;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * User: xinyuan.ymm
 * Date: 13-12-27
 * Time: 下午3:51
 */
public class ProjectGridImplTest {


    @Test
    public void test() {
        List<ProjectDO> ProjectDOs = new ArrayList<ProjectDO>();
        ProjectDO ProjectDO1 = new ProjectDO();
        ProjectDO1.setId(0);
        ProjectDO1.setDownStreamProjects("2,3");
        ProjectDOs.add(ProjectDO1);

        ProjectDO ProjectDO2 = new ProjectDO();
        ProjectDO2.setId(2);
        ProjectDO2.setDownStreamProjects("4,5");
        ProjectDOs.add(ProjectDO2);

        ProjectDO ProjectDO3 = new ProjectDO();
        ProjectDO3.setId(3);
        ProjectDOs.add(ProjectDO3);

        ProjectDO ProjectDO4 = new ProjectDO();
        ProjectDO4.setId(4);
        ProjectDO4.setDownStreamProjects("6");
        ProjectDOs.add(ProjectDO4);

        ProjectDO ProjectDO5 = new ProjectDO();
        ProjectDO5.setId(5);
        ProjectDO5.setDownStreamProjects("6");
        ProjectDOs.add(ProjectDO5);

        ProjectDO ProjectDO6 = new ProjectDO();
        ProjectDO6.setId(6);
        ProjectDOs.add(ProjectDO6);

        ProjectGridImpl projectGrid = new ProjectGridImpl(ProjectDO1, ProjectDOs);
        System.out.println(projectGrid);
    }
}
