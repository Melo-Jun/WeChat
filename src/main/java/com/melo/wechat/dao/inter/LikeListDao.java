package com.melo.wechat.dao.inter;

import com.melo.wechat.model.entity.LikeList;

import java.util.LinkedList;

/**
 * @Description: 点赞记录数据库操作接口
 * @author: Jun
 * @date: 16:05 2021/5/17
 */
public interface LikeListDao extends BaseDao{

    /**
     * @Description: 根据朋友圈id列出点赞过的用户对象
     * @param momentId
     * @date: 0:04 2021/5/26
     * @return: java.util.LinkedList<java.lang.Integer>
     */
    LinkedList<Integer> listUserIdByMomentId(Integer momentId);

    /**
     * @Description: 判断是否点赞过
     * @param likeList
     * @date: 0:04 2021/5/26
     * @return: boolean
     */
    boolean everLike(LikeList likeList);

    /**
     * @Description: 点赞
     * @param likeList
     * @date: 0:04 2021/5/26
     * @return: int
     */
    int like(LikeList likeList);

    /**
     * @Description: 取消点赞
     * @param likeList
     * @date: 0:04 2021/5/26
     * @return: int
     */
    int unLike(LikeList likeList);


}
