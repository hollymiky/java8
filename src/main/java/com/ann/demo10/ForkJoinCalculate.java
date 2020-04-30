package com.ann.demo10;

import java.util.concurrent.RecursiveTask;

/**
 * @author longquan
 * @date 2020/4/30 4:27 下午
 * <p>
 * fork/join 框架
 */
public class ForkJoinCalculate extends RecursiveTask<Long> {

    public ForkJoinCalculate(long start, long end) {
        this.start = start;
        this.end = end;
    }


    private static final long serialVersionUID = 8448895162311052667L;

    private long start;
    private long end;

    private static final long THRESHOLD = 100000;

    @Override
    protected Long compute() {
        long length = end - start;
        if (length <= THRESHOLD) {
            long sum = 0;
            for (long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        } else {
            long middle = (start + end) / 2;
            ForkJoinCalculate left = new ForkJoinCalculate(start, middle);
            left.fork();    // 拆分子任务，并压入线程队列

            ForkJoinCalculate right = new ForkJoinCalculate(middle +1, end);
            right.fork();   // 拆分子任务，并压入线程队列

            return left.join() + right.join();
        }
    }
}
