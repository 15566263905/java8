package cn.example.improve.designPatterns.strategicModel;

/**
 * @Author Gosin
 * @Date 2019/6/10 10:02
 */
public class IsAllLowerCase implements ValidationStrategy {
  @Override
  public boolean execute(String s) {
    return s.matches("[a-z]+");
  }
}
