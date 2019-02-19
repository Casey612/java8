package java8.ch02;

import java.math.BigInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author yuzhe
 * @since 1/18/18
 */
public class StreamTest {

    public static void main(String[] args) {
        LCG(25214903917L, 11, 1 << 48, 1).limit(5).forEach(x -> System.out.println(x));
    }

    public static Stream<Long> LCG(long a, long c, long m, long seed) {
        Stream<Long> randoms = Stream.iterate(seed, xn -> (a * xn + c) % m);
        return randoms;
    }

    public static void create() {
        Stream<String> song = Stream.of("gently", "down", "the", "stream");
        Stream<String> empty = Stream.empty();
        Stream<String> echoes = Stream.generate(() -> "echo");
        Stream<Double> randoms = Stream.generate(Math::random);
        Stream<BigInteger> integers = Stream.iterate(BigInteger.ONE, n -> n.add(BigInteger.ONE));

        Integer[] values = {1, 4, 9, 16};
        int[] intValues = {1, 4, 9, 16};
        Stream<Integer> arrays = Stream.of(values);
        IntStream intStream = IntStream.of(intValues);

    }

}
