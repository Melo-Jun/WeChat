package com.melo.wechat.dao.inter;

import com.melo.wechat.model.entity.Notice;

import java.util.LinkedList;

public interface NoticeDao extends BaseDao {

    /**
     * @Description: 展示通知所有信息
     * @param notice 通知对象
     * @date: 21:52 2021/5/6
     * @return: java.util.LinkedList<com.melo.wechat.model.entity.NoticeVO>
     */
     LinkedList<Notice> getNotice(Notice notice);

    /**
     * @Description: 判断是否已经发送过消息
     * @param notice 消息对象
     * @date: 10:10 2021/5/22
     * @return: boolean
     */
     boolean hasSend(Notice notice);

    /**
     * @Description: 判断消息是否已读
     * @param notice 消息对象
     * @date: 10:10 2021/5/22
     * @return: boolean
     */
     boolean hasRead(Notice notice);
}
