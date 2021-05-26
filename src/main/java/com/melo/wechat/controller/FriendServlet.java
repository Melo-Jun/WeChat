package com.melo.wechat.controller;

import com.melo.wechat.controller.abs.BaseServlet;
import com.melo.wechat.model.dto.ServiceResult;
import com.melo.wechat.model.entity.Friend;
import com.melo.wechat.model.entity.Notice;
import com.melo.wechat.service.impl.ChatServiceImpl;
import com.melo.wechat.service.impl.FriendServiceImpl;
import com.melo.wechat.service.inter.ChatService;
import com.melo.wechat.service.inter.FriendService;
import com.melo.wechat.utils.proxy.ServiceProxy;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.melo.wechat.utils.BeanUtils.parameter2Object;
import static com.melo.wechat.utils.JsonUtils.sendJsonObject;

/***
 * @Description: 好友业务Servlet
 * @author: Jun
 * @date: 21:55 2021/5/4
 */
@WebServlet("/friend")
public class FriendServlet extends BaseServlet {

    /**
     * 相关操作类
     */
    private final FriendService friendService = ServiceProxy.getProxyInstance(FriendServiceImpl.class);
    private final ChatService chatService = ServiceProxy.getProxyInstance(ChatServiceImpl.class);

    /**
     * @param request
     * @param response
     * @Description: 添加好友
     * @date: 20:11 2021/5/8
     * @return: void
     */
    public void addFriend(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Friend friend = parameter2Object(request.getParameterMap(), Friend.class);
        System.out.println(friend.getDescription());
        ServiceResult result = friendService.addFriend(friend);
        sendJsonObject(response, result);
    }

    /**
     * @Description: 拒绝好友
     * @param request
     * @param response
     * @date: 11:49 2021/5/25
     * @return: void
     */
    public void refuseFriend(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Friend friend = parameter2Object(request.getParameterMap(), Friend.class);
        Notice notice = parameter2Object(request.getParameterMap(), Notice.class);
        ServiceResult result = friendService.refuseFriend(friend,notice);
        sendJsonObject(response, result);
    }

    /**
     * @param request
     * @param response
     * @Description: 加载好友
     * @date: 14:56 2021/5/9
     * @return: void
     */
    public void loadFriend(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
        Integer userId = Integer.parseInt(request.getParameter("userId"));
        ServiceResult result = friendService.loadFriend(userId);
        sendJsonObject(response, result);
    }

    /**
     * @Description: 展示好友聊天窗口
     * @param request
     * @param response
     * @date: 14:26 2021/5/21
     * @return: void
     */
    public void showFriendChat(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        ServiceResult result = friendService.showFriendChat(id);
        sendJsonObject(response, result);
    }

    /**
     * @param request
     * @param response
     * @Description: 显示好友个人信息
     * @date: 22:14 2021/5/13
     * @return: void
     */
    public void showFriendInform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        ServiceResult result = friendService.showFriendInform(id);
        sendJsonObject(response, result);
    }

    /**
     * @param request
     * @param response
     * @Description: 拉黑好友
     * @date: 17:41 2021/5/14
     * @return: void
     */
    public void blockFriend(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Friend friend = parameter2Object(request.getParameterMap(), Friend.class);
        ServiceResult result = friendService.blockFriend(friend);
        sendJsonObject(response, result);
    }

    /**
     * @Description: 取消拉黑好友
     * @param request
     * @param response
     * @date: 14:26 2021/5/21
     * @return: void
     */
    public void unBlockFriend(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Friend friend = parameter2Object(request.getParameterMap(), Friend.class);
        ServiceResult result = friendService.unBlockFriend(friend);
        sendJsonObject(response, result);
    }

    /**
     * @Description: 判断自己是否被好友拉黑
     * @param request
     * @param response
     * @date: 14:27 2021/5/21
     * @return: void
     */
    public void isBlocked(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Friend friend = parameter2Object(request.getParameterMap(), Friend.class);
        ServiceResult result=friendService.isBlocked(friend);
        sendJsonObject(response,result);
    }


    /**
     * @Description: 判断是否为好友
     * @param request
     * @param response
     * @date: 11:49 2021/5/25
     * @return: void
     */
    public void isFriend(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Friend friend = parameter2Object(request.getParameterMap(), Friend.class);
        System.out.println(request.getParameter("userId"));
        System.out.println(request.getParameter("friendId"));
        ServiceResult result=friendService.isFriend(friend);
        sendJsonObject(response,result);
    }

        /**
         * @Description: 更新好友备注
         * @param request
         * @param response
         * @date: 14:27 2021/5/21
         * @return: void
         */
    public void updateAlias(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Friend friend = parameter2Object(request.getParameterMap(), Friend.class);
        ServiceResult result = friendService.updateAlias(friend);
        sendJsonObject(response,result);
    }

    /**
     * @Description: 删除好友
     * @param request
     * @param response
     * @date: 14:27 2021/5/21
     * @return: void
     */
    public void deleteFriend(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer chatId= Integer.parseInt(request.getParameter("chatId"));
        ServiceResult result=chatService.deleteFriend(chatId);
    }
}
