package com.melo.wechat.service.impl;

import com.melo.wechat.dao.impl.NoticeDaoImpl;
import com.melo.wechat.dao.impl.UserDaoImpl;
import com.melo.wechat.dao.inter.NoticeDao;
import com.melo.wechat.dao.inter.UserDao;
import com.melo.wechat.model.builder.NoticeVOBuilder;
import com.melo.wechat.model.dto.ServiceResult;
import com.melo.wechat.model.entity.Notice;
import com.melo.wechat.model.entity.User;
import com.melo.wechat.model.vo.NoticeVO;
import com.melo.wechat.controller.websocket.WebSocket;
import com.melo.wechat.service.inter.NoticeService;
import com.melo.wechat.utils.proxy.DaoProxy;

import java.util.LinkedList;


/**
 * @Description: 通知业务相关逻辑实现类
 * @author: Jun
 * @date: 21:47 2021/5/6
 */
public class NoticeServiceImpl implements NoticeService {

    /**
     * 代理对象类
     */
    NoticeDao noticeDao= DaoProxy.getProxyInstance(NoticeDaoImpl.class);
    UserDao userDao= DaoProxy.getProxyInstance(UserDaoImpl.class);

    /**
     * @param notice 通知对象
     * @Description: 列出相应类型通知列表
     * @date: 21:56 2021/5/6
     * @return: com.melo.wechat.model.dto.ServiceResult
     */
    @Override
    public ServiceResult loadNotice(Notice notice) {
        LinkedList<Notice> notices = noticeDao.getNotice(notice);
        return new ServiceResult(notices);
    }
    
    @Override
    public ServiceResult showNotice(Notice notice) {
        Notice tempNotice = noticeDao.getNotice(notice).getFirst();
        User tempUser = userDao.getUserById(notice.getSenderId());
        NoticeVO noticeVO = new NoticeVOBuilder().setContent(tempNotice.getContent())
                .setId(notice.getId())
                .setSenderId(tempNotice.getSenderId())
                .setReceiverId(tempNotice.getReceiverId())
                .setChatId(tempNotice.getChatId())
                .setAvatar(tempUser.getAvatar())
                .setUserName(tempUser.getUserName())
                .setStatus(tempNotice.getStatus())
                .setType("friendNotice").build();
        return new ServiceResult(noticeVO);
    }

    /**
     * @Description: 更新通知状态
     * @param id 通知id
     * @date: 10:51 2021/5/22
     * @return: com.melo.wechat.model.dto.ServiceResult
     */
    @Override
    public ServiceResult updateStatus(Integer id) {
        if(noticeDao.update(new Notice(id,1))==1){
            return new ServiceResult(true);
        }
        return new ServiceResult(false);
    }

    @Override
    public ServiceResult sendNotice(Notice notice) {
        WebSocket.sendNotice(notice,notice.getReceiverId());
        if(noticeDao.insert(notice)==1){
            return new ServiceResult(true);
        }
        return new ServiceResult(false);
    }

    public boolean hasRead(Notice notice){
        return noticeDao.hasRead(notice);
    }

    public boolean hasSend(Notice notice){
        return noticeDao.hasSend(notice);
    }

}
