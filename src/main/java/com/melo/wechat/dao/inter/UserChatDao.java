package com.melo.wechat.dao.inter;

import com.melo.wechat.model.entity.UserChat;

import java.util.LinkedList;

/**
 * @Description: 用户表与聊天表的中间表接口
 * @author: Jun
 * @date: 9:19 2021/5/9
 */
public interface UserChatDao extends BaseDao {

    /**
     * @Description: 负责新增中间表记录
     * @param userChat 中间表对象
     * @date: 13:16 2021/5/10
     * @return: int
     */
    int insertUserChat(UserChat userChat);

    /**
     * @Description: 列出chatId
     * @param userId  用户id
     * @param type 类型(为null则默认全部列出)
     * @date: 13:17 2021/5/10
     * @return: java.util.LinkedList<java.lang.Integer>
     */
    LinkedList<Integer> listChatId(Integer userId,String type);

    /**
     * @Description: 根据chatId列出用户id
     * @param chatId 聊天id
     * @date: 13:17 2021/5/10
     * @return: java.util.LinkedList<java.lang.Integer>
     */
    LinkedList<Integer> listUserIdByChatId(Integer chatId);

    /**
     * @Description: 获取中间表对象
     * @param tempUserChat 临时中间表对象
     * @date: 17:29 2021/5/22
     * @return: com.melo.wechat.model.entity.UserChat
     */
    UserChat getUserChat(UserChat tempUserChat);

    LinkedList<UserChat> listUserChat(UserChat userChat);


    /**
     * @Description: 判断是否已经在群聊中
     * @param userChat 群聊中间表对象
     * @date: 17:29 2021/5/22
     * @return: boolean
     */
    boolean isAtGroup(UserChat userChat);

    /**
     * @Description: 判断是否被禁言
     * @param userChat
     * @date: 0:05 2021/5/26
     * @return: boolean
     */
    boolean isBlocked(UserChat userChat);
}
