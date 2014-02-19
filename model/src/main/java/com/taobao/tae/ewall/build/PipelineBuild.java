package com.taobao.tae.ewall.build;

import com.taobao.tae.ewall.pipeline.PipelineDO;
import com.taobao.tae.ewall.project.ProjectGrid;

public class PipelineBuild {

    //pipeline基本信息
    private PipelineDO pipelineDO;

    private BuildPipelineDO buildPipelineDO;

    //pipeline所对应的矩阵信息
    private BuildGrid buildGrid;

    //矩阵列数
    private Integer gridColumnSize;

    //矩阵行数
    private Integer gridRowSize;

    public PipelineBuild() {
    }

    public PipelineDO getPipelineDO() {
        return pipelineDO;
    }

    public void setPipelineDO(PipelineDO pipelineDO) {
        this.pipelineDO = pipelineDO;
    }

    public BuildGrid getBuildGrid() {
        return buildGrid;
    }

    public void setBuildGrid(BuildGrid buildGrid) {
        this.buildGrid = buildGrid;
    }

    public Integer getGridColumnSize() {
        return gridColumnSize;
    }

    public void setGridColumnSize(Integer gridColumnSize) {
        this.gridColumnSize = gridColumnSize;
    }

    public Integer getGridRowSize() {
        return gridRowSize;
    }

    public void setGridRowSize(Integer gridRowSize) {
        this.gridRowSize = gridRowSize;
    }

    public String getGridPercent() {
        if (gridColumnSize == null || gridColumnSize == 0) {
            return "100%";
        }
        Integer percent = 90 / gridColumnSize;
        return percent.toString().concat("%");
    }

    public BuildPipelineDO getBuildPipelineDO() {
        return buildPipelineDO;
    }

    public void setBuildPipelineDO(BuildPipelineDO buildPipelineDO) {
        this.buildPipelineDO = buildPipelineDO;
    }
}
