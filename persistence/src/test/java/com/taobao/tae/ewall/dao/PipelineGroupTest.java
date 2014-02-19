package com.taobao.tae.ewall.dao;

import com.taobao.itest.ITestSpringContextBaseCase;
import com.taobao.itest.annotation.ITestSpringContext;
import com.taobao.tae.ewall.pipeline.PipelineGroupDO;
import junit.framework.Assert;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * User: xinyuan.ymm
 * Date: 13-12-2
 * Time: 下午3:01
 */
@ITestSpringContext(value = {"/ctx-test.xml"})
public class PipelineGroupTest  extends ITestSpringContextBaseCase {

    @Resource
    PipelineGroupDao pipelineGroupDao;

    Integer gourpId;

    @Test
    public void testCreate() {
        gourpId = createPipelineGroup();
        PipelineGroupDO result = pipelineGroupDao.load(gourpId);
        pipelineGroupDao.delete(gourpId);
        PipelineGroupDO prototype = generatePipelineGroup();
        Assert.assertEquals(prototype.getName(), result.getName());
        Assert.assertEquals(prototype.getDescription(), result.getDescription());
    }


    private Integer createPipelineGroup() {
        PipelineGroupDO groupDO = generatePipelineGroup();
        return pipelineGroupDao.create(groupDO);
    }

    private PipelineGroupDO generatePipelineGroup() {
        PipelineGroupDO groupDO = new PipelineGroupDO();
        groupDO.setName("JAE");
        groupDO.setDescription("Jae pipeline 管道");
        return groupDO;
    }

}
