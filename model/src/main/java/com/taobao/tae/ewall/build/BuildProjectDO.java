package com.taobao.tae.ewall.build;

import java.util.Date;

/**
 * User: xinyuan.ymm
 * Date: 14-2-13
 * Time: 下午8:20
 */
public class BuildProjectDO {

    private Long id;

    //原始Project ID
    private Integer projectId;

    //原始Project Name
    private String projectName;

    // jenkins 工程 构建 的版本号
    private Long jenkinsProjectBuildNumber;

    //构建关联的PipelineId
    private Long buildPipelineId;

    //测试报告ID
    private Long testReportId;

    //构建状态
    private String status;

    //构建结果
    private String result;

    // 持续 构建时长
    private Integer duration;

    //代码变更
    private String codeChanges;

    //下游工程名称列表
    private String downStreamProjects;

    //描述
    private String description;

    //注释
    private String comment;

    //构建开始时间
    private Date gmtStart;

    //构建结束时间
    private Date gmtEnd;

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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Long getJenkinsProjectBuildNumber() {
        return jenkinsProjectBuildNumber;
    }

    public void setJenkinsProjectBuildNumber(Long jenkinsProjectBuildNumber) {
        this.jenkinsProjectBuildNumber = jenkinsProjectBuildNumber;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Long getBuildPipelineId() {
        return buildPipelineId;
    }

    public void setBuildPipelineId(Long buildPipelineId) {
        this.buildPipelineId = buildPipelineId;
    }

    public Long getTestReportId() {
        return testReportId;
    }

    public void setTestReportId(Long testReportId) {
        this.testReportId = testReportId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getCodeChanges() {
        return codeChanges;
    }

    public void setCodeChanges(String codeChanges) {
        this.codeChanges = codeChanges;
    }

    public String getDownStreamProjects() {
        return downStreamProjects;
    }

    public void setDownStreamProjects(String downStreamProjects) {
        this.downStreamProjects = downStreamProjects;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getGmtStart() {
        return gmtStart;
    }

    public void setGmtStart(Date gmtStart) {
        this.gmtStart = gmtStart;
    }

    public Date getGmtEnd() {
        return gmtEnd;
    }

    public void setGmtEnd(Date gmtEnd) {
        this.gmtEnd = gmtEnd;
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
