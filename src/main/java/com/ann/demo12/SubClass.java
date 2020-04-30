package com.ann.demo12;

import com.ann.demo12.interfaces.MyFun;
import com.ann.demo12.interfaces.MyInterface;

/**
 * @author longquan
 * @date 2020/4/30 19:44
 */
public class SubClass implements MyFun, MyInterface {


    @Override
    public String getName() {
        return MyInterface.super.getName();
    }
}
