package com.taobao.tae.ewall.model;

/**
 * User: xinyuan.ymm
 * Date: 14-2-14
 * Time: 下午2:46
 */
public enum BuildStatus {
    INIT(-1, "初始化"),
    STOP(0, "构建完成"),
    BUILDING(1, "构建进行中");

    Integer code;
    String description;

    BuildStatus(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
