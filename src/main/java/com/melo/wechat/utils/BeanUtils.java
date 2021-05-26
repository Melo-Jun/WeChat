package com.melo.wechat.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.Map;


/**
 * @Description: 将getParameterMap映射为Entity对象
 * @author: Jun
 * @date: 15:25 2021/5/26
 */
public class BeanUtils {

    public static <T> T parameter2Object(Map<String, String[]> parameter, Class<T> clazz) {

        T instance=null;

        try {
             instance=clazz.newInstance();

            //获取传入类的所有属性(包括父类属性)
            LinkedList<Field> fields =ReflectUtils.getFields(clazz);
            //遍历所有属性
            for(Field field:fields){
                String[] value=parameter.get(field.getName());
                //判断前台所传入的参数中是否有该属性
                if(value!=null&&value[0]!=null){
                    //根据属性名构造相应的set方法
                    String setMethodName="set"+field.getName().substring(0,1).toUpperCase()+field.getName().substring(1);
                    //根据方法名找到该类的方法
                    Method setMethod=clazz.getMethod(setMethodName,field.getType());
                    //获取该属性String类型的构造器
                    Constructor<?> constructor=field.getType().getConstructor(String.class);
                    //通过String类型的构造器来构造属性
                    setMethod.invoke(instance,constructor.newInstance(value[0]));
                }
            }

        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return  instance;
    }
}
