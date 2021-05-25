package com.melo.wechat.model.entity;

import com.melo.wechat.annotation.Column;
import com.melo.wechat.annotation.Table;
import com.melo.wechat.model.entity.abs.BaseEntity;

/**
 * @Description: 用户朋友圈中间表
 * @author: Jun
 * @date: 11:19 2021/5/16
 */
@Table(name = "user_moment")
public class UserMoment extends BaseEntity {

    @Column( "user_id")
    private Integer userId;
    @Column( "moment_id")
    private Integer momentId;
    @Column( "background")
    private String background;

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

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }
}
