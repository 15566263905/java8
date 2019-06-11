package cn.example.improve.DefaultMethod;

/**
 * @Author Gosin
 * @Date 2019/6/10 13:14
 */
public class C1 implements B1, A{
  @Override
  public void hello(){
    B1.super.hello();
  }
  public static void main(String... args) {
    new C1().hello();
  }
}
