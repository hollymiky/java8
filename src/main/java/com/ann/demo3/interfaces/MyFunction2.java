package com.ann.demo3.interfaces;

/**
 * @author longquan
 * @date 2020/4/29 4:17 下午
 */
@FunctionalInterface
public interface MyFunction2<T,R> {

    public R getValue(T t1,T t2);
}
