package com.melo.wechat.controller;

import com.melo.wechat.controller.abs.BaseServlet;
import com.melo.wechat.model.dto.ServiceResult;
import com.melo.wechat.service.impl.MessageServiceImpl;
import com.melo.wechat.service.inter.MessageService;
import com.melo.wechat.utils.proxy.ServiceProxy;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.melo.wechat.utils.JsonUtils.sendJsonObject;

/**
 * @Description: 聊天记录Servlet
 * @author: Jun
 * @date: 18:40 2021/5/25
 */
@WebServlet("/message")
public class MessageServlet extends BaseServlet {

    /**
     * 相关操作类
     */
    private final MessageService messageService = ServiceProxy.getProxyInstance(MessageServiceImpl.class);

    /**
     * @Description: 获取聊天记录条数
     * @param request
     * @param response
     * @date: 18:39 2021/5/25
     * @return: void
     */
    public void getMessageCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer chatId = Integer.parseInt(request.getParameter("chatId"));
        Integer currentPage = Integer.parseInt(request.getParameter("currentPage"));
        ServiceResult result = messageService.listMessageByPage(chatId,currentPage);
        sendJsonObject(response, result);
    }

    /**
     * @Description: 聊天记录分页展示(前台出了问题,后台都写好了)
     * @param request
     * @param response
     * @date: 18:31 2021/5/25
     * @return: void
     */
    public void listMessageByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Integer chatId = Integer.valueOf(request.getParameter("chatId"));
        Integer currentPage = Integer.valueOf(request.getParameter("currentPage"));

        ServiceResult result = messageService.listMessageByPage(chatId,currentPage);
        sendJsonObject(response, result);
    }
}
