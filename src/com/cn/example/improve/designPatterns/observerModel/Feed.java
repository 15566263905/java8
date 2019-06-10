package cn.example.improve.designPatterns.observerModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 通知注册
 * @Author Gosin
 * @Date 2019/6/10 10:27
 */
public class Feed implements Subject{

  private final List<Observer> observers = new ArrayList<>();

  @Override
  public void registerObserver(Observer o) {
    this.observers.add(o);
  }

  @Override
  public void notifyObservers(String tweet) {
    observers.forEach(o -> o.notify(tweet));
  }
}
