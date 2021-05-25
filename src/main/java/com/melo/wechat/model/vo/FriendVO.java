package com.melo.wechat.model.vo;

import com.melo.wechat.model.entity.User;

/**
 * @Description: 好友个人信息视图层对象(包含了好友表和用户表)
 *               负责展示好友信息(包括备注,好友状态和个人信息)
 * @author: Jun
 * @date: 9:09 2021/5/15
 */
public class FriendVO extends User {

    /**
     * 好友备注
     */
    private String alias;

    /**
     * 好友拉黑状态
     */
    private Integer isBlock;

    /**
     * 聊天id
     */
    private Integer chatId;

    /**
     * 好友对应的用户id
     */
    private Integer userId;


    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Integer getIsBlock() {
        return isBlock;
    }

    public void setIsBlock(Integer isBlock) {
        this.isBlock = isBlock;
    }

    public Integer getChatId() {
        return chatId;
    }

    public void setChatId(Integer chatId) {
        this.chatId = chatId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
