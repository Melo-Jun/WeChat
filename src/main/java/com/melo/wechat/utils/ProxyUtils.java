package com.melo.wechat.utils;

import com.melo.wechat.annotation.log.LogInfo;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Date;

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
        Logger logger = LogInfoUtil.getLogger(ProxyUtils.class);
        // 检查该方法上是否有LogInf注解
        if (method.isAnnotationPresent(LogInfo.class)) {
            //取得注解
            LogInfo logInfo = method.getAnnotation(LogInfo.class);
            //取得注解的值，输出日志
            logger.info(new Date().toString() + " ---> " + logInfo.value());
        }
        return method.invoke(targetObject,args);
    }
//        if(method.getAnnotation(Insert.class)!=null) {
//            StringBuilder stringBuilder = new StringBuilder("" + method.getAnnotation(Insert.class).value());
//            for (Object arg : args) {
//                stringBuilder.append(arg).append(",");
//            }
//            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
//            //暂时打印日志
//            System.out.println(stringBuilder);
//            try {
//                return method.invoke(targetObject, args);
//            } catch (Exception e) {
//                throw new DaoException(stringBuilder+"失败");
//            }
//        }

}
