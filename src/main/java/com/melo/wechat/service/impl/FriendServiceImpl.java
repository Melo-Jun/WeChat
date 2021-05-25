package com.melo.wechat.service.impl;

import com.melo.wechat.constant.Status;
import com.melo.wechat.dao.impl.*;
import com.melo.wechat.dao.inter.*;
import com.melo.wechat.model.builder.FriendVOBuilder;
import com.melo.wechat.model.builder.NoticeBuilder;
import com.melo.wechat.model.dto.ServiceResult;
import com.melo.wechat.model.entity.*;
import com.melo.wechat.model.vo.FriendVO;
import com.melo.wechat.controller.websocket.WebSocket;
import com.melo.wechat.service.inter.FriendService;
import com.melo.wechat.utils.UUIDUtils;
import com.melo.wechat.utils.proxy.DaoProxy;

import java.util.LinkedList;

/**
 * @Description: 好友业务逻辑实现类
 * @author: Jun
 * @date: 23:40 2021/5/13
 */
public class FriendServiceImpl implements FriendService {

    /**
     * 代理对象类
     */
    FriendDao friendDao= DaoProxy.getProxyInstance(FriendDaoImpl.class);
    NoticeDao noticeDao= DaoProxy.getProxyInstance(NoticeDaoImpl.class);
    ChatDao chatDao= DaoProxy.getProxyInstance(ChatDaoImpl.class);
    UserChatDao userChatDao=DaoProxy.getProxyInstance(UserChatDaoImpl.class);
    UserDao userDao= DaoProxy.getProxyInstance(UserDaoImpl.class);


    /**
     * @Description: 添加好友
     * @param friend 好友对象
     * @date: 9:55 2021/5/5
     * @return: com.melo.wechat.model.dto.ServiceResult
     */
    @Override
    public ServiceResult addFriend(Friend friend) {
        Integer userId = friend.getUserId();
        Integer friendId = friend.getFriendId();
        //判断自己是否已经发送过好友申请(处于未读状态)
        if(friendDao.hasAddFriend(friend)){
            return new ServiceResult (Status.HAS_SEND_FRIEND.getMessage(),false);
        }
        //没发送过就先发送
        else if(friendDao.addFriend(friend)==1){
            //若彼此都曾发送过好友请求(则双方成为好友,无需发送好友申请给对方)
            if(friendDao.hasAddFriend(friend)&& friendDao.hasAddFriend(new Friend(friendId,userId))){
                //建立属于两人的聊天会话框
                String chatNumber= UUIDUtils.getUniqueNumber();
                chatDao.insertChat(new Chat(chatNumber));
                //为好友关系设置ChatId(触发器中间表会新增两条记录)
                updateFriendChat(userId,friendId,chatDao.getIdByNumber(chatNumber));
                return new ServiceResult(Status.SUCCEED_ADD.getMessage(),true);
            }
            //还未发送过好友申请
            else {
                Notice notice = new NoticeBuilder().setContent(friend.getDescription())
                        .setSenderId(friend.getUserId())
                        .setReceiverId(friend.getFriendId())
                        .setType("friendNotice").build();
                //将通知存进数据库中
                noticeDao.insert(notice);
                //发送通知给对方
                WebSocket.sendNotice(notice, friend.getFriendId());
                return new ServiceResult(Status.WAIT_AGREE.getMessage(),false);
            }
        }
        return new ServiceResult(false);
    }

    /**
     * @Description: 为好友关系设置一个聊天会话
     * @param userId 用户id
     * @param friendId 好友id
     * @param chatId 聊天id
     * @date: 15:18 2021/5/13
     * @return: void
     */
    @Override
    public void updateFriendChat(Integer userId, Integer friendId, Integer chatId){
        Friend friend1 = new Friend(userId,friendId);
        friendDao.updateFriendChat(friend1,chatId);
        Friend friend2 = new Friend(friendId,userId);
        friendDao.updateFriendChat(friend2,chatId);
    }

    /**
     * @Description: 初始化好友信息
     * @param avatar 好友头像
     * @param id 好友的id
     * @param alias 好友备注
     * @date: 15:30 2021/5/13
     * @return: void
     */
    @Override
    public void initFriend(Integer id, String avatar,String alias){
        friendDao.update(new Friend(id,alias,avatar));
    }

    /**
     * @Description: 拉黑好友
     * @param friend 好友对象
     * @date: 10:02 2021/5/15
     * @return: com.melo.wechat.model.dto.ServiceResult
     */
    @Override
    public ServiceResult blockFriend(Friend friend){
        ServiceResult result = new ServiceResult();
        if(friendDao.update(friend)==1) {
            result.setMessage(Status.SUCCEED_BLOCK.getMessage());
        }
        return result;
    }

    /**
     * @Description: 取消拉黑好友
     * @param friend 好友对象
     * @date: 14:27 2021/5/21
     * @return: com.melo.wechat.model.dto.ServiceResult
     */
    @Override
    public ServiceResult unBlockFriend(Friend friend){
        ServiceResult result = new ServiceResult();
        if(friendDao.update(friend)==1) {
            result.setMessage(Status.SUCCEED_UNBLOCK.getMessage());
        }
        return result;
    }

