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
     * 获取 所有的 Job
     *
     * @return
     */
    public List<JobDO> getAllJobs();

    /**
     * 通过 id 删除 Job
     *
     * @param id
     * @return
     */
    public Boolean deleteById(Integer id);

    /**
     * 添加Job
     *
     * @param jobDO
     * @return 返回创建后的Job id
     */
    public Integer addJob(JobDO jobDO);

    /**
     * 更新Job
     *
     * @param jobDO
     * @return
     */
    public Boolean updateJob(JobDO jobDO);
}
