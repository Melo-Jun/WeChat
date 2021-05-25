package com.melo.wechat.service.inter;

import com.melo.wechat.model.dto.ServiceResult;
import com.melo.wechat.model.entity.Message;

/**
 * @Description: 消息相关业务接口
 * @author: Jun
 * @date: 17:10 2021/5/14
 */
public interface MessageService {

    /**
     * @Description: 获取该聊天下的信息
     * @param chatId 聊天id
     * @date: 18:33 2021/5/25
     * @return: com.melo.wechat.model.dto.ServiceResult
     */
    ServiceResult getMessageByChatId(Integer chatId);

    /**
     * @Description: 向数据库中插入信息
     * @param message 信息对象
     * @date: 18:33 2021/5/25
     * @return: int
     */
    int insertMessage(Message message);

    /**
     * @Description: 聊天记录分页展示
     * @param chatId 聊天id
     * @param currentPage 当前页数
     * @date: 18:32 2021/5/25
     * @return: com.melo.wechat.model.dto.ServiceResult
     */
    ServiceResult listMessageByPage(Integer chatId, Integer currentPage);

}
