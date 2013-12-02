package com.taobao.tae.ewall.service.impl;

import com.taobao.tae.ewall.dao.JobDao;
import com.taobao.tae.ewall.job.JobDO;
import com.taobao.tae.ewall.service.JobService;
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
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
