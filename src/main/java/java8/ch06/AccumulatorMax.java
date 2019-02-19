package java8.ch06;

import java.util.concurrent.atomic.LongAccumulator;

/**
 * @author yuzhe
 * @since 2/5/18
 */
public class AccumulatorMax {

    public static void main(String[] args) {
        LongAccumulator accumulator = new LongAccumulator(Math::max, 0L);
        long[] array = new long[]{4, 1, 2, 7, 9};
        for(int i = 0; i < array.length; i++){
            accumulator.accumulate(array[i]);
        }
        System.out.println("max: " + accumulator.get());
    }

}
