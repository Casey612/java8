package java8.ch02;

import java.util.Iterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author yuzhe
 * @since 1/23/18
 */
public class ZipStream {

    public static void main(String[] args) {
        Stream<Integer> stream = zip(Stream.of(new Integer[]{1, 3, 5}), Stream.of(new Integer[]{2, 4, 6, 8, 10}));
        stream.forEach(System.out::println);
    }

    //将两个流合并为1个，并且元素交替
    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
        Iterator<T> firstIterator = first.iterator();
        Iterator<T> secondIterator = second.iterator();

        Iterator<T> iterator = new Iterator<T>() {
            private boolean first = false;

            @Override
            public boolean hasNext() {
                return first ? firstIterator.hasNext() : secondIterator.hasNext();
            }

            @Override
            public T next() {
                first = !first;
                if (first) {
                    if (firstIterator.hasNext()) {
                        return firstIterator.next();
                    } else {
                        return secondIterator.next();
                    }
                } else {
                    if (secondIterator.hasNext()) {
                        return secondIterator.next();
                    } else {
                        return firstIterator.next();
                    }
                }
            }
        };
        Iterable<T> iterable = () -> iterator;
        boolean parallel = first.isParallel() || second.isParallel();
        return StreamSupport.stream(iterable.spliterator(), parallel);
    }

}
