package cn.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Semaphore;

import static java.util.concurrent.Executors.*;

/**
 * @Author Gosin
 * @Date 2019/8/19 9:47
 */
public class SemaphoreTest {
    public static void main(String[] args) {
        // 线程池
        ExecutorService exec = newCachedThreadPool();
        //设置信号量同时执行的线程数是5
        final Semaphore semp = new Semaphore(5);
        // 模拟20个客户端访问
        for (int index = 0; index < 20; index++) {
            final int NO = index;
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    try {
                        //使用acquire()获取锁
                        semp.acquire();
                        System.out.println("Accessing: " + NO);
                        //睡眠1秒
                        Thread.sleep(1000);
                        
                    } catch (InterruptedException e) {
                    }  finally {
                        //使用完成释放锁
                        semp.release();
                    }
                }
            };
            exec.execute(run);
        }
        // 退出线程池
        exec.shutdown();
    }
}
