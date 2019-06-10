package cn.example.improve.designPatterns.factoryModel;

/**
 * @Author Gosin
 * @Date 2019/6/10 10:52
 */
public class ProductFactory {
  public static Product createProduct(String name){
    switch(name){
      case "loan": return new Loan();
      case "stock": return new Stock();
      case "bond": return new Bond();
      default: throw new RuntimeException("No such product " + name);
    }
  }
}
