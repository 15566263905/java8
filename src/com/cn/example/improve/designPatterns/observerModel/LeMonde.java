package cn.example.improve.designPatterns.observerModel;

/**
 * @Author Gosin
 * @Date 2019/6/10 10:25
 */
public class LeMonde implements Observer{
  @Override
  public void notify(String tweet) {
    if(tweet != null && tweet.contains("wine")){
      System.out.println("Today cheese, wine and news! [" + tweet +"]");
    }
  }
}
