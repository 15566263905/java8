package cn.example.improve.DefaultMethod;

/**
 * 菱形继承问题
 * @Author Gosin
 * @Date 2019/6/10 13:19
 */
public class D2 implements B2, C2{

  public static void main(String... args) {
    new D2().hello();
  }

  @Override
  public void hello() {
    System.out.println("Hello from D2");
  }
}
