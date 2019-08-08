package cn.sync;

import java.util.concurrent.TimeUnit;

/**
 * @Date 2019/8/7 10:15
 */
public class SynchronizedBlocked implements Runnable {
    
    public synchronized void sync() {
        System.out.println("尝试调用sync()");
        while(true){
            // 它让出当前线程 CPU 的时间片，使正在运行中的线程重新变成就绪状态，并重新竞争 CPU 的调度权。它可能会获取到，也有可能被其他线程获取到。
            Thread.yield();
        }
    }
    
    /**
     * 在构造器中创建新线程并启动获取对象锁
     */
    public SynchronizedBlocked() {
        //该线程已持有当前实例锁
        new Thread() {
            @Override
            public void run() {
                // 此线程获取的锁
                sync();
            }
        }.start();
    }
    
    @Override
    public void run() {
        //中断判断
        while (true) {
            if (Thread.interrupted()) {
                System.out.println("中断线程!!");
                break;
            } else {
                sync();
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        SynchronizedBlocked sync = new SynchronizedBlocked();
        Thread t = new Thread(sync);
        //启动后调用sync()方法,无法获取当前实例锁处于等待状态
        t.start();
        TimeUnit.SECONDS.sleep(1);
        //中断线程,无法生效
        t.interrupt();
    }
}
