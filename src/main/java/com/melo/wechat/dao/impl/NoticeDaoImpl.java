package com.melo.wechat.dao.impl;

import com.melo.wechat.dao.inter.NoticeDao;
import com.melo.wechat.model.entity.Notice;

import java.util.LinkedList;

public class NoticeDaoImpl extends BaseDaoImpl implements NoticeDao {



    /**
     * 本表对应所有字段
     */
    private final Object[] ALL_FIELD_NAME=new Object[]{"id","content","sender_id","chat_id","receiver_id",
            "type","status","gmt_create","gmt_modified"};

    /**
     * @Description: 展示通知所有信息
     * @param notice 通知对象
     * @date: 21:52 2021/5/6
     * @return: java.util.LinkedList<com.melo.wechat.model.entity.NoticeVO>
     */
    @Override
    public LinkedList<Notice> getNotice(Notice notice){
        StringBuilder sql = appendSelect(ALL_FIELD_NAME, notice, "AND");
        return queryAll(sql.toString(), notice, Notice.class);
    }

    /**
     * @Description: 判断是否已经发送过消息
     * @param notice 消息对象
     * @date: 10:10 2021/5/22
     * @return: boolean
     */
    @Override
    public boolean hasSend(Notice notice) {
        notice.setContent(null);
        StringBuilder sql = appendSelect(new Object[]{"id"}, notice, "AND");
        return !queryList(sql.toString(), notice).isEmpty();
    }


    /**
     * @Description: 判断消息是否已读
     * @param notice 消息对象
     * @date: 10:10 2021/5/22
     * @return: boolean
     */
    @Override
    public boolean hasRead(Notice notice) {
        notice.setContent(null);
        StringBuilder sql = appendSelect(new Object[]{"status"}, notice, "AND");
        LinkedList<Integer> statuses = queryList(sql.toString(), notice);
        for(Integer status:statuses){
            if(status==0){
                return false;
            }
        }
        return true;
    }


}
