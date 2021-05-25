package com.melo.wechat.dao.impl;

import com.melo.wechat.dao.inter.UserChatDao;
import com.melo.wechat.model.entity.UserChat;

import java.util.LinkedList;

/**
 * @Description: 用户表与聊天表的中间表实现类
 * @author: Jun
 * @date: 9:19 2021/5/9
 */
public class UserChatDaoImpl extends BaseDaoImpl implements UserChatDao {

    /**
     * 本表对应所有字段
     */
    private final Object[] ALL_FIELD_NAME=new Object[]{"id","user_id","chat_id","member_name","member_avatar","role","is_block","type","gmt_create", "gmt_modified"};


    /**
     * @Description: 负责新增中间表记录
     * @param userChat 中间表对象
     * @date: 13:16 2021/5/10
     * @return: int
     */
    @Override
    public int insertUserChat(UserChat userChat) {
        return super.insert(userChat);
    }

    /**
     * @Description: 列出chatId
     * @param userId  用户id
     * @param type 类型(为null则默认全部列出)
     * @date: 13:17 2021/5/10
     * @return: java.util.LinkedList<java.lang.Integer>
     */
    @Override
    public LinkedList<Integer> listChatId(Integer userId,String type){
        UserChat userChat = new UserChat();
        userChat.setUserId(userId);
        if(type!=null){
            userChat.setType(type);
        }
        StringBuilder sql = appendSelect(new Object[]{"chat_id"}, userChat, "AND");
        return queryList(sql.toString(), userChat);
    }

    /**
     * @Description: 根据chatId列出用户id
     * @param chatId 聊天id
     * @date: 13:17 2021/5/10
     * @return: java.util.LinkedList<java.lang.Integer>
     */
    @Override
    public LinkedList<Integer> listUserIdByChatId(Integer chatId){
        UserChat userChat = new UserChat();
        userChat.setChatId(chatId);
        StringBuilder sql=appendSelect(new Object[]{"user_id"}, userChat ,"AND");
        return queryList(sql.toString(),userChat);
    }

    /**
     * @Description: 获取中间表对象
     * @param tempUserChat 临时中间表对象
     * @date: 17:29 2021/5/22
     * @return: com.melo.wechat.model.entity.UserChat
     */
    @Override
    public UserChat getUserChat(UserChat tempUserChat) {
        StringBuilder sql=appendSelect(ALL_FIELD_NAME,tempUserChat,"AND");
        return getObjectBy(sql.toString(),tempUserChat,UserChat.class);
    }

    /**
     * @Description: 判断是否已经在群聊中
     * @param userChat 群聊中间表对象
     * @date: 17:29 2021/5/22
     * @return: boolean
     */
    @Override
    public boolean isAtGroup(UserChat userChat) {
        StringBuilder sql = appendSelect(new Object[]{"id"}, userChat, "AND");
        return  !queryList(sql.toString(),userChat).isEmpty();
    }



    @Override
    public LinkedList<UserChat> listUserChat(UserChat userChat) {
        StringBuilder sql=appendSelect(ALL_FIELD_NAME,userChat,"AND");
        return queryAll(sql.toString(),userChat,UserChat.class);
    }

    @Override
    public boolean isBlocked(UserChat userChat) {
        StringBuilder sql=appendSelect(new Object[]{"is_block"},userChat,"AND");
        Integer isBlocked = (Integer) queryList(sql.toString(), userChat).getFirst();
        return isBlocked == 1;
    }

}
