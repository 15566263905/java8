package cn.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.Executors.*;

public class SemaphoreMutex {
    //初始化为1,互斥信号量
    private final static Semaphore mutex = new Semaphore(1);
    
    public static void main(String[] args){
        ExecutorService pools = newCachedThreadPool();
        
        for (int i=0 ; i < 10;i++){
            final int index = i;
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    try {
                        //使用acquire()获取锁
                        mutex.acquire();
                        System.out.println(String.format("[Thread-%s]任务id --- %s",Thread.currentThread().getId(),index));
                        TimeUnit.SECONDS.sleep(1);
                        
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        //使用完成释放锁
                        mutex.release();
                        System.out.println("-----------release");
                    }
                }
            };
            pools.execute(run);
        }
        pools.shutdown();
    }
}
