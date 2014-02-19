package com.taobao.tae.ewall.service.impl;

import com.taobao.tae.ewall.dao.ResourcesLockDao;
import com.taobao.tae.ewall.lock.ResourcesLockDO;
import com.taobao.tae.ewall.lock.ResourcesPriority;
import com.taobao.tae.ewall.service.ResourcesLockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * User: xinyuan.ymm
 * Date: 14-2-14
 * Time: 下午4:09
 */
@Component("resourcesLockService")
public class ResourcesLockServiceImpl implements ResourcesLockService {

    @Autowired
    ResourcesLockDao resourcesLockDao;

    @Override
    public Boolean lock(String resourceName) {
        ResourcesLockDO resourcesLockDO = new ResourcesLockDO();
        resourcesLockDO.setName(resourceName);
        List<ResourcesLockDO> resourcesLockDOs = resourcesLockDao.find(resourcesLockDO);
        if (resourcesLockDOs == null || resourcesLockDOs.size() == 0) {
            resourcesLockDO.setLocked(Boolean.TRUE);
            resourcesLockDO.setLastLock(new Date());
            resourcesLockDO.setPriority(ResourcesPriority.DEFAULT.getPriority());
            resourcesLockDao.create(resourcesLockDO);
        } else if (resourcesLockDOs.get(0).getLocked()) {
            return true;
        } else if (!resourcesLockDOs.get(0).getLocked()) {
            resourcesLockDO.setLocked(Boolean.TRUE);
            resourcesLockDO.setLastLock(new Date());
            resourcesLockDao.update(resourcesLockDO);
        } else {
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean unlock(String resourceName) {
        ResourcesLockDO resourcesLockDO = new ResourcesLockDO();
        resourcesLockDO.setName(resourceName);
        List<ResourcesLockDO> resourcesLockDOs = resourcesLockDao.find(resourcesLockDO);
        if (resourcesLockDOs == null || resourcesLockDOs.size() == 0) {
            return false;
        } else if (!resourcesLockDOs.get(0).getLocked()) {
            return true;
        } else if (resourcesLockDOs.get(0).getLocked()) {
            resourcesLockDOs.get(0).setLocked(Boolean.FALSE);
            resourcesLockDOs.get(0).setLastUnLock(new Date());
            resourcesLockDao.update(resourcesLockDOs.get(0));
        } else {
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean isLock(String resourceName) {
        ResourcesLockDO resourcesLockDO = new ResourcesLockDO();
        resourcesLockDO.setName(resourceName);
        List<ResourcesLockDO> resourcesLockDOs = resourcesLockDao.find(resourcesLockDO);
        if (resourcesLockDOs == null || resourcesLockDOs.size() == 0) {
            return Boolean.FALSE;
        } else if (resourcesLockDOs.get(0).getLocked()) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }
}
