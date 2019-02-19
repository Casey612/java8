package java8.ch06;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author yuzhe
 * @since 2/5/18
 */
public class TimeWastedForIncrements {

    private static AtomicLong atomicLong = new AtomicLong();

    private static LongAdder adder = new LongAdder();

    public static void main(String[] args) {

        LocalDateTime atomicStart = LocalDateTime.now();
        for(int i = 0; i < 1000; i++){
            new AtomicTest().start();
        }
        LocalDateTime atomicEnd = LocalDateTime.now();
        System.out.println(Duration.between(atomicStart, atomicEnd).toNanos());

        LocalDateTime adderStart = LocalDateTime.now();
        for(int i = 0; i < 1000; i++){
            new AdderTest().start();
        }
        LocalDateTime adderEnd = LocalDateTime.now();
        System.out.println(Duration.between(adderStart, adderEnd).toNanos());

    }

    public static class AtomicTest extends Thread{
        @Override
        public void run(){
            for(int i = 0; i < 100000; i++){
                atomicLong.incrementAndGet();
            }
        }
    }

    public static class AdderTest extends Thread{
        @Override
        public void run(){
            for(int i = 0; i < 100000; i++){
                adder.increment();
            }
        }
    }

}
