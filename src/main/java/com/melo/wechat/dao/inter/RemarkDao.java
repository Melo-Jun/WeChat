package com.melo.wechat.dao.inter;

import com.melo.wechat.model.entity.Remark;

import java.util.LinkedList;

/**
 * @Description: 评论数据库操作接口
 * @author: Jun
 * @date: 19:38 2021/5/16
 */
public interface RemarkDao extends BaseDao{

    /**
     * @Description: 列出该朋友圈消息下的所有评论
     * @param remark
     * @date: 0:05 2021/5/26
     * @return: java.util.LinkedList<com.melo.wechat.model.entity.Remark>
     */
    LinkedList<Remark> listRemarkByMomentId(Remark remark);
}
