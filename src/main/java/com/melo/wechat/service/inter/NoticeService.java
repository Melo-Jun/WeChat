package com.melo.wechat.service.inter;

import com.melo.wechat.model.dto.ServiceResult;
import com.melo.wechat.model.entity.Notice;

/**
 * @Description: 通知业务相关逻辑接口
 * @author: Jun
 * @date: 21:47 2021/5/6
 */
public interface NoticeService {

    /**
     * @Description: 列出相应类型通知列表
     * @param notice 通知对象
     * @date: 21:56 2021/5/6
     * @return: com.melo.wechat.model.dto.ServiceResult
     */
     ServiceResult loadNotice(Notice notice);

     /**
      * @Description: 展示通知内容
      * @param notice
      * @date: 0:09 2021/5/26
      * @return: com.melo.wechat.model.dto.ServiceResult
      */
     ServiceResult showNotice(Notice notice);

    /**
     * @Description: 更新通知状态
     * @param id 通知id
     * @date: 10:51 2021/5/22
     * @return: com.melo.wechat.model.dto.ServiceResult
     */
     ServiceResult updateStatus(Integer id);

     /**
      * @Description: 发送通知
      * @param notice
      * @date: 0:09 2021/5/26
      * @return: com.melo.wechat.model.dto.ServiceResult
      */
     ServiceResult sendNotice(Notice notice);
}
