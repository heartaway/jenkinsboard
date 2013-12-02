package com.taobao.tae.ewall.dao;

import com.taobao.itest.ITestSpringContextBaseCase;
import com.taobao.itest.annotation.ITestSpringContext;
import com.taobao.tae.ewall.job.JobDO;
import junit.framework.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * User: xinyuan.ymm
 * Date: 13-12-2
 * Time: 下午3:01
 */
@ITestSpringContext(value = {"/ctx-test.xml"})
public class JobDaoTest extends ITestSpringContextBaseCase {

    @Resource
    JobDao jobDao;

    Integer jobId;

    @Test
    public void testCreate() {
        jobId = createJob();
        JobDO result = jobDao.load(jobId);
        jobDao.delete(jobId);
        JobDO prototype = generateJob();
        Assert.assertEquals(prototype.getName(), result.getName());
        Assert.assertEquals(prototype.getJenkinsURL(), result.getJenkinsURL());
        Assert.assertEquals(prototype.getJobType(), result.getJobType());
    }


    @Test
    public void testDelete() {
        jobId = createJob();
        JobDO jobDO = new JobDO();
        jobDO.setName("deploycenter");
        jobDO.setJenkinsURL("http://10.20.153.118:8080");
        List<JobDO> result = jobDao.find(jobDO);
        Assert.assertEquals(1, result.size());
        jobDao.delete(result.get(0).getId());
        Assert.assertEquals(0, jobDao.find(jobDO).size());
    }

    @Test
    public void testUpdate() {
        jobId = createJob();
        JobDO jobNewDO = jobDao.load(jobId);
        JobDO jobOrginDo = generateJob();
        jobNewDO.setName("jenkins");
        jobNewDO.setJenkinsURL("http://www.jenkins.com");
        jobDao.update(jobNewDO);
        List<JobDO> result = jobDao.find(jobNewDO);
        Assert.assertEquals(1, result.size());
        jobDao.delete(result.get(0).getId());
        Assert.assertEquals(0, jobDao.find(jobNewDO).size());
        List<JobDO> orginResult = jobDao.find(jobOrginDo);
        Assert.assertEquals(0, orginResult.size());
    }

    private Integer createJob() {
        JobDO jobDO = generateJob();
        return jobDao.create(jobDO);
    }

    private JobDO generateJob() {
        JobDO jobDO = new JobDO();
        jobDO.setName("deploycenter");
        jobDO.setJenkinsURL("http://10.20.153.118:8080");
        jobDO.setJobType(1);
        return jobDO;
    }
}
