package com.ann.demo4;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author longquan
 * @date 2020/4/29 4:38 下午
 * Java8 内置四大核心函数式接口
 * 1. Consumer<T> 消费型接口，参数T，返回void，用途：对类型为T的对象应用操作，包含方法：void accept(T t);
 * 2. Supplier<T> 供给型接口，无参，返回T，用途：返回类型为T的对象，包含方法：T get();
 * 3. Function<T,R> 函数型接口，参数T，返回R，用途：对类型为T的对象应用操作，并返回结果。结果是R类型的对象。包含方法：R apply(T t);
 * 4. Predicate<T> 断定型接口，参数T，返回boolean，用途：确定类型为T的对象是否满足某约束，并返回boolean值，包含方法：boolean test(T t);
 */
public class LambdaDemo04 {

    @Test
    public void test1() {
        consumerTest(10000, (m) -> System.out.println("测试消费型接口返回,消费：" + m));
    }

    //  测试 Consumer<T> 消费型接口
    public void consumerTest(double money, Consumer<Double> con) {
        con.accept(money);
    }

    @Test
    public void test2() {
        //  产生指定个数整数放入集合中
        List<Integer> list = getNumList(10, () -> (int) (Math.random() * 100));
        list.forEach(System.out::println);
    }

    // 测试 Supplier<T> 供给型接口
    public List<Integer> getNumList(int num, Supplier<Integer> supplier) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            list.add(supplier.get());
        }
        return list;
    }

    @Test
    public void test3(){
        //  处理字符串
        //String s = strHandler("abc", String::toUpperCase);
        String s = strHandler("abc",(str) -> str.toUpperCase());
        System.out.println(s);
    }

    //  测试 Function<T,R> 函数型接口
    public String strHandler(String str, Function<String,String> function){
        return function.apply(str);
    }


    @Test
    public void test4(){
        //  将满足条件的字符串放入集合中去
        List<String> list = Arrays.asList("Hello","World","Lambda","Hi");
        List<String> strs = filterStr(list, (s) -> s.length() == 5);
        strs.forEach(System.out::println);
    }

    //  测试 Predicate<T> 断定型接口
    public List<String> filterStr(List<String> list, Predicate<String> predicate){
        List<String> strList = new ArrayList<>();
        for (String str: list) {
            if(predicate.test(str)){
                strList.add(str);
            }
        }
        return strList;
    }

}
