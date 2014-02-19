package com.taobao.tae.ewall.project;

/**
 * User: xinyuan.ymm
 * Date: 13-12-2
 * Time: 上午10:56
 */
public enum ProjectType {

    Environment("1", "基础环境"),
    AutoTest("2", "自动化测试"),
    HandTest("3", "手工测试");

    private ProjectType(String value, String description) {
        this.value = value;
        this.description = description;
    }

    private String value;

    private String description;

    public static ProjectType valueof(String value) {
        for (ProjectType e : values())
            if (e.value.equals(value))
                return e;
        return null;
    }

    public static ProjectType descriptionof(String description) {
        for (ProjectType e : values())
            if (e.description.equals(description.trim()))
                return e;
        return null;
    }

    public String value() {
        return value;
    }

    public String description() {
        return description;
    }
}
