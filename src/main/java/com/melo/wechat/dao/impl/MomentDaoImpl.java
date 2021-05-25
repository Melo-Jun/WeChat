package com.melo.wechat.dao.impl;

import com.melo.wechat.dao.inter.MomentDao;
import com.melo.wechat.model.entity.Moment;

import java.util.LinkedList;

/**
 * @Description: 朋友圈数据库操作实现类
 * @author: Jun
 * @date: 11:22 2021/5/16
 */
public class MomentDaoImpl extends BaseDaoImpl implements MomentDao {

    /**
     * 本表对应所有字段
     */
    private final Object[] ALL_FIELD_NAME=new Object[]{"id","content","photo","user_id","like_count","gmt_create", "gmt_modified"};


    /**
     * @Description: 列出某用户下所有朋友圈
     * @param userId 用户id
     * @date: 14:14 2021/5/19
     * @return: java.util.LinkedList<com.melo.wechat.model.entity.Moment>
     */
    @Override
    public LinkedList<Moment> getMomentByUserId(Integer userId){
        Moment moment = new Moment();
        moment.setUserId(userId);
        StringBuilder sql = appendSelect(ALL_FIELD_NAME, moment, "AND");
        return queryAll(sql.toString(),moment,Moment.class);
    }

    /**
     * @Description: 根据id找到朋友圈记录
     * @param momentId 朋友圈id
     * @date: 14:14 2021/5/19
     * @return: java.util.LinkedList<com.melo.wechat.model.entity.Moment>
     */
    @Override
    public Moment getMomentById(Integer momentId){
        Moment moment = new Moment();
        moment.setId(momentId);
        StringBuilder sql = appendSelect(ALL_FIELD_NAME, moment, "AND");
        return getObjectBy(sql.toString(),moment,Moment.class);
    }

    @Override
    public void increaseLike(Integer momentId) {
        Moment moment = getMomentById(momentId);
        update(new Moment(momentId,moment.getLikeCount()+1));
    }

    @Override
    public void decreaseLike(Integer momentId) {
        Moment moment = getMomentById(momentId);
        update(new Moment(momentId,moment.getLikeCount()-1));
    }


}
