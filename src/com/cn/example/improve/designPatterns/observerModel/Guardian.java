package cn.example.improve.designPatterns.observerModel;

/**
 * @Author Gosin
 * @Date 2019/6/10 10:25
 */
public class Guardian implements Observer{
  @Override
  public void notify(String tweet) {
    if(tweet != null && tweet.contains("queen")){
      System.out.println("Yet another news in London... [" + tweet + "]");
    }
  }
}
