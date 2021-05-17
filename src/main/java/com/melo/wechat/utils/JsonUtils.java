package com.melo.wechat.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.melo.wechat.model.dto.ServiceResult;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/***
 * @Description: 实现json和javabean的互相转化的工具类
 * @author: Jun
 * @date: 10:18 2021/4/27
 */
public class JsonUtils {

    /**
     * 用户将服务结果转换成json数据并返回客户端
     *
     * @param resp   响应
     * @param serviceResult 服务结果
     * @author Jun
     * @date 2021-4-27
     */
    public static void sendJsonObject(HttpServletResponse resp, ServiceResult serviceResult) throws IOException {
        JSON json = (JSON) JSON.toJSON(serviceResult);
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(json.toJSONString());
    }

    /**
     * 将输入流中的json数据转化成java对象
     * @param inputStream 输入json数据的输入流
     * @author Jun
     */
    public static Object jsonToJavaObject(InputStream inputStream, Class<?> targetClass) {
        JSONObject jsonObject = null;
        try {
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            StringBuilder builder = new StringBuilder();
            String jsonData;
            while ((jsonData = streamReader.readLine()) != null) {
                builder.append(jsonData);
            }
            jsonObject = JSONObject.parseObject(builder.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSONObject.toJavaObject(jsonObject, targetClass);
    }

}
