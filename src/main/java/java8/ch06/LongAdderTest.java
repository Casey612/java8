package java8.ch06;

import java.util.concurrent.atomic.LongAdder;

/**
 * @author yuzhe
 * @since 2/5/18
 */
public class LongAdderTest {

    private static LongAdder adder = new LongAdder();

    public static void main(String[] args) {
        for(int i = 0; i < 100; i++){
            MyThread thread = new MyThread();
            thread.start();
        }
    }

    public static class MyThread extends Thread{
        @Override
        public void run(){
            adder.increment();
            System.out.println("the number of adder: " + adder.sum());
        }
    }

}
