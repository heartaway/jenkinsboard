package com.taobao.tae.ewall.system;

/**
 * User: xinyuan.ymm
 * Date: 13-12-2
 * Time: 上午10:33
 */
public enum SystemType {

    Jenkins(1, "Jenkins Home Url"),
    Sonar(2, "Sonar Home Url");

    private Integer id;

    private String type;

    private SystemType(Integer id, String type) {
        this.id = id;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public String getType() {
        return type;
    }

}
