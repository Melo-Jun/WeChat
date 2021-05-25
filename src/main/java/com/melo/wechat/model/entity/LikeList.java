package com.melo.wechat.model.entity;

import com.melo.wechat.annotation.Column;
import com.melo.wechat.annotation.Table;
import com.melo.wechat.model.entity.abs.BaseEntity;

/**
 * @Description: 点赞记录实体类
 * @author: Jun
 * @date: 16:07 2021/5/17
 */
@Table(name = "like_list")
public class LikeList extends BaseEntity {

    @Column("user_id")
    private Integer userId;
    @Column("moment_id")
    private Integer momentId;

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
}
