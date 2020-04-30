package com.ann.demo6;

import com.ann.demo1.entity.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author longquan
 * @date 2020/4/30 9:40 上午
 *
 * 创建 Stream
 *
 * Stream的操作三步骤：
 * - 创建 Stream：一个数据源（如：集合、数组），获取一个流。
 * - 中间操作：一个中间操作链，对数据源的数据进行处理。
 * - 终止操作（终端操作）：一个终止操作，执行中间操作链，并产生结果。
 */
public class StreamDemo01 {

    /**
     * 创建Stream
     * java8中的Collection接口被扩展，提供了两个获取流的方法
     * 1. default Stream<E> stream() 返回一个顺序流(串行)
     * 2. default Stream<E> parallelStream() 返回一个并行流
     */
    @Test
    public void test1(){
        //  1. 创建流
        List<String> list = new ArrayList<>();
        Stream<String> stringStream = list.stream();

        //  2. 通过 Arrays 中的静态方法 stream() 获取数组流
        Employee[] employees = new Employee[10];
        Stream<Employee> employeeStream = Arrays.stream(employees);

        //  3. 通过Stream类中的静态方法of()
        Stream<String> stringStream1 = Stream.of("a","b","c");

        //  4. 创建无限流（无穷尽）
        //  迭代 iterate @param1 种子，@param2 一元运算
        Stream<Integer> stream = Stream.iterate(0,(x) -> x+2);
        stream.limit(10).forEach(System.out::println);

        //  生成
        Stream.generate(() -> Math.random())
                .limit(5)
                .forEach(System.out::println);

    }
}
