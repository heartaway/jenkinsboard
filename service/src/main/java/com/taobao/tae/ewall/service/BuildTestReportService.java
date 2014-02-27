package com.taobao.tae.ewall.service;

import com.taobao.tae.ewall.build.BuildTestReportDO;

/**
 * User: xinyuan.ymm
 * Date: 14-2-14
 * Time: 下午2:16
 */
public interface BuildTestReportService {

    /**
     * 插入新构建测试报告
     *
     * @param buildTestReportDO
     * @return 返回记录ID
     */
    public Long create(BuildTestReportDO buildTestReportDO);
}
