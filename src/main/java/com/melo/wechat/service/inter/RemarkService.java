package com.melo.wechat.service.inter;

import com.melo.wechat.annotation.log.LogInfo;
import com.melo.wechat.model.dto.ServiceResult;
import com.melo.wechat.model.entity.Remark;

/**
 * @Description: 评论业务逻辑接口
 * @author: Jun
 * @date: 19:36 2021/5/16
 */
public interface RemarkService {

    /**
     * @Description: 展示评论
     * @param momentId
     * @date: 0:09 2021/5/26
     * @return: com.melo.wechat.model.dto.ServiceResult
     */
    ServiceResult showRemark(Integer momentId);

    /**
     * @Description: 发布新评论
     * @param remark
     * @date: 0:09 2021/5/26
     * @return: com.melo.wechat.model.dto.ServiceResult
     */
    @LogInfo("有新的评论-->")
    ServiceResult newRemark(Remark remark);
}
