package com.taobao.tae.ewall.model;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

/**
 * User: xinyuan.ymm
 * Date: 14-2-26
 * Time: 下午1r1:03
 */
public class TestReport extends BaseModel{

    public Integer failCount;

    public Integer skipCount;

    public Integer totalCount;

    public String urlName;

    public List<ChildReports> childReports;

    public Integer getFailCount() {
        return failCount;
    }

    public void setFailCount(Integer failCount) {
        this.failCount = failCount;
    }

    public Integer getSkipCount() {
        return skipCount;
    }

    public void setSkipCount(Integer skipCount) {
        this.skipCount = skipCount;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public String getUrlName() {
        return urlName;
    }

    public void setUrlName(String urlName) {
        this.urlName = urlName;
    }

    public List<ChildReports> getChildReports() {
        return childReports;
    }

    public void setChildReports(List<ChildReports> childReports) {
        this.childReports = childReports;
    }
}
