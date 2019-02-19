package java8.ch08;

import java.util.Random;

/**
 * @author yuzhe
 * @since 2/6/18
 */
public class MathTest {

    public static void main(String[] args) {
        Random generator = new Random();
        double r = 1 - generator.nextDouble();
    }

    private MathTest(){
        throw new AssertionError();
    }

    public static class SubMathTest extends MathTest {
        public SubMathTest(){
            super();//GG
        }
    }

}
