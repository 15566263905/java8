package cn.example.functionalFormula;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Main {
  public static void main(String[] args) {
    // 函数式编程-求子集
    System.out.println("-------函数式编程-求子集-------");
    List<List<Integer>> subs = subsets(Arrays.asList(1, 4, 9));
    subs.forEach(System.out::println);

    // 科里化-单位换算
    System.out.println("-------科里化-单位换算-------");
    DoubleUnaryOperator convertCtoF = curriedConverter(9.0/5, 32);
    DoubleUnaryOperator convertUSDtoGBP = curriedConverter(0.6, 0);
    DoubleUnaryOperator convertKmtoMi = curriedConverter(0.6214, 0);

    System.out.println(convertCtoF.applyAsDouble(24));
    System.out.println(convertUSDtoGBP.applyAsDouble(100));
    System.out.println(convertKmtoMi.applyAsDouble(20));

    DoubleUnaryOperator convertFtoC = expandedCurriedConverter(-32, 5.0/9, 0);
    System.out.println(convertFtoC.applyAsDouble(98.6));

    System.out.println(converter(1,2,3));

    // 函数式方法-二叉树
    System.out.println("-------函数式方法-二叉树-------");
    Tree t = new Tree("Mary", 22,
        new Tree("Emily", 20,
            new Tree("Alan", 50, null, null),
            new Tree("Georgie", 23, null, null)
        ),
        new Tree("Tian", 29,
            new Tree("Raoul", 23, null, null),
            null
        )
    );

    System.out.println(lookup("Raoul", -1, t));
    Tree f = fupdate("Jeff", 80, t);
    System.out.println(lookup("Jeff", -1, f));
    Tree u = update("Jim", 40, t);
    System.out.println(lookup("Jim", -1, u));

    // Stream自定义延迟列表
    System.out.println("------Stream自定义延迟列表-------");
    MyList<Integer> l = new MyLinkedList<>(5, new MyLinkedList<>(10, new Empty<>()));
    System.out.println(l.head());

    LazyList<Integer> numbers = from(2);
    int two = numbers.head();
    int three = numbers.tail().head();
    int four = numbers.tail().tail().head();
    System.out.println(two + " " + three + " " + four);

    numbers = from(2);
    int prime_two = primes(numbers).head();
    int prime_three = primes(numbers).tail().head();
    int prime_five = primes(numbers).tail().tail().head();
    System.out.println(prime_two + " " + prime_three + " " + prime_five);

    // 模式匹配
    System.out.println("------模式匹配[Java8不支持]-------");
    simplify();

    Expr e = new BinOp("+", new Number(5), new BinOp("*", new Number(3), new Number(4)));
    Integer result = evaluate(e);
    System.out.println(e + " = " + result);

    // 结合器
    System.out.println("------结合器-------");
    System.out.println(repeat(0, (Integer x) -> 2 * x).apply(10));
    System.out.println(repeat(1, (Integer x) -> 2 * x).apply(10));
    System.out.println(repeat(2, (Integer x) -> 2 * x).apply(10));
    System.out.println(repeat(3, (Integer x) -> 2 * x).apply(10));

  }

  // 函数式编程------------------------------------------------------------------------------------
  public static List<List<Integer>> subsets(List<Integer> l) {
    if (l.isEmpty()) {
      List<List<Integer>> ans = new ArrayList<>();
      ans.add(Collections.emptyList());
      return ans;
    }
    Integer first = l.get(0);
    List<Integer> rest = l.subList(1,l.size());
    List<List<Integer>> subans = subsets(rest);
    List<List<Integer>> subans2 = insertAll(first, subans);
    return concat(subans, subans2);
  }

  public static List<List<Integer>> insertAll(Integer first, List<List<Integer>> lists) {
    List<List<Integer>> result = new ArrayList<>();
    for (List<Integer> l : lists) {
      List<Integer> copyList = new ArrayList<>();
      copyList.add(first);
      copyList.addAll(l);
      result.add(copyList);
    }
    return result;
  }

  public static List<List<Integer>> concat(List<List<Integer>> a, List<List<Integer>> b) {
    List<List<Integer>> r = new ArrayList<>(a);
    r.addAll(b);
    return r;
  }

  // 科里化------------------------------------------------------------------------------------
  public static double converter(double x, double y, double z) {
    return x * y + z;
  }

  public static DoubleUnaryOperator curriedConverter(double y, double z) {
    return (double x) -> x * y + z;
  }

  public static DoubleUnaryOperator expandedCurriedConverter(double w, double y, double z) {
    return (double x) -> (x + w) * y + z;
  }

  // 函数式方法-二叉树------------------------------------------------------------------------------------
  static class Tree {
    private String key;
    private int val;
    private Tree left, right;

    public Tree(String k, int v, Tree l, Tree r) {
      key = k;
      val = v;
      left = l;
      right = r;
    }
  }
  // 命令式更新值,共享同一份数据结构
  public static Tree update(String k, int newval, Tree t) {
    if (t == null) {
      t = new Tree(k, newval, null, null);
    } else if (k.equals(t.key)) {
      t.val = newval;
    } else if (k.compareTo(t.key) < 0) {
      t.left = update(k, newval, t.left);
    } else {
      t.right = update(k, newval, t.right);
    }
    return t;
  }
  // 函数式更新值,纯函数式
  // 对树结构进行更新时，现存数据结构不会被破坏
  public static Tree fupdate(String k, int newval, Tree t) {
    return (t == null) ? new Tree(k, newval, null, null) :
        k.equals(t.key) ? new Tree(k, newval, t.left, t.right) :
            k.compareTo(t.key) < 0 ? new Tree(t.key, t.val, fupdate(k,newval, t.left), t.right) :
                new Tree(t.key, t.val, t.left, fupdate(k,newval, t.right));
  }
  // 递归查询值
  public static int lookup(String k, int defaultval, Tree t) {
    if (t == null) {
      return defaultval;
    }
    if (k.equals(t.key)) {
      return t.val;
    }
    return lookup(k, defaultval, k.compareTo(t.key) < 0 ? t.left : t.right);
  }

  // Stream自定义延迟列表------------------------------------------------------------------------------------
  public interface MyList<T> {
    T head();

    MyList<T> tail();

    default boolean isEmpty() {
      return true;
    }

    MyList<T> filter(Predicate<T> p);
  }

  public static class MyLinkedList<T> implements MyList<T> {
    final T head;
    final MyList<T> tail;

    public MyLinkedList(T head, MyList<T> tail) {
      this.head = head;
      this.tail = tail;
    }
    @Override
    public T head() {
      return head;
    }
    @Override
    public MyList<T> tail() {
      return tail;
    }
    @Override
    public boolean isEmpty() {
      return false;
    }
    @Override
    public MyList<T> filter(Predicate<T> p) {
      return isEmpty() ? this : p.test(head()) ? new MyLinkedList<>(
          head(), tail().filter(p)) : tail().filter(p);
    }
  }

  public static class Empty<T> implements MyList<T> {
    @Override
    public T head() {
      throw new UnsupportedOperationException();
    }
    @Override
    public MyList<T> tail() {
      throw new UnsupportedOperationException();
    }
    @Override
    public MyList<T> filter(Predicate<T> p) {
      return this;
    }
  }

  public static class LazyList<T> implements MyList<T> {
    final T head;
    final Supplier<MyList<T>> tail;

    public LazyList(T head, Supplier<MyList<T>> tail) {
      this.head = head;
      this.tail = tail;
    }
    @Override
    public T head() {
      return head;
    }
    @Override
    public MyList<T> tail() {
      // 触发延迟列表（ LazyList ）的节点创建,体现延迟计算的特定
      return tail.get();
    }
    @Override
    public boolean isEmpty() {
      return false;
    }
    @Override
    public MyList<T> filter(Predicate<T> p) {
      return isEmpty() ? this : p.test(head()) ? new LazyList<>(head(), () -> tail().filter(p)) : tail().filter(p);
    }
  }

  public static LazyList<Integer> from(int n) {
    return new LazyList<>(n, () -> from(n + 1));
  }

  public static MyList<Integer> primes(MyList<Integer> numbers) {
    return new LazyList<>(
        numbers.head(), () -> primes(numbers.tail().filter(n -> n % numbers.head() != 0))
    );
  }

  public static <T> void printAll(MyList<T> numbers) {
    if (numbers.isEmpty()) {
      return;
    }
    System.out.println(numbers.head());
    printAll(numbers.tail());
  }

  // 模式匹配-----------Java8不支持------自动匹配操作符号------------------------------------------------------------
  private static void simplify() {
    TriFunction<String, Expr, Expr, Expr> binopcase =
        (opname, left, right) -> {
          if ("+".equals(opname)) {
            if (left instanceof Number && ((Number) left).val == 0) {
              return right;
            }
            if (right instanceof Number && ((Number) right).val == 0) {
              return left;
            }
          }
          if ("*".equals(opname)) {
            if (left instanceof Number && ((Number) left).val == 1) {
              return right;
            }
            if (right instanceof Number && ((Number) right).val == 1) {
              return left;
            }
          }
          return new BinOp(opname, left, right);
        };
    Function<Integer, Expr> numcase = val -> new Number(val);
    Supplier<Expr> defaultcase = () -> new Number(0);

    Expr e = new BinOp("+", new Number(5), new Number(0));
    Expr match = patternMatchExpr(e, binopcase, numcase, defaultcase);
    if (match instanceof Number) {
      System.out.println("Number: " + match);
    } else if (match instanceof BinOp) {
      System.out.println("BinOp: " + match);
    }
  }

  private static Integer evaluate(Expr e) {
    Function<Integer, Integer> numcase = val -> val;
    Supplier<Integer> defaultcase = () -> 0;
    TriFunction<String, Expr, Expr, Integer> binopcase =
        (opname, left, right) -> {
          if ("+".equals(opname)) {
            if (left instanceof Number && right instanceof Number) {
              return ((Number) left).val + ((Number) right).val;
            }
            if (right instanceof Number && left instanceof BinOp) {
              return ((Number) right).val + evaluate((BinOp) left);
            }
            if (left instanceof Number && right instanceof BinOp) {
              return ((Number) left).val + evaluate((BinOp) right);
            }
            if (left instanceof BinOp && right instanceof BinOp) {
              return evaluate((BinOp) left) + evaluate((BinOp) right);
            }
          }
          if ("*".equals(opname)) {
            if (left instanceof Number && right instanceof Number) {
              return ((Number) left).val * ((Number) right).val;
            }
            if (right instanceof Number && left instanceof BinOp) {
              return ((Number) right).val * evaluate((BinOp) left);
            }
            if (left instanceof Number && right instanceof BinOp) {
              return ((Number) left).val * evaluate((BinOp) right);
            }
            if (left instanceof BinOp && right instanceof BinOp) {
              return evaluate((BinOp) left) * evaluate((BinOp) right);
            }
          }
          return defaultcase.get();
        };

    return patternMatchExpr(e, binopcase, numcase, defaultcase);
  }

  public static class Expr {
  }

  public static class Number extends Expr {
    int val;
    public Number(int val) {
      this.val = val;
    }

    @Override
    public String toString() {
      return "" + val;
    }
  }

  public static class BinOp extends Expr {
    String opname;
    Expr left, right;
    public BinOp(String opname, Expr left, Expr right) {
      this.opname = opname;
      this.left = left;
      this.right = right;
    }

    @Override
    public String toString() {
      return "(" + left + " " + opname + " " + right + ")";
    }
  }

  public static <T> T MyIf(boolean b, Supplier<T> truecase, Supplier<T> falsecase) {
    return b ? truecase.get() : falsecase.get();
  }

  public static interface TriFunction<S, T, U, R> {
    R apply(S s, T t, U u);
  }

  public static <T> T patternMatchExpr(Expr e,
                                TriFunction<String, Expr, Expr, T> binopcase,
                                Function<Integer, T> numcase, Supplier<T> defaultcase) {

    if (e instanceof BinOp) {
      return binopcase.apply(((BinOp) e).opname, ((BinOp) e).left, ((BinOp) e).right);
    } else if (e instanceof Number) {
      return numcase.apply(((Number) e).val);
    } else {
      return defaultcase.get();
    }
  }

  // 结合器------------------------------------------------------------------------------------
  // Function<T, R>
  // R apply(T t);
  public static <A, B, C> Function<A, C> compose(Function<B, C> g, Function<A, B> f) {
    return x -> g.apply(f.apply(x));
  }

  public static <A> Function<A, A> repeat(int n, Function<A, A> f) {
    return n == 0 ? x -> x : compose(f, repeat(n - 1, f));
    // return n == 0 ? x -> x : repeat(n - 1, f).andThen(f);
  }
}
