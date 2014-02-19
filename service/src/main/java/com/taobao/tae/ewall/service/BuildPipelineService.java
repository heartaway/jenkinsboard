package com.taobao.tae.ewall.service;

import com.taobao.tae.ewall.build.BuildPipelineDO;

/**
 * User: xinyuan.ymm
 * Date: 14-2-14
 * Time: 下午2:16
 */
public interface BuildPipelineService {

    /**
     * 构建 pipeline
     *
     * @param pipelineId
     * @return
     */
    public Long triggerPipelineBuild(Integer pipelineId);


    /**
     * 强制停止 构建pipeline
     *
     * @param buildpipelineId
     * @return
     */
    public Boolean stopPipelineBuild(Long buildpipelineId);

    /**
     * 通过Id超找 Build pipeline
     *
     * @param id
     * @return
     */
    public BuildPipelineDO load(Long id);

    /**
     * 跟新 buildPipelineDO
     * @param buildPipelineDO
     * @return
     */
    public Boolean update(BuildPipelineDO buildPipelineDO);

    /**
     * 通过 pipelineId 查找 pipilineid下最后一次构建信息
     *
     * @param pipelineId
     * @return
     */
    public BuildPipelineDO loadLastBuildPipelineByPipelineId(Integer pipelineId);
}
