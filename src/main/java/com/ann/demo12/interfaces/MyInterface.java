package com.ann.demo12.interfaces;

/**
 * @author longquan
 * @date 2020/4/30 19:54
 */
public interface MyInterface {

    default String getName(){
        return "hello MyInterface";
    }

    public static void show(){
        System.out.println("MyInterface Static Method");
    }
}
