package java8.ch01;

/**
 * @author yuzhe
 * @since 1/18/18
 */
public class AndThen {


    public static Runnable andThen(Runnable r1, Runnable r2) {
        return () -> {
            r1.run();
            r2.run();
        };
    }


    public static void main(String[] args) {
        Runnable result = andThen(() -> {
            System.out.println("this is runner 1");
        }, () -> {
            System.out.println("this is runner 2");
        });
    }

}
