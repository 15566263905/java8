package cn.example.future;

import java.util.Random;
import java.util.concurrent.*;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * @Author Gosin
 * @Date 2019/6/11 18:44
 */
public class Main {
  public static void main(String[] args) {
    try{
      //futureTest();

      //runAsync();
      //supplyAsync();

      //whenComplete();
      //whenComplete2();

      //thenAcceptTest();
      //thenRunTest();
      //thenApplyTest();
      //thenCombineTest();
      //thenAcceptBothTest();
      //runAfterBothTest();

      //thenComposeTest();

      //applyToEitherTest();
      //acceptEitherTest();

      //exceptionallyTest();

      //handleTest();

      allOfAndAnyOfTest();
    }catch (Exception ex) {
      System.out.println(ex);
    }
  }

  public static void futureTest() {
    try{
      // CompletableFuture实现了Future接口，因此你可以像Future那样使用它。
      //其次，CompletableFuture并非一定要交给线程池执行才能实现异步，你可以像下面这样实现异步运行：
      //如果发生异常:异常会被限制在执行任务的线程的范围内，最终会杀死该线程，而这会导致等待get方法返回,结果的线程永久地被阻塞.
      //[解]客户端可以使用重载版本的get 方法，它使用一个超时参数来避免发生这样的情况。这是一种值得推荐的做法，你应该尽量在你的代码中添加超时判断的逻辑，避免发生类似的问题。
      CompletableFuture<String> completableFuture = new CompletableFuture<>();
      new Thread(() -> {
        // 模拟执行耗时任务
        System.out.println("task doing...");
        try {
          Thread.sleep(3000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        // 告诉completableFuture任务已经完成
        completableFuture.complete("ok");
      }).start();
      // 获取任务结果，如果没有完成会一直阻塞等待
      String result = completableFuture.get();
      System.out.println("计算结果:" + result);
    } catch (Exception e) {
      System.out.println(e);
    }

    // 获取任务线程内发生的异常
    try{
      CompletableFuture<String> completableFuture = new CompletableFuture<>();
      new Thread(() -> {
        // 模拟执行耗时任务
        System.out.println("task doing...");
        try {
          Thread.sleep(3000);
          int i = 1/0;
        } catch (Exception e) {
          // 告诉completableFuture任务发生异常了
          completableFuture.completeExceptionally(e);
        }
        // 告诉completableFuture任务已经完成
        completableFuture.complete("ok");
      }).start();
      // 获取任务结果，如果没有完成会一直阻塞等待
      String result = completableFuture.get();
      System.out.println("计算结果:" + result);
    }catch (Exception ex) {
      System.out.println(ex);
    }
  }

  public static void thenAcceptTest(){
    //当前任务正常完成以后执行,当前任务的执行结果可以作为下一任务的输入参数,无返回值.
    //执行任务A,同时异步执行任务B,待任务B正常返回之后,用B的返回值执行任务C,任务C无返回值
    CompletableFuture<String> futureA = CompletableFuture.supplyAsync(() -> "任务A");
    CompletableFuture<String> futureB = CompletableFuture.supplyAsync(() -> "任务B");
    futureB.thenAccept(b -> {
      System.out.println("参数:" + b);
      System.out.println("执行任务C.");
    });
  }

  public static void thenRunTest(){
    //对不关心上一步的计算结果，执行下一个操作
    //执行任务A,任务A执行完以后,执行任务B,任务B不接受任务A的返回值(不管A有没有返回值),也无返回值
    CompletableFuture<String> futureA = CompletableFuture.supplyAsync(() -> "任务A");
    futureA.thenRun(() -> System.out.println("执行任务B"));
  }

  public static void thenApplyTest(){
    //当前任务正常完成以后执行，当前任务的执行的结果会作为下一任务的输入参数,有返回值
    //多个任务串联执行,下一个任务的执行依赖上一个任务的结果,每个任务都有输入和输出
    //异步执行任务A,当任务A完成时使用A的返回结果resultA作为入参进行任务B的处理,可实现任意多个任务的串联执行
    CompletableFuture<String> futureA = CompletableFuture.supplyAsync(() -> "hello");
    CompletableFuture<String> futureB = futureA.thenApply(s->s + " world");
    CompletableFuture<String> futureC = futureB.thenApply(String::toUpperCase);
    System.out.println(futureC.join());
//    try {
//      System.out.println(futureC.get());
//    } catch (InterruptedException e) {
//      e.printStackTrace();
//    } catch (ExecutionException e) {
//      e.printStackTrace();
//    }
  }

  public static void thenCombineTest(){
    //结合两个CompletionStage的结果，进行转化后返回
    CompletableFuture<Double> futurePrice = CompletableFuture.supplyAsync(() -> 100d);
    CompletableFuture<Double> futureDiscount = CompletableFuture.supplyAsync(() -> 0.8);
    CompletableFuture<Double> futureResult = futurePrice.thenCombine(futureDiscount, (price, discount) -> price * discount);
    System.out.println("最终价格为:" + futureResult.join());
  }

  public static void thenAcceptBothTest(){
    //结合两个CompletionStage的结果，进行转化
    CompletableFuture<Double> futurePrice = CompletableFuture.supplyAsync(() -> 100d);
    CompletableFuture<Double> futureDiscount = CompletableFuture.supplyAsync(() -> 0.8);
    futurePrice.thenAcceptBoth(futureDiscount, (price, discount) -> {
      System.out.println("最终价格为:" + price * discount);
    });
  }

  public static void runAfterBothTest(){
    //结合两个CompletionStage的结果，进行转化后执行
    CompletableFuture<Double> futurePrice = CompletableFuture.supplyAsync(() -> 100d);
    CompletableFuture<Double> futureDiscount = CompletableFuture.supplyAsync(() -> 0.8);
    System.out.println(futureDiscount.join());
    futurePrice.runAfterBoth(futureDiscount, new Runnable() {
      @Override
      public void run() {
        System.out.println("开始 run");
      }
    });
  }

  public static void thenComposeTest(){
    //这个方法接收的输入是当前的CompletableFuture的计算值，返回结果将是一个新的CompletableFuture
    CompletableFuture<String> futureA = CompletableFuture.supplyAsync(() -> "hello");
    CompletableFuture<String> futureB = futureA.thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " world"));
    CompletableFuture<String> futureC = futureB.thenCompose(s -> CompletableFuture.supplyAsync(s::toUpperCase));
    System.out.println(futureC.join());
  }

