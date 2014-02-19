package com.taobao.tae.ewall.lock;

import java.util.Date;

/**
 * User: xinyuan.ymm
 * Date: 14-2-14
 * Time: 下午3:50
 */
public class ResourcesLockDO {

    private Long id;

    private String name;

    private Integer priority;

    private Boolean locked;

    private String description;

    private Date lastLock;

    private Date lastUnLock;

    private Date gmtCreate;

    private Date gmtModified;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getLastLock() {
        return lastLock;
    }

    public void setLastLock(Date lastLock) {
        this.lastLock = lastLock;
    }

    public Date getLastUnLock() {
        return lastUnLock;
    }

    public void setLastUnLock(Date lastUnLock) {
        this.lastUnLock = lastUnLock;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}
