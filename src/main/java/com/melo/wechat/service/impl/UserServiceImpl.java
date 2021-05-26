package com.melo.wechat.service.impl;

import com.melo.wechat.constant.Status;
import com.melo.wechat.dao.impl.*;
import com.melo.wechat.dao.inter.*;
import com.melo.wechat.model.dto.ServiceResult;
import com.melo.wechat.model.entity.*;
import com.melo.wechat.service.inter.FriendService;
import com.melo.wechat.service.inter.UserService;
import com.melo.wechat.utils.proxy.ServiceProxy;
import com.melo.wechat.utils.UUIDUtils;
import com.melo.wechat.utils.proxy.DaoProxy;

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
    UserDao userDao= DaoProxy.getProxyInstance(UserDaoImpl.class);
    ChatDao chatDao= DaoProxy.getProxyInstance(ChatDaoImpl.class);
    FriendService friendService= ServiceProxy.getProxyInstance(FriendServiceImpl.class);
    FriendDao friendDao= DaoProxy.getProxyInstance(FriendDaoImpl.class);


    /**
     * 判断登录是否成功
     * @param user 用户
     * @return Result 返回结果封装类
     */
    @Override
    public ServiceResult login(User user) {
        user.setPassword(getDigest(user.getPassword()));
        if(judgePass(user)){
            Integer id = userDao.getId(user);
            user=getUserById(id);
            //判断用户是否有效
            if(user.getValidity()==0){
                return new ServiceResult(Status.IS_BLOCKED_USER.getMessage(),false);
            }
            return new ServiceResult(true,Status.SUCCEED_LOGIN.getMessage(),user);
        }
            return new ServiceResult(Status.FAILED_LOGIN.getMessage());

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
        user.setPassword(getDigest(user.getPassword()));
        if(userDao.isExistEmail(user)){
            return new ServiceResult(Status.IS_EXIST_EMAIL.getMessage(),false);
        }else {
            userDao.addUser(user);
            Integer id = getIdByEmail(user.getEmail());
            //默认加管理员好友
            //建立属于两人的聊天会话框
            String chatNumber= UUIDUtils.getUniqueNumber();
            friendDao.addFriend(new Friend(0,id));
            friendDao.addFriend(new Friend(id,0));
            chatDao.insertChat(new Chat(chatNumber));
            //为好友关系设置ChatId(触发器中间表会新增两条记录)
            friendService.updateFriendChat(id,0,chatDao.getIdByNumber(chatNumber));
            return new ServiceResult(Status.SUCCEED_REGISTER.getMessage(),true);
        }
    }

    /**
     * @Description: 搜索用户
     * @param searchText 用户名或微信号
     * @date: 9:55 2021/5/5
     * @return: com.melo.wechat.model.dto.ServiceResult
     */
    @Override
    public ServiceResult searchUser(String searchText){
        StringBuilder text = new StringBuilder("%"+searchText+"%");
        User user = new User(text.toString(), text.toString());
        LinkedList<User> users = userDao.showUserAll(user);
        if(users.isEmpty()){
           return new ServiceResult(Status.NO_USER.getMessage(),false);
        }else {
           return new ServiceResult(users,true);
        }
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

    @Override
    public ServiceResult blockUser(User user) {
        if(userDao.updateUser(user)==1){
            return new ServiceResult(Status.SUCCESS.getMessage(),true);
        }
        return new ServiceResult(false);
    }

    @Override
    public ServiceResult unBlockUser(User user) {
        if(userDao.updateUser(user)==1){
            return new ServiceResult(Status.SUCCESS.getMessage(),true);
        }
        return new ServiceResult(false);
    }

    @Override
    public ServiceResult visit() {
        //为游客设置唯一的微信号
        String wechatId = UUIDUtils.getUniqueNumber();
        User user = new User(wechatId);
        user.setEmail(Status.VISITOR_EMAIL.getMessage());
        if(userDao.addUser(user)){
            User visitor = userDao.getVisitor(wechatId, Status.VISITOR_EMAIL.getMessage());
            return new ServiceResult(true,Status.WELCOME_VISITOR.getMessage(), visitor);
        }
        return new ServiceResult(Status.SYSTEM_ERROR.getMessage(),false);
    }

    @Override
    public ServiceResult isVisitor(Integer id) {
        User user = new User();
        user.setId(id);
        if(userDao.isVisitor(user)){
            return new ServiceResult(true);
        }
        return new ServiceResult(false);
    }

    /**
     * @Description: 重设密码
     * @param id 用户id
     * @param password 用户新密码
     * @date: 11:18 2021/5/26
     * @return: com.melo.wechat.model.dto.ServiceResult
     */
    @Override
    public ServiceResult resetPass(Integer id, String password) {
        User user = new User(id, getDigest(password));
        if(userDao.update(user)==1){
            return new ServiceResult(Status.SUCCESS.getMessage(),true);
        }
        return new ServiceResult(false);
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
