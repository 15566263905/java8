package cn.sync;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @Author Gosin
 * @Date 2019/8/15 14:16
 */
public class ABADemo {
    
    static AtomicInteger atIn = new AtomicInteger(100);
    
    // 初始化时需要传入一个初始值和初始时间
    static AtomicStampedReference<Integer> atomicStampedR =
            new AtomicStampedReference<>(101,0);
    
    
    static Thread t1 = new Thread(new Runnable() {
        @Override
        public void run() {
            System.out.println("t1 value: " + atIn);
            //101
            boolean flag = atIn.compareAndSet(100, 101);
            System.out.println("t1.1: flag[is equal](100, 101):"+flag+",newValue:"+atIn);
            //更新为100
            flag = atIn.compareAndSet(101, 100);
            System.out.println("t1.2: flag[is equal](101, 100):"+flag+",newValue:"+atIn);
        }
    });
    
    
    static Thread t2 = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t2 value: " + atIn);
            boolean flag=atIn.compareAndSet(100,101);
            System.out.println("t2: flag[is equal](100, 101):"+flag+",newValue:"+atIn);
        }
    });
    
    
    static Thread t3 = new Thread(new Runnable() {
        @Override
        public void run() {
            System.out.println("t3 value: " + atIn);
            int time=atomicStampedR.getStamp();
            //更新为101
            boolean flag = atomicStampedR.compareAndSet(100, 101,time,time+1);
            System.out.println("t3.1: flag[is equal](100, 101):"+flag+",newValue:"+atIn);
            //更新为100
            int time2=atomicStampedR.getStamp();
            flag = atomicStampedR.compareAndSet(101, 100,time2,time2+1);
            System.out.println("t3.2: flag[is equal](101, 100):"+flag+",newValue:"+atIn);
        }
    });
    
    
    static Thread t4 = new Thread(new Runnable() {
        @Override
        public void run() {
            System.out.println("t4 value: " + atIn);
            int time = atomicStampedR.getStamp();
            System.out.println("t4: before time:" + time + ",newValue:"+atomicStampedR.getReference());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int expectStamp = 0;
            boolean flag=atomicStampedR.compareAndSet(100,101,expectStamp,time+1);
            System.out.println("t4: flag[is equal](100, 101):"+flag+",newValue:"+atomicStampedR.getReference());
        }
    });
    
    public static  void  main(String[] args) throws InterruptedException {
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        
        t3.start();
        t4.start();
    }
}
