package com.jenkins.api;

import org.junit.Test;

/**
 * User: xinyuan.ymm
 * Date: 13-11-12
 * Time: 下午4:52
 * <p/>
 * jenkins 支持 3 种形式 让远程机器 访问 jenkins api接口： xml，Json,Python
 */
public class JobApiTest {

    String homeUrl = "http://10.20.153.116:8080/";

    /**
     * 创建 JOB
     */
    @Test
    public void createJob() {
        //post   param:name=JOBNAME
        String url = homeUrl.concat("createItem");
    }

    /**
     * 复制 JOB
     */
    @Test
    public void copyJob() {
        // post    param:   name=NEWJOBNAME&mode=copy&from=FROMJOBNAME
        String url = homeUrl.concat("createItem");
    }

    /**
     * 获取 JOB 配置
     */
    public void getConfig() {
        String url = homeUrl.concat("jobname").concat("/").concat("config.xml");
    }

    /**
     * 更新配置
     */
    public void updateConfig() {
        // POST

    }

    /**
     * 触发构建
     */
    public void triggerBuild() {
        String url = homeUrl.concat("jobname").concat("/").concat("build");
    }

    /**
     * 通过SCM 定时 触发构建
     */
    public void triggerBuildBySCM() {
        String url = homeUrl.concat("jobname").concat("/").concat("build");
    }


    /**
     * 删除 一个 JOB
     */
    public void deleteJob() {
        String url = homeUrl.concat("jobname").concat("/").concat("doDelete");
    }

    /**
     * 启用 Job
     */
    public void enableJob() {
        String url = homeUrl.concat("jobname").concat("/").concat("enable");
    }

    /**
     * 禁用 Job
     */
    public void disableJob() {
        String url = homeUrl.concat("jobname").concat("/").concat("disable");
    }


}
