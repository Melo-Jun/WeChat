package com.melo.wechat.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import static com.melo.wechat.utils.ReflectUtils.getMethods;

public class BeanUtils {

    public static <T> T parameter2Object(Map<String, String[]> parameter, Class<T> clazz) {

                T instance = null;
        try {
            instance = clazz.newInstance();
            LinkedList<Method> methods = getMethods(instance);
            LinkedList<Method> setMethods = new LinkedList<>();
            Set<Map.Entry<String, String[]>> entries = parameter.entrySet();
            /*
            遍历key获取与对象有关的set方法
             */
            for (Map.Entry<String, String[]> entry : entries) {
                for (Method method : methods) {
                    if (method.getName().startsWith("set") && entry.getKey().equalsIgnoreCase(method.getName().substring(3))) {
                        setMethods.add(method);
                    }
                }
            }
            /*
            根据相应key执行相应set方法
             */
            for (Map.Entry<String, String[]> entry : entries) {
                for (Method method : setMethods) {
                    if (entry.getKey().equalsIgnoreCase(method.getName().substring(3))) {
                        try {
                            String value = entry.getValue()[0];
                            if(method.toString().contains(".Integer")){
                                method.invoke(instance, Integer.parseInt(value));
                            }else {
                                method.invoke(instance,value);
                            }
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return instance;
    }
}
