package com.melo.wechat.model.entity;

import com.melo.wechat.annotation.Column;
import com.melo.wechat.annotation.Table;
import com.melo.wechat.model.entity.abs.BaseEntity;

/**
 * @Description: 用户--聊天中间表
 * @author: Jun
 * @date: 11:34 2021/5/25
 */
@Table(name = "user_chat")
public class UserChat extends BaseEntity {

    @Column( "user_id")
    private Integer userId;
    @Column( "chat_id")
    private Integer chatId;
    @Column( "member_name")
    private String memberName;
    @Column( "member_avatar")
    private String memberAvatar;
    @Column( "role")
    private String role;
    @Column( "is_block")
    private Integer isBlock;
    @Column( "type")
    private String type;

    public UserChat(Integer chatId) {
        this.chatId = chatId;
    }

    public UserChat() {
    }

    public UserChat(Integer userId, Integer chatId) {
        this.userId = userId;
        this.chatId = chatId;
    }

    public UserChat(Integer userId, Integer chatId, String role, String type) {
        this.userId = userId;
        this.chatId = chatId;
        this.role = role;
        this.type = type;
    }

    public UserChat(Integer userId, Integer chatId, String type) {
        this.userId = userId;
        this.chatId = chatId;
        this.type = type;
    }

    public UserChat(Integer id, String memberAvatar,String memberName) {
        super(id);
        this.memberAvatar = memberAvatar;
        this.memberName=memberName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getChatId() {
        return chatId;
    }

    public void setChatId(Integer chatId) {
        this.chatId = chatId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getIsBlock() {
        return isBlock;
    }

    public void setIsBlock(Integer isBlock) {
        this.isBlock = isBlock;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberAvatar() {
        return memberAvatar;
    }

    public void setMemberAvatar(String memberAvatar) {
        this.memberAvatar = memberAvatar;
    }
}
