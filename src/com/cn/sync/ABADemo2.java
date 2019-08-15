package cn.sync;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicMarkableReference;

/**
 * AtomicMarkableReference
 * @Date 2019/8/15 16:08
 */
public class ABADemo2 {
    
    static AtomicMarkableReference<Integer> atMarkRef =
            new AtomicMarkableReference<>(100,false);
    
    static Thread t5 = new Thread(new Runnable() {
        @Override
        public void run() {
            boolean mark = atMarkRef.isMarked();
            System.out.println("t5 value:" + atMarkRef.getReference() + ", mark: " + mark);
            boolean flag = atMarkRef.compareAndSet(atMarkRef.getReference(), 101, mark, !mark);
            System.out.println("t5: flag[is equal](100, 101):"+flag+",newValue:" + atMarkRef.getReference());
        }
    });
    
    static Thread t6 = new Thread(new Runnable() {
        @Override
        public void run() {
            boolean mark = atMarkRef.isMarked();
            System.out.println("t6 value:" + atMarkRef.getReference() + ", mark: " + mark);
            boolean flag = atMarkRef.compareAndSet(atMarkRef.getReference(), 100, mark, !mark);
            System.out.println("t6: flag[is equal](101, 100):"+flag+",newValue:" + atMarkRef.getReference());
        }
    });
    
    static Thread t7 = new Thread(new Runnable() {
        @Override
        public void run() {
            boolean mark = atMarkRef.isMarked();
            System.out.println("t7 value:" + atMarkRef.getReference() + ", mark: " + mark);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean flag = atMarkRef.compareAndSet(100,102,mark,!mark);
            System.out.println("t7: flag[is equal](100, 102):"+flag+",newValue:" + atMarkRef.getReference());
        }
    });
    
    public static void main(String[] args) throws InterruptedException {
        t5.start();
        t5.join();
        
        t6.start();
        t6.join();
        
        t7.start();
    }
}
