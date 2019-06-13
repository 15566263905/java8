package cn.example.enumMap;

import java.util.EnumMap;
import java.util.Map;

/**
 * @Author Gosin
 * @Date 2019/6/13 18:47
 */
public class Test {
  /*
   * jdk1.5才有EnumMap
  与枚举类型键一起使用的专用 Map 实现。枚举映射中所有键都必须来自单个枚举类型，该枚举类型在创建映射时显式或隐式地指定。枚举映射在内部表示为数组。此表示形式非常紧凑且高效。
  枚举映射根据其键的自然顺序 来维护（该顺序是声明枚举常量的顺序）。在 collection 视图（keySet()、entrySet() 和 values()）所返回的迭代器中反映了这一点。
  由 collection 视图返回的迭代器是弱一致 的：它们不会抛出 ConcurrentModificationException，也不一定显示在迭代进行时发生的任何映射修改的效果。
  不允许使用 null 键。试图插入 null 键将抛出 NullPointerException。但是，试图测试是否出现 null 键或移除 null 键将不会抛出异常。允许使用 null 值。
  像大多数 collection 一样，EnumMap 是不同步的。如果多个线程同时访问一个枚举映射，并且至少有一个线程修改该映射，则此枚举映射在外部应该是同步的。
  这一般通过对自然封装该枚举映射的某个对象进行同步来完成。如果不存在这样的对象，则应该使用 Collections.synchronizedMap(java.util.Map)
  方法来“包装”该枚举。最好在创建时完成这一操作，以防止意外的非同步访问：
   Map<EnumKey, V> m = Collections.synchronizedMap(new EnumMap<EnumKey, V>(...));
   实现注意事项：所有基本操作都在固定时间内执行。虽然并不保证，但它们很可能比其 HashMap 副本更快。
  */
  // 默认继承  extends Enum类，所以枚举也是个类，既然是类就有构造函数，变量，方法等
  public enum Color{
    //下标为0,1,2,3,4
    RED, BLUE, BLACK, YELLOW, GREEN;
    @Override
    public String toString() {
      //ordinal()方法获取下标
      return super.toString()+"..."+super.ordinal();
    }
  }

  public static void main(String[] args) {
    EnumMap<Color, String> map = new EnumMap<>(Color.class);
    System.out.println(Color.GREEN);
    map.put(Color.YELLOW, "黄色");
    map.put(Color.BLUE, null);
    // map.put(null, "无"); //会报NullPonitException的错误
    map.put(Color.RED, "红色");
    map.put(Color.GREEN, "绿色");
    map.get(Color.BLACK);
    for (Map.Entry<Color, String> entry : map.entrySet()) {
      System.out.println(entry.getKey() + ":" + entry.getValue());
    }
    System.out.println(map);
  }
}
