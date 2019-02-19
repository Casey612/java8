package java8.ch06;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author yuzhe
 * @since 2/5/18
 */
public class AtomicReferenceTest {

    private static AtomicReference<Long> longest = new AtomicReference(0L);

    public static void main(String[] args) {
        String[] words = new String[]{
                "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten"
        };
        MyThread[] threads = new MyThread[words.length / 2];
        for (int i = 0; i < words.length; i += 2) {
            threads[i / 2] = new MyThread(words[i], words[i + 1]);
            threads[i / 2].start();
        }
        System.out.println("the longest word: " + longest.get());
    }

    public static class MyThread extends Thread {
        private String start;
        private String end;

        public MyThread(String start, String end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public void run() {
            long longger = this.start.length() > this.end.length() ? this.start.length() : this.end.length();
            longest.set(Math.max(longest.get(), longger));
            super.run();
        }
    }

}
