package cn.base;

public enum EnumDemo4 implements food ,sport{
    FOOD,
    SPORT,
    ;
    
    @Override
    public void eat() {
        System.out.println("eat.....");
    }
    
    @Override
    public void run() {
        System.out.println("run.....");
    }
}
