package com.ann.demo7;

import com.ann.demo1.entity.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author longquan
 * @date 2020/4/30 9:56 上午
 *
 * 中间操作
 *
 * Stream的操作三步骤：
 * - 创建 Stream：一个数据源（如：集合、数组），获取一个流。
 * - 中间操作：一个中间操作链，对数据源的数据进行处理。
 * - 终止操作（终端操作）：一个终止操作，执行中间操作链，并产生结果。
 */
public class StreamDemo02 {

    List<Employee> employees = Arrays.asList(
            new Employee(1,"张三", 18, 9999, Employee.Status.FREE),
            new Employee(2,"里斯", 38, 8888, Employee.Status.BUSY),
            new Employee(3,"王五", 28, 1999, Employee.Status.VOCATION),
            new Employee(4,"赵六", 48, 7777, Employee.Status.FREE),
            new Employee(5,"天启", 58, 6666, Employee.Status.BUSY),
            new Employee(5,"天启", 58, 6666, Employee.Status.BUSY),
            new Employee(5,"天启", 58, 6666, Employee.Status.BUSY)
    );


    /**
     * 中间操作
     * 3. 排序
     * 	- sorted()：产生一个新流，其中按自然顺序排序。(Comparable)
     * 	- sorted(Comparator comp)：产生一个新流，其中被比较器顺序排序。(Comparator)
     */

    @Test
    public void test7(){
        //  自然排序
        List<String> list = Arrays.asList("ccc","aaa","bbb","ddd","eee");
        list.stream().sorted().forEach(System.out::println);

        System.out.println("-------------------");

        employees.stream().sorted((e1,e2) -> {
            if(e1.getAge().equals(e2.getAge())){
                return e1.getName().compareTo(e2.getName());
            }else{
                return -e1.getAge().compareTo(e2.getAge());
            }
        }).forEach(System.out::println);
    }



    /**
     * 中间操作
     * 2. 映射
     * 	- map(Function f)：接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
     * 	- mapToDouble(ToDoubleFunction f)：接收一个函数作为参数，该函数会被应用到每个元素上，产生一个新的DoubleStream。
     * 	- mapToInt(ToIntFunction f)：接收一个函数作为参数，该函数会被应用到每个元素上，产生一个新的IntStream。
     * 	- mapToLong(ToLongFunction f)：接收一个函数作为参数，该函数会被应用到每个元素上，产生一个新的LongStream。
     * 	- flatMap(Function f)：接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流。
     */

    //  map
    @Test
    public void test6(){
        List<String> list = Arrays.asList("aaa","bbb","ccc","ddd","eee");
        list.stream()
                .map(String::toUpperCase)
                .forEach(System.out::println);


        System.out.println("-----------------");

        employees.stream()
                .map(Employee::getName)
                .forEach(System.out::println);

        System.out.println("-----------------");

        //  map本身返回一个流
        Stream<Stream<Character>> stream =
                list.stream().map(StreamDemo02::filterCharacter);// { {a,a,a},{b,b,b},...}


        //  嵌套流循环遍历，比较麻烦，考虑使用flatMap
        stream.forEach((sm) -> {
            sm.forEach(System.out::println);
        });

        System.out.println("-----------------");

        //  flatMap
        Stream<Character> stream1 =
                list.stream().flatMap(StreamDemo02::filterCharacter);// {a,a,a,b,b,b,....}
        stream1.forEach(System.out::println);
    }

    //  将一个字符串拆分成每个字符元素，放进一个新的集合流中
    public static Stream<Character> filterCharacter(String str){
        List<Character> list = new ArrayList<>();
        for (Character ch: str.toCharArray()) {
            list.add(ch);
        }
        return list.stream();
    }





    /**
     * 中间操作 (不会有任何结果，要有终止操作)
     * 1. 筛选和切片：
     * 	- filter(Predicate p)：操作 Lambda，从流中排除某些元素
     * 	- distinct()：筛选，通过流所生成元素的hashcode()和equals()去除重复元素
     * 	- limit(long maxSize)：截断流，使其元素不超过给定数量
     * 	- skip(long n)：跳过元素，返回一个扔掉了前n个元素的流。若流中元素不足n个，则返回一个空流。与limit(n)互补
     */

    //  distinct
    @Test
    public void test5(){
        employees.stream()
                .filter((e) -> e.getSalary() > 5000)
                .distinct().forEach(System.out::println);
    }


    //  skip
    @Test
    public void test4(){
        employees.stream()
                .filter((e) -> e.getSalary() > 5000)
                .skip(2).forEach(System.out::println);
    }



    //  limit
    @Test
    public void test3(){
        employees.stream()
                .filter((e) -> {
                    System.out.println("短路!");
                    return e.getSalary()>5000;
                })
                .limit(2) //    截断流,后续的迭代不再继续 （称为'短路'）
                .forEach(System.out::println);
    }


    //  内部迭代，迭代器操作由Stream API 完成
    @Test
    public void test1(){
        Stream<Employee> stream = employees.stream()
                                            .filter((e) -> {
                                                System.out.println("Stream API 的中间操作");
                                                return e.getAge() > 35;
                                            });
        //  终止操作，一次性执行全部内容，即'惰性求值'
        stream.forEach(System.out::println);
    }

    //  外部迭代
    @Test
    public void test2(){
        Iterator<Employee> iterator = employees.iterator();

        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
