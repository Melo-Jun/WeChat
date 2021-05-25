package com.melo.wechat.dao.impl;

import com.melo.wechat.annotation.Column;
import com.melo.wechat.annotation.Table;
import com.melo.wechat.annotation.log.LogError;
import com.melo.wechat.controller.websocket.WebSocket;
import com.melo.wechat.dao.inter.BaseDao;
import com.melo.wechat.dao.inter.ResultMapper;
import com.melo.wechat.exception.DaoException;
import com.melo.wechat.utils.ConnectionManager;
import com.melo.wechat.utils.log.LogErrorUtil;
import com.melo.wechat.utils.log.LogInfoUtil;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.logging.Logger;

import static com.melo.wechat.utils.ConnectionPool.*;
import static com.melo.wechat.utils.JdbcUtils.*;
import static com.melo.wechat.utils.ReflectUtils.getFields;
import static com.melo.wechat.utils.ReflectUtils.getMethods;
import static com.melo.wechat.utils.StringUtils.*;


/**
 * @author Jun
 * @program WeChat
 * @description 数据库通用操作实现类
 * @date 2021-4-24
 */
public class BaseDaoImpl implements BaseDao {

    /**
     * 封装数据库更新操作
     *
     * @param obj 对象
     * @param sql sql语句
     * @return int 影响的行数
     */
    @Override
    public int executeUpdate(Object obj,String sql) {
        //影响的行数
        int count = 0;
        Connection conn=getLocalConnection();
        //判断线程上是否有绑定连接(需要事务处理),没有则直接从连接池中拿即可(无需事务处理)
        if(conn==null){
             conn = getConnection();
        }
        PreparedStatement ps = null;
        try {
            assert conn != null;
            ps = conn.prepareStatement(sql);
            //注入Sql填充参数
            setParams(ps, obj);
            count = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if(conn!=getLocalConnection()) {
                freeConnection(conn);
                close(ps, null);
            }
        }
        return count;
    }

    /**
     * 执行一条查询语句,并对结果集进行封装
     *
     * @param obj          对象
     * @param sql          sql语句
     * @param resultMapper 实现不同功能映射的实现类
     * @return 映射结果
     */
    @Override
    public <T> T executeQuery(Object obj, String sql, ResultMapper<T> resultMapper) {
        Connection conn = getConnection();
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            assert conn != null;
            ps = conn.prepareStatement(sql);
            //根据obj注入Sql填充参数
            if (obj != null) {
                setParams(ps, obj);
            }
            System.out.println(sql);
            rs = ps.executeQuery();
            return  resultMapper.doMap(rs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            Logger logger = LogErrorUtil.getLogger(WebSocket.class.getName());
            logger.severe("预编译查询语句异常：");
            throw new DaoException("预编译查询语句异常：");
        } finally {
            freeConnection(conn);
            close(ps, rs);
        }
    }


    /**
     * 增加一条记录进入数据库
     *
     * @param obj 与增加有关的对象
     * @return int 影响的数据库行数
     */
    @Override
    public int insert(Object obj) {
        LinkedList<Object> fieldNames = new LinkedList<>();
        LinkedList<Object> fieldValues = new LinkedList<>();
        fieldMapper(obj, fieldNames, fieldValues);
        /*
          根据属性名生成预编译sql插入语句
         */
        StringBuilder sql = new StringBuilder("insert into " + obj.getClass().getAnnotation(Table.class).name() + " (");
        for (Object name : fieldNames) {
            sql.append(name.toString()).append(",");
        }
        //将最后一个","改为")"，省去判断是否为最后一个")"
        sql.setCharAt(sql.length() - 1, ')');
        sql.append(" values (");
        for (int i = 0; i < fieldNames.size(); i++) {
            sql.append("?,");
        }
        sql.setCharAt(sql.length() - 1, ')');
        return executeUpdate(obj, sql.toString());
    }

