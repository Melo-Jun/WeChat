package com.melo.wechat.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import static com.melo.wechat.utils.ReflectUtils.getMethods;

public class BeanUtils {

    public static <T> T parameter2Object(Map<String, String[]> parameter, Class<T> clazz) {
//        Set<String> keys = parameter.keySet();
//        Object instance = null;
//        try {
//            instance = clazz.newInstance();
//        } catch (InstantiationException | IllegalAccessException e) {
//            e.printStackTrace();
//        }
//        LinkedList<Field> fields = getFields(instance);
//
//        for (String key : keys) {
//            boolean flag=false;
//            for(Field field:fields){
//                if(key.equalsIgnoreCase(field.getName())){
//                    flag=true;
//                    break;
//                }
//            }
//            if(flag) {
//                //目标:根据字段名获取对应的set方法,这是另外一种技术叫做 todo 内省, 通过Class对象解析set方法
//                PropertyDescriptor descriptor = null;
//                try {
//                    descriptor = new PropertyDescriptor(key, clazz);
//                } catch (IntrospectionException e) {
//                    e.printStackTrace();
//                }
//                assert descriptor != null;
//                //获取set方法
//                Method writeMethod = descriptor.getWriteMethod();
//                try {
//                    writeMethod.invoke(instance, parameter.get(key));
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return instance;

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
                            if(method.toString().contains(".Integer")){
                                Integer value = Integer.parseInt(entry.getValue()[0]);
                                method.invoke(instance, value);
                            }else {
                                method.invoke(instance, entry.getValue()[0]);
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
