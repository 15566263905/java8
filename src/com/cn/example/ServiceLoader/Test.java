package cn.example.ServiceLoader;

import java.util.ServiceLoader;

/**
 * https://www.jianshu.com/p/7601ba434ff4
 * 按照使用说明文档，应该分下面几个步骤来使用：
 *
 * 创建一个接口文件
 * 在resources资源目录下创建META-INF/services文件夹
 * 在services文件夹中创建文件，以接口全名命名
 * 创建接口实现类
 *
 * 原理：在ServiceLoader.load的时候，根据传入的接口类，遍历META-INF/services目录下的以该类命名的文件中的所有类，并实例化返回。
 *
 */
public class Test {
  public static void main(String[] args) {
    ServiceLoader<IMyServiceLoader> serviceLoader = ServiceLoader.load(IMyServiceLoader.class);
    for (IMyServiceLoader myServiceLoader : serviceLoader){
      System.out.println(myServiceLoader.getName() + myServiceLoader.sayHello());
    }
  }
}
