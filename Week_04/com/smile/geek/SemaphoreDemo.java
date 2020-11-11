package com.smile.geek;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Semaphore;

/**
 * @author zhufang
 * @date 2020/11/7 11:29 下午
 */
public class SemaphoreDemo {
    private static int result;
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        long start=System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        Semaphore semaphore = new Semaphore(1);

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                sum(semaphore);
            }
        });
        t.start();
        // 确保  拿到result 并输出
        System.out.println("异步计算结果为："+result);

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

        // 然后退出main线程
    }

    private static int sum(Semaphore semaphore) {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        result = fibo(36);
        semaphore.release();;
        return result;
    }

    private static int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }
}
