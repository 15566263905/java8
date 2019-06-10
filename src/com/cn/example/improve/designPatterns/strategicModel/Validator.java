package cn.example.improve.designPatterns.strategicModel;

/**
 * @Author Gosin
 * @Date 2019/6/10 10:03
 */
public class Validator {
  private final ValidationStrategy strategy;

  public Validator(ValidationStrategy v){
    this.strategy = v;
  }
  public boolean validate(String s){
    return strategy.execute(s);
  }

  public static void main(String[] args) {
    System.out.println("-----------------传统方式-------------------");
    Validator numericValidator = new Validator(new IsNumeric());
    boolean b1 = numericValidator.validate("aaaa");
    System.out.println(b1);

    Validator lowerCaseValidator = new Validator(new IsAllLowerCase ());
    boolean b2 = lowerCaseValidator.validate("bbbb");
    System.out.println(b2);

    System.out.println("-------Java8-Lambda方式----更加简化---避免了僵化的模板代码-----");
    Validator numericValidatorLambda = new Validator((String s) -> s.matches("[a-z]+"));
    boolean b3 = numericValidator.validate("aaaa");
    System.out.println(b3);

    Validator lowerCaseValidatorLambda = new Validator((String s) -> s.matches("\\d+"));
    boolean b4 = lowerCaseValidator.validate("bbbb");
    System.out.println(b4);
  }
}
