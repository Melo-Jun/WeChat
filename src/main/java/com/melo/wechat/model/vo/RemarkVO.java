package com.melo.wechat.model.vo;

import com.melo.wechat.model.entity.User;

/**
 * @Description: 评论视图层对象
 * @author: Jun
 * @date: 12:01 2021/5/25
 */
public class RemarkVO extends User {

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
