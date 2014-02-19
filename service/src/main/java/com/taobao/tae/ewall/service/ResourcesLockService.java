package com.taobao.tae.ewall.service;

/**
 * User: xinyuan.ymm
 * Date: 14-2-14
 * Time: 下午4:09
 */
public interface ResourcesLockService {

    /**
     * 对  resourceName 进行加锁
     *
     * @param resourceName
     */
    public Boolean lock(String resourceName);

    /**
     * 对  resourceName 进行解锁
     *
     * @param resourceName
     */
    public Boolean unlock(String resourceName);

    /**
     * 对  resourceName 判断当前状态是否处于所状态，如果是返回 True，否则，返回False
     *
     * @param resourceName
     * @return
     */
    public Boolean isLock(String resourceName);
}
