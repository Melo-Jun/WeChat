package com.melo.wechat.utils;

import com.melo.wechat.dao.impl.BaseDaoImpl;

import java.sql.*;
import java.util.LinkedList;

/**
 * @author Jun
 * @program WeChat
 * @description Jdbc工具类
 * @data 2021-4-24
 */
public class JdbcUtils {

    private static final BaseDaoImpl BASE_DAO =new BaseDaoImpl();

    /**
     * 释放资源
     * @param ps PreparedStatement
     * @param rs ResultSet
     */
    public static void close(PreparedStatement ps, ResultSet rs) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
    }

    /**
     * 释放资源
     * @param stmt Statement
     * @param rs ResultSet
     */
    public static void close(Statement stmt, ResultSet rs) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
    }

    /**
     * 根据实际情况配置相应的PreparedStatement参数
     * @param ps PreparedStatement
     * @param obj 传入的对象
     */
    public static void setParams(PreparedStatement ps, Object obj) {

        LinkedList<Object> fieldNames = new LinkedList<>();
        LinkedList<Object> fieldValues = new LinkedList<>();
        BASE_DAO.fieldMapper(obj, fieldNames, fieldValues);
        Object[] params = fieldValues.toArray();
        for (int i = 0; i < params.length; i++) {
            try {
                ps.setObject(i + 1, params[i]);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}

