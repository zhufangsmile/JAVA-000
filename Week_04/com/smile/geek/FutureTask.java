package com.smile.geek;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/**
 * @author zhufang
 * @date 2020/11/7 11:29 下午
 */
public class FutureTask {
    private static int result;
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        long start=System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        java.util.concurrent.FutureTask<Integer> futureTask = new java.util.concurrent.FutureTask<Integer>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return sum();
            }
        });
        new Thread(futureTask).start();

        // 异步执行 下面方法

        int result = futureTask.get(); //这是得到的返回值

        // 确保  拿到result 并输出
        System.out.println("异步计算结果为："+result);

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

        // 然后退出main线程
    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }
}
