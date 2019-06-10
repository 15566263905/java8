package cn.example.improve.designPatterns.factoryModel;

/**
 * @Author Gosin
 * @Date 2019/6/10 11:07
 */
public class Bond extends Product{
  @Override
  public void desc() {
    System.out.println("--债券--");
  }
}
