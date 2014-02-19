package com.taobao.tae.ewall.dao;

import com.alibaba.pt.commons.persistence.page.Page;
import com.taobao.itest.ITestSpringContextBaseCase;
import com.taobao.itest.annotation.ITestSpringContext;
import com.taobao.tae.ewall.build.BuildProjectDO;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * User: xinyuan.ymm
 * Date: 14-2-15
 * Time: 下午5:16
 */
@ITestSpringContext(value = {"/ctx-test.xml"})
public class BuildProjectTest extends ITestSpringContextBaseCase {

    @Resource
    BuildProjectDao buildProjectDao;

    @Test
    public void testUpdate() {
        BuildProjectDO buildProjectDO = new BuildProjectDO();
        buildProjectDO.setProjectId(1);
        Page<BuildProjectDO> buildProjectsByPage = buildProjectDao.page("page",buildProjectDO, 1,10);
        System.out.println(buildProjectsByPage);
    }
}
