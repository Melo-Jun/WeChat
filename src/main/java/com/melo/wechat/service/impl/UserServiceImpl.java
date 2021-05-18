package com.melo.wechat.service.impl;

import com.melo.wechat.constant.Status;
import com.melo.wechat.dao.impl.NoticeDaoImpl;
import com.melo.wechat.dao.impl.UserDaoImpl;
import com.melo.wechat.dao.inter.NoticeDao;
import com.melo.wechat.dao.inter.UserDao;
import com.melo.wechat.model.dto.ServiceResult;
import com.melo.wechat.model.entity.User;
import com.melo.wechat.service.inter.UserService;
import com.melo.wechat.utils.ProxyUtils;

import java.util.LinkedList;

import static com.melo.wechat.utils.Md5Utils.getDigest;

/**
 * @Description: 用户业务相关逻辑实现类
 * @author: Jun
 * @date: 9:54 2021/5/5
 */
public class UserServiceImpl implements UserService {

    /**
     * 代理对象类
     */
    UserDao userDao= (UserDao) new ProxyUtils().newProxyInstance(UserDaoImpl.getInstance());
    NoticeDao noticeDao=(NoticeDao) new ProxyUtils().newProxyInstance(NoticeDaoImpl.getInstance());

    /**
     * 判断登录是否成功
     * @param user 用户
     * @return Result 返回结果封装类
     */
    @Override
    public ServiceResult login(User user) {
        ServiceResult serviceResult = new ServiceResult();
        user.setPassword(getDigest(user.getPassword()));
        if(judgePass(user)){
            serviceResult.setMessage(Status.SUCCEED_LOGIN.getMessage());
            serviceResult.setFlag(true);
            user.setId(userDao.getId(user));
        }else {
            serviceResult.setMessage(Status.FAILED_LOGIN.getMessage());
        }
        return serviceResult;
    }

    /**
     * @Description: 判断密码
     * @param user 用户
     * @date: 10:33 2021/5/18
     * @return: boolean
     */
    @Override
    public boolean judgePass(User user){
        return userDao.judgePass(user);
    }

    /**
     * @Description: 判断注册是否成功
     * @param user 用户
     * @date: 14:37 2021/5/3
     * @return: com.melo.wechat.model.dto.Result 返回结果封装类
     */
    @Override
    public ServiceResult register(User user){
        ServiceResult serviceResult =new ServiceResult();
        user.setPassword(getDigest(user.getPassword()));
        if(userDao.isExistEmail(user)){
            serviceResult.setMessage(Status.IS_EXIST_EMAIL.getMessage());
        }else {
            serviceResult.setMessage(Status.SUCCEED_REGISTER.getMessage());
            serviceResult.setFlag(true);
            userDao.addUser(user);
        }
        return serviceResult;
    }

    /**
     * @Description: 搜索用户
     * @param user 用户
     * @date: 9:55 2021/5/5
     * @return: com.melo.wechat.model.dto.ServiceResult
     */
    @Override
    public ServiceResult searchUser(User user){
        ServiceResult serviceResult =new ServiceResult();
        LinkedList<User> users = userDao.showUserAll(user);
        if(users.isEmpty()){
            serviceResult.setFlag(false);
            serviceResult.setMessage(Status.NO_USER.getMessage());
        }else {
            serviceResult.setData(users);
            serviceResult.setFlag(true);
        }
        return serviceResult;
    }

    /**
     * @Description: 根据id获取User对象
     * @param userId user的Id
     * @date: 10:33 2021/5/18
     * @return: com.melo.wechat.model.entity.User
     */
    @Override
    public User getUserById(Integer userId){
        return userDao.getUserById(userId);
    }

    /**
     * @Description: 通过邮箱找到该用户的id
     * @param email 邮箱
     * @date: 11:23 2021/5/18
     * @return: java.lang.Integer
     */
    @Override
    public Integer getIdByEmail(String email){
        User user = new User();
        user.setEmail(email);
        return userDao.getId(user);
    }

    /**
     * @Description: 更新用户信息
     * @param oldUser 旧用户
     * @param newUser 新用户
     * @date: 10:34 2021/5/18
     * @return: com.melo.wechat.model.dto.ServiceResult
     */
    @Override
    public ServiceResult updateUser(User oldUser, User newUser){
        ServiceResult result = new ServiceResult();
        //将密码加密处理后再进入数据库
        oldUser.setPassword(getDigest(oldUser.getPassword()));
        newUser.setPassword(getDigest(newUser.getPassword()));
        if(judgePass(oldUser)) {
            if(userDao.updateUser(newUser)==1){
                result.setFlag(true);
                result.setMessage(Status.SUCCESS.getMessage());
            }
        }else {
            result.setMessage(Status.WRONG_OLD_PASS.getMessage());
        }
        return result;
    }


    /*
     **************************************************************
     *               检查用户信息
     **************************************************************
     */

    public boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        String regex = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}";
        return email.matches(regex);
    }


    private boolean isValidWechatId(String wechatId) {
        if (wechatId == null || wechatId.trim().isEmpty()) {
            return false;
        }
        String regex = "[\\w_]{6,20}$";
        return wechatId.matches(regex);
    }

    private boolean isValidPassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            return false;
        }
        String regex = "[\\w_]{6,20}$";
        return password.matches(regex);
    }
}
