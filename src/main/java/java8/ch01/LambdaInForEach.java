package java8.ch01;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuzhe
 * @since 1/18/18
 */
public class LambdaInForEach {

    public static void main(String[] args) {
        String[] names = {"123", "234", "345"};
        List<Runnable> runners1 = new ArrayList<>();
        for(String s : names){
            runners1.add(() -> {
                System.out.println(s);
            });
        }
        for(Runnable runnable : runners1){
            runnable.run();
        }
    }

}
