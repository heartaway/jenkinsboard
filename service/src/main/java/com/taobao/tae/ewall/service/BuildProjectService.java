package com.taobao.tae.ewall.service;

import com.alibaba.pt.commons.persistence.page.Page;
import com.taobao.tae.ewall.build.BuildProjectDO;

import java.util.List;

/**
 * User: xinyuan.ymm
 * Date: 14-2-14
 * Time: 下午2:15
 */
public interface BuildProjectService {

    /**
     * 初始化 Project 构建前的信息记录
     *
     * @param projectId       需要被构建的工程名称
     * @param buildPipelineId 触发本次构建的pipline的构建编号
     * @param isStartProject  是否是pipeline的其实Job
     */
    public void triggerProjectBuild(Integer projectId, Long buildPipelineId, Boolean isStartProject);

    /**
     * 强制停止 project的构建
     *
     * @param projectId
     * @param buildPipelineId
     */
    public void stopProjectBuild(Integer projectId, Long buildPipelineId);

    /**
     * 获取 buildpipline 下 的构建工程信息
     */
    public List<BuildProjectDO> getBuildProjectsByBuildPipelineId(Long buildPipelineId);

    /**
     * 分页查询 某个 project下的构建信息，默认分页大小为 20
     *
     * @param projectId
     * @param index     记录起始行下标( 页面默认大小：Page.DEFAULT_SIZE_PER_PAGE)
     * @return
     */
    public Page<BuildProjectDO> findProjectBuildsByPage(Integer projectId, Integer index);

    /**
     * 通过 工程Name & pipeline构建 ID 来查找某次pipeline构建下的某个工程的构建信息
     *
     * @param projectName
     * @param buildPipelineId
     * @return
     */
    public BuildProjectDO loadByBuildPipelineIdAndProjectName(String projectName, Long buildPipelineId);

    /**
     * 更新 buildProjectDO 对象
     *
     * @param buildProjectDO
     */
    public Boolean update(BuildProjectDO buildProjectDO);

    /**
     * 构建中 通过 project ID 查找 对应Jenkins的Name
     *
     * @return
     */
    public String getJenkinsUrlByProjectId(Integer projectId);
}
