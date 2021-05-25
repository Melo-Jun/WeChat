package com.melo.wechat.controller;

import com.melo.wechat.controller.abs.BaseServlet;
import com.melo.wechat.model.dto.ServiceResult;
import com.melo.wechat.model.entity.LikeList;
import com.melo.wechat.model.entity.Moment;
import com.melo.wechat.model.entity.Remark;
import com.melo.wechat.service.impl.LikeListServiceImpl;
import com.melo.wechat.service.impl.MomentServiceImpl;
import com.melo.wechat.service.impl.RemarkServiceImpl;
import com.melo.wechat.service.inter.LikeListService;
import com.melo.wechat.service.inter.MomentService;
import com.melo.wechat.service.inter.RemarkService;
import com.melo.wechat.utils.proxy.ServiceProxy;
import com.melo.wechat.utils.proxy.TrancationProxy;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.melo.wechat.utils.BeanUtils.parameter2Object;
import static com.melo.wechat.utils.JsonUtils.sendJsonObject;

/**
 * @Description: 朋友圈业务Servlet
 * @author: Jun
 * @date: 12:10 2021/5/16
 */
@WebServlet("/moment")
public class MomentServlet extends BaseServlet {

    /**
     * 相关操作类
     */
    private final MomentService momentService = ServiceProxy.getProxyInstance(MomentServiceImpl.class);
    private final RemarkService remarkService = ServiceProxy.getProxyInstance(RemarkServiceImpl.class);
    private final LikeListService likeListService = ServiceProxy.getProxyInstance(LikeListServiceImpl.class);

    /**
     * @param request
     * @param response
     * @Description: 展示单一好友朋友圈
     * @date: 14:38 2021/5/18
     * @return: void
     */
    public void showMoment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer userId = Integer.parseInt(request.getParameter("userId"));
        ServiceResult result = momentService.showMoment(userId);
        sendJsonObject(response, result);
    }

    /**
     * @Description: 发布朋友圈
     * @param request
     * @param response
     * @date: 11:49 2021/5/25
     * @return: void
     */
    public void newMoment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Moment moment = parameter2Object(request.getParameterMap(), Moment.class);
        ServiceResult result = momentService.newMoment(moment);
        sendJsonObject(response, result);
    }

    /**
     * @Description: 展示朋友圈评论
     * @author: Jun
     * @date: 14:38 2021/5/18
     */
    public void showRemark(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer momentId = Integer.parseInt(request.getParameter("momentId"));
        ServiceResult result = remarkService.showRemark(momentId);
        sendJsonObject(response, result);
    }

    /**
     * @Description: 发布新评论
     * @param request
     * @param response
     * @date: 23:43 2021/5/19
     * @return: void
     */
    public void newRemark(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Remark remark = parameter2Object(request.getParameterMap(), Remark.class);
        ServiceResult result= remarkService.newRemark(remark);
        sendJsonObject(response,result);
    }

    /**
     * @Description: 展示点赞过的用户
     * @author: Jun
     * @date: 14:39 2021/5/18
     */
    public void showLikeUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer momentId = Integer.parseInt(request.getParameter("momentId"));
        ServiceResult result = likeListService.showLikeUser(momentId);
        sendJsonObject(response, result);
    }

    /**
     * @Description: 判断是否点过赞
     * @author: Jun
     * @date: 14:39 2021/5/18
     */
    public void everLike(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LikeList likeList = parameter2Object(request.getParameterMap(), LikeList.class);
        ServiceResult result = new ServiceResult(likeListService.everLike(likeList));
        sendJsonObject(response,result);
    }


    /**
     * @Description: 点赞
     * @author: Jun
     * @date: 14:39 2021/5/18
     */
    public void like(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LikeList likeList = parameter2Object(request.getParameterMap(), LikeList.class);
        ServiceResult result = likeListService.like(likeList);
        sendJsonObject(response,result);
    }

    /**
     * @Description: 取消点赞
     * @author: Jun
     * @date: 14:39 2021/5/18
     */
    public void unLike(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LikeList likeList = parameter2Object(request.getParameterMap(), LikeList.class);
        ServiceResult result = likeListService.unLike(likeList);
        sendJsonObject(response,result);
    }

    /**
     * @Description: 删除朋友圈
     * @param request
     * @param response
     * @date: 11:50 2021/5/25
     * @return: void
     */
    public void deleteMoment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer momentId = Integer.parseInt(request.getParameter("momentId"));
        ServiceResult result = momentService.deleteMoment(momentId);
        sendJsonObject(response, result);
    }

}
