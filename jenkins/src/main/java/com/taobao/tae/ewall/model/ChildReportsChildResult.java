package com.taobao.tae.ewall.model;

import java.util.List;

/**
 * User: xinyuan.ymm
 * Date: 14-2-26
 * Time: 下午11:55
 */
public class ChildReportsChildResult{

    public String duration;

    public Integer failCount;

    public Integer passCount;

    public Integer skipCount;

    public List<ClassCases> suites;

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Integer getFailCount() {
        return failCount;
    }

    public void setFailCount(Integer failCount) {
        this.failCount = failCount;
    }

    public Integer getPassCount() {
        return passCount;
    }

    public void setPassCount(Integer passCount) {
        this.passCount = passCount;
    }

    public Integer getSkipCount() {
        return skipCount;
    }

    public void setSkipCount(Integer skipCount) {
        this.skipCount = skipCount;
    }

    public List<ClassCases> getSuites() {
        return suites;
    }

    public void setSuites(List<ClassCases> suites) {
        this.suites = suites;
    }
}
