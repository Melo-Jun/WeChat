package com.melo.wechat.dao.inter;

import com.melo.wechat.model.entity.Message;

import java.util.LinkedList;
import java.util.List;

/**
 * @Description: 聊天消息数据库操作接口
 * @author: Jun
 * @date: 23:39 2021/5/13
 */
public interface MessageDao extends BaseDao {

    /**
     * @Description: 显示该chatId下的五条最新消息
     * @param message 信息对象
     * @date: 21:16 2021/5/14
     * @return: java.util.LinkedList<com.melo.wechat.model.entity.Message>
     */
    LinkedList<Message> getMessageByChatId(Message message);

    /**
     * @Description: 获取该chat下的聊天记录条数
     * @param chatId chatId
     * @date: 18:33 2021/5/25
     * @return: java.lang.Integer
     */
    Integer getMessageCount(Integer chatId);

    /**
     * @Description: 根据前台页码展示特定页
     * @param start 起始索引
     * @param rows 每页条数
     * @param chatId 聊天id
     * @date: 18:34 2021/5/25
     * @return: java.util.List<com.melo.wechat.model.entity.Message>
     */
    List<Message> listMessageByPage(int start, int rows, Integer chatId);
}