  public static void applyToEitherTest(){
    //执行两个CompletionStage的结果,那个先执行完了,就是用哪个的返回值进行下一步操作
    //假设查询商品a,有两种方式,A和B,但是A和B的执行速度不一样,我们希望哪个先返回就用那个的返回值.
    CompletableFuture<String> futureA = CompletableFuture.supplyAsync(() -> {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      return "通过方式A获取商品a";
    });
    CompletableFuture<String> futureB = CompletableFuture.supplyAsync(() -> {
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      return "通过方式B获取商品a";
    });
    CompletableFuture<String> futureC = futureA.applyToEither(futureB, product -> "结果:" + product);
    System.out.println(futureC.join());
  }

  public static void acceptEitherTest(){
    //执行两个CompletionStage的结果,那个先执行完了,就是用哪个的返回值进行下一步操作
    //假设查询商品a,有两种方式,A和B,但是A和B的执行速度不一样,我们希望哪个先返回就用那个的返回值.
    CompletableFuture<String> futureA = CompletableFuture.supplyAsync(() -> {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      return "通过方式A获取商品a";
    });
    CompletableFuture<String> futureB = CompletableFuture.supplyAsync(() -> {
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      return "通过方式B获取商品a";
    });
    futureA.acceptEither(futureB, product -> {
      System.out.println( "结果:" + product);
    });
  }

  public static void exceptionallyTest(){
    CompletableFuture<String> futureA = CompletableFuture.
        supplyAsync(() -> "执行结果:" + (100 / 0))
        .thenApply(s -> "futureA result:" + s)
        .exceptionally(e -> {
          System.out.println(e.getMessage());
          return "futureA result: 100";
        });
    CompletableFuture<String> futureB = CompletableFuture.
        supplyAsync(() -> "执行结果:" + 50)
        .thenApply(s -> "futureB result:" + s)
        .exceptionally(e -> "futureB result: 100");
    System.out.println(futureA.join());
    System.out.println(futureB.join());
  }

  public static void handleTest(){
    //当CompletableFuture的计算结果完成，或者抛出异常的时候，可以通过handle方法对结果进行处理
    CompletableFuture<String> futureA = CompletableFuture.
    supplyAsync(() -> "执行结果:" + (100 / 0))
    .thenApply(s -> "apply result:" + s)
    .exceptionally(e -> {
      System.out.println("ex:" + e.getMessage());
      return "futureA result: 100";
    })
    .handle((s, e) -> {
      if (e == null) {
        System.out.println(s);
      } else {
        //未执行
        System.out.println(e.getMessage());
      }
      return "handle result:" + (s == null ? "500" : s);
    });
    // 100
    System.out.println(futureA.join());


    CompletableFuture<String> futureB = CompletableFuture.
    supplyAsync(() -> "执行结果:" + (100 / 0))
    .thenApply(s -> "apply result:" + s)
    .handle((s, e) -> {
      if (e == null) {
        //未执行
        System.out.println(s);
      } else {
        System.out.println(e.getMessage());
      }
      return "handle result:" + (s == null ? "500" : s);
    })
    .exceptionally(e -> {
      //未执行
      System.out.println("ex:" + e.getMessage());
      return "futureB result: 100";
    });
    // 500
    System.out.println(futureB.join());

    // handle和whenComplete的区别
    // 1.都是对结果进行处理,handle有返回值,whenComplete没有返回值
    // 2.由于1的存在,使得handle多了一个特性,可在handle里实现exceptionally的功能
  }

