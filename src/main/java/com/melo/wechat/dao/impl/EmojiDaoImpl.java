package com.melo.wechat.dao.impl;

import com.melo.wechat.dao.inter.EmojiDao;
import com.melo.wechat.model.entity.Emoji;

import java.util.LinkedList;

public class EmojiDaoImpl extends BaseDaoImpl implements EmojiDao {

    /**
     * 本表对应所有字段
     */
    private final Object[] ALL_FIELD_NAME=new Object[]{"id","user_id","path","gmt_create", "gmt_modified"};



    @Override
    public LinkedList<Emoji> loadEmoji(Emoji emoji) {
        StringBuilder sql = appendSelect(ALL_FIELD_NAME, emoji, "AND");
        return queryAll(sql.toString(),emoji,Emoji.class);
    }
}