    /**
     * 删除记录
     *
     * @param obj 与删除有关的对象
     * @return int 影响的数据库记录数
     */
    @Override
    public int delete(Object obj) {
        StringBuilder sql =  new StringBuilder("delete from "+obj.getClass().getAnnotation(Table.class).name()+" ");
        appendWhereToSql(sql,obj,"AND");
        /*
          完成sql注入和执行
         */
        return executeUpdate(obj, sql.toString());
    }

    /**
     * 更新记录
     * @param obj 与更新有关的对象
     * @return int 影响的数据库记录数
     */
    @Override
    public int update(Object obj) {
        /*
          根据更新依据的字段名构造对象，取出对应数据库字段名和值
         */
        LinkedList<Object> fieldNames = new LinkedList<>();
        LinkedList<Object> fieldValues = new LinkedList<>();
        fieldMapper(obj, fieldNames, fieldValues);
        StringBuilder sql = new StringBuilder("update " + obj.getClass().getAnnotation(Table.class).name() + " set ");
        for(Object fieldName:fieldNames) {
            //先过滤id,id要留到最后作为根据
            if(!"id".equals(fieldName.toString())) {
                sql.append(fieldName).append("=?, ");
            }
        }
        //删除最后一个逗号
        sql.deleteCharAt(sql.length()-2);
        sql.append("where id=?");
        return executeUpdate(obj, sql.toString());
    }

