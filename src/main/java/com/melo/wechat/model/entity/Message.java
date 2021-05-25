package com.melo.wechat.model.entity;

import com.melo.wechat.annotation.Column;
import com.melo.wechat.annotation.Table;
import com.melo.wechat.model.entity.abs.BaseEntity;

/**
 * @Description: 信息实体类
 * @author: Jun
 * @date: 17:02 2021/5/4
 */
@Table(name="message")
public class Message extends BaseEntity {

    @Column("sender_id")
    private Integer senderId;
    @Column("chat_id")
    private Integer chatId;
    @Column("content")
    private String content;
    @Column("type")
    private String type;

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getChatId() {
        return chatId;
    }

    public void setChatId(Integer chatId) {
        this.chatId = chatId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
