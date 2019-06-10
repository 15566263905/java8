package cn.example.improve.test;

import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @Author Gosin
 * @Date 2019/6/10 11:14
 */
public class Point {

  public final static Comparator<Point> compareByXAndThenY =
      Comparator.comparing(Point::getX).thenComparing(Point::getY);

  public static List<Point> moveAllPointsRightBy(List<Point> points, int x){
    return points.stream()
        .map(p -> new Point(p.getX() + x, p.getY()))
        .collect(toList());
  }

  private final int x;
  private final int y;
  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }
  public int getX() { return x; }
  public int getY() { return y; }
  public Point moveRightBy(int x){
    return new Point(this.x + x, this.y);
  }

  @Override
  public String toString() {
    return "Point{" +
        "x=" + x +
        ", y=" + y +
        '}';
  }
}
