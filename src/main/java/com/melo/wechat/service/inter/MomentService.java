package com.melo.wechat.service.inter;

import com.melo.wechat.annotation.log.LogInfo;
import com.melo.wechat.model.dto.ServiceResult;
import com.melo.wechat.model.entity.Moment;

/**
 * @Description: 朋友圈业务逻辑接口
 * @author: Jun
 * @date: 12:12 2021/5/16
 */
public interface MomentService {

    /**
     * @Description: 查看某用户朋友圈
     * @param userId
     * @date: 0:08 2021/5/26
     * @return: com.melo.wechat.model.dto.ServiceResult
     */
    ServiceResult showMoment(Integer userId);

    ServiceResult showAllMoment(Integer userId);

    /**
     * @Description: 发布新的朋友圈动态
     * @param moment
     * @date: 0:08 2021/5/26
     * @return: com.melo.wechat.model.dto.ServiceResult
     */
    @LogInfo("有新的朋友圈动态-->")
    ServiceResult newMoment(Moment moment);

    /**
     * @Description: 删除朋友圈
     * @param momentId
     * @date: 0:08 2021/5/26
     * @return: com.melo.wechat.model.dto.ServiceResult
     */
    ServiceResult deleteMoment(Integer momentId);
}
