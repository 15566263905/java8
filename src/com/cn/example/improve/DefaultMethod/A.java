package cn.example.improve.DefaultMethod;

/**
 * @Author Gosin
 * @Date 2019/6/10 11:57
 */
public interface A {
  default void hello() {
    System.out.println("Hello from A");
  }
}
