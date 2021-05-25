package com.melo.wechat.service.inter;

import com.melo.wechat.annotation.dao.Transaction;
import com.melo.wechat.model.dto.ServiceResult;
import com.melo.wechat.model.entity.LikeList;

/**
 * @Description: 点赞记录逻辑接口
 * @author: Jun
 * @date: 16:09 2021/5/17
 */
public interface LikeListService {

    /**
     * @Description: 展示点赞过的好友
     * @param momentId 朋友圈id
     * @date: 23:43 2021/5/25
     * @return: com.melo.wechat.model.dto.ServiceResult
     */
    ServiceResult showLikeUser(Integer momentId);

    /**
     * @Description: 点赞
     * @param likeList 点赞记录对象
     * @date: 23:43 2021/5/25
     * @return: com.melo.wechat.model.dto.ServiceResult
     */
    @Transaction()
    ServiceResult like(LikeList likeList);

    /**
     * @Description: 取消点赞
     * @param likeList 点赞记录对象
     * @date: 23:43 2021/5/25
     * @return: com.melo.wechat.model.dto.ServiceResult
     */
    @Transaction()
    ServiceResult unLike(LikeList likeList);

    /**
     * @Description: 判断是否点赞过
     * @param likeList 点赞记录
     * @date: 23:43 2021/5/25
     * @return: boolean
     */
    boolean everLike(LikeList likeList);
}
