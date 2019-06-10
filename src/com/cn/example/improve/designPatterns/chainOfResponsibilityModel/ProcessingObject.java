package cn.example.improve.designPatterns.chainOfResponsibilityModel;

/**
 * @Author Gosin
 * @Date 2019/6/10 10:43
 */
public abstract class ProcessingObject<T> {

  protected ProcessingObject<T> successor;

  public void setSuccessor(ProcessingObject<T> successor){
    this.successor = successor;
  }

  public T handle(T input){
    T r = handleWork(input);
    if(successor != null){
      return successor.handle(r);
    }
    return r;
  }

  abstract protected T handleWork(T input);
}
