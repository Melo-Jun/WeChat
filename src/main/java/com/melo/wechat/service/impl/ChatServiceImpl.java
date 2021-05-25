package com.melo.wechat.service.impl;

import com.melo.wechat.constant.Status;
import com.melo.wechat.dao.impl.ChatDaoImpl;
import com.melo.wechat.dao.impl.NoticeDaoImpl;
import com.melo.wechat.dao.impl.UserChatDaoImpl;
import com.melo.wechat.dao.impl.UserDaoImpl;
import com.melo.wechat.dao.inter.ChatDao;
import com.melo.wechat.dao.inter.NoticeDao;
import com.melo.wechat.dao.inter.UserChatDao;
import com.melo.wechat.dao.inter.UserDao;
import com.melo.wechat.model.dto.ServiceResult;
import com.melo.wechat.model.entity.Chat;
import com.melo.wechat.model.entity.Notice;
import com.melo.wechat.model.entity.User;
import com.melo.wechat.model.entity.UserChat;
import com.melo.wechat.controller.websocket.WebSocket;
import com.melo.wechat.service.inter.ChatService;
import com.melo.wechat.utils.UUIDUtils;
import com.melo.wechat.utils.proxy.DaoProxy;

import java.util.LinkedList;

/**
 * @Description: 聊天业务逻辑实现类
 * @author: Jun
 * @date: 19:40 2021/5/25
 */
public class ChatServiceImpl implements ChatService {

    /**
     * 代理对象类
     */
    ChatDao chatDao = DaoProxy.getProxyInstance(ChatDaoImpl.class);
    UserChatDao userChatDao = DaoProxy.getProxyInstance(UserChatDaoImpl.class);
    UserDao userDao = DaoProxy.getProxyInstance(UserDaoImpl.class);
    NoticeDao noticeDao =  DaoProxy.getProxyInstance(NoticeDaoImpl.class);


    /**
     * @Description: 创建新的好友聊天
     * @date: 8:46 2021/5/9
     * @return: int
     */
    @Override
    public int newFriendChat() {
        Chat chat = new Chat();
        chat.setType("friend");
        return chatDao.insertChat(chat);
    }

    /**
     * @Description: 加载群聊列表
     * @param userId 用户id
     * @date: 23:10 2021/5/15
     * @return: com.melo.wechat.model.dto.ServiceResult
     */
    @Override
    public ServiceResult loadGroup(Integer userId) {
        ServiceResult result = new ServiceResult();
        /*
        获取该用户群聊类型的chatId
         */
        LinkedList<Integer> chatIds = userChatDao.listChatId(userId, "group");
        LinkedList<Chat> groups = new LinkedList<>();
        /*
        根据chatId列出群聊
         */
        for (Integer chatId : chatIds) {
            Chat chat = chatDao.getChatById(chatId);
            groups.add(chat);
        }
        result.setData(groups);
        return result;
    }

    /**
     * @Description: 根据chatId返回一个群聊对象
     * @param chatId 聊天id
     * @date: 23:11 2021/5/15
     * @return: com.melo.wechat.model.dto.ServiceResult
     */
    @Override
    public ServiceResult showGroupChat(Integer chatId) {
        Chat chat=chatDao.getChatById(chatId);
        return new ServiceResult(chat);
    }

    /**
     * @Description: 查看群成员
     * @param chatId 该群id
     * @date: 13:13 2021/5/23
     * @return: com.melo.wechat.model.dto.ServiceResult
     */
    @Override
    public ServiceResult showChatMember(Integer chatId) {
        LinkedList<UserChat> members =userChatDao.listUserChat(new UserChat(chatId));
        for(UserChat member:members){
            //初始化群成员头像和群昵称
            User user = userDao.getUserById(member.getUserId());
            member.setMemberAvatar(user.getAvatar());
            //若未设置群昵称,则保证群昵称为用户最新的用户名
            if(Status.UNSET.getMessage().equals(member.getMemberName())){
                member.setMemberName(user.getUserName());
            }
        }
        return new ServiceResult(members);
    }


    @Override
    public ServiceResult deleteFriend(Integer chatId) {
        ServiceResult result=new ServiceResult();
        Chat chat = new Chat();
        chat.setId(chatId);
        if(chatDao.delete(chat)==1){
            result.setMessage(Status.SUCCESS.getMessage());
        }else {
            result.setMessage(Status.FAILED.getMessage());
        }
        return result;
    }

    @Override
    public ServiceResult newGroup(Integer userId, String name, String[] userLists) {
        //生成群号
        String number=UUIDUtils.getUniqueNumber();
        //创建群聊chat
        chatDao.insertChat(new Chat(name,number,userId,"group"));
        //根据唯一的群号找到新生成的chatId
        Integer chatId = chatDao.getIdByNumber(number);
        //中间表新增群主记录
        userChatDao.insertUserChat(new UserChat(userId, chatId, "群主", "group"));
        //中间表新增群成员记录
        if(userLists.length>0) {
            for (String userList : userLists) {
                int userListId = Integer.parseInt(userList);
                userChatDao.insertUserChat(new UserChat(userListId, chatId, "group"));
            }
            return new ServiceResult(Status.SUCCESS.getMessage(),true);
        }
        return new ServiceResult(Status.NO_SELECTED_MEMBER.getMessage(),false);

    }

