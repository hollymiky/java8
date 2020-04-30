package com.ann.demo15.annotation;

import com.ann.demo15.MyAnnotations;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.LOCAL_VARIABLE;

/**
 * @author longquan
 * @date 2020/4/30 22:07
 */
//  想要@MyAnnotation 这个注解可以重复，就要把这个注解放入一个容器当中@MyAnnotations
@Repeatable(MyAnnotations.class)
@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE, TYPE_PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {

    String value() default "hello java8";
}
