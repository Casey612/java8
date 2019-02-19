package java8.ch07;

import java.math.BigInteger;

/**
 * @author yuzhe
 * @since 2/5/18
 */
public class BigIntegerTest {

    public static void main(String[] args) {
        BigInteger b = new BigInteger("1234567890987654321");
        System.out.println(b.mod(BigInteger.TEN));
    }

}
