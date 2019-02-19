package java8.ch06;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAccumulator;

/**
 * @author yuzhe
 * @since 2/1/18
 */
public class AtomicTest {

    public static AtomicInteger counter = new AtomicInteger();

    public static LongAccumulator accumulator = new LongAccumulator(((left, right) -> left + right), 0L);

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            MyThread thread = new MyThread();
            thread.setName("thread" + i);
            thread.start();
        }
    }

    public static class MyThread extends Thread {
        @Override
        public void run() {
            int count = counter.accumulateAndGet(1, (left, right) -> left + right);
            System.out.println(Thread.currentThread().getName() + " count: " + count);
        }
    }

}
