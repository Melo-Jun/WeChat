package com.melo.wechat.dao.impl;

import com.melo.wechat.annotation.Insert;
import com.melo.wechat.annotation.Table;
import com.melo.wechat.dao.inter.UserDao;
import com.melo.wechat.model.entity.User;

import java.util.LinkedList;

/**
 * @author Jun
 * @program WeChat
 * @description 用户类数据库操作实现类
 * @date 2021-4-25
 */
public class UserDaoImpl extends BaseDaoImpl implements UserDao {

    private static UserDaoImpl instance = new UserDaoImpl();
    private UserDaoImpl (){}
    public static UserDaoImpl getInstance() {
        return instance;
    }

    /**
     * 本表对应所有字段
     */
    private final Object[] ALL_FIELD_NAME=new Object[]{"id","email","wechat_id","user_name","password",
            "avatar","type","status","validity","gmt_create", "gmt_modified"};

    /**
     * 注册时验证邮箱是否已被注册
     *
     * @param user 用户实体类
     * @return boolean 邮箱是否已被注册
     */
    @Override
    public boolean isExistEmail(User user) {
        StringBuilder sql=appendSelect(new Object[]{"email"},user,"AND");
        LinkedList<Object> emails = queryList(sql.toString(), user);
        return !emails.isEmpty();
    }


    /**
     * 验证密码是否正确
     * @param user 用户实体类
     * @notice 需要将输入进来的密码Md5解码看对不对应数据库中的字段
     * @return boolean 密码是否正确
     */
    @Override
    public boolean judgePass(User user){
        StringBuilder sql = appendSelect(new Object[]{"password"}, user,"AND");
        LinkedList<Object> password = queryList(sql.toString(), user);
        //搜不到密码
        if(password.isEmpty()){
            return false;
        }
        //md5后验证
        return password.getFirst().equals(user.getPassword());
    }

    /**
     * 增加用户
     * @param user 用户实体类
     * @return boolean 是否增加成功
     */
    @Insert()
    @Override
    public boolean addUser(User user) {
        return super.insert(user) == 1;
    }

    /**
     * 修改用户
     * @param user 用户实体类
     * @return int 影响的行数
     */
    @Override
    public int updateUser(User user){
        return super.update(user);
    }

    /**
     * 根据id展示用户名
     * @param user 用户实体类
     * @return String 用户名
     */
    @Override
    public String showUserName(User user){
        String sql="select user_name from "+User.class.getAnnotation(Table.class).name()+" where id=?";
        return queryList(sql,user).getFirst().toString();
    }

    /**
     * 展示用户所有信息
     * 用在搜索时
     * @param user 用户实体类
     * @return 用户信息链表
     */
    @Override
    public LinkedList<User> showUserAll(User user){
        StringBuilder sql=appendSelect(ALL_FIELD_NAME,user,"OR");
        return queryAll(sql.toString(),user,User.class);
    }

    /**
     * @Description: 通过id返回一个用户
     * @param userId 用户id
     * @date: 13:22 2021/5/6
     * @return: com.melo.wechat.model.entity.User
     */
    @Override
    public User getUserById(Integer userId){
        User user = new User();
        user.setId(userId);
        StringBuilder sql=appendSelect(ALL_FIELD_NAME,user,"AND");
        return getObjectById(sql.toString(),user,User.class);
    }



}
