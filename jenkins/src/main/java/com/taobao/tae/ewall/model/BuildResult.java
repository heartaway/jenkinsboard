/*
 * Copyright (c) 2013 Rising Oak LLC.
 *
 * Distributed under the MIT license: http://opensource.org/licenses/MIT
 */

package com.taobao.tae.ewall.model;

/**
 * 构建结果
 */
public enum BuildResult {

    FAILURE(0, "失败"),
    SUCCESS(1, "成功"),
    UNSTABLE(2, "不稳定"),
    BUILDING(3, "构建中"),
    REBUILDING(4, "重新构建中"),
    ABORTED(5, "被中断"),
    NOT_BUILT(6,"初始化"),
    UNKNOWN(7, "未知");

    Integer code;
    String description;

    BuildResult(Integer code, String description) {
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
