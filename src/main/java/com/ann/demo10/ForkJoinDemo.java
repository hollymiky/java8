package com.ann.demo10;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * @author longquan
 * @date 2020/4/30 4:36 下午
 */
public class ForkJoinDemo {

    /**
     * fork/join 测试
     */
    @Test
    public void test1() {
        Instant start = Instant.now();
        ForkJoinPool joinPool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinCalculate(0, 10000000000L);
        Long sum = joinPool.invoke(task);
        System.out.println(sum);
        Instant end = Instant.now();
        System.out.println("耗费时间为：" + Duration.between(start, end).toMillis());
    }

    /**
     * 普通for循环 测试
     */
    @Test
    public void test2() {
        Instant start = Instant.now();
        long sum = 0L;
        for (long i = 0; i <= 10000000000L; i++) {
            sum += i;
        }
        System.out.println(sum);
        Instant end = Instant.now();
        System.out.println("耗费时间为：" + Duration.between(start, end).toMillis());
    }

    /**
     * java8并行流
     */
    @Test
    public void test3(){
        Instant start = Instant.now();
        //  顺序流,单线程
        long sum = LongStream.rangeClosed(0,10000000000L)
                .reduce(0,Long::sum);
        System.out.println(sum);
        Instant end = Instant.now();
        System.out.println("耗费时间为：" + Duration.between(start, end).toMillis());

        //  并行流
        /*long sum1 = LongStream.rangeClosed(0,10000000000L)
                .parallel()
                .reduce(0,Long::sum);
        System.out.println(sum1);
        Instant end = Instant.now();
        System.out.println("耗费时间为：" + Duration.between(start, end).toMillis());*/
    }
}
