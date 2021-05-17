
package com.melo.wechat.utils;

import java.util.UUID;

/**
 * @author Jun
 * @program WeChat
 * @description 用于提供一个uuid，并去除其中的横线
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
}
