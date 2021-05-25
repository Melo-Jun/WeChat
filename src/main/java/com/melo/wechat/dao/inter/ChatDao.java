package com.melo.wechat.dao.inter;

import com.melo.wechat.model.entity.Chat;

import java.util.LinkedList;

/**
 * @Description:
 * @author: Jun
 * @date: 20:28 2021/5/8
 */
public interface ChatDao extends BaseDao {

      /**
       * @Description: 新增一个聊天
       * @param chat 聊天对象
       * @date: 11:55 2021/5/25
       * @return: int
       */
      int insertChat(Chat chat);

      /**
       * @Description: 根据id列出所有chat
       * @param id id
       * @date: 11:55 2021/5/25
       * @return: java.util.LinkedList<com.melo.wechat.model.entity.Chat>
       */
      LinkedList<Chat> listChatById(Integer id);

      /**
       * @Description: 根据群号获取到id
       * @param number
       * @date: 11:55 2021/5/25
       * @return: java.lang.Integer
       */
      Integer getIdByNumber(String number);

      /**
       * @Description: 通过id获取到chat对象
       * @param chatId
       * @date: 11:55 2021/5/25
       * @return: com.melo.wechat.model.entity.Chat
       */
      Chat getChatById(Integer chatId);

      /**
       * @Description: 根据临时对象获取到数据库中的对象
       * @param chat 临时对象
       * @date: 11:56 2021/5/25
       * @return: java.util.LinkedList<com.melo.wechat.model.entity.Chat>
       */
      LinkedList<Chat> showChatAll(Chat chat);
}
