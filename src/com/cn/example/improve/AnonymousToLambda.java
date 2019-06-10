package cn.example.improve;

/**
 * @Author Gosin
 * @Date 2019/6/10 8:50
 */
public class AnonymousToLambda {
  public static void main(String[] args) {
    Runnable r1 = new Runnable(){
      @Override
      public void run(){
        System.out.println("Hello");
      }
    };
   // 优化后
    Runnable r2 = () -> System.out.println("Hello");
  }
}