    /**
     * 查找记录封装成Map键值对
     *
     * @param sql 特定查询语句
     * @param obj 根据的对象(用来填充参数)
     * @return HashMap 结果集封装Map
     */
    @Override
    public HashMap<String,String> queryMap(String sql, Object obj) {
        return  executeQuery(obj, sql, rs -> {
            HashMap<String,String> resultMap = new HashMap<>();
            try {
                while (rs.next()) {
                    resultMap.put(rs.getString(1), rs.getString(2));
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return resultMap;
        });
    }

    /**
     * 查找记录(只查找单一值)
     *
     * @param sql 查询语句
     * @param obj 用以填充的属性值
     * @return LinkedList 结果集封装LinkedList
     */
    @Override
    public <T> LinkedList<T> queryList(String sql, Object obj) {
            return  executeQuery(obj, sql, rs -> {
                LinkedList<T> resultList = new LinkedList<>();
                try {
                    while (rs.next()) {
                        resultList.add((T)rs.getObject(1));
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                return resultList;
            });
        }


    /**
     * 查找所有属性
     * @param sql 查询语句
     * @param obj 用以填充的语句
     * @param clazz 相关类名(决定映射为什么对象)
     * @return LinkedList  封装后的对象集合
     */
    @Override
    public <T> LinkedList<T> queryAll(String sql, Object obj,Class<?> clazz) {
        return  executeQuery(obj,sql,rs -> {
            LinkedList<T> values = new LinkedList<>();
            ResultSetMetaData rsmd = null;
            try {
                rsmd = rs.getMetaData();
                LinkedList<Method> methods = getMethods(clazz.newInstance());
                //存储set方法
                LinkedList<Method> setMethods = new LinkedList<>();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    /*
                      将列名转化为实体类属性名
                     */
                    String columnName = rsmd.getColumnName(i);
                    String fieldName = toEntityField(columnName);
                    /*
                      获取与列名有关的set方法,且会一一对应保证顺序
                     */
                    for (Method method : methods) {
                        if (method.getName().startsWith("set") && method.getName().substring(3).equalsIgnoreCase(fieldName)) {
                            setMethods.add(method);
                        }
                    }
                }
                /*
                  调用invoke执行set方法,映射成对象加入链表中
                 */
                while (rs.next()) {
                    T newInstance = (T) clazz.newInstance();
                    for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                         if(rsmd.getColumnName(i).equals("gmt_create")||rsmd.getColumnName(i).equals("gmt_modified")){
                             setMethods.get(i-1).invoke(newInstance,rs.getTimestamp(i));
                         }
                        else {
                            setMethods.get(i - 1).invoke(newInstance, rs.getObject(i));
                        }
                    }
                    values.add(newInstance);
                }
            } catch (SQLException | InstantiationException | IllegalAccessException | InvocationTargetException throwables) {
                Logger logger = LogErrorUtil.getLogger(WebSocket.class.getName());
                logger.severe("映射对象出现异常：");
                throw new DaoException("映射对象出现异常");
            }
            return values;
    });
}

    /**
     * 将对象映射成属性名和属性值
     * @param obj 对象
     * @param fieldNames 属性名
     * @param fieldValues 属性值
     * @throws DaoException 数据库类异常
     */
    @Override
    public void fieldMapper(Object obj, LinkedList<Object> fieldNames, LinkedList<Object> fieldValues) throws DaoException {
        if (obj == null) {
            return;
        }
        /*
          取出包括父类在内的所有属性
         */
        LinkedList<Field> fields = getFields(obj);
        for (Field field : fields) {
            /*
              获取get方法并invoke执行取得属性值
             */
            String methodName = "get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
            Object value = null;
            try {
                Method method = obj.getClass().getMethod(methodName);
                value = method.invoke(obj);
            } catch (Exception e) {
                e.printStackTrace();
                Logger logger = LogErrorUtil.getLogger(WebSocket.class.getName());
                logger.severe("反射调用该get方法不存在或异常->" + methodName);
                throw new DaoException("反射调用该get方法不存在或异常->" + methodName);
            }
           /*
           添加非空的属性值
            */
            if (value != null) {
                fieldValues.add(value);
                //通过该属性的注解获取数据库列名
                fieldNames.add(field.getAnnotation(Column.class).value());
            }
        }
    }

    /**
     * @Description: 拼装select语句
     * @param objs 要查询的属性值数组
     * @param obj 用以填充where子句的对象
     * @param type 决定填充and还是or
     * @date: 10:08 2021/5/3
     * @return: java.lang.StringBuilder
     */
    @Override
    public StringBuilder appendSelect(Object[] objs, Object obj,String type){
        StringBuilder sql=new StringBuilder("select ");
        if(objs!=null) {
            for (Object o : objs) {
                sql.append(o).append(",");
            }
            sql.deleteCharAt(sql.length() - 1);
        }
        sql.append(" from ").append(obj.getClass().getAnnotation(Table.class).name());
        appendWhereToSql(sql,obj,type);
        return sql;
    }

    /**
     * 填充where条件给sql语句
     * @param sql sql语句
     * @param obj 属性值对象
     * @param type 决定是and还是or
     */
    @Override
    public void appendWhereToSql(StringBuilder sql, Object obj,String type){
        /*
          将对象映射成属性和值(属性会映射为数据库字段名)
         */
        LinkedList<Object> fieldNames = new LinkedList<>();
        LinkedList<Object> fieldValues = new LinkedList<>();
        fieldMapper(obj,fieldNames,fieldValues);
        /*
          将字段名填入sql语句
          没有where条件则不添加
         */
        if(fieldValues.size()!=0) {
            sql.append(" where ");
            for (Object fieldName : fieldNames) {
                //若是AND类型
                if("AND".equals(type)) {
                    sql.append(fieldName).append(" = ? ").append(type).append(" ");
                }
                //若为OR类型(在本项目中同时也是LIKE类型)
                else {
                    sql.append(fieldName).append(" like ? ").append(type).append(" ");
                }
            }
            //删除最后一个AND/OR
            sql.delete(sql.length()-4,sql.length());
        }
    }


    /**
     * 根据xxx获取id
     * @param obj xxx
     * @return String id
     */
    @Override
    public Integer getId(Object obj) {
        StringBuilder sql = appendSelect(new Object[]{"id"}, obj, "AND");
        LinkedList<Integer> id = queryList(sql.toString(), obj);
        return id.isEmpty()?null:id.getFirst();
    }


    @LogError("你所获取到的对象为空")
    @Override
    public <T> T getObjectBy(String sql, Object obj, Class<?> clazz){
        return (T)queryAll(sql,obj,clazz).getFirst();
    }


}
