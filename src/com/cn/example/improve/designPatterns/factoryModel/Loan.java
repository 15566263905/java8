package cn.example.improve.designPatterns.factoryModel;

/**
 * @Author Gosin
 * @Date 2019/6/10 11:06
 */
public class Loan extends Product {
  @Override
  public void desc() {
    System.out.println("--贷款--");
  }
}
