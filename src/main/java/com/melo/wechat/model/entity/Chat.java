package com.melo.wechat.model.entity;

import com.melo.wechat.annotation.Column;
import com.melo.wechat.annotation.Table;
import com.melo.wechat.model.entity.abs.BaseEntity;

/**
 * @Description: 聊天会话实体类
 * @author: Jun
 * @date: 11:24 2021/5/25
 */
@Table(name = "chat")
public class Chat  extends BaseEntity {

    @Column("name")
    private String name;
    @Column("number")
    private String number;
    @Column("avatar")
    private String avatar;
    @Column("type")
    private String type;
    @Column("master")
    private Integer master;

    public Chat(){

    }



    public Chat(String number) {
        this.number = number;
    }

    public Chat(String name, String number, Integer master,String type) {
        this.name=name;
        this.number=number;
        this.master=master;
        this.type=type;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getMaster() {
        return master;
    }

    public void setMaster(Integer master) {
        this.master = master;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
