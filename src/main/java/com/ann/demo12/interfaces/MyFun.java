package com.ann.demo12.interfaces;

/**
 * @author longquan
 * @date 2020/4/30 19:40
 */
public interface MyFun {

    /**
     * java8 接口默认方法
     */
    default String getName(){
        return "hello MyFun";
    }

}
