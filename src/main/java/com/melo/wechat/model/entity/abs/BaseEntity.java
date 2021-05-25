package com.melo.wechat.model.entity.abs;

import com.alibaba.fastjson.annotation.JSONField;
import com.melo.wechat.annotation.Column;

import java.util.Date;

/**
 * @author jun
 * @program WeChat
 * @description 所有数据库记录的父类
 * @date 2021-4-24
 */
public abstract class BaseEntity {

    @Column("id")
    private Integer id;
    @Column("gmt_create")
    @JSONField(format="MM-dd HH:mm")
    private Date gmtCreate;
    @Column("gmt_modified")
    @JSONField(format="MM-dd HH:mm")
    private Date gmtModified;


    public BaseEntity() {
    }

    public BaseEntity(Integer id) {
        this.id = id;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
