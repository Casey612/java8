package java8.ch02;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author yuzhe
 * @since 1/23/18
 */
public class streamMax {

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

        long singleStr = System.currentTimeMillis();

        Stream<String> longWords = words.stream().filter(word -> {
            if(word.length() > 3){
                System.out.println("long word: " + word);
                return true;
            }else{
                System.out.println("short word：" + word);
                return false;
            }
        });
        long singleEnd = System.currentTimeMillis();
        System.out.println("single times : " + (singleEnd - singleStr));
        System.out.println(longWords.count());


        long paralStr = System.currentTimeMillis();
        Stream<String> paralLongWords = words.parallelStream().filter(word -> {
            if(word.length() > 3){
                System.out.println("long word: " + word);
                return true;
            }else{
                System.out.println("short word：" + word);
                return false;
            }
        });
        long paralEnd = System.currentTimeMillis();
        System.out.println("paral times : " + (paralEnd - paralStr));
        System.out.println(paralLongWords.count());
    }

}
