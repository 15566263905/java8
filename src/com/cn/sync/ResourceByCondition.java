package cn.sync;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author Gosin
 * @Date 2019/8/15 20:55
 */
public class ResourceByCondition {
    private String name;
    private int count = 1;
    private boolean flag = false;
    
    // 创建一个锁对象。
    Lock lock = new ReentrantLock();
    
    // 通过已有的锁获取两组监视器，一组监视生产者，一组监视消费者。
    Condition producer_con = lock.newCondition();
    Condition consumer_con = lock.newCondition();
    
    /**
     * 生产
     * @param name
     */
    public  void product(String name)
    {
        lock.lock();
        try
        {
            while(flag){
                try{producer_con.await();}catch(InterruptedException e){}
            }
            this.name = name + count;
            count++;
            System.out.println(Thread.currentThread().getName()+"...生产者5.0..."+this.name);
            flag = true;
            // 直接唤醒消费线程
            consumer_con.signal();
        }
        finally
        {
            lock.unlock();
        }
    }
    
    /**
     * 消费
     */
    public  void consume()
    {
        lock.lock();
        try
        {
            while(!flag){
                try{consumer_con.await();}catch(InterruptedException e){}
            }
            // 消费烤鸭1
            System.out.println(Thread.currentThread().getName()+"...消费者.5.0......."+this.name);
            flag = false;
            // 直接唤醒生产线程
            producer_con.signal();
        }
        finally
        {
            lock.unlock();
        }
    }
    
    public static void main(String[] args) {
        ResourceByCondition r = new ResourceByCondition();
        Mutil_Producer pro = new Mutil_Producer(r);
        Mutil_Consumer con = new Mutil_Consumer(r);
        //生产者线程
        Thread t0 = new Thread(pro);
        Thread t1 = new Thread(pro);
        //消费者线程
        Thread t2 = new Thread(con);
        Thread t3 = new Thread(con);
        //启动线程
        t0.start();
        t1.start();
        t2.start();
        t3.start();
    }
}
