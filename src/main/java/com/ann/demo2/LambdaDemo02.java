package com.ann.demo2;

import com.ann.demo2.interfaces.MyFun;
import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * @author longquan
 * @date 2020/4/29 2:03 下午
 * <p>
 * 1.Lambda 表达式的语法：Java8中引入一个新的操作符 -> ，该操作符称为箭头操作符或Lambda操作符
 * 箭头操作符将Lambda表达式拆分成两部分：
 * 左侧：Lambda 表达式的参数列表
 * 右侧：Lambda 表达式中所需要执行的功能，即Lambda体
 */
public class LambdaDemo02 {

    @Test
    public void test1() {
        final int num = 0;
        //  内部类想要使用局部变量，该局部变量必须final
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("hello world" + num);
            }
        };

        System.out.println("-------------");

        //  lambda就不需要加final，但是其实使用起来默认还是final
        Runnable r = () -> System.out.println("hello world");
    }

    @Test
    public void test2() {
        Consumer<String> con = (x) -> System.out.println(x);
        con.accept("hello jdk8");
    }

    @Test
    public void test3() {
        Comparator<Integer> com = (x, y) -> {
            System.out.println("函数式接口");
            return Integer.compare(x, y);
        };
    }

    @Test
    public void test4() {
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
    }

    //  需求：对一个数进行运算
    @Test
    public void test5() {
        Integer i = operation(100, (x) -> x * x);
        System.out.println(i);

        System.out.println(operation(200, x -> x + 200));
    }

    public Integer operation(Integer num, MyFun fun) {
        return fun.getValue(num);
    }
}
