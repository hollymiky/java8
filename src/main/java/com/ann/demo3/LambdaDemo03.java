package com.ann.demo3;

import com.ann.demo1.entity.Employee;
import com.ann.demo2.interfaces.MyFun;
import com.ann.demo3.interfaces.MyFunction;
import com.ann.demo3.interfaces.MyFunction2;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author longquan
 * @date 2020/4/29 4:02 下午
 */
public class LambdaDemo03 {

    List<Employee> employees = Arrays.asList(
            new Employee(1,"张三", 18, 9999, Employee.Status.FREE),
            new Employee(2,"里斯", 38, 8888, Employee.Status.BUSY),
            new Employee(3,"王五", 28, 1999, Employee.Status.VOCATION),
            new Employee(4,"赵六", 48, 7777, Employee.Status.FREE),
            new Employee(5,"天启", 58, 6666, Employee.Status.BUSY)
    );

    @Test
    public void test() {
        Collections.sort(employees, (e1, e2) -> {
            if (e1.getAge() == e2.getAge()) {
                return e1.getName().compareTo(e2.getName());
            } else {
                //  按年龄正序排序
                return -Integer.compare(e1.getAge(), e2.getAge());
                //  按年龄倒序排序
                //return -Integer.compare(e1.getAge(),e2.getAge());
            }
        });

        employees.forEach(System.out::println);
    }

    @Test
    public void test2() {
        //  去掉所有空格
        String s = strHandler("\t\t\t\t hello lambda", (str) -> str.trim());
        System.out.println(s);

        System.out.println("---------------");

        //  将字母转为大写
        String s2 = strHandler("hello world", (str) -> str.toUpperCase());
        System.out.println(s2);

        System.out.println("---------------");

        //  截取字符串
        String s3 = strHandler("helloWorld", (str) -> str.substring(2, 5));
        System.out.println(s3);
    }

    //  用于处理字符串
    public String strHandler(String str, MyFunction myFunction) {
        return myFunction.getValue(str);
    }


    @Test
    public void test3() {
        //  op(100L, 200L, (x, y) -> x + y);
        op(100L, 200L, Long::sum);
        op(100L, 200L, (x, y) -> x * y);
    }

    //  处理两个long类型
    public void op(Long l1, Long l2, MyFunction2<Long, Long> myFunction2) {
        System.out.println(myFunction2.getValue(l1, l2));
    }

}
