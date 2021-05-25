package com.melo.wechat.dao.impl;

import com.melo.wechat.dao.inter.RemarkDao;
import com.melo.wechat.model.entity.Remark;

import java.util.LinkedList;

/**
 * @Description: 评论数据库操作实现类
 * @author: Jun
 * @date: 19:38 2021/5/16
 */
public class RemarkDaoImpl extends BaseDaoImpl implements RemarkDao {


    /**
     * 本表对应所有字段
     */
    private final Object[] ALL_FIELD_NAME=new Object[]{"id","content","user_id","moment_id","gmt_create","gmt_modified"};

    @Override
    public LinkedList<Remark> listRemarkByMomentId(Remark remark) {
        StringBuilder sql = appendSelect(ALL_FIELD_NAME, remark, "AND");
        return queryAll(sql.toString(),remark,Remark.class);
    }
}
