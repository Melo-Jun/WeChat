package com.melo.wechat.model.entity;

import com.melo.wechat.annotation.Column;
import com.melo.wechat.annotation.Table;
import com.melo.wechat.model.entity.abs.BaseEntity;

/**
 * @Description: 表情包实体类
 * @author: Jun
 * @date: 16:47 2021/5/23
 */
@Table(name = "emoji")
public class Emoji extends BaseEntity {

    @Column("user_id")
    private Integer userId;
    @Column("path")
    private String path;

    public Emoji() {
    }

    public Emoji(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "Emoji{" +
                "userId=" + userId +
                ", path='" + path + '\'' +
                '}';
    }
}
