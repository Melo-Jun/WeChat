package com.melo.wechat.dao.inter;

import com.melo.wechat.model.entity.User;

import java.util.LinkedList;

/**
 * @author Jun
 * @program WeChat
 * @description 用户类数据库操作接口
 * @date 2021-4-25
 */
public interface UserDao extends BaseDao {

    /**
     * 验证是否已存在该用户
     *
     * @param user 用户实体类
     * @return boolean 是否有效
     */
    boolean isExistEmail(User user);


    /**
     * 验证密码是否正确
     *
     * @param user 用户实体类
     * @return boolean 密码是否正确
     * @notice 需要将输入进来的密码Md5解码看对不对应数据库中的字段
     */
    boolean judgePass(User user);

    /**
     * 增加用户
     *
     * @param user 用户实体类
     * @return boolean 是否增加成功
     */
    boolean addUser(User user);

    /**
     * 修改用户
     *
     * @param user 用户实体类
     * @return int 影响的行数
     */
    int updateUser(User user);

    /**
     * 根据id展示用户名
     *
     * @param user 用户实体类
     * @return String 用户名
     */
    String showUserName(User user);

    /**
     * 展示用户所有信息
     *
     * @param user 用户实体类
     * @return 用户信息链表
     */
    LinkedList<User> showUserAll(User user);

    /**
     * @Description: 通过id返回一个用户
     * @param userId 用户id
     * @date: 13:22 2021/5/6
     * @return: com.melo.wechat.model.entity.User
     */
     User getUserById(Integer userId);
}
