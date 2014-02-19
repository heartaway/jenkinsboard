package com.taobao.tae.ewall.lock;

/**
 * User: xinyuan.ymm
 * Date: 14-2-15
 * Time: 上午10:21
 */
public enum ResourcesPriority {
    LOW(0, "低"),
    DEFAULT(10, "默认"),
    MIDDLE(20, "中"),
    HIGH(30, "高"),
    FIRST(40, "最优");

    Integer priority;
    String comments;

    ResourcesPriority(Integer priority, String comments) {
        this.priority = priority;
        this.comments = comments;
    }

    public Integer getPriority() {
        return priority;
    }

    public String getComments() {
        return comments;
    }
}
