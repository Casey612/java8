package java8.ch06;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * @author yuzhe
 * @since 2/5/18
 */
public class TimeWastedForArraysSort {

    public static void main(String[] args) {
        for (int i = 2; i < Integer.MAX_VALUE; i = i << 1){
            test(i);
        }
    }

    static void test(long limit) {
        Random rand = new Random();
        IntStream stream = rand.ints(limit);
        int[] arr = stream.toArray();
        int[] arr1 = Arrays.copyOf(arr, arr.length);

        long t1 = System.currentTimeMillis();
        Arrays.parallelSort(arr);
        long t2 = System.currentTimeMillis();
        Arrays.sort(arr1);
        long t3 = System.currentTimeMillis();
        System.out.println("limit:" + limit + "\t parallelSort: " + (t2 - t1) + "ms\tserialSort: " + (t3 - t2) + "ms");
    }

}
