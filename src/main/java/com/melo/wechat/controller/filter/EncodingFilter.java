
package com.melo.wechat.controller.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Jun
 * @program WeChat
 * @description 负责设置编码格式
 * @date 2021-4-26
 */

@WebFilter(
        filterName = "EncodingFilter",
        urlPatterns = {"/*"}, servletNames = {"/*"},
        initParams = {
                @WebInitParam(name = "ENCODING", value = "UTF-8")
        }
)
public class EncodingFilter implements Filter {

    /**
     * 编码
     */
    private String ENCODING = null;

    @Override
    public void init(FilterConfig config) {
        this.ENCODING = config.getInitParameter("ENCODING");
    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        /*
        强转
         */
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
            /*
            设置编码并放行
             */
            req.setCharacterEncoding(ENCODING);
            resp.setContentType("text/html;charset=utf-8");
            resp.setCharacterEncoding(ENCODING);
            filterChain.doFilter(servletRequest, servletResponse);
    }
}

