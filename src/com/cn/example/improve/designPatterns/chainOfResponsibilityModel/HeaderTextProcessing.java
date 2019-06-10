package cn.example.improve.designPatterns.chainOfResponsibilityModel;

/**
 * @Author Gosin
 * @Date 2019/6/10 10:44
 */
public class HeaderTextProcessing extends ProcessingObject<String>{
  @Override
  protected String handleWork(String input) {
    return "From Raoul, Mario and Alan: " + input;
  }
}
