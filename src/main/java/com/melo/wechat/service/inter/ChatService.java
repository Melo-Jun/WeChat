package com.melo.wechat.service.inter;

import com.melo.wechat.annotation.log.LogInfo;
import com.melo.wechat.model.dto.ServiceResult;
import com.melo.wechat.model.entity.Chat;
import com.melo.wechat.model.entity.Notice;
import com.melo.wechat.model.entity.UserChat;

/**
 * @Description: 聊天业务
 * @author: Jun
 * @date: 18:39 2021/5/25
 */
public interface ChatService {

     /**
      * @Description: 创建新的好友聊天
      * @date: 8:46 2021/5/9
      * @return: int
      */
     int newFriendChat();

     /**
      * @Description: 加载群聊列表
      * @param userId 用户id
      * @date: 23:10 2021/5/15
      * @return: com.melo.wechat.model.dto.ServiceResult
      */
     ServiceResult loadGroup(Integer userId);

     /**
      * @Description: 根据chatId返回一个群聊对象
      * @param chatId 聊天id
      * @date: 23:11 2021/5/15
      * @return: com.melo.wechat.model.dto.ServiceResult
      */
     ServiceResult showGroupChat(Integer chatId);

     /**
      * @Description: 展示群成员
      * @param chatId 聊天id
      * @date: 23:05 2021/5/25
      * @return: com.melo.wechat.model.dto.ServiceResult
      */
     ServiceResult showChatMember(Integer chatId);

     /**
      * @Description: 删除好友
      * @param chatId 聊天id
      * @date: 23:05 2021/5/25
      * @return: com.melo.wechat.model.dto.ServiceResult
      */
     ServiceResult deleteFriend(Integer chatId);

     /**
      * @Description: 创建群聊
      * @param userId 群主id
      * @param name 群聊名称
      * @param userLists 群成员id
      * @date: 23:05 2021/5/25
      * @return: com.melo.wechat.model.dto.ServiceResult
      */
     @LogInfo("创建了新的微信群,群主id,群名,群成员id分别为:-->")
     ServiceResult newGroup(Integer userId,String name,String[] userLists);

     /**
      * @Description: 邀请群成员
      * @param chatId 聊天id
      * @param userLists 群成员id
      * @date: 23:06 2021/5/25
      * @return: com.melo.wechat.model.dto.ServiceResult
      */
     @LogInfo("邀请新成员,群聊id,新成员id分别为:-->")
     ServiceResult inviteMember(Integer chatId, String[] userLists);

     /**
      * @Description: 搜索群聊
      * @param tempNumber 群号
      * @date: 23:06 2021/5/25
      * @return: com.melo.wechat.model.dto.ServiceResult
      */
     ServiceResult searchGroup(String tempNumber);

     /**
      * @Description: 申请加入群聊
      * @param userChat 中间表对象
      * @param notice 群聊申请内容
      * @date: 23:06 2021/5/25
      * @return: com.melo.wechat.model.dto.ServiceResult
      */
     ServiceResult addGroup(UserChat userChat, Notice notice);

     /**
      * @Description: 判断是否在群聊中
      * @param userChat 中间表对象
      * @date: 23:07 2021/5/25
      * @return: com.melo.wechat.model.dto.ServiceResult
      */
     ServiceResult isAtGroup(UserChat userChat);

     /**
      * @Description: 接受群聊申请
      * @param userChat 中间表对象
      * @date: 23:07 2021/5/25
      * @return: com.melo.wechat.model.dto.ServiceResult
      */
     ServiceResult receiveAddGroup(UserChat userChat);

     /**
      * @Description: 修改群名称
      * @param chat 群对象
      * @date: 23:07 2021/5/25
      * @return: com.melo.wechat.model.dto.ServiceResult
      */
     ServiceResult updateChatName(Chat chat);

     /**
      * @Description: 显示群成员信息(聊天时可以展示)
      * @param tempUserChat 中间表对象
      * @date: 23:07 2021/5/25
      * @return: com.melo.wechat.model.dto.ServiceResult
      */
     ServiceResult showMemberInform(UserChat tempUserChat);

     /**
      * @Description: 修改群昵称
      * @param userChat 中间表对象
      * @date: 23:08 2021/5/25
      * @return: com.melo.wechat.model.dto.ServiceResult
      */
     ServiceResult updateMemberName(UserChat userChat);

     /**
      * @Description: 判断是否被禁言
      * @param userChat 中间表对象
      * @date: 23:08 2021/5/25
      * @return: com.melo.wechat.model.dto.ServiceResult
      */
     ServiceResult isBlocked(UserChat userChat);

     /**
      * @Description: 群主修改群头像
      * @param chat 群对象
      * @date: 23:08 2021/5/25
      * @return: com.melo.wechat.model.dto.ServiceResult
      */
     ServiceResult updateGroupAvatar(Chat chat);

     /**
      * @Description: 群主禁言
      * @param userChat 中间表对象
      * @date: 23:08 2021/5/25
      * @return: com.melo.wechat.model.dto.ServiceResult
      */
     ServiceResult blockMember(UserChat userChat);

     /**
      * @Description: 群主取消禁言
      * @param userChat 中间表对象
      * @date: 23:09 2021/5/25
      * @return: com.melo.wechat.model.dto.ServiceResult
      */
     ServiceResult unBlockMember(UserChat userChat);

     /**
      * @Description: 送飞机票
      * @param userChat 中间表对象
      * @date: 23:09 2021/5/25
      * @return: com.melo.wechat.model.dto.ServiceResult
      */
     ServiceResult deleteMember(UserChat userChat);

     /**
      * @Description: 解散群聊
      * @param chat 群聊对象
      * @date: 23:09 2021/5/25
      * @return: com.melo.wechat.model.dto.ServiceResult
      */
     ServiceResult deleteGroup(Chat chat);

     /**
      * @Description: 退出群聊
      * @param userChat
      * @date: 23:09 2021/5/25
      * @return: com.melo.wechat.model.dto.ServiceResult
      */
     ServiceResult exitGroup(UserChat userChat);

    /**
     * @Description: 初始化群成员
     * @param id id
     * @param avatar 头像
     * @param memberName 成员名称
     * @date: 23:07 2021/5/25
     * @return: int
     */
    int initMember(Integer id, String avatar,String memberName);


}
