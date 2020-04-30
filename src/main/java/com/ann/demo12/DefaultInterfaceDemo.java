package com.ann.demo12;

import com.ann.demo12.interfaces.MyInterface;

/**
 * @author longquan
 * @date 2020/4/30 19:45
 */
public class DefaultInterfaceDemo {

    public static void main(String[] args) {
        SubClass subClass = new SubClass();
        System.out.println(subClass.getName());

        MyInterface.show();
    }

}
