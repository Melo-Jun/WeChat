package com.melo.wechat.dao.inter;

import com.melo.wechat.model.entity.Moment;

import java.util.LinkedList;

/**
 * @Description: 朋友圈数据库操作接口
 * @author: Jun
 * @date: 11:22 2021/5/16
 */
public interface MomentDao extends BaseDao{

    /**
     * @Description: 列出某用户下所有朋友圈
     * @param userId 用户id
     * @date: 14:14 2021/5/19
     * @return: java.util.LinkedList<com.melo.wechat.model.entity.Moment>
     */
    LinkedList<Moment> getMomentByUserId(Integer userId);

    /**
     * @Description: 根据id找到朋友圈记录
     * @param momentId 朋友圈id
     * @date: 14:14 2021/5/19
     * @return: java.util.LinkedList<com.melo.wechat.model.entity.Moment>
     */
    Moment getMomentById(Integer momentId);

    void increaseLike(Integer momentId);

    void decreaseLike(Integer momentId);
}
