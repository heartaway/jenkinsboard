package com.taobao.tae.ewall.dao;

import com.taobao.itest.ITestSpringContextBaseCase;
import com.taobao.itest.annotation.ITestSpringContext;
import com.taobao.tae.ewall.pipeline.PipelineDO;
import junit.framework.Assert;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * User: xinyuan.ymm
 * Date: 13-12-2
 * Time: 下午3:01
 */
@ITestSpringContext(value = {"/ctx-test.xml"})
public class PipelineTest  extends ITestSpringContextBaseCase {

    @Resource
    PipelineDao pipelineDao;

    Integer pipelineId;

    @Test
    public void testCreate() {
        pipelineId = createPipeline();
        PipelineDO result = pipelineDao.load(pipelineId);
        pipelineDao.delete(pipelineId);
        PipelineDO prototype = generatePipeline();
        Assert.assertEquals(prototype.getName(), result.getName());
        Assert.assertEquals(prototype.getName(), result.getName());
        Assert.assertEquals(prototype.getDescription(), result.getDescription());
        Assert.assertEquals(prototype.getGroupId(), result.getGroupId());
    }

    private int createPipeline() {
        PipelineDO pipelineDO = generatePipeline();
        return pipelineDao.create(pipelineDO);
    }

    private PipelineDO generatePipeline() {
        PipelineDO pipelineDO = new PipelineDO();
        pipelineDO.setName("JAE");
        pipelineDO.setDescription("测试");
        pipelineDO.setGroupId(1);
        return pipelineDO;
    }
}
