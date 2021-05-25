package com.melo.wechat.service.impl;

import com.melo.wechat.dao.impl.MessageDaoImpl;
import com.melo.wechat.dao.inter.MessageDao;
import com.melo.wechat.model.dto.ServiceResult;
import com.melo.wechat.model.entity.Message;
import com.melo.wechat.model.vo.MessageVo;
import com.melo.wechat.service.inter.MessageService;
import com.melo.wechat.utils.proxy.DaoProxy;

import java.util.LinkedList;
import java.util.List;

/**
 * @Description: 消息相关业务实现类
 * @author: Jun
 * @date: 17:10 2021/5/14
 */
public class MessageServiceImpl implements MessageService {

    /**
     * 代理对象类
     */
    MessageDao messageDao= DaoProxy.getProxyInstance(MessageDaoImpl.class);

    @Override
    public ServiceResult getMessageByChatId(Integer chatId) {
        Message message = new Message();
        message.setChatId(chatId);
        LinkedList<Message> messages = messageDao.getMessageByChatId(message);
        ServiceResult result = new ServiceResult();
        result.setData(messages);
        return result;
    }

    @Override
    public int insertMessage(Message message){
        return messageDao.insert(message);
    }

    /**
     * @Description: 聊天记录分页展示
     * @param chatId 聊天id
     * @param currentPage 当前页数
     * @date: 18:32 2021/5/25
     * @return: com.melo.wechat.model.dto.ServiceResult
     */
    @Override
    public ServiceResult listMessageByPage(Integer chatId, Integer currentPage) {

        int rows = 5;

        MessageVo messageVo = new MessageVo();
        //设置参数
        messageVo.setCurrentPage(currentPage);
        messageVo.setRows(rows);

        //查询总条数
        Integer totalCount = messageDao.getMessageCount(chatId);
        messageVo.setTotalCount(totalCount);
       /*
       查询每页的记录
        */
        //计算开始的记录索引
        int start = (currentPage - 1) * rows;
        List<Message> list = messageDao.listMessageByPage(start,rows,chatId);
        messageVo.setList(list);

        //计算总页码
        int totalPage = (totalCount % rows)  == 0 ? totalCount/rows : (totalCount/rows) + (totalCount % rows);
        messageVo.setTotalPage(totalPage);

        return new ServiceResult(messageVo);

    }

}