    @Override
    public ServiceResult searchGroup(String tempNumber) {
        StringBuilder number = new StringBuilder("%"+tempNumber+"%");
        Chat chat = new Chat(number.toString());
        LinkedList<Chat> chats = chatDao.showChatAll(chat);
        ServiceResult result = new ServiceResult();
        if(chats.isEmpty()){
            result.setFlag(false);
            result.setMessage(Status.NO_GROUP.getMessage());
        }else {
            result.setFlag(true);
            result.setData(chats);
        }
        return result;
    }

    @Override
    public ServiceResult addGroup(UserChat userChat, Notice notice) {
        String content=notice.getContent();
        //若还未发送过通知(或通知已读且是被拒绝),则可以再次发送通知
        if(!noticeDao.hasSend(notice)||noticeDao.hasRead(notice)){
            notice.setType("groupNotice");
            notice.setContent(content);
            //将通知存进数据库中
            noticeDao.insert(notice);
            //发送通知给对方
            WebSocket.sendNotice(notice, notice.getReceiverId());
            return new ServiceResult(Status.WAIT_AGREE.getMessage());
        }
        return new ServiceResult(Status.HAS_SEND_GROUP.getMessage());
    }

    @Override
    public ServiceResult isAtGroup(UserChat userChat) {
       if(userChatDao.isAtGroup(userChat)){
           return new ServiceResult (Status.HAD_AT_GROUP.getMessage(),true);
       }
       return new ServiceResult(false);
    }

    @Override
    public ServiceResult receiveAddGroup(UserChat userChat) {
        if( userChatDao.insert(userChat)==1){
            return new ServiceResult(Status.SUCCESS.getMessage(),true);
        }
        return new ServiceResult(Status.FAILED.getMessage(),false);
    }

    @Override
    public ServiceResult updateChatName(Chat chat) {
        if(chatDao.update(chat)==1){
            return new ServiceResult(Status.SUCCESS.getMessage(),true);
        }
        return new ServiceResult(Status.FAILED.getMessage(),false);
    }

    @Override
    public ServiceResult showMemberInform(UserChat tempUserChat) {
        User user = userDao.getUserById(tempUserChat.getUserId());
        UserChat userChat = userChatDao.getUserChat(tempUserChat);
        //为群成员设置头像和群昵称并更新
        userChat.setMemberAvatar(user.getAvatar());
        //若未设置群昵称,则默认为用户的最新用户名
        if(Status.UNSET.getMessage().equals(userChat.getMemberName())){
            userChat.setMemberName(user.getUserName());
        }
        initMember(userChat.getId(),user.getAvatar(),userChat.getMemberName());
        return new ServiceResult(userChat);
    }

    @Override
    public int initMember(Integer id, String avatar,String memberName) {
        return userChatDao.update(new UserChat(id,avatar,memberName));
    }

    @Override
    public ServiceResult updateMemberName(UserChat userChat) {
        Integer id = userChatDao.getId(new UserChat(userChat.getUserId(),userChat.getChatId()));
        userChat.setId(id);
        if(userChatDao.update(userChat)==1){
            return new ServiceResult(Status.SUCCESS.getMessage(),true);
        }
        return new ServiceResult(Status.FAILED.getMessage(),false);
    }

    @Override
    public ServiceResult isBlocked(UserChat userChat) {
        if(userChatDao.isBlocked(userChat)){
            return new ServiceResult(Status.IS_BLOCKED_GROUP.getMessage(),true);
        }
        return new ServiceResult(false);
    }

    @Override
    public ServiceResult updateGroupAvatar(Chat chat) {
        if(chatDao.update(chat)==1){
            return new ServiceResult(Status.IS_BLOCKED_GROUP.getMessage(),true);
        }
        return new ServiceResult(false);
    }

    @Override
    public ServiceResult blockMember(UserChat userChat) {
        if (userChatDao.update(userChat) == 1) {
            return new ServiceResult(Status.SUCCESS.getMessage(), true);
        }
        return new ServiceResult(false);
    }

    @Override
    public ServiceResult unBlockMember(UserChat userChat) {
        if (userChatDao.update(userChat) == 1) {
            return new ServiceResult(Status.SUCCESS.getMessage(), true);
        }
        return new ServiceResult(false);
    }

    @Override
    public ServiceResult deleteMember(UserChat userChat) {
        if(userChatDao.delete(userChat)==1){
            return new ServiceResult(Status.SUCCESS.getMessage(), true);
        }
        return new ServiceResult(false);
    }

    @Override
    public ServiceResult deleteGroup(Chat chat) {
        if(chatDao.delete(chat)==1){
            return new ServiceResult(Status.SUCCESS.getMessage(), true);
        }
        return new ServiceResult(false);
    }

    @Override
    public ServiceResult exitGroup(UserChat userChat) {
        if(userChatDao.delete(userChat)==1){
            return new ServiceResult(Status.SUCCESS.getMessage(), true);
        }
        return new ServiceResult(false);
    }

    @Override
    public ServiceResult inviteMember(Integer chatId, String[] userLists) {
        //中间表新增群成员记录
        if (userLists.length > 0) {
            for (String userList : userLists) {
                int userId = Integer.parseInt(userList);
                userChatDao.insertUserChat(new UserChat(userId, chatId, "group"));
            }
            return new ServiceResult(Status.SUCCESS.getMessage(), true);
        }
        return new ServiceResult(Status.NO_SELECTED_MEMBER.getMessage(), false);

    }
}
