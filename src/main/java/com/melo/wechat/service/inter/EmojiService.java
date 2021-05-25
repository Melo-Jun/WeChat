package com.melo.wechat.service.inter;

import com.melo.wechat.annotation.log.LogInfo;
import com.melo.wechat.model.dto.ServiceResult;
import com.melo.wechat.model.entity.Emoji;

public interface EmojiService {

    /**
     * @Description: 用户加载表情包
     * @param userId 用户id
     * @date: 23:52 2021/5/25
     * @return: com.melo.wechat.model.dto.ServiceResult
     */
    ServiceResult loadEmoji(Integer userId);

    /**
     * @Description: 用户上传表情包
     * @param emoji 表情包对象
     * @date: 23:52 2021/5/25
     * @return: com.melo.wechat.model.dto.ServiceResult
     */
    @LogInfo("用户上传表情包")
    ServiceResult uploadEmoji(Emoji emoji);
}
