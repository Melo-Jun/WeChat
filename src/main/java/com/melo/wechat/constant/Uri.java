package com.melo.wechat.constant;

/**
 * @Description: Uri枚举类
 * @author: Jun
 * @date: 11:46 2021/5/25
 */
public enum Uri {

    /**
     * 验证码
     */
    CHECK_CODE("/CheckCode"),
    /**
     * 用户相关
     */
    USER("/user"),
    /**
     * 上传
     */
    UPLOAD("/upload"),
    /**
     * 发布朋友圈
     */
    MOMENT("/moment");

    private String uri;

    Uri(String uri) {
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }


}
