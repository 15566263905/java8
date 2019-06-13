package cn.example.ServiceLoader;

/**
 * @Author Gosin
 * @Date 2019/6/13 16:57
 */
public class MyServiceLoaderImpl2 implements IMyServiceLoader {
  @Override
  public String sayHello() {
    return "hello2";
  }

  @Override
  public String getName() {
    return "name2";
  }
}