    /**
     * @Description: 更新好友备注
     * @param friend 好友对象
     * @date: 14:28 2021/5/21
     * @return: com.melo.wechat.model.dto.ServiceResult
     */
    @Override
    public ServiceResult updateAlias(Friend friend) {
        ServiceResult result = new ServiceResult();
        if(friendDao.update(friend)==1){
           result.setMessage(Status.SUCCESS.getMessage());
       }else {
            result.setMessage(Status.FAILED.getMessage());
        }
        return result;
    }

    /**
    * @Description: 加载好友聊天列表
    * @param userId 用户id
    * @date: 14:07 2021/5/9
    * @return: com.melo.wechat.model.dto.ServiceResult
    */
    @Override
    public ServiceResult loadFriend(Integer userId){
        /*
        获取该用户好友类型的chatId
         */
        LinkedList<Integer> chatIds = userChatDao.listChatId(userId,"friend");
        LinkedList<Friend> friends = new LinkedList<>();
        if(!chatIds.isEmpty()) {
        /*
        根据chatId列出好友
         */
            for (Integer chatId : chatIds) {
                LinkedList<Integer> friendIds = friendDao.getFriendIdByChatId(chatId);
                Integer friendId = friendIds.pop();
                System.out.println(friendId+"first");
                if (friendId.equals(userId)) {
                    friendId = friendIds.pop();
                }
                System.out.println(friendId+"second");
                Friend friend = friendDao.getFriendByFriendIdAndChatId(friendId,chatId);
                //获取好友的个人用户信息,以便设置备注以及头像
                User user = userDao.getUserById(friendId);
                //若未设置备注,则默认为用户名(每次列出都判断,才能保证对方修改用户名后默认备注实时更新为对方用户名)
                if (Status.UNSET.getMessage().equals(friend.getAlias())) {
                    friend.setAlias(user.getUserName());
                }
                friend.setAvatar(user.getAvatar());
                //初始化好友(头像和备注),方便后续查询
                initFriend(friend.getId(), user.getAvatar(), friend.getAlias());
                friends.add(friend);
            }
            return new ServiceResult(friends,true);
        }
        return new ServiceResult(false);
    }

    /**
     * @Description: 加载好友聊天窗口
     * @param id 好友关系id
     * @date: 10:04 2021/5/15
     * @return: com.melo.wechat.model.dto.ServiceResult
     */
    @Override
    public ServiceResult showFriendChat(Integer id) {
        ServiceResult result=new ServiceResult();
        Friend friend = friendDao.getFriendById(id);
        if(WebSocket.SERVER_MAP.get(friend.getFriendId())!=null){
            friend.setStatus(Status.ONLINE.getMessage());
        }else {
            friend.setStatus(Status.OFFLINE.getMessage());
        }
        result.setData(friend);
        return result;
    }

    /**
     * @Description: 展示好友个人信息
     * @param id
     * @date: 14:28 2021/5/21
     * @return: com.melo.wechat.model.dto.ServiceResult
     */
    @Override
    public ServiceResult showFriendInform(Integer id) {
        Friend friend = friendDao.getFriendById(id);
        User user = userDao.getUserById(friend.getFriendId());
        FriendVO friendVo = new FriendVOBuilder().setWechatId(user.getWechatId())
                .setUserName(user.getUserName())
                .setAlias(friend.getAlias())
                .setPhoto(user.getAvatar())
                .setId(friend.getId())
                .setUserId(user.getId())
                .setIsBlock(friend.getIsBlock())
                .setValidity(user.getValidity())
                .setChatId(friend.getChatId()).build();
        ServiceResult result = new ServiceResult();
        result.setData(friendVo);
        return result;
    }

    /**
     * @Description: 判断自己是否被好友拉黑
     * @param friend
     * @date: 14:28 2021/5/21
     * @return: com.melo.wechat.model.dto.ServiceResult
     */
    @Override
    public ServiceResult isBlocked(Friend friend) {
        if(friendDao.isBlocked(friend)){
            return new ServiceResult(Status.IS_BLOCKED.getMessage(),true);
        }
        return new ServiceResult(false);
    }

    /**
     * @Description: 判断对方是否为自己好友
     * @param friend 好友对象
     * @date: 9:12 2021/5/22
     * @return: com.melo.wechat.model.dto.ServiceResult
     */
    @Override
    public ServiceResult isFriend(Friend friend) {
        if(friendDao.isFriend(friend)){
           return new ServiceResult (Status.HAD_ADD_USER.getMessage(),true);
        }
        return new ServiceResult(false);
    }

    @Override
    public ServiceResult refuseFriend(Friend friend, Notice notice) {
        if(friendDao.delete(friend)==1){
            notice.setType("systemNotice");
            //将通知存进数据库中
            noticeDao.insert(notice);
            //发送通知给对方
            WebSocket.sendNotice(notice, notice.getReceiverId());
            return new ServiceResult(Status.SUCCESS.getMessage());
        }
        return new ServiceResult(Status.FAILED.getMessage());
    }
}
