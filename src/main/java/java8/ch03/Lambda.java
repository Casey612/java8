package java8.ch03;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author yuzhe
 * @since 1/24/18
 */
public class Lambda {


    public static <T, U> Future<U> map(Future<T> future, Function<T, U> function) {
        return new Future<U>() {


            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                return false;
            }

            @Override
            public boolean isCancelled() {
                return false;
            }

            @Override
            public boolean isDone() {
                return false;
            }

            @Override
            public U get() throws InterruptedException, ExecutionException {
                if (future.isDone()) {
                    return function.apply(future.get());
                } else {
                    return null;
                }
            }

            @Override
            public U get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
                return null;
            }
        };
    }

    public static <T, U> List<U> map(List<T> list, Function<T, U> function) {
        return list.stream().map(function).collect(Collectors.toList());
    }

    public static void doInParallelAsync(Runnable first, Runnable second, Consumer<Throwable> exFunction) {
        try {
            first.run();
            second.run();
        } catch (Throwable e) {
            exFunction.accept(e);
        }
    }

    public static Comparator<String> getComparator(boolean asc, boolean capital, boolean blank) {
        return (Comparator<String>) (o1, o2) -> {
            if (!capital) {
                o1 = o1.toLowerCase();
                o2 = o2.toLowerCase();
            }
            if (!blank) {
                o1 = o1.replace(" ", "");
                o2 = o2.replace(" ", "");
            }
            if (asc) {
                return o1.compareTo(o2);
            } else {
                return o2.compareTo(o1);
            }
        };
    }

    public static <T> Comparator<T> lexicograohicComparator(String... fieldNames) {
        return (o1, o2) -> {
            for (String fieldName : fieldNames) {
                try {
                    Object fieldValueForO1 = o1.getClass().getDeclaredField(fieldName).get(o1);
                    Object fieldValueForO2 = o2.getClass().getDeclaredField(fieldName).get(o2);
                    if (!fieldValueForO1.toString().equals(fieldValueForO2.toString())) {
                        return fieldValueForO1.toString().compareTo(fieldValueForO2.toString());
                    }
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            return 0;
        };
    }

}
