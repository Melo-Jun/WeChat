package com.melo.wechat.controller;

import com.melo.wechat.controller.abs.BaseServlet;
import com.melo.wechat.model.dto.ServiceResult;
import com.melo.wechat.model.entity.Emoji;
import com.melo.wechat.service.impl.EmojiServiceImpl;
import com.melo.wechat.service.inter.EmojiService;
import com.melo.wechat.utils.proxy.ServiceProxy;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.melo.wechat.utils.BeanUtils.parameter2Object;
import static com.melo.wechat.utils.JsonUtils.sendJsonObject;

/**
 * @Description: 表情包Servlet
 * @author: Jun
 * @date: 16:48 2021/5/23
 */
@WebServlet("/emoji")
public class EmojiServlet extends BaseServlet {

    /**
     * 相关操作类
     */
    private final EmojiService emojiService = ServiceProxy.getProxyInstance(EmojiServiceImpl.class);

    /**
     * @Description: 加载用户的个人表情包
     * @param request
     * @param response
     * @date: 11:48 2021/5/25
     * @return: void
     */
    public void loadEmoji(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer userId=Integer.parseInt(request.getParameter("userId"));
        ServiceResult result = emojiService.loadEmoji(userId);
        sendJsonObject(response, result);
    }

    /**
     * @Description: 上传表情包
     * @param request
     * @param response
     * @date: 11:48 2021/5/25
     * @return: void
     */
    public void uploadEmoji(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Emoji emoji = parameter2Object(request.getParameterMap(), Emoji.class);
        ServiceResult result = emojiService.uploadEmoji(emoji);
        sendJsonObject(response, result);
    }
}
