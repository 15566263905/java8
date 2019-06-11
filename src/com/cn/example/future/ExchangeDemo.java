package cn.example.future;

/**
 * @Author Gosin
 */
public class ExchangeDemo {
  public static void delay() {
    try {
      Thread.sleep(1000L);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
  public static double getRate(String source,String target){
    delay();
    return 10;
  }
}
