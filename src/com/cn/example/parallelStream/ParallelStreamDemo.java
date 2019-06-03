package cn.example.parallelStream;

import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * @Author Gosin
 * @Date 2019/6/3 19:27
 */
public class ParallelStreamDemo {
  public static void main(String[] args) {
    long n = 10;
    System.out.println("并行流:" + parallelSum(n));
    System.out.println("顺序流-无限流:" + sequentialSum(n));
    System.out.println("java原生:" + iterativeSum(n));

    System.out.println("性能测试[sequentialSum]:" +
        measureSumPerf(ParallelStreamDemo::sequentialSum, 10_000_000) + " msecs");
    System.out.println("性能测试[iterativeSum]:" +
        measureSumPerf(ParallelStreamDemo::iterativeSum, 10_000_000) + " msecs");
    System.out.println("性能测试[parallelSum]: " +
        measureSumPerf(ParallelStreamDemo::parallelSum, 10_000_000) + " msecs" );
    System.out.println("性能测试[rangedSum]: " +
        measureSumPerf(ParallelStreamDemo::rangedSum, 10_000_000) + " msecs" );
    System.out.println("性能测试[parallelRangedSum]: " +
        measureSumPerf(ParallelStreamDemo::parallelRangedSum, 10_000_000) + " msecs" );
  }

  /**
   * a3,并行归纳,并行流,计算求和
   * iterate 生成的是装箱的对象，必须拆箱成数字才能求和；
   * 很难把 iterate 分成多个独立块来并行执行；
   * 不建议做并行操作
   * 改善：用LongStream.rangeClosed代替，原因如下
   *  LongStream.rangeClosed直接产生原始类型的 long 数字，没有装箱拆箱的开销。
   *  LongStream.rangeClosed 会生成数字范围，很容易拆分为独立的小块。
   * **/
  public static long parallelSum(long n) {
    return Stream.iterate(1L, i -> i + 1)
        .limit(n)
        .parallel() // 将流转换为并行流，之后进行的所有操作都并行执行,默认的线程数量就是你的处理器数量
        // .sequential() // 将流转换为顺序流，之后进行的所有操作都顺序执行
        .reduce(0L, Long::sum);
  }

  /**
   * a4 = a3的改善，更利于并行
   * **/
  public static long rangedSum(long n) {
    return LongStream.rangeClosed(1, n).reduce(0L, Long::sum);
  }

  /**
   * a5 = a4的改进版，性能更佳
   * **/
  public static long parallelRangedSum(long n) {
    return LongStream.rangeClosed(1, n)
        .parallel()
        .reduce(0L, Long::sum);
  }

  /**
   * a1,顺序归纳,顺序流,求和
   * **/
  public static long sequentialSum(long n) {
    return Stream.iterate(1L, i -> i + 1) // 生成自然数无限流
        .limit(n)
        .reduce(0L, Long::sum);
  }

  /**
   * a2,迭代式,求和，与a1等价
   * **/
  public static long iterativeSum(long n) {
    long result = 0;
    for (long i = 1L; i <= n; i++) {
      result += i;
    }
    return result;
  }

  /**
   * 性能测试
   * **/
  public static long measureSumPerf(Function<Long, Long> adder, long n) {
    long fastest = Long.MAX_VALUE;
    for (int i = 0; i < 10; i++) {
      long start = System.nanoTime();
      long sum = adder.apply(n);
      long duration = (System.nanoTime() - start) / 1_000_000;
      System.out.println("Result: " + sum);
      if (duration < fastest) {
        fastest = duration;
      }
    }
    return fastest;
  }
}
