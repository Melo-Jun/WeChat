package com.melo.wechat.model.builder;

import com.melo.wechat.model.vo.NoticeVO;

/**
 * @Description: 通知视图层builder
 * @author: Jun
 * @date: 23:22 2021/5/9
 */
public class NoticeVOBuilder {

    private NoticeVO noticeVO;

    public NoticeVOBuilder(){
        this.noticeVO =new NoticeVO();
    }

    public NoticeVO build(){
        return this.noticeVO;
    }

    public NoticeVOBuilder setContent(String content) {
        this.noticeVO.setContent(content);
        return this;
    }

    public NoticeVOBuilder setId(Integer id) {
        this.noticeVO.setId(id);
        return this;
    }

    public NoticeVOBuilder setSenderId(Integer senderId) {
        this.noticeVO.setSenderId(senderId);
        return this;
    }

    public NoticeVOBuilder setReceiverId(Integer receiverId) {
        this.noticeVO.setReceiverId(receiverId);
        return this;
    }

    public NoticeVOBuilder setChatId(Integer chatId) {
        this.noticeVO.setChatId(chatId);
        return this;
    }

    public NoticeVOBuilder setAvatar(String avatar) {
        this.noticeVO.setAvatar(avatar);
        return this;
    }

    public NoticeVOBuilder setUserName(String userName) {
        this.noticeVO.setUserName(userName);
        return this;
    }

    public NoticeVOBuilder setStatus(Integer status) {
        this.noticeVO.setStatus(status);
        return this;
    }

    public NoticeVOBuilder setType(String type) {
        this.noticeVO.setType(type);
        return this;
    }



}
