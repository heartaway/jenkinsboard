package com.taobao.tae.ewall.project;

import com.taobao.tae.ewall.BaseDO;

/**
 * User: xinyuan.ymm
 * Date: 13-11-30
 * Time: 上午11:59
 */
public class ProjectDO extends BaseDO {

    private Integer id;

    private String name;

    /**
     * 此project 所在的 Jenkins 的 Home Url
     */
    private String jenkinsURL;

    /**
     * project 类型
     */
    private String projectType;

    /**
     * 当前状态
     */
    private String status;


    /**
     * 特征字段
     */
    private String features;

    /**
     *  某个Pipeline中的某个project的上游project的列表，逗号分割
     */
    private String upStreamProjects;

    /**
     *  某个Pipeline中的某个project的下游project的列表，逗号分割
     */
    private String downStreamProjects;


    /**
     * 当前project描述
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

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public String getUpStreamProjects() {
        return upStreamProjects;
    }

    public void setUpStreamProjects(String upStreamProjects) {
        this.upStreamProjects = upStreamProjects;
    }

    public String getDownStreamProjects() {
        return downStreamProjects;
    }

    public void setDownStreamProjects(String downStreamProjects) {
        this.downStreamProjects = downStreamProjects;
    }
}
