package com.ann.demo8;

import com.ann.demo1.entity.Employee;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author longquan
 * @date 2020/4/30 11:18 上午
 * <p>
 * - 终止操作（终端操作）：一个终止操作，执行中间操作链，并产生结果。
 */
public class StreamDemo03 {

    List<Employee> employees = Arrays.asList(
            new Employee(1, "张三", 18, 9999, Employee.Status.FREE),
            new Employee(2, "里斯", 38, 8888, Employee.Status.BUSY),
            new Employee(3, "王五", 28, 1999, Employee.Status.VOCATION),
            new Employee(4, "赵六", 48, 7777, Employee.Status.FREE),
            new Employee(5, "天启", 58, 6666, Employee.Status.BUSY),
            new Employee(5, "天启", 58, 6666, Employee.Status.BUSY)
    );

    /**
     * - 收集
     * - collect：将流转换为其他形式，接收一个Collector接口的实现，用于给Stream中元素做汇总的方法
     */

    @Test
    public void test5() {
        //  按状态分组
        Map<Employee.Status, List<Employee>> map = employees.stream().collect(Collectors.groupingBy(Employee::getStatus));
        System.out.println(map);

        System.out.println("-----------------");

        //  多级分组(可以实现无限分组)
        Map<Employee.Status, Map<String, List<Employee>>> maps =
                employees.stream().collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy((e) -> {
                    if (e.getAge() <= 35) {
                        return "青年";
                    } else if (e.getAge() <= 50) {
                        return "中年";
                    } else {
                        return "老年";
                    }
                })));
        System.out.println(maps);

        System.out.println("-----------------");

        //  分区（分片） 比如true false
        Map<Boolean,List<Employee>> map2 = employees.stream()
                .collect(Collectors.partitioningBy((e)->e.getSalary()>7900));
        System.out.println(map2);

        System.out.println("-----------------");

        //
        DoubleSummaryStatistics dss =  employees.stream().collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println(dss.getMax());
        System.out.println(dss.getMin());
        System.out.println(dss.getAverage());
        System.out.println(dss.getCount());
        System.out.println(dss.getSum());

        System.out.println("-----------------");

        String str = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.joining(",","===","==="));
        System.out.println(str);

    }

    @Test
    public void test4() {
        //  收集总数
        Long count = employees.stream().collect(Collectors.counting());
        // Long count = employees.stream().count();
        System.out.println(count);

        System.out.println("-----------------");

        //  平均值
        Double avg = employees.stream().collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println(avg);

        System.out.println("-----------------");

        //  总和
        Double sum = employees.stream().collect(Collectors.summingDouble(Employee::getSalary));
        // Double sum = employees.stream().mapToDouble(Employee::getSalary).sum();
        System.out.println(sum);

        System.out.println("-----------------");

        //  最大值
        Optional<Employee> max = employees.stream().collect(Collectors.maxBy((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary())));
        // Optional<Employee> max = employees.stream().max((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
        System.out.println(max.get());

        System.out.println("-----------------");

        Optional<Double> optionalDouble = employees.stream().map(Employee::getSalary).collect(Collectors.minBy(Double::compare));
        // Optional<Double> optionalDouble = employees.stream().map(Employee::getSalary).min(Double::compare);
        System.out.println(optionalDouble.get());

    }

    @Test
    public void test3() {
        List<String> list = employees.stream().map(Employee::getName).collect(Collectors.toList());
        list.forEach(System.out::println);

        System.out.println("-----------------");

        Set<String> strings = employees.stream().map(Employee::getName).collect(Collectors.toSet());
        strings.forEach(System.out::println);

        System.out.println("-----------------");

        HashSet<String> hashSet = employees.stream().map(Employee::getName).collect(Collectors.toCollection(HashSet::new));
        hashSet.forEach(System.out::println);
    }


    /**
     * - 归约
     * - reduce(T identity,BinaryOperator) / reduce(BinaryOperator)：可以将流中元素反复结合起来，得到一个流
     */

    @Test
    public void test2() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        //  reduce @param1 起始值，@param2 二元运算
        // Integer sum = list.stream().reduce(0, (x, y) -> x + y);
        Integer sum = list.stream().reduce(0, Integer::sum);
        System.out.println(sum);


        System.out.println("------------------");

        //  计算员工的总工资
        Optional<Double> optionalDouble = employees.stream().map(Employee::getSalary).reduce(Double::sum);
        System.out.println(optionalDouble.get());
    }


    /**
     * - 终止操作（终端操作）：一个终止操作，执行中间操作链，并产生结果。
     * * 	- allMatch：检查是否匹配所有参数
     * * 	- anyMatch：检查是否至少匹配一个元素
     * * 	- nonMatch：检查是否没有匹配所有元素
     * * 	- findFirst：返回第一个元素
     * * 	- findAny：返回当前流中的任意元素
     * * 	- count：返回流中元素的总个数
     * * 	- max：返回流中最大值
     * * 	- min：返回流中最小值
     */

    @Test
    public void test() {
        //  员工集合中是否都是BUSY状态，all
        boolean b = employees.stream().allMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b);

        System.out.println("-----------------------");

        //  员工集合中至少由一个BUSY状态，any
        boolean b1 = employees.stream().anyMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b1);

        System.out.println("-----------------------");

        //  员工集合中没有VOCATION状态，non
        boolean b2 = employees.stream().noneMatch((e) -> e.getStatus().equals(Employee.Status.VOCATION));
        System.out.println(b2);

        System.out.println("-----------------------");

        //  员工按工资排行，取出第一个值
        Optional<Employee> optionalEmployee =
                employees.stream().sorted((e1, e2) -> -Double.compare(e1.getSalary(), e2.getSalary())).findFirst();
        System.out.println(optionalEmployee.get());

        System.out.println("-----------------------");

        //  获取返回当前流中的任意元素
        //  parallelStream 并行流
        Optional<Employee> optionalEmployee1 =
                employees.parallelStream().filter((e) -> e.getStatus().equals(Employee.Status.FREE))
                        .findAny();
        System.out.println(optionalEmployee1.get());

        System.out.println("-----------------------");

        long count = employees.stream().count();
        System.out.println(count);

        System.out.println("-----------------------");

        //  获取员工最高工资的员工信息
        Optional<Employee> optionalEmployee2 =
                employees.stream().max((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
        System.out.println(optionalEmployee2.get());

        System.out.println("-----------------------");

        //  获取最低工资
        Optional<Double> optionalEmployee3 =
                employees.stream().map(Employee::getSalary).min(Double::compare);
        System.out.println(optionalEmployee3.get());
    }
}
