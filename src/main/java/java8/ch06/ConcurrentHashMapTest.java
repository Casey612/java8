package java8.ch06;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yuzhe
 * @since 2/5/18
 */
public class ConcurrentHashMapTest {

    public static void main(String[] args) {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        map.put("four", 4);
        map.put("five", 5);
        map.put("six", 6);
        String maxKey = map.reduceEntries(3, (entry1, entry2) -> {
           if(entry1.getValue() > entry2.getValue()){
               return entry1;
           } else{
               return entry2;
           }
        }).getKey();
        System.out.println(maxKey);
    }

}
