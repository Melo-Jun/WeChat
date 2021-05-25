package com.melo.wechat.model.vo;

import com.melo.wechat.model.entity.Notice;

/**
 * @Description: 通知视图层对象
 * @author: Jun
 * @date: 12:00 2021/5/25
 */
public class NoticeVO extends Notice {

    private String avatar;
    private String userName;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
