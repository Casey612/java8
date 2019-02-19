package java8.ch06;

/**
 * @author yuzhe
 * @since 2/1/18
 */
public class SynchronizedTest {

    private static Integer counter = new Integer(0);

    public static synchronized int incrementAndGet(int arg) {
        counter += arg;
        return counter;
    }

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
            int count = incrementAndGet(1);
            System.out.println(Thread.currentThread().getName() + " count: " + count);
        }
    }

}
