package cn.example.collector;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.IntStream;

import static java.util.stream.Collector.Characteristics.IDENTITY_FINISH;

/**
 * @Author Gosin
 * @Date 2019/6/3 14:46
 */
public class PrimeNumbersCollector
    implements Collector<Integer, Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> {

  public static void main(String[] args) {
    PrimeNumbersCollector primeNumbersCollector = new PrimeNumbersCollector();
    int n = 9;
    Map<Boolean, List<Integer>> result = IntStream.rangeClosed(2, n).boxed()
        .collect(primeNumbersCollector);

    result.forEach((k1,v1)->v1.forEach(v2->System.out.println(v2)));

    System.out.println("--------------------------");
    result = primeNumbersCollector.partitionPrimesWithCustomCollector(9);
    result.forEach((k1,v1)->v1.forEach(v2->System.out.println(v2)));

  }

  // 除了实现接口外，还可以实现
  public Map<Boolean, List<Integer>> partitionPrimesWithCustomCollector(int n) {
    return IntStream.rangeClosed(2, n).boxed()
      .collect(
          () -> new HashMap<Boolean, List<Integer>>(2) {{
            put(true, new ArrayList<>());
            put(false, new ArrayList<>());
          }},
          (acc, candidate) -> {
            acc.get(isPrime(acc.get(true), candidate) )
                .add(candidate);
          },
          (map1, map2) -> {
            map1.get(true).addAll(map2.get(true));
            map1.get(false).addAll(map2.get(false));
          });
  }

  /**
   * 将找到的质数添加已经找到的质数列表
   * @param sourceList 已经找到的质数列表
   * @param candidate 判断是否是质数
   * @return boolean true质数,false非质数
   * **/
  private boolean isPrime(List<Integer> sourceList, int candidate) {
    int candidateRoot = (int) Math.sqrt((double) candidate);
    if (IntStream.rangeClosed(2, candidateRoot).noneMatch(i -> candidate % i == 0)) {
     return false;
    }
    return true;
  }

  @Override
  public Supplier<Map<Boolean, List<Integer>>> supplier() {
    return () -> new HashMap<Boolean, List<Integer>>() {{
      put(true, new ArrayList<>());
      put(false, new ArrayList<>());
    }};
  }
  @Override
  public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
    return (Map<Boolean, List<Integer>> acc, Integer candidate) -> {
      acc.get(isPrime(acc.get(true), candidate)).add(candidate);
    };
  }
  @Override
  public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
    return (Map<Boolean, List<Integer>> map1,
            Map<Boolean, List<Integer>> map2) -> {
      map1.get(true).addAll(map2.get(true));
      map1.get(false).addAll(map2.get(false));
      return map1;
    };
  }
  @Override
  public Function<Map<Boolean, List<Integer>>,
      Map<Boolean, List<Integer>>> finisher() {
    return Function.identity();
  }
  @Override
  public Set<Characteristics> characteristics() {
    return Collections.unmodifiableSet(EnumSet.of(IDENTITY_FINISH));
  }
}
