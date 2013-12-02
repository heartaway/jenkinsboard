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
    FAILURE,     // 失败
    UNSTABLE,    // 不稳定
    REBUILDING,  // 重新构建中
    BUILDING,    // 构建中
    ABORTED,     // 被中断
    SUCCESS,     // 成功
    UNKNOWN      // 未知
}
