package cn.example.improve.designPatterns.observerModel;

/**
 * 主题
 * @Author Gosin
 * @Date 2019/6/10 10:26
 */
public interface Subject {
  void registerObserver(Observer o);
  void notifyObservers(String tweet);
}
