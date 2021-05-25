package com.melo.wechat.dao.impl;

import com.melo.wechat.dao.inter.LikeListDao;
import com.melo.wechat.model.entity.LikeList;

import java.util.LinkedList;

/**
 * @Description: 点赞记录数据库操作接口
 * @author: Jun
 * @date: 16:05 2021/5/17
 */
public class LikeListDaoImpl extends BaseDaoImpl implements LikeListDao {



    /**
     * 本表对应所有字段
     */
    private final Object[] ALL_FIELD_NAME=new Object[]{"id","user_id","moment_id","gmt_create","gmt_modified"};

    @Override
    public LinkedList<Integer> listUserIdByMomentId(Integer momentId){
        LikeList likeList = new LikeList();
        likeList.setMomentId(momentId);
        StringBuilder sql = appendSelect(new Object[]{"user_id"}, likeList, "AND");
        return queryList(sql.toString(), likeList);
    }

    /**
     * 判断该用户是否点赞过
     * @param likeList 点赞记录对象
     * @return boolean 是否点赞过
     */
    @Override
    public boolean everLike(LikeList likeList){
        StringBuilder sql = appendSelect(new Object[]{"id"}, likeList, "AND");
        System.out.println(queryList(sql.toString(),likeList).getFirst());
        return !queryList(sql.toString(),likeList).isEmpty();
    }

    @Override
    public int like(LikeList likeList) {
        return insert(likeList);
    }

    @Override
    public int unLike(LikeList likeList) {
        return delete(likeList);
    }

}
