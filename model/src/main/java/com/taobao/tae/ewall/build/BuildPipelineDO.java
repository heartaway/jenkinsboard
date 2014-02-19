package com.taobao.tae.ewall.build;

import java.util.Date;

/**
 * User: xinyuan.ymm
 * Date: 14-2-13
 * Time: 下午10:24
 */
public class BuildPipelineDO {

    private Long id;

    private Integer pipelineId;

    //构建状态
    private String status;

    //构建结果
    private String result;

    //构建触发操作者
    private String operator;

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

    public Integer getPipelineId() {
        return pipelineId;
    }

    public void setPipelineId(Integer pipelineId) {
        this.pipelineId = pipelineId;
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

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
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
