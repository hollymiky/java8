package com.ann.demo1.interfaces;

/**
 * @author longquan
 * @date 2020/4/29 10:27 上午
 * 策略设计模式
 */
public interface MyPredicate<T> {

    public boolean test(T t);

}
