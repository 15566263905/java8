package cn.sync;

/**
 * synchronized的可重入性
 * @Date 2019/8/7 9:48
 */
public class AccountingSyncReentrant implements Runnable {
    
    static AccountingSyncReentrant instance = new AccountingSyncReentrant();
    
    static int i = 0;
    static int j = 0;
    
    public synchronized void increase(){
        j++;
    }
    @Override
    public void run() {
        for(int j = 0; j < 1000000; j++){
            //this,当前实例对象锁
            synchronized(this){
                i++;
                increase();
            }
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        Thread t1=new Thread(instance);
        Thread t2=new Thread(instance);
        t1.start();
        t2.start();
        //join含义:当前线程A等待thread线程终止之后才能从thread.join()返回
        t1.join();
        t2.join();
        System.out.println(i);
    }
}

