package cn.example;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.reducing;
import static java.util.stream.Collectors.toList;

/**
 * @Author Gosin
 * @Date 2019/5/27 20:36
 */
public class LambdaTest {
  public static void main(String[] args) {
    List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4, 6, 8, 10, 11, 9, 5, 7, 3);
    numbers.stream()
        .filter(i -> i % 2 == 0)
        .distinct()
        .limit(3)
        .forEach(System.out::print);
    System.out.println();
    System.out.println("-----------------------");

    List<Integer> dishes = numbers.stream()
        .filter(i -> i % 2 == 0)
        .distinct()
        .limit(3)
        .skip(2)
        .collect(toList());
    dishes.forEach(System.out::print);
    System.out.println();
    System.out.println("-----------------------");

    List<String> words = Arrays.asList("Java 8", "Lambdas", "In", "Action");
    List<Integer> wordLengths = words.stream()
        .map(String::length)
        .collect(toList());
    wordLengths.forEach(System.out::print);
    System.out.println();
    System.out.println("-----------------------");

    List<String> uniqueCharacters =
        words.stream()
            .map(w -> w.split(""))
            .flatMap(Arrays::stream)
            .distinct()
            .collect(Collectors.toList());
    uniqueCharacters.forEach(System.out::print);
    System.out.println();
    System.out.println("-----------------------");

    List<String[]> testCharacters =
        words.stream()
            .map(w -> w.split(""))
            .distinct()
            .collect(Collectors.toList());
    testCharacters.forEach(System.out::print);
    System.out.println();
    System.out.println("-----------------------");

  }
}
