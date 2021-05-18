package com.melo.wechat.controller;

import com.melo.wechat.constant.Status;
import com.melo.wechat.controller.abs.BaseServlet;
import com.melo.wechat.model.dto.ServiceResult;
import com.melo.wechat.model.entity.User;
import com.melo.wechat.service.impl.UserServiceImpl;
import com.melo.wechat.service.inter.UserService;
import com.melo.wechat.utils.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import static com.melo.wechat.utils.BeanUtils.parameter2Object;
import static com.melo.wechat.utils.JsonUtils.sendJsonObject;

/**
 * @Description: 登录相关Servlet
 * @author: Jun
 * @date: 10:35 2021/5/2
 */
@WebServlet("/user")
public class UserServlet extends BaseServlet {

    /**
     * Cookie存活时间
     */
    private final int AUTO_LOGIN_AGE = 60 * 60 * 24 * 30;

    /**
     * 相关操作类
     */
    private final UserService userService = (UserService) new ProxyUtils().newProxyInstance(new UserServiceImpl());


    /**
     * @param request
     * @param response
     * @Description: 发送验证码
     * @date: 10:44 2021/5/2
     * @return: com.melo.wechat.model.dto.Result
     */
    public void sendCheckCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String code = UUIDUtils.getUUID().substring(0, 4);
        //将验证码存到session
        HttpSession session = request.getSession();
        session.setAttribute("code", code);

        ServiceResult serviceResult = new ServiceResult();
        MailUtils mailUtils = new MailUtils(email, code);
        mailUtils.start();
        serviceResult.setMessage("发送成功,请注意查收");
        sendJsonObject(response, serviceResult);
    }

    /**
     * @Description: 判断邮箱验证码是否正确
     * @param request
     * @param response
     * @date: 10:50 2021/5/18
     * @return: boolean
     */
    public boolean judgeCheckCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        HttpSession session = request.getSession();
        String checkCode = (String) session.getAttribute("code");
        return code.equals(checkCode);
    }

        /**
         * @param request
         * @param response
         * @Description: 注册业务
         * @date: 20:24 2021/5/2
         * @return: void
         */
    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServiceResult serviceResult = new ServiceResult();
        User user = parameter2Object(request.getParameterMap(), User.class);
        if(judgeCheckCode(request,response)){
            serviceResult = userService.register(user);
        } else {
            serviceResult.setMessage(Status.WRONG_CHECKCODE.getMessage());
        }
        sendJsonObject(response, serviceResult);

    }

    /**
     * @Description: 登录业务
     * @param request
     * @param response
     * @date: 10:50 2021/5/18
     * @return: void
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = parameter2Object(request.getParameterMap(), User.class);
        ServiceResult serviceResult = new ServiceResult();
        //先获取生成的验证码
        String code = request.getParameter("checkCode");
        //获取session中的验证码
        HttpSession session = request.getSession();
        String checkCode = (String) session.getAttribute("checkCode");
        //先判断验证码
        if (!code.equalsIgnoreCase(checkCode)) {
            serviceResult.setMessage(Status.WRONG_CHECKCODE.getMessage());
        } else {
            serviceResult = userService.login(user);
            if (serviceResult.flag) {
                session.setAttribute("login", user);
            }
            //判断是否要记住登录状态
            String checkbox = request.getParameter("checkbox");
            if ("true".equals(checkbox)) {
                setAutoLoginCookie(response, request, user.getId());
            }
        }
        sendJsonObject(response, serviceResult);

    }


    /**
     * 设置用于自动登陆的cookie
     *
     * @param userId 用户id
     * @author Jun
     * @date 2021-4-28
     */
    private void setAutoLoginCookie(HttpServletResponse resp, HttpServletRequest req, Integer userId) throws ServletException, IOException {
        Cookie cookie = new Cookie("user_id", userId.toString());
        cookie.setMaxAge(AUTO_LOGIN_AGE);
        cookie.setPath(req.getContextPath());
        resp.addCookie(cookie);
    }

    /**
     * 自动登录
     *
     * @param resp
     * @param req
     * @throws ServletException
     * @throws IOException
     */
    public void autoLogin(HttpServletResponse resp, HttpServletRequest req) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session == null || session.getAttribute("login") == null) {
            Cookie[] cookies = req.getCookies();
            if (cookies != null && cookies.length > 0) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("user_id")) {
                        String temp = cookie.getValue();
                        Integer userId = Integer.parseInt(temp);
                        User user = userService.getUserById(userId);
                        session.setAttribute("login", user);
                    }
                }
            }
        }
    }

    /**
     * @param request
     * @param response
     * @Description: 搜索用户
     * @date: 19:46 2021/5/4
     * @return: void
     */
    public void searchUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchText = request.getParameter("searchText");
        User user = new User();
        user.setWechatId(searchText);
        user.setUserName(searchText);
        ServiceResult result = userService.searchUser(user);
        sendJsonObject(response, result);
    }

    /**
     * @Description: 显示用户个人信息
     * @param request
     * @param response
     * @date: 22:14 2021/5/13
     * @return: void
     */
    public void showUserInform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id=Integer.parseInt(request.getParameter("id"));
        User user = userService.getUserById(id);
        ServiceResult result = new ServiceResult();
        result.setData(user);
        sendJsonObject(response,result);
    }

        /**
         * @Description: 更新用户信息
         * @param request
         * @param response
         * @date: 23:54 2021/5/12
         * @return: void
         */
    public void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = parameter2Object(request.getParameterMap(), User.class);
        User oldUser = new User();
        oldUser.setId(user.getId());
        oldUser.setPassword(request.getParameter("oldPass"));
        ServiceResult result = userService.updateUser(oldUser, user);
        sendJsonObject(response,result);
    }

    /**
     * @Description: 重设密码
     * @param request
     * @param response
     * @date: 10:51 2021/5/18
     * @return: void
     */
    public void resetPass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServiceResult result = new ServiceResult();
        if (judgeCheckCode(request, response)) {
            String password=request.getParameter("password");
            String oldPass = request.getParameter("oldPass");
            //根据绑定的邮箱找到该用户id来更新信息
            Integer id = userService.getIdByEmail(request.getParameter("email"));
            User oldUser = new User(id,oldPass);
            User newUser = new User(id,password);
            result = userService.updateUser(oldUser, newUser);
        }else {
            result.setMessage(Status.WRONG_CHECKCODE.getMessage());
        }
        sendJsonObject(response, result);
    }
}