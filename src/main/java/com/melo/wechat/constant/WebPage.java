
package com.melo.wechat.constant;

/**
 * @Description: 界面网址枚举类
 * @author: Jun
 * @date: 11:47 2021/5/25
 */
public enum WebPage {

    /**
     * 注册界面
     */
    REGISTER_JSP,
    /**
     * 登陆界面
     */
    LOGIN_JSP,
    /**
     * 网站首页
     */
    WECHAT_JSP,
    /**
     * 找回密码
     */
    RESETPASS_JSP;

    @Override
    public String toString() {
        return "/"+super.toString().toLowerCase().replaceAll("_", ".");
    }
}