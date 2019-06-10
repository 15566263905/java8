package cn.example.improve.designPatterns.chainOfResponsibilityModel;

import cn.example.improve.designPatterns.observerModel.Feed;
import cn.example.improve.designPatterns.observerModel.Guardian;
import cn.example.improve.designPatterns.observerModel.LeMonde;
import cn.example.improve.designPatterns.observerModel.NYTimes;

import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * @Author Gosin
 * @Date 2019/6/10 10:45
 */
public class Main {
  public static void main(String[] args) {
    // tradition();
    java8();
  }

  private static void tradition() {
    ProcessingObject<String> p1 = new HeaderTextProcessing();
    ProcessingObject<String> p2 = new SpellCheckerProcessing();
    p1.setSuccessor(p2);
    String result = p1.handle("Aren't labdas really sexy?!!");
    System.out.println(result);
  }

  private static void java8() {
    // UnaryOperator继承Function,同样是JDK1.8中的新特性。功能就是：对数据进行操作，生成一个与同类型对象。
    // 方法只有一个static UnaryOperator identity()，该方法返回一个UnaryOerator对象，并且apply()方法中直接返回范型对象。
    // static <T> UnaryOperator<T> identity() {
    //   return new UnaryOperator<T>() {
    //   @Override
    //   public T apply(T t) {
    //     return t;
    //   }
    //  };
    //    }

    UnaryOperator<String> headerProcessing =
        (String text) -> "From Raoul, Mario and Alan: " + text;
    UnaryOperator<String> spellCheckerProcessing =
        (String text) -> text.replaceAll("labda", "lambda");
    Function<String, String> pipeline =
        headerProcessing.andThen(spellCheckerProcessing);
    String result = pipeline.apply("Aren't labdas really sexy?!!");
    System.out.println(result);
  }
}
