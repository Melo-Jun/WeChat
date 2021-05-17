package com.melo.wechat.utils;

import com.melo.wechat.annotation.Insert;
import com.melo.wechat.exception.DaoException;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Description: 代理类
 * @author: Jun
 * @date: 11:06 2021/5/3
 */
public class ProxyUtils implements InvocationHandler {
    /**
     * 被代理的对象
     */
    private Object targetObject;
    /**
    *将被代理的对象传入获得它的类加载器和实现接口作为Proxy.newProxyInstance方法的参数。
     */
    public Object newProxyInstance(Object targetObject){
        this.targetObject = targetObject;
        return Proxy.newProxyInstance(targetObject.getClass().getClassLoader(),targetObject.getClass().getInterfaces(),this);
    }
    /**
     * 该方法在代理对象调用方法时调用
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(method.getAnnotation(Insert.class)!=null) {
            StringBuilder stringBuilder = new StringBuilder("" + method.getAnnotation(Insert.class).value());
            for (Object arg : args) {
                stringBuilder.append(arg).append(",");
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            //暂时打印日志
            System.out.println(stringBuilder);
            try {
                return method.invoke(targetObject, args);
            } catch (Exception e) {
                throw new DaoException(stringBuilder+"失败");
            }
        }
        return method.invoke(targetObject,args);
    }
}
