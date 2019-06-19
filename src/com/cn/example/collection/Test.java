package cn.example.collection;

import java.util.*;

public class Test {
  public static void main(String[] args) {
    CollectionsTest();
  }

  private static void CollectionsTest(){

    // listTest();
    // vectorTest();
    // stackTest();
    // hashSetTest();
    // treeSetTest();
    // hashMapTest();
    // treeMapTest();
    weakHashMap();

  }

  public static void listTest() {
    List<Integer> list = new ArrayList<>();
    List<Integer> newlist = new ArrayList<>();
    List<Integer> sublist = new ArrayList<>();
    Integer array[] = {112, 111, 23, 456, 231, 23};
    for (int i = 0; i < array.length; i++) {
      list.add(array[i]);
      if (i > 3) {
        sublist.add(array[i]);
      }
    }
    // 列表按升序进行排序
    // Collections.sort(list);
    // 混排（Shuffling）
    // Collections.shuffle(list);
    // 反转(Reverse)
    // Collections.reverse(list);
    // 替换所以的元素(Fill)
    // Collections.fill(list,112);
    // 拷贝(Copy)
    Collections.copy(list, newlist);
    // 返回Collections中最小元素(min)
    System.out.println("最小元素:" + Collections.min(list));
    // 返回Collections中最大元素(max)
    System.out.println("最大元素:" + Collections.max(list));
    // 返回指定源列表中最后一次出现指定目标列表的起始位置
    System.out.println("最后一次出现的起始位置:" + Collections.lastIndexOfSubList(list, sublist));
    // 回指定源列表中第一次出现指定目标列表的起始位置
    System.out.println("第一次出现的起始位置:" + Collections.indexOfSubList(list, sublist));
    // 根据指定的距离循环移动指定列表中的元素;如果是负数，则正向移动，正数则负向移动;
    Collections.rotate(list,-1);
    list.remove(0);
    List<Integer> removelist = new ArrayList<Integer>();
    removelist.add(0,23);
    list.removeAll(removelist);
    System.out.println(list);

    removelist.clear();

    System.out.println("-----------------------------");
    ArrayList<Integer> arrayList = new ArrayList<>(Arrays.asList(array));
    System.out.println(arrayList);
  }

  public static void vectorTest() {
    Vector v1 = new Vector();

    v1.addElement("one");

    // 不要直接调用v1.addElement(1)
    Integer num = new Integer(1);
    v1.addElement(num);

    v1.addElement("two");
    v1.addElement(new Integer(2));

    v1.addElement(num);
    v1.addElement(num);
    System.out.println(v1);

    v1.insertElementAt("three",2);
    v1.insertElementAt(new Float(3.9),3);
    System.out.println(v1);

    v1.setElementAt("four",2);
    System.out.println(v1);

    // 删除找到的第一个integer1
    v1.removeElement(num);
    System.out.println(v1);

    // 使用枚举类(Enumeration)的方法取得向量对象的每个元素
    Enumeration enumeration = v1.elements();
    while(enumeration.hasMoreElements()) {
      System.out.print(enumeration.nextElement()+" ");
    }

    // 重新设置v1的大小，多余的元素被抛弃
    v1.setSize(4);
    System.out.println(v1);
  }

  public static void stackTest () {
    Stack stack = new Stack();
    // 将1,2,3,4,5添加到栈中
    for(int i=1; i<6; i++) {
      stack.push(String.valueOf(i));
    }

    // 遍历并打印出该栈
    iteratorThroughRandomAccess(stack) ;

    // 查找“2”在栈中的位置，并输出
    int pos = stack.search("2");
    System.out.println("the postion of 2 is:"+pos);

    // pup栈顶元素之后，遍历栈
    stack.pop();
    iteratorThroughRandomAccess(stack) ;

    // peek栈顶元素之后，遍历栈
    String val = (String)stack.peek();
    System.out.println("peek:"+val);
    iteratorThroughRandomAccess(stack) ;

    // 通过Iterator去遍历Stack
    iteratorThroughIterator(stack) ;
  }

