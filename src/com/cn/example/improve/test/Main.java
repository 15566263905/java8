package cn.example.improve.test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author Gosin
 * @Date 2019/6/10 11:15
 */
public class Main {

  public static void main(String[] args) {

    Point p1 = new Point(5, 5);
    Point p2 = p1.moveRightBy(10);
    System.out.println(p1.toString());
    System.out.println(p2.toString());

    // 测试可见 Lambda 函数的行为
    int result = Point.compareByXAndThenY.compare(p1 , p2);
    System.out.println(result);

    // 测试使用 Lambda 的方法的行为
    List<Point> points =
        Arrays.asList(new Point(5, 5), new Point(10, 5));
    List<Point> expectedPoints =
        Arrays.asList(new Point(15, 5), new Point(20, 5));
    List<Point> newPoints = Point.moveAllPointsRightBy(points, 10);
    System.out.println(newPoints.size());

    //
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
    List<Integer> even = numbers.stream().filter(i -> i % 2 == 0).collect(Collectors.toList());
    List<Integer> smallerThanThree = numbers.stream().filter(i -> i < 3).collect(Collectors.toList());
    System.out.println(even.size());

    // 调试
    System.out.println("----调试----");
    numbers = Arrays.asList(2, 3, 4, 5);
    List<Integer> result1 =
        numbers.stream()
            .peek(x -> System.out.println("from stream: " + x))
            .map(x -> x + 17)
            .peek(x -> System.out.println("after map: " + x))
            .filter(x -> x % 2 == 0)
            .peek(x -> System.out.println("after filter: " + x))
            .limit(3)
            .peek(x -> System.out.println("after limit: " + x))
            .collect(Collectors.toList());

  }


}
