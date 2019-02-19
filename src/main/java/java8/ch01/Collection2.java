package java8.ch01;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * @author yuzhe
 * @since 1/18/18
 */
public interface Collection2 extends Collection {

    default public <T> void forEachIf(Consumer<T> action, Predicate<T> filter) {
        this.stream().filter(filter).forEach(action);
    }

}
