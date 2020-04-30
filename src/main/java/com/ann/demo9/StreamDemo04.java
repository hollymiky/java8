package com.ann.demo9;

import com.ann.demo1.entity.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author longquan
 * @date 2020/4/30 3:04 下午
 * <p>
 * stream 练习
 */
public class StreamDemo04 {

    List<Employee> employees = Arrays.asList(
            new Employee(1, "张三", 18, 9999, Employee.Status.FREE),
            new Employee(2, "里斯", 38, 8888, Employee.Status.BUSY),
            new Employee(3, "王五", 28, 1999, Employee.Status.VOCATION),
            new Employee(4, "赵六", 48, 7777, Employee.Status.FREE),
            new Employee(5, "天启", 58, 6666, Employee.Status.BUSY)
    );

    /**
     * 1、给定一个数字列表，如何返回一个由每个数的平方构成的列表呢？给定【1,2,3,4,5】，应该返回【1,4,9,16,25】
     */
    @Test
    public void test1() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        list.stream().map((x) -> x * x).forEach(System.out::println);
    }

    /**
     * 2、怎样使用map和reduce方法，数一数流中有多少个Employee呢？
     */
    @Test
    public void test2(){
        Optional<Integer> count = employees.stream().map((e) ->1).reduce(Integer::sum);
        System.out.println(count.get());
    }


}
