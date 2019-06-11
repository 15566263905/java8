package cn.example.improve.DefaultMethod;

/**
 * @Author Gosin
 * @Date 2019/6/10 13:13
 */
public interface B1 {
  default void hello() {
    System.out.println("Hello from B1");
  }
}
