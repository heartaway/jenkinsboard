package com.taobao.tae.ewall.service;

import com.taobao.tae.ewall.job.JobDO;

import java.util.List;

/**
 * User: xinyuan.ymm
 * Date: 13-12-2
 * Time: 下午9:20
 */
public interface JobService {

    /**
     *  获取 所有的 Job
     * @return
     */
    public List<JobDO> getAllJobs();
}
