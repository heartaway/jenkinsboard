package com.taobao.tae.ewall.service.impl;

import com.taobao.tae.ewall.build.BuildPipelineDO;
import com.taobao.tae.ewall.dao.BuildPipelineDao;
import com.taobao.tae.ewall.dao.ResourcesLockDao;
import com.taobao.tae.ewall.lock.Resources;
import com.taobao.tae.ewall.model.BuildResult;
import com.taobao.tae.ewall.model.BuildStatus;
import com.taobao.tae.ewall.service.BuildPipelineService;
import com.taobao.tae.ewall.service.ResourcesLockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * User: xinyuan.ymm
 * Date: 14-2-14
 * Time: 下午2:17
 */
@Component("buildPipelineService")
public class BuildPipelineServiceImpl implements BuildPipelineService {

    @Autowired
    BuildPipelineDao buildPipelineDao;

    @Autowired
    ResourcesLockService resourcesLockService;

    @Override
    public Long triggerPipelineBuild(Integer pipelineId) {
        //pipeline lock
        resourcesLockService.lock(Resources.PIPELINE.concat("_").concat(pipelineId.toString()));
        BuildPipelineDO buildPipelineDO = new BuildPipelineDO();
        buildPipelineDO.setPipelineId(pipelineId);
        buildPipelineDO.setGmtStart(new Date());
        buildPipelineDO.setResult(BuildResult.BUILDING.getCode().toString());
        buildPipelineDO.setStatus(BuildStatus.BUILDING.getCode().toString());
        Long id = buildPipelineDao.create(buildPipelineDO);
        return id;
    }

    @Override
    public Boolean stopPipelineBuild(Long buildpipelineId) {
        //pipeline unlock
        BuildPipelineDO buildPipelineDO = buildPipelineDao.load(buildpipelineId);
        resourcesLockService.unlock(Resources.PIPELINE.concat("_").concat(buildPipelineDO.getPipelineId().toString()));
        buildPipelineDO.setGmtEnd(new Date());
        buildPipelineDO.setResult(BuildResult.ABORTED.getCode().toString());
        buildPipelineDO.setStatus(BuildStatus.STOP.getCode().toString());
        return buildPipelineDao.update(buildPipelineDO);
    }

    @Override
    public BuildPipelineDO load(Long id) {
        return buildPipelineDao.load(id);
    }

    @Override
    public Boolean update(BuildPipelineDO buildPipelineDO) {
        return buildPipelineDao.update(buildPipelineDO);
    }

    @Override
    public BuildPipelineDO loadLastBuildPipelineByPipelineId(Integer pipelineId) {
        BuildPipelineDO buildPipelineDO = new BuildPipelineDO();
        buildPipelineDO.setPipelineId(pipelineId);
        return buildPipelineDao.load("loadLast", buildPipelineDO);
    }
}
