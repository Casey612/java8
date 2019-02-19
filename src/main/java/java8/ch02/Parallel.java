package java8.ch02;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuzhe
 * @since 1/23/18
 */
public class Parallel {

    public static void main(String[] args) {
        List<String> words = new ArrayList<>();
        words.add("word");
        words.add("the");
        words.add("a");
        words.add("apple");
        words.add("doctor");
        words.add("well");
        words.add("abandon");
        words.add("permission");
        words.add("access");
        words.add("mysql");
        words.add("hadoop");


        long count = words.stream().parallel().filter(w -> w.length() > 3).count();
        System.out.println(count);

        int CORES = Runtime.getRuntime().availableProcessors();
        System.out.println(CORES);

        Thread[] threads = new Thread[CORES];
        int[] results = new int[CORES];

        for(int i = 0; i < CORES; i++){
            threads[i] = new BigWordThread(words, results, i);
            threads[i].start();
        }

        int result = 0;
        for(int i = 0; i < CORES; i++){
            result += results[i];
        }

        System.out.println(result);
    }

    public static class BigWordThread extends Thread{
        private List<String> list;
        private int[] results;
        private int times;

        public BigWordThread(List<String> words, int[] results, int i) {
            this.list = words;
            this.results = results;
            this.times = i;
        }

        @Override
        public void run(){
            for(int index = 3 * times; index < Math.min(list.size(), 3 * times + 3); index++){
                if(list.get(index).length() > 3){
                    results[times]++;
                }
            }
        }

    }

}
