package cn.sync;

/**
 * @decrition 消费者线程
 * @Author Gosin
 * @Date 2019/8/15 20:58
 */
public class Mutil_Consumer implements Runnable{
    private ResourceByCondition r;
    
    Mutil_Consumer(ResourceByCondition r) {
        this.r = r;
    }
    @Override
    public void run() {
        while (true) {
            r.consume();
        }
    }
}
