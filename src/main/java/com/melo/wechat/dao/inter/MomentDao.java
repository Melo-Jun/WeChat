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

    /**
     * @Description: 新增点赞记录
     * @param momentId
     * @date: 0:05 2021/5/26
     * @return: void
     */
    void increaseLike(Integer momentId);

    /**
     * @Description: 删除点赞记录
     * @param momentId
     * @date: 0:05 2021/5/26
     * @return: void
     */
    void decreaseLike(Integer momentId);
}
