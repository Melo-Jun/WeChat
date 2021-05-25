
package com.melo.wechat.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author Jun
 * @program WeChat
 * @description 用于提供一个uuid或唯一的群号,微信号等
 * @date 2021-4-29
 */
public class UUIDUtils {
    /**
     * 获取一个UUID
     * @return String UUID
     */
    public static String getUUID(){
        String uuid = UUID.randomUUID().toString();
        return uuid.replace("-","");
    }
    /**
     * @Description: 综合uuid+时间戳毫秒+随机数获取number
     * @date: 8:35 2021/5/18
     * @return: java.lang.String
     */
    public static String getUniqueNumber(){
        String uuid = getUUID().substring(0, 4);
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("ssSSS");
        String dateString = formatter.format(currentTime);
        dateString += (int)(10*(Math.random()));
        return uuid+dateString;
    }
}
