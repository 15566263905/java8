package cn.sync;

/**
 * @decrition 生产者线程
 * @Author Gosin
 * @Date 2019/8/15 20:57
 */
public class Mutil_Producer implements Runnable {
    private ResourceByCondition r;
    
    Mutil_Producer(ResourceByCondition r) {
        this.r = r;
    }
    @Override
    public void run() {
        while (true) {
            r.product("北京烤鸭");
        }
    }
}
