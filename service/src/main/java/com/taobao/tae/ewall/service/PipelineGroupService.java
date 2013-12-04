package com.taobao.tae.ewall.service;

import com.taobao.tae.ewall.pipeline.PipelineGroupDO;

import java.util.List;

/**
 * User: xinyuan.ymm
 * Date: 13-12-2
 * Time: 下午9:22
 */
public interface PipelineGroupService {
    /**
     * 获取 所有的 分组
     *
     * @return
     */
    public List<PipelineGroupDO> getAllGroups();

    /**
     * 通过 id 删除 分组
     *
     * @param id
     * @return
     */
    public Boolean deleteById(Integer id);

    /**
     * 添加分组
     *
     * @param groupDO
     * @return 返回创建后的分组 id
     */
    public Integer addGroup(PipelineGroupDO groupDO);

    /**
     * 更新分组
     *
     * @param groupDO
     * @return
     */
    public Boolean updateGroup(PipelineGroupDO groupDO);

    /**
     * 根据 名称 查找 group
     * @param groupName
     * @return
     */
    public PipelineGroupDO findByName(String groupName);
}
