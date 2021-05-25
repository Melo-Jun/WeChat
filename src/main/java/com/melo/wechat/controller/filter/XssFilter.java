package com.melo.wechat.controller.filter;

import com.melo.wechat.constant.Path;
import com.melo.wechat.constant.Uri;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: Xss过滤器
 * @author: Jun
 * @date: 9:00 2021/5/23
 */
@WebFilter(
        filterName = "XssFilter",
        urlPatterns = {"/*"}, servletNames = {"/*"},
        initParams = {
                @WebInitParam(name = "ENCODING", value = "UTF-8")
        }
)
public class XssFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)throws IOException, ServletException
    {
        /*
        强转
         */
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String uri = req.getRequestURI();
        String contextPath = req.getContextPath();
        String path = uri.substring(contextPath.length());
            if (uri.contains(Uri.MOMENT.getUri())||uri.contains(Uri.UPLOAD.getUri())|| path.endsWith(Path.PNG.getPath()) || path.endsWith(Path.JPG.getPath()) )
            {
                chain.doFilter(req, resp);
            }else {
                chain.doFilter(new XssRequestWrapper((HttpServletRequest) request), response);
            }
    }
}
