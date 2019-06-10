package cn.example.improve.designPatterns.observerModel;

/**
 * @Author Gosin
 * @Date 2019/6/10 10:28
 */
public class Main {
  public static void main(String[] args) {
    Feed f = new Feed();
    // tradition(f);
    // 省去模版方法
    java8(f);

    f.notifyObservers("The queen said her favourite book is Java 8 in Action!");
    f.notifyObservers("money is Java 8 in Action!");
    f.notifyObservers("wine is Java 8 in Action!");
  }

  private static void tradition(Feed f) {
    f.registerObserver(new NYTimes());
    f.registerObserver(new Guardian());
    f.registerObserver(new LeMonde());
  }

  private static void java8(Feed f) {
    f.registerObserver((String tweet) -> {
      if(tweet != null && tweet.contains("money")){
        System.out.println("Breaking news in NY! [" + tweet + "]");
      }
    });
    f.registerObserver((String tweet) -> {
      if(tweet != null && tweet.contains("queen")){
        System.out.println("Yet another news in London... [" + tweet + "]");
      }
    });
  }
}
