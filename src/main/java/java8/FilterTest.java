package java8;

import com.google.common.base.Strings;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yuzhe
 * @since 12/27/18
 */
public class FilterTest {

    public static void main(String[] args) {
        String str = "1,2,3,,";
        List<String> result = Arrays.stream(str.split(",")).filter(num -> {
            return !Strings.isNullOrEmpty(num.trim()) && Integer.valueOf(num.trim()) % 2 == 0;
        }).collect(Collectors.toList());
        System.out.println(result);
    }

}
