package com.melo.wechat.utils.log;

import java.io.*;
import java.util.logging.Handler;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * @Description: 日志info工具类
 * @author: Jun
 * @date: 19:00 2021/5/25
 */
public class LogInfoUtil {
    /**
     * 配置文件路径
     */
    private static String PROP_PATH = "logInfo.properties";


    public static Logger getLogger(String name) {
        Logger logger = Logger.getLogger(name);
        for (Handler h : logger.getHandlers()) {
            h.close();
        }
        LogManager logManager = LogManager.getLogManager();
        InputStream in = null;
        try {
            in = Thread.currentThread().getContextClassLoader().getResourceAsStream(PROP_PATH);
            logManager.readConfiguration(in);
            logManager.addLogger(logger);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            for (Handler h : logger.getHandlers()) {
                h.close();
            }
        }
        return logger;
    }
}

