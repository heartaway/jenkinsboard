package com.taobao.tae.ewall.pipeline;

import com.taobao.tae.ewall.BaseDO;

/**
 * User: xinyuan.ymm
 * Date: 13-11-29
 * Time: 下午5:40
 */
public class PipelineGroupDO extends BaseDO {

    private Integer id;

    /**
     * Pipeline Group Name
     */
    private String name;

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
