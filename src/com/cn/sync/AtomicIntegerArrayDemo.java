package cn.sync;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @Author Gosin
 * @Date 2019/8/15 13:46
 */
public class AtomicIntegerArrayDemo {
    static AtomicIntegerArray arr = new AtomicIntegerArray(10);
    
    public static class AddThread implements Runnable{
        @Override
        public void run(){
            for(int k=0; k<10000; k++){
                // 执行数组中元素自增操作,参数为index,即数组下标
                arr.getAndIncrement(k%arr.length());
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        
        Thread[] ts=new Thread[10];
        // 创建10条线程
        for(int k=0;k<10;k++){
            ts[k]=new Thread(new AddThread());
        }
        // 启动10条线程
        for(int k=0;k<10;k++){ts[k].start();}
        for(int k=0;k<10;k++){ts[k].join();}
        // 执行结果
        // [10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000]
        System.out.println(arr);
    }
}
