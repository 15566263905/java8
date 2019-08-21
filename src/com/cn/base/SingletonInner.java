package cn.base;

/**
 * 静态内部类
 */
public class SingletonInner {
    private static class Holder {
        private static SingletonInner singleton = new SingletonInner();
    }
    
    private SingletonInner(){}
    
    public static SingletonInner getSingleton(){
        return Holder.singleton;
    }
}
