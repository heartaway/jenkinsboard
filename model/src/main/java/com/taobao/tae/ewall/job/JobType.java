package com.taobao.tae.ewall.job;

/**
 * User: xinyuan.ymm
 * Date: 13-12-2
 * Time: 上午10:56
 */
public enum JobType {

    Environment(1, "环境"),
    Test(2, "测试");

    private JobType(Integer value, String description) {
        this.value = value;
        this.description = description;
    }

    private Integer value;

    private String description;

    public static JobType valueof(int value) {
        for (JobType e : values())
            if (e.value.equals(value))
                return e;
        return null;
    }

    public int value() {
        return value;
    }

    public String getDescription() {
        return description;
    }
}
