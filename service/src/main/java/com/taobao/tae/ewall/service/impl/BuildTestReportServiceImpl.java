package com.taobao.tae.ewall.service.impl;

import com.taobao.tae.ewall.build.BuildTestReportDO;
import com.taobao.tae.ewall.dao.BuildTestReportDao;
import com.taobao.tae.ewall.service.BuildTestReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * User: xinyuan.ymm
 * Date: 14-2-14
 * Time: 下午2:17
 */
@Component("buildTestReportService")
public class BuildTestReportServiceImpl implements BuildTestReportService {

    @Autowired
    BuildTestReportDao buildTestReportDao;

    @Override
    public Long create(BuildTestReportDO buildTestReportDO) {
        return buildTestReportDao.create(buildTestReportDO);
    }
}
