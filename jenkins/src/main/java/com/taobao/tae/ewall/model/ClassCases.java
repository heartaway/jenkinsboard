package com.taobao.tae.ewall.model;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

/**
 * User: xinyuan.ymm
 * Date: 14-2-26
 * Time: 下午11:57
 */
public class ClassCases {


    public String duration;

    @JsonProperty("name")
    public String className;

    public Integer id;

    @JsonProperty("cases")
    public List<MethodCases> methodCaseses;

    public String timestamp;

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<MethodCases> getMethodCaseses() {
        return methodCaseses;
    }

    public void setMethodCaseses(List<MethodCases> methodCaseses) {
        this.methodCaseses = methodCaseses;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
