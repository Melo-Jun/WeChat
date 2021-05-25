package com.melo.wechat.service.impl;

import com.melo.wechat.constant.Status;
import com.melo.wechat.dao.impl.EmojiDaoImpl;
import com.melo.wechat.dao.inter.EmojiDao;
import com.melo.wechat.model.dto.ServiceResult;
import com.melo.wechat.model.entity.Emoji;
import com.melo.wechat.service.inter.EmojiService;
import com.melo.wechat.utils.proxy.DaoProxy;

import java.util.LinkedList;

public class EmojiServiceImpl implements EmojiService {



    /**
     * 相关操作类
     */
    EmojiDao emojiDao= DaoProxy.getProxyInstance(EmojiDaoImpl.class);


    @Override
    public ServiceResult loadEmoji(Integer userId) {
        Emoji emoji = new Emoji(userId);
        LinkedList<Emoji> emojis = emojiDao.loadEmoji(emoji);
        return new ServiceResult(emojis);
    }

    @Override
    public ServiceResult uploadEmoji(Emoji emoji) {
        if( emojiDao.insert(emoji)==1){
            return new ServiceResult(Status.SUCCESS.getMessage(),true);
        }
        return new ServiceResult(false);
    }
}
