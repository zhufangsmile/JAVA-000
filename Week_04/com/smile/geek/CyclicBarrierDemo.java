package com.smile.geek;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author zhufang
 * @date 2020/11/11 10:54 下午
 */
public class CyclicBarrierDemo {
    private static int result;
    private static volatile boolean flag = false;

    public static void main(String[] args) {

        long start=System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        CyclicBarrier cyclicBarrier = new CyclicBarrier(1, ()-> {
            flag = true;
        });

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                sum();
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();

        while (true) {
            if (flag) {
                // 确保  拿到result 并输出
                System.out.println("异步计算结果为："+result);
                System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
                break;
            }
        }




        // 然后退出main线程
    }

    private static int sum() {
        result = fibo(36);
        return result;
    }

    private static int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }
}
