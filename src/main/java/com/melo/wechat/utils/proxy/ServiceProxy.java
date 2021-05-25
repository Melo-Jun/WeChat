package com.melo.wechat.utils.proxy;

import com.melo.wechat.annotation.log.LogInfo;
import com.melo.wechat.utils.log.LogInfoUtil;

import java.util.HashMap;
import java.util.logging.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Date;



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
    public synchronized  static<T> T getProxyInstance(Class<T> clazz){
        //先从Map中获取
        ServiceProxy serviceProxy = serviceProxys.get(clazz);
        //取不到则生成
        if(null == serviceProxy){
            serviceProxy = new ServiceProxy();
            try {
                T tar = clazz.newInstance();
                serviceProxy.setTarget(tar);
                serviceProxy.setProxy(Proxy.newProxyInstance(tar.getClass().getClassLoader(),
                        tar.getClass().getInterfaces(), serviceProxy));
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

        if (method.isAnnotationPresent(LogInfo.class)) {

            Logger logger= LogInfoUtil.getLogger(target.getClass().getName());
            //取得注解
            LogInfo logInfo = method.getAnnotation(LogInfo.class);
            //取得注解以及参数的值，输出日志
            StringBuilder msg=new StringBuilder(new Date().toString() + " ---> " + logInfo.value());
            for(Object arg:args){
                msg.append(arg).append(",");
            }
            msg.deleteCharAt(msg.length()-1);
            logger.info(msg.toString());
            for(Handler h:logger.getHandlers())
            {
                h.close();
            }
        }
        return method.invoke(target,args);
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

