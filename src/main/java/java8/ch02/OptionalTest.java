package java8.ch02;

import java.util.Optional;

/**
 * @author yuzhe
 * @since 1/22/18
 */
public class OptionalTest {

    public static void main(String[] args) {
        double x = 0D;
        Optional<Double> result = process(x);
        if (result.isPresent()) {
            System.out.println(result.get());
        } else {
            System.out.println(" x == 0;");
        }

        Double answer = processDouble(x);
        Optional<Double> optionalAnswer = Optional.ofNullable(answer);
        System.out.println(optionalAnswer.isPresent() ? optionalAnswer.get() : null);

        Optional<Double> flatTest = Optional.of(-4d).flatMap(OptionalTest::squareRoot);
    }

    public static Optional<Double> squareRoot(Double x) {
        return x < 0 ? Optional.empty() : Optional.of(Math.sqrt(x));
    }

    private static Double processDouble(double x) {
        return x == 0 ? null : 1 / x;
    }

    private static Optional<Double> process(double x) {
        return x == 0d ? Optional.empty() : Optional.of(1 / x);
    }
}
