package com.melo.wechat.model.builder;

import com.melo.wechat.model.entity.Notice;
import com.melo.wechat.model.vo.NoticeVO;

/**
 * @Description: 通知消息builder
 * @author: Jun
 * @date: 23:22 2021/5/9
 */
public class NoticeBuilder {

    private Notice notice;

    public NoticeBuilder(){
        this.notice =new Notice();
    }

    public Notice build(){
        return this.notice;
    }

    public NoticeBuilder setContent(String content) {
        this.notice.setContent(content);
        return this;
    }

    public NoticeBuilder setSenderId(Integer senderId) {
        this.notice.setSenderId(senderId);
        return this;
    }

    public NoticeBuilder setReceiverId(Integer receiverId) {
        this.notice.setReceiverId(receiverId);
        return this;
    }

    public NoticeBuilder setChatId(Integer chatId) {
        this.notice.setChatId(chatId);
        return this;
    }


    public NoticeBuilder setStatus(Integer status) {
        this.notice.setStatus(status);
        return this;
    }

    public NoticeBuilder setType(String type) {
        this.notice.setType(type);
        return this;
    }



}
