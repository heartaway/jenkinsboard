package com.taobao.tae.ewall.build;

import java.util.Date;

/**
 * User: xinyuan.ymm
 * Date: 14-2-13
 * Time: 下午10:26
 */
public class BuildTestReportDO {

    private Long id;

    // 工程构建编号
    private Long buildProjectId;

    //在Jenkins中的构建编号
    private Long number;

    //在Jenkins中的构建Url
    private String url;

    //总共运行的测试用例数
    private Integer totalCount;

    //跳过运行的测试用例数
    private Integer skipCount;

    //运行失败的测试用例数
    private Integer failCount;

    //测试所花费时间
    private String duration;

    //具体构建结果报告
    private String testReport;

    //创建时间
    private Date gmtCreate;

    //修改时间
    private Date gmtModifid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBuildProjectId() {
        return buildProjectId;
    }

    public void setBuildProjectId(Long buildProjectId) {
        this.buildProjectId = buildProjectId;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getSkipCount() {
        return skipCount;
    }

    public void setSkipCount(Integer skipCount) {
        this.skipCount = skipCount;
    }

    public Integer getFailCount() {
        return failCount;
    }

    public void setFailCount(Integer failCount) {
        this.failCount = failCount;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getTestReport() {
        return testReport;
    }

    public void setTestReport(String testReport) {
        this.testReport = testReport;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModifid() {
        return gmtModifid;
    }

    public void setGmtModifid(Date gmtModifid) {
        this.gmtModifid = gmtModifid;
    }
}
