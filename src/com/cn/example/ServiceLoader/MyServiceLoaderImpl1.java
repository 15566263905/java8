package cn.example.ServiceLoader;

/**
 * @Author Gosin
 * @Date 2019/6/13 16:56
 */
public class MyServiceLoaderImpl1 implements IMyServiceLoader {
  @Override
  public String sayHello() {
    return "hello1";
  }

  @Override
  public String getName() {
    return "name1";
  }
}
