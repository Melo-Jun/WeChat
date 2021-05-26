package com.melo.wechat.service.inter;

import com.melo.wechat.annotation.log.LogInfo;
import com.melo.wechat.model.dto.ServiceResult;
import com.melo.wechat.model.entity.User;

/**
 * @Description: 用户业务相关逻辑接口
 * @author: Jun
 * @date: 9:55 2021/5/5
 */
public interface UserService {

    /**
     * @Description: 判断登录是否成功
     * @param user 用户
     * @return com.melo.wechat.model.dto.Result 返回结果封装类
     */
    @LogInfo("新用户登录->")
    ServiceResult login(User user);

    /**
     * @Description: 判断密码
     * @param user 用户
     * @date: 10:33 2021/5/18
     * @return: boolean
     */
    boolean judgePass(User user);

    /**
     * @Description: 判断注册是否成功
     * @param user 对象
     * @date: 14:37 2021/5/3
     * @return: com.melo.wechat.model.dto.Result 返回结果封装类
     */
    @LogInfo("新用户注册->")
    ServiceResult register(User user);

    /**
     * @Description: 搜索用户
     * @param searchText
     * @date: 9:55 2021/5/5
     * @return: com.melo.wechat.model.dto.ServiceResult
     */
    ServiceResult searchUser(String searchText);

    /**
     * @Description: 根据id获取User对象
     * @param userId user的Id
     * @date: 10:33 2021/5/18
     * @return: com.melo.wechat.model.entity.User
     */
    User getUserById(Integer userId);

    /**
     * @Description: 通过邮箱找到该用户的id
     * @param email 邮箱
     * @date: 11:23 2021/5/18
     * @return: java.lang.Integer
     */
    Integer getIdByEmail(String email);

    /**
     * @Description: 更新用户信息
     * @param oldUser 旧用户
     * @param newUser 新用户
     * @date: 10:34 2021/5/18
     * @return: com.melo.wechat.model.dto.ServiceResult
     */
    ServiceResult updateUser(User oldUser,User newUser);

    /**
     * @Description: 封号
     * @param user
     * @date: 0:10 2021/5/26
     * @return: com.melo.wechat.model.dto.ServiceResult
     */
    ServiceResult blockUser(User user);

    /**
     * @Description: 取消封号
     * @param user
     * @date: 0:10 2021/5/26
     * @return: com.melo.wechat.model.dto.ServiceResult
     */
    ServiceResult unBlockUser(User user);

    /**
     * @Description: 游客模式
     * @param
     * @date: 0:10 2021/5/26
     * @return: com.melo.wechat.model.dto.ServiceResult
     */
    @LogInfo("新游客登录体验微信->")
    ServiceResult visit();

    /**
     * @Description: 判断是否为游客
     * @param id
     * @date: 0:10 2021/5/26
     * @return: com.melo.wechat.model.dto.ServiceResult
     */
    ServiceResult isVisitor(Integer id);

    ServiceResult resetPass(Integer id, String password);
}
