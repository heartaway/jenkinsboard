package com.taobao.tae.ewall.service.impl;

import com.taobao.tae.ewall.JenkinsServer;
import com.taobao.tae.ewall.client.JenkinsHttpClient;
import com.taobao.tae.ewall.dao.PipelineDao;
import com.taobao.tae.ewall.dao.ProjectDao;
import com.taobao.tae.ewall.model.Job;
import com.taobao.tae.ewall.pipeline.PipelineDO;
import com.taobao.tae.ewall.project.ProjectDO;
import com.taobao.tae.ewall.service.PipelineService;
import com.taobao.tae.ewall.util.DomainUtil;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * User: xinyuan.ymm
 * Date: 13-12-2
 * Time: 下午9:19
 */
@Component("pipelineService")
public class PipelineServiceImpl implements PipelineService {

    @Autowired
    private PipelineDao pipelineDao;
    @Autowired
    private ProjectDao projectDao;

    @Override
    public List<PipelineDO> findPipelinesByGroupId(Integer groupId) {
        PipelineDO pipelineDO = new PipelineDO();
        pipelineDO.setGroupId(groupId);
        return pipelineDao.find(pipelineDO);
    }

    @Override
    public Integer addPipeline(PipelineDO pipelineDO) {
        if (StringUtils.isBlank(pipelineDO.getName())) {
            return -1;
        }
        return pipelineDao.create(pipelineDO);
    }

    @Override
    public Boolean updatePipeline(PipelineDO pipelineDO) {
        if (pipelineDO == null || pipelineDO.getId() == null || StringUtils.isBlank(pipelineDO.getName())) {
            return false;
        }
        return pipelineDao.update(pipelineDO);
    }

    @Override
    public Boolean deleteById(Integer pipelineId) {
        return pipelineDao.delete(pipelineId);
    }

    @Override
    public PipelineDO findPipelineById(Integer pipelineId) {
        return pipelineDao.load(pipelineId);
    }

    /**
     * 初始化 pipeline
     *
     * @param pipelineId
     */
    public void initPipeline(Integer pipelineId) {
        if (pipelineId == null) {
            return;
        }
        try {
            PipelineDO pipelineDO = pipelineDao.load(pipelineId);
            ProjectDO startProject = projectDao.load(pipelineDO.getHeadProjectId());
            if (StringUtils.isBlank(startProject.getJenkinsURL())) {
                return;
            }
            JenkinsServer jenkinsServer = new JenkinsServer(new JenkinsHttpClient(new URI(startProject.getJenkinsURL())));
            Job job = jenkinsServer.getJob(startProject.getName());
            initDownStreamProjects(job, jenkinsServer);
        } catch (URISyntaxException uriException) {

        } catch (IOException ioException) {

        }

    }

    /**
     * '
     * 初始化 pipeline 的 下游工程。
     *
     * @param job
     * @param jenkinsServer
     */
    public void initDownStreamProjects(Job job, JenkinsServer jenkinsServer) {
        try {
            ProjectDO projectDO = new ProjectDO();
            projectDO.setName(job.getName());
            List<ProjectDO> projectsFromDB = projectDao.find(projectDO);
            List<Job> downJobs = jenkinsServer.getJob(job.getName()).getDownstreamProjects();
            StringBuilder downJobsString = new StringBuilder();
            for (Job downJob : downJobs) {
                downJobsString.append(downJob.getName());
                downJobsString.append(",");
            }
            // if this job is not type-in system, here will be auto added;else update downstream jobs.
            if (projectsFromDB == null || projectsFromDB.size() == 0) {
                projectDO.setJenkinsURL(DomainUtil.getDomainForUrl(job.getUrl()));
                projectDO.setDownStreamProjects(downJobsString.toString());
                projectDao.create(projectDO);
            } else {
                projectsFromDB.get(0).setDownStreamProjects(downJobsString.toString());
                projectDao.update(projectsFromDB.get(0));
            }
            List<Job> jobs = jenkinsServer.getJob(job.getName()).getDownstreamProjects();
            if (jobs == null || jobs.isEmpty()) {
                return;
            }
            for (Job downStreamJob : jobs) {
                initDownStreamProjects(downStreamJob, jenkinsServer);
            }
        } catch (IOException ioException) {

        }
    }


}
