package com.ann.demo5;

import com.ann.demo1.entity.Employee;
import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.*;

/**
 * @author longquan
 * @date 2020/4/29 19:03
 * 一、方法引用：若lambda体中的内容有方法已经实现了，我们可以使用‘方法引用’
 *          <可以理解为方法引用是Lambda表达式的另一种表现形式>
 *
 * 主要有三种语法格式：
 *
 * 对象::实例方法名
 *
 * 类::静态方法名
 *
 * 类::实例方法名
 *
 * 注意：
 * 1. Lambda 体中调用方法参数列表与返回值类型，要与函数式接口中抽象方法的函数列表和返回值类型保持一致
 * 2.若Lambda参数列表中，第一个参数是实例方法的调用者，而第二个参数是实例方法的参数时，可以使用ClassNam::method
 *
 * 二、构造器引用
 *
 * ClassName::new;
 * 注意：需要调用的构造器的参数列表要与函数式接口中抽象方法的参数列表保持一致
 *
 * 三、数组引用
 * Type::new;
 */
public class MethodRefDemo01 {

    /**
     * 对象::实例方法名 1
     */
    @Test
    public void test1(){
        //  Consumer<String> con = (x) -> System.out.println(x);
        //  需要注意的是：该对象的实例方法的返回值和参数必须和该函数式接口的参数和返回值保持一致
        Consumer<String> con = System.out::println;
        con.accept("ABCDEF");

        PrintStream ps = System.out;
        Consumer<String> con2 = ps::println;

    }

    /**
     * 对象::实例方法名 2
     */
    @Test
    public void test2(){
        Employee emp = new Employee();
        Supplier<String> supplier = () -> emp.getName();
        String str = supplier.get();
        System.out.println(str);

        //  简化
        Supplier<Integer> supplier2 = emp::getAge;
        Integer age = supplier2.get();
        System.out.println(age);
    }

    /**
     * 类::静态方法名 1
     */
    @Test
    public void test3(){
        Comparator<Integer> com = Integer::compare;
    }

    /**
     * 类::实例方法名
     * 第一个参数是方法的调用者，第二个参数是参数时，可以用这种方式
     */
    @Test
    public void test4(){
        //BiPredicate<String,String> biPredicate = (s1,s2) -> s1.equals(s2);
        BiPredicate<String,String> biPredicate = String::equals;
        boolean b = biPredicate.test("s1","s1");
        System.out.println(b);
    }

    /**
     * 构造器引用 1
     */
    @Test
    public void test5(){
        //  lambda旧方式
        Supplier<Employee> supplier = () -> new Employee();

        //  构造器引用方式
        Supplier<Employee> supplier1 = Employee::new;
        Employee emp = supplier1.get();
        System.out.println(emp);
    }

    /**
     * 构造器引用 2
     */
    @Test
    public void test6(){
        Function<Integer,Employee> function = (x)->new Employee(x);

        //  构造器引用方式
        Function<Integer,Employee> function1 = Employee::new;
        Employee emp = function1.apply(111);
        System.out.println(emp);

        BiFunction<Integer,Integer,Employee> biFunction = Employee::new;
        Employee emp1 = biFunction.apply(1,20);
        System.out.println(emp1);
    }


    /**
     * 数组引用
     */
    @Test
    public void test7(){
        //  lambda 旧方式
        Function<Integer,String[]> function = (x) -> new String[x];
        String[] strs = function.apply(10);
        System.out.println(strs.length);

        //  数组引用
        Function<Integer,String[]> function1 = String[]::new;
        String[] strs1 = function1.apply(20);
        System.out.println(strs1.length);
    }
}
