package com.melo.wechat.model.vo;

import com.melo.wechat.model.entity.Moment;

/**
 * @Description: 好友朋友圈视图层对象
 * @author: Jun
 * @date: 15:14 2021/5/16
 */
public class MomentVO extends Moment {
    /**
     * 朋友圈所属用户微信号
     */
    private String wechatId;
    /**
     * 朋友圈所属用户名称
     */
    private String userName;
    /**
     * 朋友圈所属用户头像
     */
    private String avatar;

    public MomentVO() {
    }

    public MomentVO(String userName, String avatar) {
        this.userName = userName;
        this.avatar = avatar;
    }

    public String getWechatId() {
        return wechatId;
    }

    public void setWechatId(String wechatId) {
        this.wechatId = wechatId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }


}
