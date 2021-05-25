package com.melo.wechat.model.builder;

import com.melo.wechat.model.vo.RemarkVO;

import java.util.Date;

/**
 * @Description: 评论视图层builder
 * @author: Jun
 * @date: 20:02 2021/5/16
 */
public class RemarkVOBuilder {

    private RemarkVO remarkVO;

    public RemarkVOBuilder(){
        this.remarkVO=new RemarkVO();
    }

    public RemarkVO build(){
        return this.remarkVO;
    }

    public RemarkVOBuilder setContent(String content) {
        this.remarkVO.setContent(content);
        return this;
    }

    public RemarkVOBuilder setUserName(String userName) {
        this.remarkVO.setUserName(userName);
        return this;
    }

    public RemarkVOBuilder setAvatar(String avatar) {
        this.remarkVO.setAvatar(avatar);
        return this;
    }

    public RemarkVOBuilder setGmtCreate(Date gmtCreate) {
        this.remarkVO.setGmtCreate(gmtCreate);
        return this;
    }

}
