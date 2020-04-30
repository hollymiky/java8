package com.ann.demo13;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author longquan
 * @date 2020/4/30 20:11
 */
public class SimpleDateFormatDemo {

    /**
     * 演示旧 Date 类，存在线程安全问题
     */
    /*public static void main(String[] args) throws Exception {

        Callable<Date> task = () -> DateFormatThreadLocal.convert("20201216");

        //  创建一个长度为10的线程池
        ExecutorService pool = Executors.newFixedThreadPool(10);

        List<Future<Date>> results = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            results.add(pool.submit(task));
        }

        for (Future<Date> future: results) {
            System.out.println(future.get());
        }

        pool.shutdown();
    }*/


    /**
     * java8 处理 LocalDate，不存在线程安全问题
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {

//        DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");

        Callable<LocalDate> task = () -> LocalDate.parse("20201216",dtf);

        //  创建一个长度为10的线程池
        ExecutorService pool = Executors.newFixedThreadPool(10);

        List<Future<LocalDate>> results = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            results.add(pool.submit(task));
        }

        for (Future<LocalDate> future: results) {
            System.out.println(future.get());
        }

        pool.shutdown();
    }
}
