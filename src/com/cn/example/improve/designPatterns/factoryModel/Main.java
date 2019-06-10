package cn.example.improve.designPatterns.factoryModel;

import cn.example.improve.designPatterns.observerModel.Feed;
import cn.example.improve.designPatterns.observerModel.Guardian;
import cn.example.improve.designPatterns.observerModel.LeMonde;
import cn.example.improve.designPatterns.observerModel.NYTimes;

import java.util.function.Supplier;

/**
 * @Author Gosin
 * @Date 2019/6/10 11:07
 */
public class Main {
  public static void main(String[] args) {
    // tradition();
    java8();
  }

  private static void tradition() {
    Product p = ProductFactory.createProduct("loan");
    p.desc();
  }

  private static void java8() {
    Supplier<Product> loanSupplier = Loan::new;
    Loan loan = (Loan)loanSupplier.get();
    loan.desc();
  }
}
