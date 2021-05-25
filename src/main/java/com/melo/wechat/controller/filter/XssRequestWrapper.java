package com.melo.wechat.controller.filter;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import static com.melo.wechat.utils.StringUtils.delHtmlTag;
/**
 * @Description:  ServletRequest 的包装类
 * @author: Jun
 * @date: 9:00 2021/5/23
 */
public class XssRequestWrapper extends HttpServletRequestWrapper {

    public XssRequestWrapper(HttpServletRequest servletRequest) {
        super(servletRequest);
    }

    @Override
    public String[] getParameterValues(String parameter) {
        String[] values = super.getParameterValues(parameter);
        if (values == null) {
            return null;
        }
        int count = values.length;
        String[] encodedValues = new String[count];
        for (int i = 0; i < count; i++) {
            encodedValues[i] = delHtmlTag(values[i]);
        }
        return encodedValues;
    }

    @Override
    public String getParameter(String parameter) {
        String value = super.getParameter(parameter);
        return delHtmlTag(value);
    }

    @Override
    public String getHeader(String name) {
        String value = super.getHeader(name);
        return delHtmlTag(value);
    }
}

