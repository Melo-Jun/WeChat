package com.melo.wechat.dao.inter;

import com.melo.wechat.model.entity.Emoji;

import java.util.LinkedList;

public interface EmojiDao extends BaseDao{

    /**
     * @Description: 加载表情包
     * @param emoji 表情包
     * @date: 0:02 2021/5/26
     * @return: java.util.LinkedList<com.melo.wechat.model.entity.Emoji>
     */
    LinkedList<Emoji> loadEmoji(Emoji emoji);
}
