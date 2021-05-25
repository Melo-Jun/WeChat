package com.melo.wechat.dao.inter;

import com.melo.wechat.model.entity.Friend;

import java.util.LinkedList;

/**
 * @Description: 好友关系数据库操作接口
 * @author: Jun
 * @date: 20:28 2021/5/8
 */
public interface FriendDao extends BaseDao {

    /**
     * @Description: 添加好友
     * @param friend 好友对象
     * @date: 10:01 2021/5/15
     * @return: int
     */
    int addFriend(Friend friend);

    /**
     * @Description: 判断是否已经加过好友
     * @param friend 好友对象
     * @date: 10:02 2021/5/15
     * @return: boolean
     */
    boolean hasAddFriend(Friend friend);

    /**
     * @Description: 为好友聊天设置chatId
     * @param friend 好友对象
     * @param chatId chatId
     * @date: 0:03 2021/5/26
     * @return: int
     */
    int updateFriendChat(Friend friend,Integer chatId);

    /**
     * @Description: 通过id找到好友对象
     * @param id id
     * @date: 0:03 2021/5/26
     * @return: com.melo.wechat.model.entity.Friend
     */
    Friend getFriendById(Integer id);

    /**
     * @Description: 通过chatId列出好友对象
     * @param chatId
     * @date: 0:03 2021/5/26
     * @return: java.util.LinkedList<java.lang.Integer>
     */
    LinkedList<Integer> getFriendIdByChatId(Integer chatId);

    /**
     * @Description: 判断是否被拉黑
     * @param friend
     * @date: 0:03 2021/5/26
     * @return: boolean
     */
    boolean isBlocked(Friend friend);

    /**
     * @Description: 判断是不是好友
     * @param friend
     * @date: 0:04 2021/5/26
     * @return: boolean
     */
    boolean isFriend(Friend friend);

    /**
     * @Description: 根据friendId和ChatId找到好友
     * @param friendId
     * @param chatId
     * @date: 0:04 2021/5/26
     * @return: com.melo.wechat.model.entity.Friend
     */
    Friend getFriendByFriendIdAndChatId(Integer friendId, Integer chatId);
}
