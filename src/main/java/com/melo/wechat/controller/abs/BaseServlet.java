package com.melo.wechat.controller.abs;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @Description: 所有servlet的父类,统一在这里根据methodName反射调用方法
 * @author: Jun
 * @date: 10:52 2021/5/25
 */
public class BaseServlet extends HttpServlet {

    /**
     * 创建一个session对象，让BaseServlet的子类可以直接拿来用
     */
    public HttpSession session = null;

    /**
     * @Description: 重写doService方法,分配method
     * @param request
     * @param response
     * @date: 9:46 2021/5/9
     * @return: void
     */
    @Override
    protected void service(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {

        session=request.getSession();

        // 1. 获取method参数的值，它是用户想调用的方法名称
        String methodName = request.getParameter("method");
        //如果method参数为空
        if(methodName == null){
            throw new ServletException("请传递method参数以确定您要调用的方法！");
        }
        Method method = null;

        try{
            method = this.getClass().getMethod(methodName , HttpServletRequest.class , HttpServletResponse.class);

        }catch(NoSuchMethodException e){
            //如果没有找到就说明你写的Servlet中没有此方法
            throw new ServletException("您调用的"+methodName+
                    "(HttpServletRequest request , HttpServletResponse response)方法不存在",e);
        }

        // 3. 通过Method对象来调用它
        try{
            method.invoke(this,request,response);

        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }



}
