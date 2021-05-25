package com.melo.wechat.utils;

import java.sql.Connection;
import java.sql.SQLException;


/**
 * @Description: 管理事务
 * @author: Jun
 * @notice 异常统一在代理类中处理
 * @date: 18:51 2021/5/25
 */
public class ConnectionManager {

    private ConnectionManager() {

    }
    /**
     * 线程连接池
     */
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();

    /**
     * 获取连接池中的连接，并设置该连接为线程共享
     *
     * @return 返回connection对象
     */
    public static Connection getConnection() {
        return ConnectionPool.getThreadConnection();
    }


    /**
     * 设置事务手动提交，事务开始
     *
     * @param conn connection对象
     */
    public static void beginTransaction(Connection conn) throws SQLException {
            if (conn != null) {
                if (conn.getAutoCommit()) {
                    conn.setAutoCommit(false);
                }
            }
    }


    /**
     * 事务正常运行，提交事务
     * @param conn connection对象
     */
    public static void commitTransaction(Connection conn) throws SQLException {
            if (conn != null) {
                if (!conn.getAutoCommit()) {
                    conn.commit();
                }
            }
    }

    /**
     * 设置Connection为原始状态
     *
     * @param conn connection对象
     */
    public static void recoverTransaction(Connection conn) {
        try {
            if (conn != null) {
                conn.setAutoCommit(!conn.getAutoCommit());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发生异常回滚事务
     *
     * @param conn connection对象
     */
    public static void rollback(Connection conn) {
        try {
            if (conn != null) {
                conn.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭连接,并将其从当前线程删除
     */
    public static void closeThreadConn() {
        Connection conn = threadLocal.get();
        if (conn != null) {
            ConnectionPool.freeConnection(conn);
            threadLocal.remove();
        }
    }
}