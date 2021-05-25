package com.melo.wechat.controller;

import com.melo.wechat.controller.abs.BaseServlet;
import com.melo.wechat.model.dto.ServiceResult;
import com.melo.wechat.model.entity.Chat;
import com.melo.wechat.model.entity.Notice;
import com.melo.wechat.model.entity.UserChat;
import com.melo.wechat.service.impl.ChatServiceImpl;
import com.melo.wechat.service.impl.MessageServiceImpl;
import com.melo.wechat.service.inter.ChatService;
import com.melo.wechat.service.inter.MessageService;
import com.melo.wechat.utils.proxy.ServiceProxy;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.melo.wechat.utils.BeanUtils.parameter2Object;
import static com.melo.wechat.utils.JsonUtils.sendJsonObject;

/**
 * @Description: 聊天业务Servlet
 * @author: Jun
 * @date: 10:59 2021/5/9
 */
@WebServlet("/chat")
public class ChatServlet extends BaseServlet {

    /**
     * 相关操作类
     */
    private final ChatService chatService = ServiceProxy.getProxyInstance(ChatServiceImpl.class);
    private final MessageService messageService = ServiceProxy.getProxyInstance(MessageServiceImpl.class);

    /**
     * @Description: 加载近期聊天记录
     * @param request
     * @param response
     * @date: 19:49 2021/5/19
     * @return: void
     */
    public void loadMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer chatId = Integer.parseInt(request.getParameter("chatId"));
        ServiceResult result = messageService.getMessageByChatId(chatId);
        sendJsonObject(response, result);
    }

    /**
     * @Description: 加载群聊列表
     * @param request
     * @param response
     * @date: 19:49 2021/5/19
     * @return: void
     */
    public void loadGroup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer userId = Integer.parseInt(request.getParameter("userId"));
        ServiceResult result = chatService.loadGroup(userId);
        sendJsonObject(response, result);
    }

    /**
     * @Description: 搜索群聊
     * @param request
     * @param response
     * @date: 20:49 2021/5/21
     * @return: void
     */
    public void searchGroup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String number = request.getParameter("number");
        ServiceResult result = chatService.searchGroup(number);
        sendJsonObject(response, result);
    }

    /**
     * @Description: 判断是否在群聊中
     * @param request
     * @param response
     * @date: 17:06 2021/5/24
     * @return: void
     */
    public void isAtGroup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserChat userChat = parameter2Object(request.getParameterMap(), UserChat.class);
        ServiceResult result = chatService.isAtGroup(userChat);
        sendJsonObject(response, result);
    }

    /**
     * @Description: 判断是否被禁言
     * @param request
     * @param response
     * @date: 17:06 2021/5/24
     * @return: void
     */
    public void isBlocked(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserChat userChat = parameter2Object(request.getParameterMap(), UserChat.class);
        ServiceResult result = chatService.isBlocked(userChat);
        sendJsonObject(response, result);
    }

    /**
     * @Description: 禁言群成员
     * @param request
     * @param response
     * @date: 17:07 2021/5/24
     * @return: void
     */
    public void blockMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserChat userChat = parameter2Object(request.getParameterMap(), UserChat.class);
        ServiceResult result = chatService.blockMember(userChat);
        sendJsonObject(response, result);
    }

    /**
     * @Description: 取消禁言
     * @param request
     * @param response
     * @date: 17:07 2021/5/24
     * @return: void
     */
    public void unBlockMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserChat userChat = parameter2Object(request.getParameterMap(), UserChat.class);
        ServiceResult result = chatService.unBlockMember(userChat);
        sendJsonObject(response, result);
    }

    /**
     * @Description: 删除群成员
     * @param request
     * @param response
     * @date: 17:07 2021/5/24
     * @return: void
     */
    public void deleteMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserChat userChat = parameter2Object(request.getParameterMap(), UserChat.class);
        ServiceResult result = chatService.deleteMember(userChat);
        sendJsonObject(response, result);
    }

    /**
     * @Description: 解散群聊
     * @param request
     * @param response
     * @date: 17:07 2021/5/24
     * @return: void
     */
    public void deleteGroup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Chat chat = parameter2Object(request.getParameterMap(), Chat.class);
        ServiceResult result = chatService.deleteGroup(chat);
        sendJsonObject(response, result);
    }

    /**
     * @Description: 退出群聊
     * @param request
     * @param response
     * @date: 17:07 2021/5/24
     * @return: void
     */
    public void exitGroup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserChat userChat = parameter2Object(request.getParameterMap(), UserChat.class);
        ServiceResult result = chatService.exitGroup(userChat);
        sendJsonObject(response, result);
    }

    /**
     * @Description: 申请加入群聊
     * @param request
     * @param response
     * @date: 20:49 2021/5/21
     * @return: void
     */
    public void addGroup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserChat userChat = parameter2Object(request.getParameterMap(), UserChat.class);
        Notice notice = parameter2Object(request.getParameterMap(), Notice.class);
        ServiceResult result = chatService.addGroup(userChat,notice);
        sendJsonObject(response, result);
    }

    /**
     * @Description: 同意加入群聊
     * @param request
     * @param response
     * @date: 17:07 2021/5/24
     * @return: void
     */
    public void receiveAddGroup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserChat userChat = parameter2Object(request.getParameterMap(), UserChat.class);
        ServiceResult result = chatService.receiveAddGroup(userChat);
        sendJsonObject(response, result);
    }

    /**
     * @Description: 加载群聊聊天窗口
     * @param request
     * @param response
     * @date: 19:49 2021/5/19
     * @return: void
     */
    public void showGroupChat(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer chatId = Integer.parseInt(request.getParameter("chatId"));
        ServiceResult result = chatService.showGroupChat(chatId);
        sendJsonObject(response, result);
    }

    /**
     * @Description: 展示聊天窗口成员信息
     * @param request
     * @param response
     * @date: 17:07 2021/5/24
     * @return: void
     */
    public void showMemberInform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserChat userChat = parameter2Object(request.getParameterMap(), UserChat.class);
        ServiceResult result = chatService.showMemberInform(userChat);
        sendJsonObject(response, result);
    }

    /**
     * @Description: 创建群聊
     * @param request
     * @param response
     * @date: 14:25 2021/5/21
     * @return: void
     */
    public void newGroup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userList = request.getParameter("userList");
        Integer userId = Integer.parseInt(request.getParameter("userId"));
        String[] userLists = userList.split(",");
        ServiceResult result=chatService.newGroup(userId,request.getParameter("name"),userLists);
        sendJsonObject(response,result);
    }

    /**
     * @Description: 邀请新成员
     * @param request
     * @param response
     * @date: 14:25 2021/5/21
     * @return: void
     */
    public void inviteMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userList = request.getParameter("userList");
        Integer chatId = Integer.parseInt(request.getParameter("chatId"));
        String[] userLists = userList.split(",");
        ServiceResult result=chatService.inviteMember(chatId,userLists);
        sendJsonObject(response,result);
    }

    /**
     * @Description: 展示群聊的资料信息
     * @param request
     * @param response
     * @date: 14:25 2021/5/21
     * @return: void
     */
    public void showChatInform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer chatId = Integer.parseInt(request.getParameter("chatId"));
        ServiceResult result = chatService.showGroupChat(chatId);
        sendJsonObject(response,result);
    }

    /**
     * @Description: 修改群名称
     * @param request
     * @param response
     * @date: 17:08 2021/5/24
     * @return: void
     */
    public void updateChatName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Chat chat = parameter2Object(request.getParameterMap(), Chat.class);
        ServiceResult result = chatService.updateChatName(chat);
        sendJsonObject(response,result);
    }

    /**
     * @Description: 修改群昵称
     * @param request
     * @param response
     * @date: 17:08 2021/5/24
     * @return: void
     */
    public void updateMemberName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserChat userChat = parameter2Object(request.getParameterMap(), UserChat.class);
        ServiceResult result = chatService.updateMemberName(userChat);
        sendJsonObject(response,result);
    }

    /**
     * @Description: 修改群头像
     * @param request
     * @param response
     * @date: 17:09 2021/5/24
     * @return: void
     */
    public void updateGroupAvatar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Chat chat = parameter2Object(request.getParameterMap(), Chat.class);
        ServiceResult result = chatService.updateGroupAvatar(chat);
        sendJsonObject(response,result);
    }

    /**
     * @Description: 查看群成员
     * @param request
     * @param response
     * @date: 14:26 2021/5/21
     * @return: void
     */
    public void showChatMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer chatId = Integer.parseInt(request.getParameter("chatId"));
        ServiceResult result = chatService.showChatMember(chatId);
        sendJsonObject(response,result);
    }
    
}

