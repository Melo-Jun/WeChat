package com.melo.wechat.model.entity;

import com.melo.wechat.annotation.Column;
import com.melo.wechat.annotation.Table;
import com.melo.wechat.model.entity.abs.BaseEntity;

/**
 * @Description: 通知实体类
 * @author: Jun
 * @date: 9:53 2021/5/5
 */
@Table(name = "notice")
public class Notice extends BaseEntity {

    @Column("content")
    private String content;
    @Column("sender_id")
    private Integer senderId;
    @Column("receiver_id")
    private Integer receiverId;
    @Column("chat_id")
    private Integer chatId;
    @Column("status")
    private Integer status;
    @Column("type")
    private String type;

    public Notice() {
    }

    public Notice(Integer id) {
        super(id);
    }

    public Notice(Integer id, Integer status) {
        super(id);
        this.status = status;
    }

    public Notice(String content, Integer senderId, Integer receiverId, String type) {
        this.content = content;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    public Integer getChatId() {
        return chatId;
    }

    public void setChatId(Integer chatId) {
        this.chatId = chatId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
