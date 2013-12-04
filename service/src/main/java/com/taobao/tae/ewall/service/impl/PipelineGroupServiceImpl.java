package com.taobao.tae.ewall.service.impl;

import com.taobao.tae.ewall.dao.JobDao;
import com.taobao.tae.ewall.dao.PipelineGroupDao;
import com.taobao.tae.ewall.pipeline.PipelineGroupDO;
import com.taobao.tae.ewall.service.PipelineGroupService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * User: xinyuan.ymm
 * Date: 13-12-2
 * Time: 下午9:24
 */
@Component("pipelineGroupService")
public class PipelineGroupServiceImpl implements PipelineGroupService {

    @Autowired
    PipelineGroupDao pipelineGroupDao;

    @Override
    public List<PipelineGroupDO> getAllGroups() {
        PipelineGroupDO pipelineGroupDO = new PipelineGroupDO();
        List<PipelineGroupDO> pipelineGroupDOs = pipelineGroupDao.find(pipelineGroupDO);
        return pipelineGroupDOs;
    }

    @Override
    public Boolean deleteById(Integer id) {
        return pipelineGroupDao.delete(id);
    }

    @Override
    public Integer addGroup(PipelineGroupDO groupDO) {
        if (groupDO == null || StringUtils.isBlank(groupDO.getName()) || StringUtils.isBlank(groupDO.getName())) {
            return -1;
        }
        PipelineGroupDO pipelineGroupDO = findByName(groupDO.getName());
        if (pipelineGroupDO != null) {
            groupDO.setId(pipelineGroupDO.getId());
            pipelineGroupDao.update(groupDO);
            return pipelineGroupDO.getId();
        } else {
            return pipelineGroupDao.create(groupDO);
        }

    }

    @Override
    public Boolean updateGroup(PipelineGroupDO groupDO) {
        if (groupDO == null ||groupDO.getId()==null || StringUtils.isBlank(groupDO.getName())) {
            return false;
        }
        return pipelineGroupDao.update(groupDO);
    }

    @Override
    public PipelineGroupDO findByName(String groupName) {
        PipelineGroupDO pipelineGroupDO = new PipelineGroupDO();
        pipelineGroupDO.setName(groupName.trim());
        List<PipelineGroupDO> pipelineGroupDOs = pipelineGroupDao.find(pipelineGroupDO);
        return pipelineGroupDOs != null && pipelineGroupDOs.size() > 0 ? pipelineGroupDOs.get(0) : null;
    }
}
