package com.taobao.tae.ewall.service.impl;

import com.taobao.tae.ewall.dao.JobDao;
import com.taobao.tae.ewall.job.JobDO;
import com.taobao.tae.ewall.service.JobService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * User: xinyuan.ymm
 * Date: 13-12-2
 * Time: 下午9:21
 */
@Component("jobService")
public class JobServiceImpl implements JobService {

    @Autowired
    JobDao jobDao;

    @Override
    public List<JobDO> getAllJobs() {
        JobDO jobDO = new JobDO();
        List<JobDO> result = jobDao.find(jobDO);
        return result;
    }

    @Override
    public Boolean deleteById(Integer id) {
        return jobDao.delete(id);
    }

    @Override
    public Integer addJob(JobDO jobDO) {
        if (jobDO == null || StringUtils.isBlank(jobDO.getName()) || StringUtils.isBlank(jobDO.getName())) {
            return -1;
        }
        return jobDao.create(jobDO);
    }

    @Override
    public Boolean updateJob(JobDO jobDO) {
        if (jobDO == null || StringUtils.isBlank(jobDO.getName()) || StringUtils.isBlank(jobDO.getName())) {
            return false;
        }
        return jobDao.update(jobDO);
    }
}
