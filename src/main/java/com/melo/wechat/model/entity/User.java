package com.melo.wechat.model.entity;

import com.melo.wechat.annotation.Column;
import com.melo.wechat.annotation.Table;
import com.melo.wechat.model.entity.abs.BaseEntity;

/**
 * @author Jun
 * @program WeChat
 * @description 用户实体类
 * @date 2021-4-24
 */
@Table(name="user")
public class User extends BaseEntity {

    /**
     * 邮箱
     */
    @Column("email")
    private String email;
    /**
     *微信号
     */
    @Column("wechat_id")
    private String wechatId;
    /**
     * 用户名
     */
    @Column( "user_name")
    private String userName;
    /**
     * 密码
     */
    @Column( "password")
    private String password;
    /**
     * 头像
     */
    @Column( "avatar")
    private String avatar;
    /**
     * 在线状态
     */
    @Column("status")
    private Integer status;
    /**
     * 用户有效性
     */
    @Column("validity")
    private Integer validity;
    /**
     * 权限类型
     */
    @Column( "type")
    private String type;

    public User() {
    }

    public User(String wechatId) {
        this.wechatId = wechatId;
    }

    public User(Integer id, String password) {
        super(id);
        this.password = password;
    }

    public User(String wechatId, String userName) {
        this.wechatId = wechatId;
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getValidity() {
        return validity;
    }

    public void setValidity(Integer validity) {
        this.validity = validity;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", wechatId='" + wechatId + '\'' +
                ", userName='" + userName + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
