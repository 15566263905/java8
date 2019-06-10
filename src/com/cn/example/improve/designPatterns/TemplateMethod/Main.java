package cn.example.improve.designPatterns.TemplateMethod;

/**
 * @Author Gosin
 * @Date 2019/6/10 10:18
 */
public class Main {
  public static void main(String[] args) {

    new OnlineBankingOne().processCustomer("1336");

    // 避免实现模版类
    new OnlineBankingLambda().processCustomer("1337", (Customer c) ->
        System.out.println("Hello " + c.getName()));
  }
}
