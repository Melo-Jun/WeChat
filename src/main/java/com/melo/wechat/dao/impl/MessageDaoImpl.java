package com.melo.wechat.dao.impl;

import com.melo.wechat.dao.inter.MessageDao;
import com.melo.wechat.model.entity.Message;

import java.util.LinkedList;
import java.util.List;

/**
 * @Description: 聊天消息数据库操作实现类
 * @author: Jun
 * @date: 23:39 2021/5/13
 */
public class MessageDaoImpl extends BaseDaoImpl implements MessageDao {

    /**
     * 本表对应所有字段
     */
    private final Object[] ALL_FIELD_NAME=new Object[]{"id","content","chat_id","sender_id","type","gmt_create", "gmt_modified"};



    /**
     * @Description: 显示该chatId下的五条最新消息
     * @param message 信息对象
     * @date: 21:16 2021/5/14
     * @return: java.util.LinkedList<com.melo.wechat.model.entity.Message>
     */
    @Override
    public LinkedList<Message> getMessageByChatId(Message message) {
        StringBuilder sql = appendSelect(ALL_FIELD_NAME, message, "AND");
        sql.append("order by id DESC ,gmt_create DESC limit 6");
        return queryAll(sql.toString(),message,Message.class);
    }

    @Override
    public Long getMessageCount(Integer chatId) {
        Message message = new Message();
        message.setChatId(chatId);
        StringBuilder sql = appendSelect(new Object[]{"count(*)"}, message, "AND");
        LinkedList<Object> objects = queryList(sql.toString(), message);
        return  (Long) objects.getFirst();
//        return (Integer) queryList(sql.toString(),message).getFirst();
    }

    @Override
    public List<Message> listMessageByPage(int start, int rows, Integer chatId) {
        Message message = new Message();
        message.setChatId(chatId);
        StringBuilder sql = appendSelect(ALL_FIELD_NAME, message, "AND");
        System.out.println(start);
        System.out.println(rows);
        sql.append("limit ").append(start).append(" , ").append(rows);
        return queryAll(sql.toString(),message,Message.class);
    }

}
