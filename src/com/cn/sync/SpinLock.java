package cn.sync;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author Gosin
 * @Date 2019/8/15 16:43
 */
public class SpinLock {
    private AtomicReference<Thread> sign =new AtomicReference<>();
    
    public void lock(){
        Thread current = Thread.currentThread();
        while(!sign .compareAndSet(null, current)){
        }
    }
    
    public void unlock (){
        Thread current = Thread.currentThread();
        sign .compareAndSet(current, null);
    }
}
