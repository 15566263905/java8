package cn.example.improve.DefaultMethod;

/**
 * @Author Gosin
 * @Date 2019/6/10 11:57
 */
public interface B extends A {
  @Override
  default void hello() {
    System.out.println("Hello from B");
  }
}
