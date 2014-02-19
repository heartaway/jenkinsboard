package com.taobao.tae.ewall.pipeline;

import com.taobao.tae.ewall.BaseDO;

import java.util.LinkedList;

/**
 * User: xinyuan.ymm
 * Date: 13-11-29
 * Time: 下午5:22
 */
public class PipelineDO extends BaseDO {

    private Integer id;

    private String name;

    private Integer groupId;

    /**
     * pipeline 头 Project Id
     */
    private Integer headProjectId;

    /**
     * 当前 pipeline 描述
     */
    private String description;

    /**
     * 特征字段
     */
    private String features;

    /**
     * 是否展示在Pipelines中
     */
    private Boolean display;


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

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getHeadProjectId() {
        return headProjectId;
    }

    public void setHeadProjectId(Integer headProjectId) {
        this.headProjectId = headProjectId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public Boolean getDisplay() {
        return display;
    }

    public void setDisplay(Boolean display) {
        this.display = display;
    }
}
