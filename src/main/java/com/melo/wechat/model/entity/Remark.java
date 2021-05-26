package com.melo.wechat.model.entity;

import com.melo.wechat.annotation.Column;
import com.melo.wechat.annotation.Table;
import com.melo.wechat.model.entity.abs.BaseEntity;

/**
 * @Description: 评论实体类
 * @author: Jun
 * @date: 11:34 2021/5/25
 */
@Table(name = "remark")
public class Remark extends BaseEntity {

    @Column("content")
    private String content;
    @Column("user_id")
    private Integer userId;
    @Column("moment_id")
    private Integer momentId;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getMomentId() {
        return momentId;
    }

    public void setMomentId(Integer momentId) {
        this.momentId = momentId;
    }

    @Override
    public String toString() {
        return "Remark{" +
                "content='" + content + '\'' +
                ", userId=" + userId +
                ", momentId=" + momentId +
                '}';
    }
}
