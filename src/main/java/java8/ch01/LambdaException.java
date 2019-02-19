package java8.ch01;

/**
 * @author yuzhe
 * @since 1/17/18
 */
public class LambdaException {

    public static void main(String[] args) {
        new Thread(uncheck(() -> {
            System.out.println("Zzz");
            Thread.sleep(1000);
        })).start();
    }

    private static Runnable uncheck(RunnabledEx runner) {
       /*
        return new Runnable() {
            @Override
            public void run() {
                try {
                    runner.run();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        */

        return () -> {
            try {
                runner.run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }


    @FunctionalInterface
    public interface RunnabledEx {
        public void run() throws Exception;
    }


    /**
     * lambda中的异常处理 1-在方法体中处理
     */
    public static void catchExceptionInLambda() {
        Runnable sleeper = () -> {
            System.out.println("zzz");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        sleeper.run();
    }

}
