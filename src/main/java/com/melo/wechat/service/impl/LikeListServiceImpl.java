package com.melo.wechat.service.impl;

import com.melo.wechat.annotation.dao.Transaction;
import com.melo.wechat.dao.impl.*;
import com.melo.wechat.dao.inter.LikeListDao;
import com.melo.wechat.dao.inter.MomentDao;
import com.melo.wechat.dao.inter.UserDao;
import com.melo.wechat.model.dto.ServiceResult;
import com.melo.wechat.model.entity.LikeList;
import com.melo.wechat.model.entity.User;
import com.melo.wechat.service.inter.LikeListService;
import com.melo.wechat.utils.proxy.DaoProxy;

import java.util.LinkedList;

/**
 * @Description: 点赞记录逻辑实现类
 * @author: Jun
 * @date: 16:09 2021/5/17
 */
public class LikeListServiceImpl implements LikeListService {

    /**
     * 代理对象类
     */
    LikeListDao likeListDao = DaoProxy.getProxyInstance(LikeListDaoImpl.class);
    UserDao userDao = DaoProxy.getProxyInstance(UserDaoImpl.class);
    MomentDao momentDao = DaoProxy.getProxyInstance(MomentDaoImpl.class);

    @Override
    public ServiceResult showLikeUser(Integer momentId){
        ServiceResult result = new ServiceResult();
        LinkedList<User> likeUsers = new LinkedList<>();
        LinkedList<Integer> userIds = likeListDao.listUserIdByMomentId(momentId);
        for(Integer userId:userIds){
            User user = userDao.getUserById(userId);
            likeUsers.add(user);
        }
        if(!likeUsers.isEmpty()){
            result.setFlag(true);
        }
        result.setData(likeUsers);
        return result;
    }


    @Override
    public ServiceResult like(LikeList likeList) {
        //新增点赞记录
        likeListDao.insert(likeList);
        //增加点赞数
        momentDao.increaseLike(likeList.getMomentId());
        return new ServiceResult(true);
    }

    @Override
    public ServiceResult unLike(LikeList likeList) {
        //减少点赞数
        momentDao.decreaseLike(likeList.getMomentId());
        //删除点赞记录
        likeListDao.delete(likeList);
        return new ServiceResult(true);
    }

    @Override
    public boolean everLike(LikeList likeList) {
        return likeListDao.everLike(likeList);
    }

}
