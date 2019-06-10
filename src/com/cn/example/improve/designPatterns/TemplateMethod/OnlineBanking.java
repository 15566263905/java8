package cn.example.improve.designPatterns.TemplateMethod;

import java.util.function.Consumer;

/**
 * @Author Gosin
 * @Date 2019/6/10 10:12
 */
public abstract class OnlineBanking {

  public void processCustomer(String id){
    Customer c = new Customer(id);
    makeCustomerHappy(c);
  }

  abstract void makeCustomerHappy(Customer c);
}
