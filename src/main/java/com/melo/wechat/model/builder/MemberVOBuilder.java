package com.melo.wechat.model.builder;

import com.melo.wechat.model.vo.MemberVO;

/**
 * @Description: 聊天成员视图层builder
 * @author: Jun
 * @date: 11:59 2021/5/25
 */
public class MemberVOBuilder {

    private MemberVO memberVO;

    public MemberVOBuilder(){
        this.memberVO =new MemberVO();
    }

    public MemberVO build(){
        return this.memberVO;
    }

    public MemberVOBuilder setId(Integer id) {
        this.memberVO.setId(id);
        return this;
    }

    public MemberVOBuilder setUserId(Integer userId) {
        this.memberVO.setUserId(userId);
        return this;
    }
}
