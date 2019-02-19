package java8.ch03;

/**
 * @author yuzhe
 * @since 1/24/18
 */
public class AssertTest {

    public static void main(String[] args) {
        String s = null;
        assert s != null ? true : false;
        assert false;
        System.out.println("end");
    }

}
