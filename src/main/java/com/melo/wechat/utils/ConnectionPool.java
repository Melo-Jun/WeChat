package com.melo.wechat.utils;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Properties;

/**
 * @author Jun
 * @program WeChat
 * @description 数据库连接池实现类
 * @date 2021-4-24
 */
public class ConnectionPool {
    /**
     * 配置文件路径
     */
    private static String PROP_PATH = "jdbc.properties";
    /**
     * /**
     * 测试数据库连接的等待时长
     */
    private static int TIMEOUT;
    /**
     * 初始连接数
     */
    private static int INIT_SIZE;
    /**
     * 最大连接数
     */
    private static int MAX_SIZE;
    /**
     * 当前已经创建的连接数
     */
    private static int currentCount = 0;

    private static String url;
    private static String user;
    private static String password;
    /**
     * 数据库连接池
     */
    private static LinkedList<Connection> connPool = new LinkedList<>();
    /**
     *线程连接池
     */
    private static ThreadLocal<Connection> threadLocal =new ThreadLocal<>();

    static {
        try {
            /**
             * 加载配置文件
             */
            Properties prop = new Properties();
            prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(PROP_PATH));
            /**
             * 驱动类名称
             */
            String driver = prop.getProperty("driver");
            url = prop.getProperty("url");
            user = prop.getProperty("username");
            password = prop.getProperty("password");
            MAX_SIZE = Integer.parseInt(prop.getProperty("MAX_SIZE"));
            INIT_SIZE = Integer.parseInt(prop.getProperty("INIT_SIZE"));
            TIMEOUT = Integer.parseInt(prop.getProperty("TIMEOUT"));
            /**
             * 注册驱动
             */
            Class.forName(driver);
            /**
             * 初始化数据库连接池
             */
            initConnection();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError(e);
        }
    }

    /**
     * 创建数据库连接 (封装操作)
     *
     * @return Connection
     */
    public static synchronized Connection creatConnection() {
        currentCount++;
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    /**
     * 初始化数据库连接池
     */
    public static void initConnection() {
        for (int i = 0; i < INIT_SIZE; i++) {
            connPool.add(creatConnection());
        }
    }

    /**
     * 从数据库连接池中获取连接
     *
     * @return Connection
     */
    public static synchronized Connection getConnection()  {
        Connection conn=null;
        if (connPool.size() > 0) {
             conn = connPool.removeLast();
            try {
                if(conn.isValid(TIMEOUT)){
                    return conn;
                }else {
                    return creatConnection();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else if (currentCount < MAX_SIZE) {
            return creatConnection();
        } else {
            throw new RuntimeException("数据库连接已经到达最大值");
        }
        return conn;
    }

    /**
     * 从线程中获取连接(用于事务)
     *
     * @return Connection
     */
    public static Connection getThreadConnection()  {
        Connection conn = threadLocal.get();
        if (conn == null) {
            conn= getConnection();
            threadLocal.set(conn);
        }
        return conn;

    }

    /**
     * 释放连接，归还到数据库连接池中
     * @param coon
     */
    public static void freeConnection(Connection coon) {
        connPool.addLast(coon);
    }


}