  public static void allOfAndAnyOfTest(){
    //allOf:当所有的CompletableFuture都执行完后执行计算
    //anyOf:最快的那个CompletableFuture执行完之后执行计算
    //查询一个商品详情,需要分别去查商品信息,卖家信息,库存信息,订单信息等,这些查询相互独立,在不同的服务上,
    //假设每个查询都需要一到两秒钟,要求总体查询时间小于2秒.
    ExecutorService executorService = Executors.newFixedThreadPool(4);

    long start = System.currentTimeMillis();
    CompletableFuture<String> futureA = CompletableFuture.supplyAsync(() -> {
      try {
        Thread.sleep(1000 + new Random().nextInt(1000));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      return "商品详情";
    },executorService);

    CompletableFuture<String> futureB = CompletableFuture.supplyAsync(() -> {
      try {
        Thread.sleep(1000 + new Random().nextInt(1000));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      return "卖家信息";
    },executorService);

    CompletableFuture<String> futureC = CompletableFuture.supplyAsync(() -> {
      try {
        Thread.sleep(1000 + new Random().nextInt(1000));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      return "库存信息";
    },executorService);

    CompletableFuture<String> futureD = CompletableFuture.supplyAsync(() -> {
      try {
        Thread.sleep(1000 + new Random().nextInt(1000));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      return "订单信息";
    },executorService);

    CompletableFuture<Void> allFuture = CompletableFuture.allOf(futureA, futureB, futureC, futureD);
    allFuture.join();

    System.out.println(futureA.join() + futureB.join() + futureC.join() + futureD.join());
    System.out.println("总耗时:" + (System.currentTimeMillis() - start));
  }


  //无返回值
  public static void runAsync() throws Exception {
    System.out.println("------runAsync 无返回值------");
    CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
      try {
        TimeUnit.SECONDS.sleep(1);
      } catch (InterruptedException e) {
        System.out.println(e);
      }
      System.out.println("run end ...");
    });

    future.get();
    //future.join();
  }

  //有返回值
  public static void supplyAsync() throws Exception {
    System.out.println("------supplyAsync 有返回值------");
    CompletableFuture<Long> future = CompletableFuture.supplyAsync(() -> {
      try {
        TimeUnit.SECONDS.sleep(1);
      } catch (InterruptedException e) {
        System.out.println(e);
      }
      System.out.println("run end ...");
      return System.currentTimeMillis();
    });

    long time = future.get();
    //long time = future.join();
    System.out.println("time = "+time);
  }

  // whenComplete 它可以处理正常的计算结果，或者异常情况。
  public static void whenComplete() throws Exception {
    CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
      try {
        TimeUnit.SECONDS.sleep(1);
      } catch (InterruptedException e) {
        System.out.println(e);
      }
      if(new Random().nextInt()%2>=0) {
        int i = 12/0;
      }
      System.out.println("run end ...");
    });
    // 执行当前任务的线程执行继续执行 whenComplete 的任务。
    future.whenComplete(new BiConsumer<Void, Throwable>() {
      @Override
      public void accept(Void t, Throwable action) {
        System.out.println("执行完成！");
      }

    });
    future.exceptionally(new Function<Throwable, Void>() {
      @Override
      public Void apply(Throwable t) {
        System.out.println("执行失败！"+t.getMessage());
        return null;
      }
    });

    TimeUnit.SECONDS.sleep(2);
  }

  public static void whenComplete2() {
    CompletableFuture<String> futureA = CompletableFuture.
        supplyAsync(() -> "执行结果:" + (100 / 0))
        .thenApply(s -> "apply result:" + s)
        .whenComplete((s, e) -> {
          if (s != null) {
            //未执行
            System.out.println(s);
          }
          if (e == null) {
            //未执行
            System.out.println(s);
          } else {
            System.out.println(e.getMessage());
          }
        })
        .exceptionally(e -> {
          System.out.println("ex"+e.getMessage());
          return "futureA result: 100";
        });
        System.out.println(futureA.join());
  }
}
