package cn.example.improve.designPatterns.observerModel;

/**
 * @Author Gosin
 * @Date 2019/6/10 10:24
 */
public class NYTimes implements Observer{
  @Override
  public void notify(String tweet) {
    if(tweet != null && tweet.contains("money")){
      System.out.println("Breaking news in NY! [" + tweet +"]");
    }
  }
}