  public static void hashSetTest() {
    // 新建HashSet
    HashSet set = new HashSet();

    // 将元素添加到Set中
    set.add("a");
    set.add("b");
    set.add("c");
    set.add("d");
    set.add("e");

    // 通过Iterator遍历HashSet
    iteratorHashSet(set);

    // 通过for-each遍历HashSet
    foreachHashSet(set);

    // 打印HashSet的实际大小
    System.out.printf("size : %d\n", set.size());

    // 判断HashSet是否包含某个值
    System.out.printf("HashSet contains a :%s\n", set.contains("a"));
    System.out.printf("HashSet contains g :%s\n", set.contains("g"));

    // 删除HashSet中的“e”
    set.remove("e");

    // 将Set转换为数组
    String[] arr = (String[])set.toArray(new String[0]);
    for (String str:arr) {
      System.out.printf("for each : %s\n", str);
    }

    // 新建一个包含b、c、f的HashSet
    HashSet otherSet = new HashSet();
    otherSet.add("b");
    otherSet.add("c");
    otherSet.add("f");

    // 克隆一个removeset，内容和set一模一样
    HashSet removeset = (HashSet)set.clone();
    // 删除“removeset中，属于otherSet的元素”
    removeset.removeAll(otherSet);
    // 打印removeset
    System.out.printf("removeset : %s\n", removeset);

    // 克隆一个retainset，内容和set一模一样
    HashSet retainset = (HashSet)set.clone();
    // 保留“retainset中，属于otherSet的元素”
    retainset.retainAll(otherSet);
    // 打印retainset
    System.out.printf("retainset : %s\n", retainset);

    // 遍历HashSet
    for(Iterator iterator = set.iterator();
        iterator.hasNext(); ){
      System.out.printf("iterator : %s\n", iterator.next());
    }

    // 清空HashSet
    set.clear();

    // 输出HashSet是否为空
    System.out.printf("%s\n", set.isEmpty()?"set is empty":"set is not empty");
  }

  public static void treeSetTest() {
    TreeSet set = new TreeSet();
    set.add("aaa");
    set.add("aaa");
    set.add("bbb");
    set.add("eee");
    set.add("ddd");
    set.add("ccc");

    System.out.printf("size : %d\n", set.size());

    // 顺序遍历TreeSet
    System.out.println("-----------顺序遍历TreeSet-------------------");
    for(Iterator iter = set.iterator(); iter.hasNext();) {
      System.out.printf(" %s", iter.next());
    }
    System.out.println();

    // 逆序遍历TreeSet
    System.out.println("-----------逆序遍历TreeSet-------------------");
    for(Iterator iter = set.descendingIterator(); iter.hasNext();){
      System.out.printf(" %s", (String)iter.next());
    }
    System.out.println();

    // 通过for-each遍历TreeSet。不推荐！此方法需要先将Set转换为数组
    System.out.println("-----------通过for-each遍历TreeSet。不推荐！此方法需要先将Set转换为数组-------------------");
    String[] arr = (String[])set.toArray(new String[0]);
    for (String str:arr) {
      System.out.printf(" %s", str);
    }
    System.out.println();

    System.out.println("-----------导航方法[floor(小于、等于)]-------------------");
    System.out.println(set.floor("bbb"));
    System.out.println("-----------导航方法[lower(小于)]-------------------");
    System.out.println(set.lower("bbb"));
    System.out.println("-----------导航方法[ceiling(大于、等于)]-------------------");
    System.out.println(set.ceiling("bbb"));
    System.out.println(set.ceiling("eee"));
    System.out.println("-----------导航方法[higher(大于)]-------------------");
    System.out.println(set.higher("eee"));
    System.out.println("-----------导航方法[subSet]-------------------");
    // subSet(E fromElement, boolean fromInclusive,
    //        E toElement,   boolean toInclusive)
    System.out.printf("subSet(aaa, true, ccc, true): %s\n", set.subSet("aaa", true, "ccc", true));
    System.out.printf("subSet(aaa, true, ccc, false): %s\n", set.subSet("aaa", true, "ccc", false));
    System.out.printf("subSet(aaa, false, ccc, true): %s\n", set.subSet("aaa", false, "ccc", true));
    System.out.printf("subSet(aaa, false, ccc, false): %s\n", set.subSet("aaa", false, "ccc", false));
    System.out.println("-----------导航方法[headSet]-------------------");
    // headSet(E toElement, boolean inclusive)
    System.out.printf("headSet(ccc, true): %s\n", set.headSet("ccc", true));
    System.out.printf("headSet(ccc, false): %s\n", set.headSet("ccc", false));
    System.out.println("-----------导航方法[tailSet]-------------------");
    // tailSet(E fromElement, boolean inclusive)
    System.out.printf("tailSet(ccc, true): %s\n", set.tailSet("ccc", true));
    System.out.printf("tailSet(ccc, false): %s\n", set.tailSet("ccc", false));

    System.out.println("-----------删除-------------------");
    set.remove("ccc");

    System.out.println("-----------删除并返回第一个元素---------");
    String val = (String)set.pollFirst();
    System.out.printf("pollFirst=%s, set=%s\n", val, set);

    System.out.println("-----------删除并返回最后一个元素---------");
    val = (String)set.pollLast();
    System.out.printf("pollLast=%s, set=%s\n", val, set);

    System.out.println("-----------清空HashSet---------");
    set.clear();

    System.out.println("-----------输出HashSet是否为空---------");
    System.out.printf("%s\n", set.isEmpty()?"set is empty":"set is not empty");
  }

