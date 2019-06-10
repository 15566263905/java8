package cn.example.model;

/**
 * @Author Gosin
 * @Date 2019/5/31 10:07
 */
public class City {
  /**
   * 城市名
   */
  private String name;

  /**
   * 构造器
   * @param name 城市名
   */
  public City(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
