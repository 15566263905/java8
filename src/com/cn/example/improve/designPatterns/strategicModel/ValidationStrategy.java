package cn.example.improve.designPatterns.strategicModel;

/**
 * @Author Gosin
 * @Date 2019/6/10 10:01
 */
// @FunctionalInterface
public interface ValidationStrategy {
  boolean execute(String s);
}
