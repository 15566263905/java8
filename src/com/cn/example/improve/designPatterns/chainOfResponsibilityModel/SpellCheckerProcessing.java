package cn.example.improve.designPatterns.chainOfResponsibilityModel;

/**
 * @Author Gosin
 * @Date 2019/6/10 10:45
 */
public class SpellCheckerProcessing extends ProcessingObject<String> {
  @Override
  protected String handleWork(String input) {
    return input.replaceAll("labda", "lambda");
  }
}
