package cn.example.Spliterator;

/**
 * 计数
 */
public class WordCounter {

    private final int counter;
    private final boolean lastSpace;

    public WordCounter(int counter, boolean lastSpace) {
        this.counter = counter;
        this.lastSpace = lastSpace;
    }

    /**
     *  一个个遍历 Character
     * @param c
     * @return
     */
    public WordCounter accumulate(Character c) {
        if (Character.isWhitespace(c)) {
            return lastSpace ?
                    this :
                    new WordCounter(counter, true);
        } else {
            return lastSpace ?
                    new WordCounter(counter + 1, false) :
                    this;
        }
    }

    /**
     * 合并两个 Word-Counter ，把其计数器加起来
     * @param wordCounter
     * @return
     */
    public WordCounter combine(WordCounter wordCounter) {
        return new WordCounter(counter + wordCounter.counter,
                wordCounter.lastSpace);
    }

    /**
     * 获取Character总和
     * @return
     */
    public int getCounter() {
        return counter;
    }
}
