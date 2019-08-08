package cn.sync;

/**
 * 由于synchronized关键字修饰的是静态increase方法，与修饰实例方法不同的是，其锁对象是当前类的class对象。
 * @Date 2019/8/6 16:02
 */
public class AccountingSyncStatic implements Runnable {
    /**
     * 共享资源(临界资源)
     */
    static int i = 0;
    
    /**
     * synchronized 修饰静态方法，锁是当前class对象，也就是AccountingSyncStatic类对应的class对象
     *
     */
    public static synchronized void increase(){
        i++;
    }
    
    /**
     * synchronized 修饰实例方法
     * 其对象锁是当前实例对象，如果别的线程调用该方法，将不会产生互斥现象，毕竟锁对象不同；
     * 此种情况线程锁对象不同，线程安全无法保证
     */
    public synchronized void increase2(){
        i++;
    }
    
    @Override
    public void run() {
        for(int j=0; j < 1000000; j++){
            increase();
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        //new新实例
        Thread t1=new Thread(new AccountingSyncStatic());
        //new新实例
        Thread t2=new Thread(new AccountingSyncStatic());
        //启动线程
        t1.start();
        t2.start();
        //join含义:当前线程A等待thread线程终止之后才能从thread.join()返回
        t1.join();
        t2.join();
        System.out.println(i);
    }
}
