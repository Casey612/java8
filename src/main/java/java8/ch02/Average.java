package java8.ch02;

import java.util.stream.Stream;

/**
 * @author yuzhe
 * @since 1/23/18
 */
public class Average {

    public static void main(String[] args) {
        Stream<Double> stream = Stream.of(new Double[]{1d, 2d, 3d});
        System.out.println(avarage(stream));
    }

    //获取流的count会使流被使用，导致第二次无法正常打开
    public static double avarage(Stream<Double> stream) {
        final double[] sum = {0d};
        final int[] size = {0};
        stream.forEach(d -> {
            sum[0] += d;
            size[0]++;
        });
        return sum[0] / size[0];
    }

}
