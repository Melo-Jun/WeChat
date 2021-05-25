package com.melo.wechat.dao.inter;

import java.sql.ResultSet;

/**
 * @author Jun
 * @program WeChat
 * @see com.melo.wechat.dao.impl.BaseDaoImpl
 * @description 将结果集映射为对象
 * @date 2021-4-24
 */
public interface ResultMapper<T> {
    /**
     * 负责提供一个映射数据库查询结果集的方法
     * @param rs 需要映射的结果集
     * @return java.lang.Object
     */
     T doMap(ResultSet rs);
}
