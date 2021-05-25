package com.melo.wechat.dao.impl;

import com.melo.wechat.dao.inter.ChatDao;
import com.melo.wechat.model.entity.Chat;

import java.util.LinkedList;


/**
 * @Description:
 * @author: Jun
 * @date: 20:29 2021/5/8
 */
public class ChatDaoImpl extends BaseDaoImpl implements ChatDao {

    /**
     * 本表对应所有字段
     */
    private final Object[] ALL_FIELD_NAME=new Object[]{"id","name","number","type","avatar","master","gmt_create", "gmt_modified"};


    @Override
    public int insertChat(Chat chat){
        return super.insert(chat);
    }

    @Override
    public Integer getIdByNumber(String number){
        Chat chat = new Chat();
        chat.setNumber(number);
        return super.getId(chat);
    }

    @Override
    public Chat getChatById(Integer chatId) {
        Chat chat = new Chat();
        chat.setId(chatId);
        StringBuilder sql=appendSelect(ALL_FIELD_NAME,chat,"AND");
        return  getObjectBy(sql.toString(),chat, Chat.class);
    }

    @Override
    public LinkedList<Chat> showChatAll(Chat chat) {
        StringBuilder sql=appendSelect(ALL_FIELD_NAME,chat,"OR");
        return queryAll(sql.toString(),chat, Chat.class);
    }

    @Override
    public LinkedList<Chat> listChatById(Integer id){
        Chat chat = new Chat();
        chat.setId(id);
        StringBuilder sql = appendSelect(ALL_FIELD_NAME, chat, "AND");
        return queryAll(sql.toString(),chat,Chat.class);
    }


}
