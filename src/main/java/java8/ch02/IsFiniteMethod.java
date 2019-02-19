package java8.ch02;

import java.util.stream.Stream;

/**
 * @author yuzhe
 * @since 1/23/18
 */
public class IsFiniteMethod {

    public static void main(String[] args) {
        System.out.println(isFinite(Stream.iterate(0, x -> x + 1)));
    }

    public static <T> boolean isFinite(Stream<T> stream){
        //GG 判断一个流是否有限 通过判断流的大小来勉强判断流是否有限 不然，你怎么判断一个死循环？微笑脸。
        return stream.spliterator().estimateSize() == Long.MAX_VALUE;
    }




}
