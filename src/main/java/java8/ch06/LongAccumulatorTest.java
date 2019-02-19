package java8.ch06;

import java.util.concurrent.atomic.LongAccumulator;

/**
 * @author yuzhe
 * @since 2/5/18
 */
public class LongAccumulatorTest {

    public static void main(String[] args) {
        LongAccumulator adder = new LongAccumulator(Long::sum, 0L);
    }
}
