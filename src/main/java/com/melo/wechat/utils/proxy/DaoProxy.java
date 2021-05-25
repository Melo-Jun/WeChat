package com.melo.wechat.utils.proxy;

import com.melo.wechat.annotation.log.LogInfo;
import com.melo.wechat.utils.log.LogInfoUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Handler;
import java.util.logging.Logger;

/**
 * @Description: Dao层代理对象工厂
 * @author: Jun
 * @date: 21:09 2021/4/27
 */
public class DaoProxy implements InvocationHandler {

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
    private static HashMap<Class<?>, DaoProxy> daoProxys = new HashMap<Class<?>, DaoProxy>();

    /**
     *将被代理的对象传入获得它的类加载器和实现接口作为Proxy.newProxyInstance方法的参数。
     */
    public synchronized  static<T> T getProxyInstance(Class<T> clazz){
        //先从Map中获取
        DaoProxy daoProxy = daoProxys.get(clazz);
        //取不到则生成
        if(null == daoProxy){
            daoProxy = new DaoProxy();
            try {
                T tar = clazz.newInstance();
                daoProxy.setTarget(tar);
                daoProxy.setProxy(Proxy.newProxyInstance(tar.getClass().getClassLoader(),
                        tar.getClass().getInterfaces(), daoProxy));
            } catch (Exception e) {
                e.printStackTrace();
            }
            daoProxys.put(clazz, daoProxy);

        }

        return (T)daoProxy.getProxy();
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
            //取得注解的值，输出日志
            logger.info(new Date().toString() + " ---> " + logInfo.value());
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
