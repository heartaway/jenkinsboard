package com.taobao.tae.ewall.model;

/**
 * User: xinyuan.ymm
 * Date: 14-2-18
 * Time: 下午9:40
 */
public enum TriggerThreshold {

    SUCCESS(0, "只有当构建成功时才触发"),
    UNSTABLE(1, "在构建不稳定时依然触发"),
    FAILURE(2, "构建失败后依然触发");

    Integer code;
    String description;

    TriggerThreshold(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        if (code == null) {
            code = 6;
        }
        return code;
    }

    public String getDescription() {
        return description;
    }

}
