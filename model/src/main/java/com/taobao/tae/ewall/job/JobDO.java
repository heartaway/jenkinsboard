package com.taobao.tae.ewall.job;

import com.taobao.tae.ewall.BaseDO;

/**
 * User: xinyuan.ymm
 * Date: 13-11-30
 * Time: 上午11:59
 */
public class JobDO extends BaseDO {

    private Integer id;

    private String name;

    /**
     * 此Job 所在的 Jenkins 的 Home Url
     */
    private String jenkinsURL;

    /**
     * Job 类型
     */
    private Integer jobType;

    /**
     * 当前状态
     */
    private Integer status;


    /**
     * 特征字段
     */
    private String features;

    /**
     * 当前Job描述
     */
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJenkinsURL() {
        return jenkinsURL;
    }

    public void setJenkinsURL(String jenkinsURL) {
        this.jenkinsURL = jenkinsURL;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getJobType() {
        return jobType;
    }

    public void setJobType(Integer jobType) {
        this.jobType = jobType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }
}
