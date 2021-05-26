package com.melo.wechat.utils;

import com.alibaba.fastjson.JSON;
import com.melo.wechat.model.dto.ServiceResult;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: 将结果集包装成Json
 * @author: Jun
 * @date: 15:33 2021/5/26
 */
public class JsonUtils {

   /**
    * @Description: 将ServiceResult包装为Json对象返回给前台
    * @param resp 请求
    * @param serviceResult 返回结果封装类
    * @date: 15:32 2021/5/26
    * @return: void
    */
    public static void sendJsonObject(HttpServletResponse resp, ServiceResult serviceResult) throws IOException {
        JSON json = (JSON) JSON.toJSON(serviceResult);
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(json.toJSONString());
    }

}
