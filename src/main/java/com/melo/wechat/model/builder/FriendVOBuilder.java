package com.melo.wechat.model.builder;

import com.melo.wechat.model.vo.FriendVO;

/**
 * @Description: 好友视图层builder
 * @author: Jun
 * @date: 11:59 2021/5/25
 */
public class FriendVOBuilder {

    private FriendVO friendVo;

    public FriendVOBuilder(){
        this.friendVo =new FriendVO();
    }

    public FriendVO build(){
        return this.friendVo;
    }

    public FriendVOBuilder setId(Integer id) {
        this.friendVo.setId(id);
        return this;
    }

    public FriendVOBuilder setUserId(Integer userId) {
        this.friendVo.setUserId(userId);
        return this;
    }

    public FriendVOBuilder setWechatId(String wechatId) {
        this.friendVo.setWechatId(wechatId);
        return this;
    }

    public FriendVOBuilder setUserName(String userName) {
        this.friendVo.setUserName(userName);
        return this;
    }

    public FriendVOBuilder setStatus(Integer status) {
        this.friendVo.setIsBlock(status);
        return this;
    }

    public FriendVOBuilder setIsBlock(Integer isBlock) {
        this.friendVo.setIsBlock(isBlock);
        return this;
    }

    public FriendVOBuilder setValidity(Integer validity) {
        this.friendVo.setValidity(validity);
        return this;
    }

    public FriendVOBuilder setPhoto(String photo) {
        this.friendVo.setAvatar(photo);
        return this;
    }

    public FriendVOBuilder setAlias(String alias) {
        this.friendVo.setAlias(alias);
        return this;
    }

    public FriendVOBuilder setChatId(Integer chatId) {
        this.friendVo.setChatId(chatId);
        return this;
    }


}
