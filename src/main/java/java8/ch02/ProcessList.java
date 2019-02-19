package java8.ch02;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author yuzhe
 * @since 1/23/18
 */
public class ProcessList {


    public static void main(String[] args) {

        Stream<ArrayList<Integer>> stream1 = init();
        Stream<ArrayList<Integer>> stream2 = init();
        Stream<ArrayList<Integer>> stream3 = init();

        ArrayList<Integer> result1 = process1(stream1);
        ArrayList<Integer> result2 = process2(stream2);
        ArrayList<Integer> result3 = process3(stream3);

        System.out.println(result1.toString());
        System.out.println(result2.toString());
        System.out.println(result3.toString());
    }

    public static Stream<ArrayList<Integer>> init(){
        return Stream.of(new ArrayList<Integer>(){
            {
                this.add(1);
                this.add(2);
                this.add(3);
                this.add(4);
                this.add(5);
            }
        }, new ArrayList<Integer>(){
            {
                this.add(6);
                this.add(7);
                this.add(8);
                this.add(9);
            }
        });
    }

    public static <T> ArrayList<T> process3(Stream<ArrayList<T>> stream){
        ArrayList<T> result = new ArrayList<T>();
        stream.reduce(result, (list1, list2) -> {
            list1.addAll(list2);
            return list1;
        });
        return result;
    }

    public static <T> ArrayList<T> process2(Stream<ArrayList<T>> stream){
        ArrayList<T> result = new ArrayList<T>();
        stream.forEach(list -> result.addAll(list));
        return result;
    }

    public static <T> ArrayList<T> process1(Stream<ArrayList<T>> stream){
        ArrayList<T> result = stream.flatMap(list -> list.stream()).collect(Collectors.toCollection(ArrayList::new));
        return result;
    }

}
