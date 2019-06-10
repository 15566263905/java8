package cn.example.improve.designPatterns.strategicModel;

/**
 * @Author Gosin
 * @Date 2019/6/10 10:03
 */
public class IsNumeric implements ValidationStrategy {
  @Override
  public boolean execute(String s) {
    return s.matches("\\d+");
  }
}
