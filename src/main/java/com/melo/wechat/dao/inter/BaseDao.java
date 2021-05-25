package com.melo.wechat.dao.inter;

import com.melo.wechat.exception.DaoException;

import java.util.HashMap;
import java.util.LinkedList;


/**
 * @author Jun
 * @program WeChat
 * @description 数据库通用操作接口
 * @date 2021-4-24
 */
public interface BaseDao {

    /**
     * 封装数据库更新操作
     *
     * @param obj 对象
     * @param sql sql语句
     * @return int 影响的行数
     */
     int executeUpdate(Object obj, String sql);

    /**
     * 执行一条查询语句,并对结果集进行封装
     * @param obj 对象
     * @param sql sql语句
     * @param resultMapper 实现不同功能映射的实现类
     * @return 映射结果
     */
     <T> T executeQuery(Object obj, String sql, ResultMapper<T> resultMapper);

    /**
     * 增加一条记录进入数据库
     *
     * @param obj 与增加有关的对象
     * @return int 影响的数据库行数
     */
    int insert(Object obj);

    /**
     * 删除记录
     *
     * @param obj 与删除有关的对象
     * @return int 影响的数据库记录数
     */
    int delete(Object obj);


    /**
     * 更新记录
     * @param obj 与更新有关的对象
     * @return int 影响的数据库记录数
     */
    int update(Object obj);


    /**
     * 查找记录封装成Map键值对
     *
     * @param sql 特定查询语句
     * @param obj 根据的对象(用来填充参数)
     * @return HashMap 结果集封装Map
     */
     HashMap queryMap(String sql, Object obj);

    /**
     * 查找记录(只查找单一值)
     *
     * @param sql 查询语句
     * @param obj 用以填充的属性值
     * @return LinkedList 结果集封装LinkedList
     */
     <T> LinkedList<T> queryList(String sql,Object obj);

    /**
     * 查找所有属性
     * @param sql 查询语句
     * @param obj 用以填充的语句
     * @param clazz 相关类名(决定映射为什么对象)
     * @return LinkedList  封装后的对象集合
     */
    <T> LinkedList<T> queryAll(String sql,Object obj,Class<?> clazz);


    /**
     * 将对象映射成属性名和属性值
     * @param obj 对象
     * @param fieldNames 属性名
     * @param fieldValues 属性值
     * @throws DaoException 数据库类异常
     */
    void fieldMapper(Object obj, LinkedList<Object> fieldNames, LinkedList<Object> fieldValues) throws DaoException;

    /**
     * @Description: 拼装select语句
     * @param objs 要查询的属性值数组
     * @param obj 用以填充where子句的对象
     * @param type 决定填充and还是or
     * @date: 10:08 2021/5/3
     * @return: java.lang.StringBuilder
     */
     StringBuilder appendSelect(Object[] objs,Object obj,String type);


    /**
     * 填充where条件给sql语句
     * @param sql sql语句
     * @param obj 属性值对象
     * @param type 决定是and还是or
     */
     void appendWhereToSql(StringBuilder sql, Object obj,String type);

    /**
     * 根据xxx获取id
     * @param obj xxx
     * @return String id
     */

    Integer getId(Object obj);

    /**
     * @Description: 根据传入的临时对象的条件获取数据库中对象
     * @param sql sql语句
     * @param obj 传入的临时对象
     * @param clazz 要映射的类
     * @date: 11:52 2021/5/25
     * @return: T
     */
     <T> T getObjectBy(String sql, Object obj, Class<?> clazz);

}
