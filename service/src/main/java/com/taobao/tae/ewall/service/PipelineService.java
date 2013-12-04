package com.taobao.tae.ewall.service;

import com.taobao.tae.ewall.pipeline.PipelineDO;
import com.taobao.tae.ewall.pipeline.PipelineGroupDO;

import java.util.List;

/**
 * User: xinyuan.ymm
 * Date: 13-12-2
 * Time: 下午9:19
 */
public interface PipelineService {

    /**
     * 通过 PipelineGroup id 查找这个分组下的所有 Pipeline
     *
     * @return
     */
    public List<PipelineDO> findPipelinesByGroupId(Integer groupId);

    /**
     * 添加 Pipeline
     *
     * @param pipelineDO
     * @return 返回创建后的Pipeline id
     */
    public Integer addPipeline(PipelineDO pipelineDO);

    /**
     * 更新 Pipeline
     * @param pipelineDO
     * @return
     */
    public Boolean updatePipeline(PipelineDO pipelineDO);

    /**
     * 通过 Pipeline id 删除某个Pipeline
     * @param pipelineId
     * @return
     */
    public Boolean deleteById(Integer pipelineId);

}
