package com.melo.wechat.model.vo;

import com.melo.wechat.model.entity.UserChat;

/**
 * @Description: 聊天成员视图层对象
 * @author: Jun
 * @date: 12:00 2021/5/25
 */
public class MemberVO extends UserChat {

    private String avatar;
    private String role;
    private Integer isBlock;
}
