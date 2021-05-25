package com.melo.wechat.model.builder;

import com.melo.wechat.model.vo.MomentVO;

import java.util.Date;

/**
 * @Description: 朋友圈视图层建造者
 * @author: Jun
 * @date: 11:38 2021/5/25
 */
public class MomentVOBuilder {

    private MomentVO momentVO;

    public MomentVOBuilder(){
        this.momentVO =new MomentVO();
    }

    public MomentVO build(){
        return this.momentVO;
    }

    public MomentVOBuilder setId(Integer id) {
        this.momentVO.setId(id);
        return this;
    }

    public MomentVOBuilder setUserId(Integer userId) {
        this.momentVO.setUserId(userId);
        return this;
    }

    public MomentVOBuilder setContent(String content) {
        this.momentVO.setContent(content);
        return this;
    }

    public MomentVOBuilder setPhoto(String photo) {
        this.momentVO.setPhoto(photo);
        return this;
    }
    
    public MomentVOBuilder setLikeCount(Integer likeCount) {
        this.momentVO.setLikeCount(likeCount);
        return this;
    }

    public MomentVOBuilder setWechatId(String wechatId) {
        this.momentVO.setWechatId(wechatId);
        return this;
    }

    public MomentVOBuilder setUserName(String userName) {
        this.momentVO.setUserName(userName);
        return this;
    }

    public MomentVOBuilder setAvatar(String avatar) {
        this.momentVO.setAvatar(avatar);
        return this;
    }

    public MomentVOBuilder setGmtCreate(Date gmtCreat) {
        this.momentVO.setGmtCreate(gmtCreat);
        return this;
    }



}
