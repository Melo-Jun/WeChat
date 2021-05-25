package com.melo.wechat.controller;

import com.melo.wechat.constant.Status;
import com.melo.wechat.controller.abs.BaseServlet;
import com.melo.wechat.model.dto.ServiceResult;
import com.melo.wechat.model.entity.User;
import com.melo.wechat.service.impl.UserServiceImpl;
import com.melo.wechat.service.inter.UserService;
import com.melo.wechat.utils.*;
import com.melo.wechat.utils.proxy.ServiceProxy;

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
     * 相关操作类
     */
    private final UserService userService = ServiceProxy.getProxyInstance(UserServiceImpl.class);


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
     * @param request
     * @param response
     * @Description: 判断邮箱验证码是否正确
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
        if (judgeCheckCode(request, response)) {
            serviceResult = userService.register(user);
        } else {
            serviceResult.setMessage(Status.WRONG_CHECKCODE.getMessage());
        }
        sendJsonObject(response, serviceResult);

    }

    /**
     * @param request
     * @param response
     * @Description: 登录业务
     * @date: 10:50 2021/5/18
     * @return: void
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = parameter2Object(request.getParameterMap(), User.class);
        ServiceResult serviceResult = new ServiceResult();
        //先获取前台输过来的
        String code = request.getParameter("code");
        //获取session中的验证码
        HttpSession session = request.getSession();
        String checkCode = (String) session.getAttribute("checkCode");
        //先判断验证码
        if (!code.equalsIgnoreCase(checkCode)) {
            serviceResult.setMessage(Status.WRONG_CHECKCODE.getMessage());
        } else {
            serviceResult = userService.login(user);
            //若登录成功
            if (serviceResult.flag) {
                User loginUser=(User)serviceResult.getData();
                session.setAttribute("login", loginUser);
                //判断是否要记住登录状态
                String checkbox = request.getParameter("checkbox");
                if ("true".equals(checkbox)) {
                    setAutoLoginCookie(response, request, loginUser.getId());
                }
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
        Cookie cookie = new Cookie("userId", userId.toString());
        cookie.setMaxAge(60 * 60 * 24 * 30);
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
                    if (cookie.getName().equals("userId") && cookie.getValue() != null) {
                        Integer userId = Integer.parseInt(cookie.getValue());
                        User user = userService.getUserById(userId);
                        session.setAttribute("login", user);
                    }
                }
            }
        }
    }

    /**
     * @Description: 退出登录
     * @param request
     * @param response
     * @date: 19:45 2021/5/25
     * @return: void
     */
    public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        HttpSession session = request.getSession();
        if (session != null || session.getAttribute("login") != null) {
            //删除自动登录cookie
            Cookie[] cookies = request.getCookies();
            if (cookies != null && cookies.length > 0) {
                Cookie cookie = new Cookie("userId", null);
                cookie.setMaxAge(0);
                cookie.setPath(request.getContextPath());
                response.addCookie(cookie);
            }
            //删除session
            session.invalidate();
        }
    }

    /**
     * @param request
     * @param response
     * @Description: 游客模式
     * @date: 10:50 2021/5/18
     * @return: void
     */
    public void visit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String wechatId = UUIDUtils.getUniqueNumber();
        ServiceResult serviceResult = userService.visit();
        //若游客登录成功
            if (serviceResult.flag) {
                User visitor=(User)serviceResult.getData();
                session.setAttribute("login", visitor);
            }
        sendJsonObject(response, serviceResult);

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
        ServiceResult result = userService.searchUser(searchText);
        sendJsonObject(response, result);
    }

    /**
     * @Description: 判断是否为游客
     * @param request
     * @param response
     * @date: 19:44 2021/5/25
     * @return: void
     */
    public void isVisitor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        ServiceResult result = userService.isVisitor(id);
        sendJsonObject(response, result);
    }

    /**
     * @param request
     * @param response
     * @Description: 显示用户个人信息
     * @date: 22:14 2021/5/13
     * @return: void
     */
    public void showUserInform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        User user = userService.getUserById(id);
        ServiceResult result = new ServiceResult();
        result.setData(user);
        sendJsonObject(response, result);
    }

    /**
     * @param request
     * @param response
     * @Description: 更新用户信息
     * @date: 23:54 2021/5/12
     * @return: void
     */
    public void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = parameter2Object(request.getParameterMap(), User.class);
        User oldUser = new User();
        oldUser.setId(user.getId());
        oldUser.setPassword(request.getParameter("oldPass"));
        ServiceResult result = userService.updateUser(oldUser, user);
        sendJsonObject(response, result);
    }

    /**
     * @param request
     * @param response
     * @Description: 重设密码
     * @date: 10:51 2021/5/18
     * @return: void
     */
    public void resetPass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServiceResult result = new ServiceResult();
        if (judgeCheckCode(request, response)) {
            String password = request.getParameter("password");
            String oldPass = request.getParameter("oldPass");
            //根据绑定的邮箱找到该用户id来更新信息
            Integer id = userService.getIdByEmail(request.getParameter("email"));
            User oldUser = new User(id, oldPass);
            User newUser = new User(id, password);
            result = userService.updateUser(oldUser, newUser);
        } else {
            result.setMessage(Status.WRONG_CHECKCODE.getMessage());
        }
        sendJsonObject(response, result);
    }

    /**
     * @Description: 封号
     * @param request
     * @param response
     * @date: 19:44 2021/5/25
     * @return: void
     */
    public void blockUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = parameter2Object(request.getParameterMap(), User.class);
        ServiceResult result = userService.blockUser(user);
        sendJsonObject(response, result);
    }

    /**
     * @Description: 取消封号
     * @param request
     * @param response
     * @date: 19:45 2021/5/25
     * @return: void
     */
    public void unBlockUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = parameter2Object(request.getParameterMap(), User.class);
        ServiceResult result = userService.unBlockUser(user);
        sendJsonObject(response, result);
    }
}