  public static void hashMapTest() {
    Random r = new Random();
    HashMap map = new HashMap();

    // 添加操作
    map.put("one", r.nextInt(10));
    map.put("two", r.nextInt(10));
    map.put("three", r.nextInt(10));

    // 通过Iterator遍历key-value
    Iterator iter = map.entrySet().iterator();
    while(iter.hasNext()) {
      Map.Entry entry = (Map.Entry)iter.next();
      System.out.print(" , "+ entry.getKey() +" - "+entry.getValue());
    }

    System.out.println("-----------HashMap的键值对个数-------------------");
    System.out.println("size:"+map.size());

    // containsKey(Object key) :是否包含键key
    System.out.println("contains key two : "+map.containsKey("two"));
    System.out.println("contains key five : "+map.containsKey("five"));

    // containsValue(Object value) :是否包含值value
    System.out.println("contains value 0 : "+map.containsValue(new Integer(0)));

    // remove(Object key) ： 删除键key对应的键值对
    map.remove("three");

    // clear() ： 清空HashMap
    map.clear();

    // isEmpty() : HashMap是否为空
    System.out.println((map.isEmpty()?"map is empty":"map is not empty") );
  }

  public static void treeMapTest() {
    Random r = new Random();
    TreeMap tmap = new TreeMap();

    // 添加操作
    tmap.put("one", r.nextInt(10));
    tmap.put("two", r.nextInt(10));
    tmap.put("three", r.nextInt(10));

    // 通过Iterator遍历key-value
    Iterator iter = tmap.entrySet().iterator();
    while(iter.hasNext()) {
      Map.Entry entry = (Map.Entry)iter.next();
      System.out.print(" , "+ entry.getKey() +" - "+entry.getValue());
    }

    System.out.println("-----------HashMap的键值对个数-------------------");
    System.out.println("size:"+tmap.size());

    // containsKey(Object key) :是否包含键key
    System.out.println("contains key two : "+tmap.containsKey("two"));
    System.out.println("contains key five : "+tmap.containsKey("five"));

    // containsValue(Object value) :是否包含值value
    System.out.println("contains value 0 : "+tmap.containsValue(new Integer(0)));

    // remove(Object key) ： 删除键key对应的键值对
    tmap.remove("three");

    // clear() ： 清空HashMap
    tmap.clear();

    // isEmpty() : HashMap是否为空
    System.out.println((tmap.isEmpty()?"map is empty":"map is not empty") );

  }

  public static void weakHashMap() {
    // 初始化3个“弱键”
    String w1 = new String("one");
    String w2 = new String("two");
    String w3 = new String("three");
    // 新建WeakHashMap
    Map wmap = new WeakHashMap();

    // 添加键值对
    wmap.put(w1, "w1");
    wmap.put(w2, "w2");
    wmap.put(w3, "w3");

    // 打印出wmap
    System.out.printf("\nwmap:%s\n",wmap );

    System.out.println("-----------containsKey(Object key) :是否包含键key-------------------");
    System.out.printf("contains key two : %s\n",wmap.containsKey("two"));
    System.out.printf("contains key five : %s\n",wmap.containsKey("five"));

    System.out.println("-----------containsValue(Object value) :是否包含值value-------------------");
    System.out.printf("contains value 0 : %s\n",wmap.containsValue(new Integer(0)));

    System.out.println("-----------remove(Object key) ： 删除键key对应的键值对-------------------");
    wmap.remove("three");

    System.out.printf("wmap: %s\n",wmap );

    // ---- 测试 WeakHashMap 的自动回收特性 ----

    // 将w1设置null。
    // 这意味着“弱键”w1再没有被其它对象引用，调用gc时会回收WeakHashMap中与“w1”对应的键值对
    w1 = null;
    // 内存回收。这里，会回收WeakHashMap中与“w1”对应的键值对
    System.gc();

    System.out.println("-----------遍历WeakHashMap-------------------");
    Iterator iter = wmap.entrySet().iterator();
    while (iter.hasNext()) {
      Map.Entry en = (Map.Entry)iter.next();
      System.out.printf("next : %s - %s\n",en.getKey(),en.getValue());
    }
    // 打印WeakHashMap的实际大小
    System.out.printf(" after gc WeakHashMap size:%s\n", wmap.size());
  }

  /**
   * 通过快速访问遍历Stack
   */
  public static void iteratorThroughRandomAccess(List list) {
    String val = null;
    for (int i=0; i<list.size(); i++) {
      val = (String)list.get(i);
      System.out.print(val+" ");
    }
    System.out.println();
  }

  /**
   * 通过迭代器遍历Stack
   */
  public static void iteratorThroughIterator(List list) {

    String val = null;
    for(Iterator iter = list.iterator(); iter.hasNext(); ) {
      val = (String)iter.next();
      System.out.print(val+" ");
    }
    System.out.println();
  }

  /**
   * 通过Iterator遍历HashSet。推荐方式
   */
  private static void iteratorHashSet(HashSet set) {
    for(Iterator iterator = set.iterator();
        iterator.hasNext();) {
      System.out.printf("iterator : %s\n", iterator.next());
    }
  }

  /**
   * 通过for-each遍历HashSet。不推荐！此方法需要先将Set转换为数组
   */
  private static void foreachHashSet(HashSet set) {
    String[] arr = (String[])set.toArray(new String[0]);
    for (String str:arr){
      System.out.printf("for each : %s\n", str);
    }
  }
}
