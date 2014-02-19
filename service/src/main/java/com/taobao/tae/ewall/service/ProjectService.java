package com.taobao.tae.ewall.service;

import com.taobao.tae.ewall.project.ProjectDO;

import java.util.List;

/**
 * User: xinyuan.ymm
 * Date: 13-12-2
 * Time: 下午9:20
 */
public interface ProjectService {

    /**
     * 获取 所有的 Project
     *
     * @return
     */
    public List<ProjectDO> getAllProjects();

    /**
     * 通过 ID 获取 project
     * @param projectId
     * @return
     */
    public ProjectDO getProjectById(Integer projectId);

    /**
     * 通过 name 获取 project
     * @param name
     * @return
     */
    public List<ProjectDO> getProjectByName(String name);

    /**
     * 通过 id 删除 Project
     *
     * @param id
     * @return
     */
    public Boolean deleteById(Integer id);

    /**
     * 添加Project
     *
     * @param projectDO
     * @return 返回创建后的project id
     */
    public Integer addProject(ProjectDO projectDO);

    /**
     * 更新Project
     *
     * @param projectDO
     * @return
     */
    public Boolean updateProject(ProjectDO projectDO);
}
