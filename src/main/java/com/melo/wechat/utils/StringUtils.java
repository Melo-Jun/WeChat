package com.melo.wechat.utils;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Jun
 * @program WeChat
 * @description 用于字符串的转换以及字符的校验等
 * @date 2021-4-24
 */
public class StringUtils {

    /**
     *  定义script的正则表达式
     */
    private static final String REG_EX_SCRIPT = "<script[^>]*?>[\\s\\S]*?<\\/script>";
    /**
     * 定义style的正则表达式
     */
    private static final String REG_EX_STYLE = "<style[^>]*?>[\\s\\S]*?<\\/style>";
    /**
     * 定义HTML标签的正则表达式
     */
    private static final String REG_EX_HTML = "<[^>]+>";

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
     * @Description: 去除输入中的注入标签，预防XSS攻击
     * @param htmlStr 前台内容
     * @date: 0:04 2021/5/22
     * @return: java.lang.String
     */
    public static String delHtmlTag(String htmlStr) {

        if(htmlStr!=null) {
                // 过滤script标签
                Pattern p_script = Pattern.compile(REG_EX_SCRIPT, Pattern.CASE_INSENSITIVE);
                Matcher m_script = p_script.matcher(htmlStr);
                htmlStr = m_script.replaceAll("*");

                // 过滤style标签
                Pattern p_style = Pattern.compile(REG_EX_STYLE, Pattern.CASE_INSENSITIVE);
                Matcher m_style = p_style.matcher(htmlStr);
                htmlStr = m_style.replaceAll("*");

                // 过滤html标签
                Pattern p_html = Pattern.compile(REG_EX_HTML, Pattern.CASE_INSENSITIVE);
                Matcher m_html = p_html.matcher(htmlStr);
                htmlStr = m_html.replaceAll("*");

                // 返回文本字符串
                return htmlStr.trim();
        }
        return null;
    };


    /**
     * @Description: 过滤script标签(用于图片和表情包文件等)
     * @param htmlStr
     * @date: 15:34 2021/5/26
     * @return: java.lang.String
     */
    public static String delScriptTag(String htmlStr) {
        if(htmlStr!=null) {
            // 过滤script标签
            Pattern p_script = Pattern.compile(REG_EX_SCRIPT, Pattern.CASE_INSENSITIVE);
            Matcher m_script = p_script.matcher(htmlStr);
            htmlStr = m_script.replaceAll("*");

            return htmlStr;
        }
        return null;
    }


}
