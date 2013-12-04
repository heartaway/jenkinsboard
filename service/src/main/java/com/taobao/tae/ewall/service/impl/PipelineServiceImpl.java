package com.taobao.tae.ewall.service.impl;

import com.taobao.tae.ewall.dao.PipelineDao;
import com.taobao.tae.ewall.pipeline.PipelineDO;
import com.taobao.tae.ewall.service.PipelineService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * User: xinyuan.ymm
 * Date: 13-12-2
 * Time: 下午9:19
 */
@Component("pipelineService")
public class PipelineServiceImpl implements PipelineService {

    @Autowired
    PipelineDao pipelineDao;

    @Override
    public List<PipelineDO> findPipelinesByGroupId(Integer groupId) {
        PipelineDO pipelineDO = new PipelineDO();
        pipelineDO.setGroupId(groupId);
        return pipelineDao.find(pipelineDO);
    }

    @Override
    public Integer addPipeline(PipelineDO pipelineDO) {
        if (StringUtils.isBlank(pipelineDO.getName())) {
            return -1;
        }
        return pipelineDao.create(pipelineDO);
    }

    @Override
    public Boolean updatePipeline(PipelineDO pipelineDO) {
        if (pipelineDO == null || pipelineDO.getId() == null || StringUtils.isBlank(pipelineDO.getName())) {
            return false;
        }
        return pipelineDao.update(pipelineDO);
    }

    @Override
    public Boolean deleteById(Integer pipelineId) {
        return pipelineDao.delete(pipelineId);
    }

}
