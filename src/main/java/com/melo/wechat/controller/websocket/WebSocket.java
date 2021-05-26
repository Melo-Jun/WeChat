package com.melo.wechat.controller.websocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.melo.wechat.dao.impl.UserChatDaoImpl;
import com.melo.wechat.dao.inter.UserChatDao;
import com.melo.wechat.model.entity.*;
import com.melo.wechat.service.impl.MessageServiceImpl;
import com.melo.wechat.service.inter.MessageService;
import com.melo.wechat.utils.proxy.ServiceProxy;
import com.melo.wechat.utils.StringUtils;
import com.melo.wechat.utils.log.LogInfoUtil;
import com.melo.wechat.utils.proxy.DaoProxy;


import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;
import java.util.logging.Logger;

import static com.melo.wechat.utils.StringUtils.*;


/**
 * @Description: WebSocket服务器
 * @author: Jun
 * @date: 13:57 2021/5/25
 */
@ServerEndpoint("/server/chat/{user_id}")
public class WebSocket {

    /**
     * 相关操作类对象
     */
    private static final MessageService messageService = ServiceProxy.getProxyInstance(MessageServiceImpl.class);
    private static final UserChatDao userChatDao = DaoProxy.getProxyInstance(UserChatDaoImpl.class);

    /**
     * 在线用户map
     */
    public static final ConcurrentHashMap<Integer, WebSocket> SERVER_MAP = new ConcurrentHashMap<>();

    /**
     * 用户的唯一标识
     */
    private Session session;


    @OnOpen
    public void onOpen(Session session, @PathParam("user_id") Integer userId) throws IOException {
        WebSocket server = new WebSocket();
        //装载用户数据，作为缓存
        server.session = session;
        //将用户id对应的对象装载到Map容器
        SERVER_MAP.put(userId, server);
        Logger logger = LogInfoUtil.getLogger(WebSocket.class.getName());
    }


    /**
     * @param msg     来自客户端的消息
     * @param session 来源的session
     * @param userId  客户端的userId
     * @Description: 当服务器接收到来自客户端的消息执行的操作
     * @date: 17:32 2021/5/5
     * @return: void
     */
    @OnMessage
    public static void onMessage(String msg, Session session, @PathParam("user_id") Integer userId) throws IOException {
        //从容器中获取server
        WebSocket server = SERVER_MAP.get(userId);
        Message message=JSON.toJavaObject(JSON.parseObject(msg),Message.class);
        //过滤消息内容,表情包或文件则只过滤js标签
        if(message.getType()!=null) {
            if ("photo".equals(message.getType())|| "file".equals(message.getType())) {
                message.setContent(StringUtils.delScriptTag(message.getContent()));
            }
        }
        else {
            message.setContent(delHtmlTag(message.getContent()));
        }
        //发送消息给相应chatId下的所有用户
        sendMessage(message, userId);
        //将聊天记录插入数据库
        messageService.insertMessage(message);
        Logger logger = LogInfoUtil.getLogger(WebSocket.class.getName());
        logger.info("收到新消息-->"+message.getContent());
    }


    @OnClose
    public void onClose(@PathParam("user_id") Integer userId) {
        SERVER_MAP.remove(userId);
    }

    @OnError
    public void onError(Throwable error) {
        error.printStackTrace();
    }


    /**
     * @param notice     通知
     * @param receiverId 接受者id
     * @Description: 发送通知
     * @date: 14:01 2021/5/6
     * @return: boolean
     */
    public static boolean sendNotice(Notice notice, Integer receiverId) {
        //从容器中获取server
        WebSocket server = SERVER_MAP.get(receiverId);
        //发送给对应用户
        if (server != null && server.session != null && server.session.isOpen()) {
            String noticeJson = JSONObject.toJSONString(notice);
            try {
                server.session.getBasicRemote().sendText(noticeJson);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    /**
     * 向与用户处于同一个聊天中的成员包括用户自己发送一条消息
     *
     * @param message 消息
     * @param userId  发送者id
     * @name sendMessage
     */
     public static void sendMessage(Message message, Integer userId) throws IOException {
        //从容器中获取server
        WebSocket server = SERVER_MAP.get(userId);
        String jsonString = JSONObject.toJSONString(message);
        /*
        根据chatId获取该chat下所有用户,如果对方在线的话,遍历发送消息
         */
         Integer chatId=message.getChatId();
         LinkedList<Integer> receiverIds = userChatDao.listUserIdByChatId(chatId);
         for(Integer receiverId:receiverIds){
             //获取接收者server
            WebSocket receiver = SERVER_MAP.get(receiverId);
            //当该成员处于消息对应的聊天中,并且在线时时给他发送消息
            if (receiver != null && receiver.session != null && receiver.session.isOpen()) {
                try {
                    synchronized (receiver.session) {
                        receiver.session.getBasicRemote().sendText(jsonString);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


