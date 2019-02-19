package java8.ch03;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yuzhe
 * @since 1/24/18
 */
public class LockTest{

    public static void main(String[] args) {
        ReentrantLock myLock = new ReentrantLock();
        myLock.lock();
        try {
            //doSth
        } finally {
            myLock.unlock();
        }
        withLock(myLock, () -> {
            System.out.println("test my lock");
        });
    }

    public static void withLock(ReentrantLock lock, Runnable runner) {
        lock.lock();
        try{
            runner.run();
        } finally {
            lock.unlock();
        }
    }

}
