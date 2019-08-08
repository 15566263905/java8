package cn.sync;

import java.util.concurrent.TimeUnit;

/**
 * @Date 2019/8/7 9:53
 */
public class InterruputThread {
    
    /**
     * 阻塞状态,线程中断
     * @throws InterruptedException
     */
    private static void blockingState() throws InterruptedException {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                try {
                    while (true) {
                        //当前线程处于阻塞状态，异常必须捕捉处理，无法往外抛出
                        TimeUnit.SECONDS.sleep(2);
                    }
                } catch (InterruptedException e) {
                    System.out.println("中断睡眠");
                    boolean interrupt = this.isInterrupted();
                    //中断状态被复位
                    System.out.println("中断: "+interrupt);
                }
            }
        };
        t1.start();
        TimeUnit.SECONDS.sleep(2);
        //中断处于阻塞状态的线程
        t1.interrupt();
    }
    
    /**
     * 非阻塞状态,线程中断
     * @throws InterruptedException
     */
    private static void notBlockingState() throws InterruptedException {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                while (true) {
                    //判断当前线程是否被中断
                    if (this.isInterrupted()) {
                        System.out.println("线程中断");
                        break;
                    }
                }
                System.out.println("已跳出循环,线程中断!");
            }
        };
        t1.start();
        TimeUnit.SECONDS.sleep(2);
        //中断处于阻塞状态的线程
        t1.interrupt();
    }
    
    /**
     * 综合
     * @throws InterruptedException
     */
    private static void comprehensive() throws InterruptedException {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                try {
                    //判断当前线程是否已中断,注意interrupted方法是静态的,执行后会对中断状态进行复位
                    while (!Thread.interrupted()) {
                        TimeUnit.SECONDS.sleep(2);
                    }
                } catch (InterruptedException e) {
                    System.out.println("中断睡眠");
                    boolean interrupt = this.isInterrupted();
                    //中断状态被复位
                    System.out.println("中断: "+interrupt);
                }
            }
        };
        t1.start();
        TimeUnit.SECONDS.sleep(2);
        //中断处于阻塞状态的线程
        t1.interrupt();
    }
    
    public static void main(String[] args) throws InterruptedException {
        //blockingState();
        //notBlockingState();
        comprehensive();
    }
}
