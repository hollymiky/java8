package com.ann.demo15;

import com.ann.demo15.annotation.MyAnnotation;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @author longquan
 * @date 2020/4/30 22:07
 *
 * 重复注解与类型注解
 * 重复注解：需要注解容器
 */
public class AnnotationDemo {

    //  编译期检查是否为null
    private /*@NonNUll*/ Object object = null;

    @Test
    public void test() throws NoSuchMethodException {
        Class<AnnotationDemo> clazz = AnnotationDemo.class;
        Method method = clazz.getMethod("show");

        MyAnnotation[] myAnnotation = method.getAnnotationsByType(MyAnnotation.class);

        for (MyAnnotation annotation:myAnnotation) {
            System.out.println(annotation.value());
        }
    }

    @MyAnnotation("hello my1")
    @MyAnnotation("hello my2")
    public void show(@MyAnnotation("test Str") String str){

    }
}
