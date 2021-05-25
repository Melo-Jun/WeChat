package com.melo.wechat.model.entity;

import com.melo.wechat.annotation.Column;
import com.melo.wechat.annotation.Table;
import com.melo.wechat.model.entity.abs.BaseEntity;

/**
 * @Description: 朋友圈实体类
 * @author: Jun
 * @date: 11:01 2021/5/16
 */
@Table(name = "moment")
public class Moment extends BaseEntity {

    @Column("content")
    private String content;
    @Column("photo")
    private String photo;
    @Column("user_id")
    private Integer userId;
    @Column("like_count")
    private Integer likeCount;

    public Moment() {
    }

    public Moment(Integer id, Integer likeCount) {
        super(id);
        this.likeCount = likeCount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
