package cn.example.improve.designPatterns.TemplateMethod;

import java.util.function.Consumer;

/**
 * @Author Gosin
 * @Date 2019/6/10 10:18
 */
public class OnlineBankingLambda {

  // 方便地通过传递Lambda表达式,插入不同的行为
  public void processCustomer(String id, Consumer<Customer> makeCustomerHappy){
    Customer c = new Customer(id);
    makeCustomerHappy.accept(c);
  }
}
