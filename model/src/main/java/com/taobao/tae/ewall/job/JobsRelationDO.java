package com.taobao.tae.ewall.job;

import com.taobao.tae.ewall.BaseDO;

/**
 * User: xinyuan.ymm
 * Date: 13-11-30
 * Time: 下午12:12
 */
public class JobsRelationDO extends BaseDO {

    private Integer id;

    /**
     * 当前Job 所属于的pipeline Id
     */
    private Integer pipelineId;

    /**
     * 某个Pipeline中的某个Job的 Id
     */
    private Integer jobId;

    /**
     * 当前Job描述
     */
    private String description;

    /**
     * 某个Pipeline中的某个Job的上游Jobs的列表，逗号分割
     */
    private String upStreamJobs;

    /**
     *  某个Pipeline中的某个Job的下游Jobs的列表，逗号分割
     */
    private String downStreamJobs;

    /**
     * 特征字段
     */
   private String features;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPipelineId() {
        return pipelineId;
    }

    public void setPipelineId(Integer pipelineId) {
        this.pipelineId = pipelineId;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public String getUpStreamJobs() {
        return upStreamJobs;
    }

    public void setUpStreamJobs(String upStreamJobs) {
        this.upStreamJobs = upStreamJobs;
    }

    public String getDownStreamJobs() {
        return downStreamJobs;
    }

    public void setDownStreamJobs(String downStreamJobs) {
        this.downStreamJobs = downStreamJobs;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
