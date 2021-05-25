package com.melo.wechat.model.entity;

import com.melo.wechat.annotation.Column;
import com.melo.wechat.annotation.Table;
import com.melo.wechat.model.entity.abs.BaseEntity;

/**
 * @Description: 好友关系实体类
 * @author: Jun
 * @date: 10:09 2021/5/5
 */
@Table(name = "friend")
public class Friend  extends BaseEntity {

    @Column("user_id")
    private Integer userId;
    @Column("friend_id")
    private Integer friendId;
    @Column("chat_id")
    private Integer chatId;
    @Column("alias")
    private String alias;
    @Column("description")
    private String description;
    @Column("avatar")
    private String avatar;
    @Column("status")
    private String status;
    @Column("is_block")
    private Integer isBlock;

    public Friend() {
    }

    public Friend(Integer id, String alias, String avatar) {
        super(id);
        this.alias = alias;
        this.avatar = avatar;
    }

    public Friend(Integer userId, Integer friendId) {
        this.userId = userId;
        this.friendId = friendId;
    }

    public Friend(Integer chatId) {
        this.chatId = chatId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getFriendId() {
        return friendId;
    }

    public void setFriendId(Integer friendId) {
        this.friendId = friendId;
    }

    public Integer getChatId() {
        return chatId;
    }

    public void setChatId(Integer chatId) {
        this.chatId = chatId;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getIsBlock() {
        return isBlock;
    }

    public void setIsBlock(Integer isBlock) {
        this.isBlock = isBlock;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

