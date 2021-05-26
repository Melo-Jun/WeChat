package com.melo.wechat.dao.impl;

import com.melo.wechat.dao.inter.FriendDao;
import com.melo.wechat.model.entity.Friend;

import java.util.LinkedList;

/**
 * @Description: 好友关系数据库操作实现类
 * @author: Jun
 * @date: 20:28 2021/5/8
 */
public class FriendDaoImpl extends BaseDaoImpl implements FriendDao  {

    /**
     * 本表对应所有字段
     */
    private final Object[] ALL_FIELD_NAME=new Object[]{"id","user_id","friend_id","chat_id","alias","description",
            "avatar","is_block","status","gmt_create", "gmt_modified"};


    /**
     * @Description: 添加好友
     * @param friend 好友对象
     * @date: 10:01 2021/5/15
     * @return: int
     */
    @Override
    public int addFriend(Friend friend) {
        return super.insert(friend);
    }

    /**
     * @Description: 判断是否已经加过好友
     * @param friend 好友对象
     * @date: 10:02 2021/5/15
     * @return: boolean
     */
    @Override
    public boolean hasAddFriend(Friend friend){
        StringBuilder sql= appendSelect(new Object[]{"id"},friend,"AND");
        return !queryList(sql.toString(),friend).isEmpty();
    }

    @Override
    public int updateFriendChat(Friend friend,Integer chatId){
        Integer id = getId(friend);
        Friend temp = new Friend(chatId);
        temp.setId(id);
        return update(temp);
    }


    @Override
    public Friend getFriendById(Integer id){
        Friend friend = new Friend();
        friend.setId(id);
        StringBuilder sql=appendSelect(ALL_FIELD_NAME,friend,"AND");
        return getObjectBy(sql.toString(),friend, Friend.class);
    }

    @Override
    public LinkedList<Integer> getFriendIdByChatId(Integer chatId){
        Friend friend = new Friend();
        friend.setChatId(chatId);
        StringBuilder sql = appendSelect(new Object[]{"friend_id"}, friend, "AND");
        return queryList(sql.toString(), friend);
    }

    @Override
    public boolean isBlocked(Friend friend) {
        StringBuilder sql = appendSelect(new Object[]{"is_block"}, friend, "AND");
        Integer isBlocked = (Integer) queryList(sql.toString(), friend).getFirst();
        return isBlocked == 1;
    }

    @Override
    public boolean isFriend(Friend friend) {
        StringBuilder sql = appendSelect(new Object[]{"chat_id"}, friend, "AND");
        //若没有记录(未发送过好友申请)
        if(queryList(sql.toString(),friend).isEmpty()){
            System.out.println("falseaaa");
            return false;
        }
        System.out.println(queryList(sql.toString(),friend).getFirst());
        //或者只有一方发送,双方还未建立起聊天会话
        return queryList(sql.toString(), friend).getFirst()!=null;
    }

    @Override
    public Friend getFriendByFriendIdAndChatId(Integer friendId, Integer chatId) {
        Friend friend = new Friend();
        friend.setFriendId(friendId);
        friend.setChatId(chatId);
        StringBuilder sql=appendSelect(ALL_FIELD_NAME,friend,"AND");
        return getObjectBy(sql.toString(),friend, Friend.class);
    }
}
