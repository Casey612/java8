package java8.ch02;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author yuzhe
 * @since 1/23/18
 */
public class ShortWords {

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

        shortCountByAtomicInteger(words);
        shortCountByGroupingBy(words);
    }

    public static void shortCountByGroupingBy(List<String> words){
        long size = words.stream()
                .collect(Collectors.groupingBy(x -> x.length() <= 3, Collectors.counting())).get(true);
        System.out.println("short: " + size);
    }


    public static void shortCountByAtomicInteger(List<String> words){
        final AtomicInteger shortCount = new AtomicInteger(0);

        long total = words.parallelStream().map(x -> {
            if(x.length() <= 3){
                shortCount.addAndGet(1);
            }
            return x;
        }).count();

        System.out.println("total: " + total);
        System.out.println("short: " + shortCount.get());
    }

}
