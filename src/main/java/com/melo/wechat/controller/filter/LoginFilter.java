package com.melo.wechat.controller.filter;

import com.melo.wechat.constant.Path;
import com.melo.wechat.constant.Uri;
import com.melo.wechat.constant.WebPage;
import com.melo.wechat.controller.UserServlet;
import com.melo.wechat.model.dto.ServiceResult;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.melo.wechat.utils.JsonUtils.sendJsonObject;

/**
 * @Description: 登录过滤器
 * @author: Jun
 * @date: 13:34 2021/5/6
 */
@WebFilter(
        filterName = "LoginFilter",
        urlPatterns = {"/*"}, servletNames = {"/*"}
)
public class LoginFilter implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        /*
        强转
         */
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String uri = req.getRequestURI();
        String contextPath = req.getContextPath();
        String path = uri.substring(contextPath.length());
        //尝试自动登录(登录成功会自动跳转)
        new UserServlet().autoLogin(resp,req);
        HttpSession session = req.getSession();

        //若还未登录(可能是自动登录失败)
        if (session == null || session.getAttribute("login") == null) {
            //放行与登录和注册以及找回密码有关的资源
            if (uri.contains(Uri.CHECK_CODE.getUri())||
                    uri.contains(Uri.USER.getUri())||
                    (WebPage.LOGIN_JSP.toString()).equalsIgnoreCase(path) ||
                    (WebPage.REGISTER_JSP.toString()).equalsIgnoreCase(path) ||
                    (WebPage.RESETPASS_JSP.toString()).equalsIgnoreCase(path) ||
                    path.endsWith(Path.PNG.getPath()) || path.endsWith(Path.JPG.getPath()) || path.endsWith(".js"))
            {
                filterChain.doFilter(req, resp);
            }else {
                sendJsonObject(resp,new ServiceResult("你还未登录,请先登录"));
                resp.sendRedirect("/melo/login.jsp");
            }
        }
        //若已登录则直接放行
        else {
            //若访问的是登录界面,则跳转到首页
            if ((WebPage.LOGIN_JSP.toString().equalsIgnoreCase(path))) {
                resp.sendRedirect("/melo/wechat.jsp");
            }
            filterChain.doFilter(req, resp);
        }

    }

    @Override
    public void destroy() {

    }
}
