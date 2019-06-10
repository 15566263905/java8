package cn.example.improve.designPatterns.TemplateMethod;

/**
 * @Author Gosin
 * @Date 2019/6/10 10:14
 */
public class Customer {

  Customer(String id) {
    this.id = id;
  }

  private String id;
  private String name = "xxx";

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
