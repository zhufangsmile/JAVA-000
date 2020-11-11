package com.smile.geek;

import java.util.concurrent.CountDownLatch;

/**
 * @author zhufang
 * @date 2020/11/7 8:41 下午
 */
public class CountDownLatchDemo {

    private static int result;

    public static void main(String[] args) throws InterruptedException {

        long start=System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        CountDownLatch countDownLatch = new CountDownLatch(1);

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                sum(countDownLatch);
            }
        });
        t.start();
        countDownLatch.await();
        // 确保  拿到result 并输出
        System.out.println("异步计算结果为："+result);

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

        // 然后退出main线程
    }

    private static int sum(CountDownLatch countDownLatch) {
        result = fibo(36);
        countDownLatch.countDown();
        return result;
    }

    private static int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }
}
