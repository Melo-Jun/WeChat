package com.melo.wechat.service.inter;

import com.melo.wechat.annotation.log.LogInfo;
import com.melo.wechat.model.dto.ServiceResult;
import com.melo.wechat.model.entity.Friend;
import com.melo.wechat.model.entity.Notice;

/**
 * @Description: 好友业务逻辑接口
 * @author: Jun
 * @date: 23:40 2021/5/13
 */
public interface FriendService {

    /**
     * @Description: 添加好友
     * @param friend 好友对象
     * @date: 9:55 2021/5/5
     * @return: com.melo.wechat.model.dto.ServiceResult
     */
    ServiceResult addFriend(Friend friend);

    /**
     * @Description: 为好友关系设置一个聊天会话
     * @param userId 用户id
     * @param friendId 好友id
     * @param chatId 聊天id
     * @date: 15:18 2021/5/13
     * @return: void
     */
    @LogInfo("建立新的好友聊天会话,用户id,好友id,聊天id分别为:->")
    void updateFriendChat(Integer userId,Integer friendId,Integer chatId);

    /**
     * @Description: 初始化好友信息
     * @param avatar 好友头像
     * @param id 好友的id
     * @param alias 好友备注
     * @date: 15:30 2021/5/13
     * @return: void
     */
    void initFriend(Integer id, String avatar,String alias);

    /**
     * @Description: 拉黑好友
     * @param friend 好友对象
     * @date: 10:02 2021/5/15
     * @return: com.melo.wechat.model.dto.ServiceResult
     */
    ServiceResult blockFriend(Friend friend);

    /**
     * @Description: 取消拉黑好友
     * @param friend 好友对象
     * @date: 14:27 2021/5/21
     * @return: com.melo.wechat.model.dto.ServiceResult
     */
    ServiceResult unBlockFriend(Friend friend);

    /**
     * @Description: 更新好友备注
     * @param friend 好友对象
     * @date: 14:28 2021/5/21
     * @return: com.melo.wechat.model.dto.ServiceResult
     */
    ServiceResult updateAlias(Friend friend);

    /**
     * @Description: 加载好友聊天列表
     * @param userId 用户id
     * @date: 14:07 2021/5/9
     * @return: com.melo.wechat.model.dto.ServiceResult
     */
    ServiceResult loadFriend(Integer userId);

    /**
     * @Description: 加载好友聊天窗口
     * @param id 好友关系id
     * @date: 10:04 2021/5/15
     * @return: com.melo.wechat.model.dto.ServiceResult
     */
    ServiceResult showFriendChat(Integer id);

    /**
     * @Description: 展示好友个人信息
     * @param id
     * @date: 14:28 2021/5/21
     * @return: com.melo.wechat.model.dto.ServiceResult
     */
    ServiceResult showFriendInform(Integer id);

    /**
     * @Description: 判断自己是否被好友拉黑
     * @param friend
     * @date: 14:28 2021/5/21
     * @return: com.melo.wechat.model.dto.ServiceResult
     */
    ServiceResult isBlocked(Friend friend);

    /**
     * @Description: 判断对方是否为自己好友
     * @param friend 好友对象
     * @date: 9:12 2021/5/22
     * @return: com.melo.wechat.model.dto.ServiceResult
     */
    ServiceResult isFriend(Friend friend);

    /**
     * @Description: 拒绝好友申请
     * @param friend
     * @param notice
     * @date: 0:07 2021/5/26
     * @return: com.melo.wechat.model.dto.ServiceResult
     */
    ServiceResult refuseFriend(Friend friend, Notice notice);
}
