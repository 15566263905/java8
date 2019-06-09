package cn.example.Spliterator;

import java.util.Spliterator;
import java.util.function.Consumer;

public class WordCounterSpliterator implements Spliterator<Character> {

    private final String string;
    private int currentChar = 0;

    public WordCounterSpliterator(String string) {
        this.string = string;
    }

    /**
     * tryAdvance方法把String中当前位置的Character传给了Consumer，并让位置加一。
     * 作为参数传递的Consumer是一个Java内部类，在遍历流时将要处理的Character传给了一系列要对其执行的函数。这里只有一个归约函数，即WordCounter类的accumulate方法。
     * 如果新的指针位置小于String的总长，且还有要遍历的Character，则tryAdvance返回true。
     * @param action
     * @return
     */
    @Override
    public boolean tryAdvance(Consumer<? super Character> action) {
        // 处理当前字符
        action.accept(string.charAt(currentChar++));
        // 如果还有字符要处理，则返回 true
        return currentChar < string.length();
    }

    /**
     * 定义了拆分要遍历的数据结构的逻辑
     * @return
     */
    @Override
    public Spliterator<Character> trySplit() {
        int currentSize = string.length() - currentChar;
        // 返回 null 表示要解 析 的 String已经足够小，可以顺序处理
        if (currentSize < 10) {
            return null;
        }
        // 将试探拆分位置设定为要解析的String的中间
        for (int splitPos = currentSize / 2 + currentChar;
             splitPos < string.length(); splitPos++) {
            // 让拆分位置前进直到下一个空格
            if (Character.isWhitespace(string.charAt(splitPos))) {
                // 创建一个新 WordCounter-Spliterator 来解析 String 从开始到拆分位置的部分
                Spliterator<Character> spliterator =
                        new WordCounterSpliterator(string.substring(currentChar,
                                splitPos));
                // 将这个 WordCounter-Spliterator 的起始位置设为拆分位置
                currentChar = splitPos;
                return spliterator;
            }
        }
        return null;
    }

    /**
     * 这个 Spliterator 解析的 String 的总长度和当前遍历的位置的差。
     * @return
     */
    @Override
    public long estimateSize() {
        return string.length() - currentChar;
    }

    /**
     * 告诉框架这个 Spliterator 是 ORDERED （顺序就是 String中各个 Character 的次序）、
     * SIZED （ estimatedSize 方法的返回值是精确的）、
     * SUBSIZED （ trySplit 方法创建的其他 Spliterator 也有确切大小）、
     * NONNULL （ String中 不 能 有 为 null 的 Character ） 和 IMMUTABLE （ 在 解 析 String 时 不 能 再 添 加
     * Character ，因为 String 本身是一个不可变类）的。
     * @return
     */
    @Override
    public int characteristics() {
        return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
    }
}
