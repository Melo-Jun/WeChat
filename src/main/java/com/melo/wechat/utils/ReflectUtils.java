package com.melo.wechat.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author Jun
 * @program WeChat
 * @description 用于反射的工具
 * @date 2021-4-24
 */
public class ReflectUtils {
    public static LinkedList<Method> getMethods(Object obj) {
        return getMethods(obj.getClass());
    }

    public static LinkedList<Method> getMethods(Class clazz) {
        LinkedList<Method> methods = new LinkedList<>();
        /**
         * 遍历获取所有除了object父类的方法
         */
        for (Class cla = clazz; cla != Object.class; cla = cla.getSuperclass()) {
            methods.addAll(Arrays.asList(cla.getDeclaredMethods()));
        }
        return methods;
    }

    public static LinkedList<Field> getFields(Object obj) {
        return getFields(obj.getClass());
    }

    public static LinkedList<Field> getFields(Class clazz) {
        LinkedList<Field> fields = new LinkedList<>();
        /**
         * 遍历获取所有除了object父类的属性
         */
        for (Class cla = clazz; cla != Object.class; cla = cla.getSuperclass()) {
            fields.addAll(Arrays.asList(cla.getDeclaredFields()));
        }
        return fields;
    }


}
