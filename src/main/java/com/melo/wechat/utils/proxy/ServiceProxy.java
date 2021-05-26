package com.melo.wechat.utils.proxy;

import com.melo.wechat.annotation.log.LogInfo;
import com.melo.wechat.utils.log.LogInfoUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.logging.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


/**
 * @Description: Service代理对象工厂
 * @author: Jun
 * @date: 11:06 2021/5/3
 */
public class ServiceProxy implements InvocationHandler {

    /**
     * 代理目标
     */
    private Object target;
    /**
     * 代理对象
     */
    private Object proxy;
    /**
     * service动态代理类Map
     */
    private static HashMap<Class<?>, ServiceProxy> serviceProxys = new HashMap<Class<?>, ServiceProxy>();

    /**
    *将被代理的对象传入获得它的类加载器和实现接口作为Proxy.newProxyInstance方法的参数。
     */
    public static<T> T getProxyInstance(Class<T> clazz){
        //先从Map中获取
        ServiceProxy serviceProxy = serviceProxys.get(clazz);
        //取不到则为第一次创建(此处有没有必要双检锁呢?)
        if(serviceProxy == null){
            synchronized (ServiceProxy.class) {
                if( serviceProxy == null) {
                    serviceProxy = new ServiceProxy();
                }
            }
            try {
                T target = clazz.newInstance();
                serviceProxy.setTarget(target);
                serviceProxy.setProxy(Proxy.newProxyInstance(target.getClass().getClassLoader(),
                        target.getClass().getInterfaces(), serviceProxy));
            } catch (Exception e) {
                e.printStackTrace();
            }
            serviceProxys.put(clazz, serviceProxy);
        }
        return (T)serviceProxy.getProxy();
    }
    /**
     * 该方法在代理对象调用方法时调用
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Object returnObject=null;

        if (method.isAnnotationPresent(LogInfo.class)) {

            Logger logger= LogInfoUtil.getLogger(target.getClass().getName());
            //取得注解
            LogInfo logInfo = method.getAnnotation(LogInfo.class);
            //取得注解以及参数的值，输出日志
            returnObject=method.invoke(target,args);
            logger.info(   logInfo.value()
                    + "____invoke method: " + method.getName()
                    + "; args: " + (null == args ? "null" : Arrays.asList(args).toString())
                    + "; return: " + returnObject);
            for(Handler h:logger.getHandlers())
            {
                h.close();
            }
        }else {
            returnObject = method.invoke(target,args);
        }
        return returnObject;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public Object getProxy() {
        return proxy;
    }

    public void setProxy(Object proxy) {
        this.proxy = proxy;
    }
}

