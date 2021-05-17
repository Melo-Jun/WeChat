

package com.melo.wechat.utils;


import java.text.SimpleDateFormat;

/**
 * @author Jun
 * @program WeChat
 * @description 用于字符串的转换
 * @date 2021-4-24
 */
public class StringUtils {

    /**
     * 将属性名转化为数据库字段名
     * @param fieldName 属性名
     * @return String  数据库字段名
     */
    public static String toColumnName(String fieldName){

        StringBuilder columnName=new StringBuilder(fieldName);
        for(int i=0;i<columnName.length();i++){
            if(columnName.charAt(i)>='A'&&columnName.charAt(i)<='Z'){
                columnName.insert(i,'_');
                columnName.setCharAt(i+1, (char) (columnName.charAt(i+1)+32));
            }
        }return columnName.toString();
    }

    /**
     * 将数据库字段名转化为属性名
     * @param columnName 数据库字段名
     * @return String 属性名
     */
    public static String toEntityField(String columnName){

        StringBuilder filedName=new StringBuilder(columnName);
        for(int i=0;i<filedName.length();i++){
            if(filedName.charAt(i)=='_'){
                filedName.deleteCharAt(i);
                filedName.setCharAt(i+1,(char)(filedName.charAt(i+1)-32));
            }
        }
        return filedName.toString();
    }

    /**
     * 将数据库获取到的对象转化为特定时间戳显示格式
     * @param date 对象
     * @return String 时间戳字符串
     */
    public static String convert2Date(Object date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return sdf.format(date);
    }

    /**
     * 去除输入中的不合法字符，忽略html标签
     *
     * @param str 需要被过滤的字符
     * @date 2021-4-27
     */
    public static String toLegalTextIgnoreTag(String str) {
        if (str == null || str.trim().isEmpty()) {
            return "";
        }
        String styleLabel = "<style[^>]*?>[\\s\\S]*?<\\/style>";
        String scriptLabel = "<script[^>]*?>[\\s\\S]*?<\\/script>";
        str = str.replaceAll(styleLabel, "");
        str = str.replaceAll(scriptLabel, "");
        return str;
    };


    /**
     * 去除输入中的不合法字符，防止标签注入
     *
     * @param str 需要被过滤的字符
     * @date 2021-4-27
     */
    public static String toLegalText(String str) {
        if (str == null || str.trim().isEmpty()) {
            return "";
        }
        str = toLegalTextIgnoreTag(str);
        String htmlLabel = "<[^>]+>";
        str = str.replaceAll(htmlLabel, "");
        str = str.replace("\"", "");
        str = str.replaceAll("\t|\r|\n", "");
        return str;
    }


}
