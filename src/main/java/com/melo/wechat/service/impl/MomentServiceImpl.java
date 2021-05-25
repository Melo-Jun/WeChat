package com.melo.wechat.service.impl;

import com.melo.wechat.constant.Status;
import com.melo.wechat.dao.impl.*;
import com.melo.wechat.dao.inter.*;
import com.melo.wechat.model.builder.MomentVOBuilder;
import com.melo.wechat.model.dto.ServiceResult;
import com.melo.wechat.model.entity.Moment;
import com.melo.wechat.model.entity.User;
import com.melo.wechat.model.vo.MomentVO;
import com.melo.wechat.service.inter.MomentService;
import com.melo.wechat.utils.proxy.DaoProxy;

import java.util.LinkedList;

/**
 * @Description: 朋友圈业务逻辑实现类
 * @author: Jun
 * @date: 12:12 2021/5/16
 */
public class MomentServiceImpl implements MomentService {

    /**
     * 代理对象类
     */
    MomentDao momentDao = DaoProxy.getProxyInstance(MomentDaoImpl.class);
    UserDao userDao = DaoProxy.getProxyInstance(UserDaoImpl.class);

    /**
     * @Description: 列出单一用户朋友圈
     * @param userId 用户id
     * @date: 14:07 2021/5/19
     * @return: com.melo.wechat.model.dto.ServiceResult
     */
    @Override
    public ServiceResult showMoment(Integer userId) {
        ServiceResult result = new ServiceResult();
        User user = userDao.getUserById(userId);
        LinkedList<Moment> moments = momentDao.getMomentByUserId(userId);
        LinkedList<MomentVO> momentVos = new LinkedList<>();
        if(!moments.isEmpty()) {
            for (Moment moment : moments) {
                MomentVO momentVO = new MomentVOBuilder()
                        .setUserId(userId)
                        .setUserName(user.getUserName())
                        .setWechatId(user.getWechatId())
                        .setAvatar(user.getAvatar())
                        .setId(moment.getId())
                        .setContent(moment.getContent())
                        .setLikeCount(moment.getLikeCount())
                        .setPhoto(moment.getPhoto())
                        .setGmtCreate(moment.getGmtCreate()).build();
                momentVos.add(momentVO);
            }
            result.setFlag(true);
        }else {
            momentVos.add(new MomentVO(user.getUserName(),user.getAvatar()));
            result.setFlag(false);
        }
        result.setData(momentVos);
        return result;
    }

    /**
     * @Description: 列出所有用户朋友圈
     * @param userId 用户id
     * @date: 14:07 2021/5/19
     * @return: com.melo.wechat.model.dto.ServiceResult
     */
    @Override
    public ServiceResult showAllMoment(Integer userId) {
        ServiceResult result = new ServiceResult();
        User user = userDao.getUserById(userId);
        LinkedList<Moment> moments = momentDao.getMomentByUserId(userId);
        LinkedList<MomentVO> momentVos = new LinkedList<>();
        if(!moments.isEmpty()) {
            for (Moment moment : moments) {
                MomentVO momentVO = new MomentVOBuilder()
                        .setUserId(userId)
                        .setUserName(user.getUserName())
                        .setWechatId(user.getWechatId())
                        .setAvatar(user.getAvatar())
                        .setId(moment.getId())
                        .setContent(moment.getContent())
                        .setLikeCount(moment.getLikeCount())
                        .setPhoto(moment.getPhoto())
                        .setGmtCreate(moment.getGmtCreate()).build();
                momentVos.add(momentVO);
            }
            result.setFlag(true);
        }else {
            momentVos.add(new MomentVO(user.getUserName(),user.getAvatar()));
            result.setFlag(false);
        }
        result.setData(momentVos);
        return result;
    }

    @Override
    public ServiceResult newMoment(Moment moment) {
        ServiceResult result = new ServiceResult();
        if(momentDao.insert(moment)==1){
           result.setMessage(Status.SUCCESS.getMessage());
           result.setFlag(true);
       }else {
            result.setMessage(Status.FAILED.getMessage());
        }
        return  result;
    }

    @Override
    public ServiceResult deleteMoment(Integer momentId) {
        ServiceResult result = new ServiceResult();
        Moment moment = new Moment();
        moment.setId(momentId);
        if(momentDao.delete(moment)==1){
            result.setFlag(true);
            result.setMessage(Status.SUCCESS.getMessage());
        }
        return result;
    }


}
