package com.smile.geek;
/**
 * @author zhufang
 * @date 2020/11/9 2:38 下午
 */
public class SynchronizedDemo {
    private static int result;

    public static void main(String[] args) throws InterruptedException {
        Object oo = new Object();

        long start=System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (oo) {
                    sum();
                    oo.notify();
                }

            }
        });
        t.start();

        synchronized (oo) {
            if (result == 0) {
                oo.wait();
            }
            // 确保  拿到result 并输出
            System.out.println("异步计算结果为："+result);

            System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
        }




        // 然后退出main线程
    }

    public synchronized static int sum() {
        result = fibo(36);

        return result;
    }

    private static int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }



}
