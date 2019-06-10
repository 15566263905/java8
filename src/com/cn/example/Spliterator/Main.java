package cn.example.Spliterator;

import java.util.Spliterator;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Main {
    public static final String SENTENCE =
            " Nel mezzo del cammin di nostra vita " +
                    "mi ritrovai in una selva oscura" +
                    " ché la dritta via era smarrita ";

    public static void main(String[] args) {
        System.out.println("--------原始方式--------");
        System.out.println("Found " + countWordsIteratively(SENTENCE) + " words");

        System.out.println("--------Java8函数方式--------");
        Stream<Character> stream0= IntStream.range(0, SENTENCE.length())
                .mapToObj(SENTENCE::charAt);
        Stream<Character> stream1 = IntStream.range(0, SENTENCE.length())
                .mapToObj(SENTENCE::charAt).filter(e -> !Character.isWhitespace(e));
        System.out.println("Found " + stream0.count() + " words");
        System.out.println("Found " + stream1.count() + " words");

        System.out.println("--------Java8方式--------[错误，把顺序流换成并行流就可能使结果出错]-----");
        Stream<Character> stream2 = IntStream.range(0, SENTENCE.length())
                .mapToObj(SENTENCE::charAt);
        // System.out.println("Found " + countWords(stream2) + " words");
        System.out.println("Found " + countWords(stream2.parallel()) + " words");

        System.out.println("--------Java8-Spliterator-方式--------");
        Spliterator<Character> spliterator = new WordCounterSpliterator(SENTENCE);
        Stream<Character> stream = StreamSupport.stream(spliterator, true);
        System.out.println("Found " + countWords(stream) + " words");
    }

    private static int countWords(Stream<Character> stream) {
        WordCounter wordCounter = stream.reduce(new WordCounter(0, true),
                WordCounter::accumulate,
                WordCounter::combine);
        return wordCounter.getCounter();
    }

    public static int countWordsIteratively(String s) {
        int counter = 0;
        boolean lastSpace = true;
        for (char c : s.toCharArray()) {
            if (Character.isWhitespace(c)) {
                lastSpace = true;
            } else {
                if (lastSpace) {
                    counter++;
                }
                lastSpace = false;
            }
        }
        return counter;
    }
}
