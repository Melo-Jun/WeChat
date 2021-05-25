package com.melo.wechat.utils.proxy;

import com.melo.wechat.annotation.dao.Transaction;
import com.melo.wechat.exception.DaoException;
import com.melo.wechat.utils.ConnectionManager;
import com.melo.wechat.utils.log.LogErrorUtil;
import com.melo.wechat.utils.log.LogInfoUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.HashMap;
import java.util.logging.Logger;

import static com.melo.wechat.utils.ConnectionPool.getThreadConnection;

/**
 * @Description: 事务管理代理对象工厂
 * @author: Jun
 * @date: 19:36 2021/5/25
 */
public class TrancationProxy implements InvocationHandler {
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
    private static HashMap<Class<?>, TrancationProxy> trancationProxys = new HashMap<Class<?>, TrancationProxy>();

    /**
     *将被代理的对象传入获得它的类加载器和实现接口作为Proxy.newProxyInstance方法的参数。
     */
    public synchronized static<T> T getProxyInstance(Class<T> clazz){
        //先从Map中获取
        TrancationProxy trancationProxy = trancationProxys.get(clazz);
        //取不到则生成
        if(null == trancationProxy){
            trancationProxy = new TrancationProxy();
            try {
                T tar = clazz.newInstance();
                trancationProxy.setTarget(tar);
                trancationProxy.setProxy(Proxy.newProxyInstance(tar.getClass().getClassLoader(),
                        tar.getClass().getInterfaces(), trancationProxy));
            } catch (Exception e) {
                e.printStackTrace();
            }
            trancationProxys.put(clazz, trancationProxy);

        }

        return (T)trancationProxy.getProxy();
    }
    /**
     * 该方法在代理对象调用方法时调用
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Logger loggerInfo= LogInfoUtil.getLogger(proxy.getClass().getName());
        Logger loggerError= LogErrorUtil.getLogger(proxy.getClass().getName());
        Object returnObj=null;
        if(method.isAnnotationPresent(Transaction.class)) {
            Connection conn = ConnectionManager.getConnection();
            System.out.println(conn);
            try {
                //如果没有出现异常就提交事务
                ConnectionManager.beginTransaction(conn);
                returnObj = method.invoke(proxy, args);
                ConnectionManager.commitTransaction(conn);
            } catch (Exception e) {
                // 代理类执行method时发生异常，回滚事务
                ConnectionManager.rollback(conn);
                loggerError.severe("事务执行发送异常,回滚事务");
                throw new DaoException("事务执行发送异常,回滚事务");
            } finally {
                //设置connection为原始状态，解绑线程上的连接
                ConnectionManager.recoverTransaction(conn);
                ConnectionManager.closeThreadConn();
            }
        }else {
            returnObj =method.invoke(proxy,args);
        }
        return returnObj;
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
