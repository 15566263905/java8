package cn.example.improve.designPatterns.TemplateMethod;

/**
 * @Author Gosin
 * @Date 2019/6/10 10:20
 */
public class OnlineBankingOne extends OnlineBanking{
  @Override
  void makeCustomerHappy(Customer c) {
    System.out.println("Hello " + c.getName());
  }
}
