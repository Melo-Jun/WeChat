package com.melo.wechat.controller;

import com.melo.wechat.controller.abs.BaseServlet;
import com.melo.wechat.model.dto.ServiceResult;
import com.melo.wechat.model.entity.Notice;
import com.melo.wechat.service.impl.NoticeServiceImpl;
import com.melo.wechat.service.inter.NoticeService;
import com.melo.wechat.utils.proxy.ServiceProxy;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.melo.wechat.utils.BeanUtils.parameter2Object;
import static com.melo.wechat.utils.JsonUtils.sendJsonObject;

/**
 * @Description: 通知业务
 * @author: Jun
 * @date: 21:39 2021/5/6
 */
@WebServlet("/notice")
public class NoticeServlet extends BaseServlet {

    private final NoticeService noticeService = ServiceProxy.getProxyInstance(NoticeServiceImpl.class);

    /**
     * @param request
     * @param response
     * @Description: 加载好友通知列表
     * @date: 22:21 2021/5/20
     * @return: void
     */
    public void loadNotice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Notice notice = parameter2Object(request.getParameterMap(), Notice.class);
        ServiceResult result = noticeService.loadNotice(notice);
        sendJsonObject(response, result);
    }

    /**
     * @param request
     * @param response
     * @Description: 查看通知内容
     * @date: 22:21 2021/5/20
     * @return: void
     */
    public void showNotice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Notice notice = parameter2Object(request.getParameterMap(), Notice.class);
        ServiceResult result = noticeService.showNotice(notice);
        sendJsonObject(response, result);

    }

    /**
     * @Description: 更改消息状态
     * @param request
     * @param response
     * @date: 11:50 2021/5/25
     * @return: void
     */
    public void updateStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        ServiceResult result = noticeService.updateStatus(id);
        sendJsonObject(response, result);
    }

    /**
     * @Description: 发送通知
     * @param request
     * @param response
     * @date: 11:50 2021/5/25
     * @return: void
     */
    public void sendNotice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Notice notice = parameter2Object(request.getParameterMap(), Notice.class);
        ServiceResult result = noticeService.sendNotice(notice);
        sendJsonObject(response,result);
    }
}
