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
     * pipeline 头 Job Id
     */
    private Integer headJobId;

    /**
     * 当前 pipeline 描述
     */
    private String description;

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

    public Integer getHeadJobId() {
        return headJobId;
    }

    public void setHeadJobId(Integer headJobId) {
        this.headJobId = headJobId;
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
}
