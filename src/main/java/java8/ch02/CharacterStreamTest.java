package java8.ch02;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author yuzhe
 * @since 1/23/18
 */
public class CharacterStreamTest {

    public static void main(String[] args) {

    }

    public Stream<Character> characterStreamByStream(String s) {
        return Stream.iterate(0, integer -> {
            if (integer < s.length()) {
                integer++;
            }
            return integer;
        }).map(s::charAt);
    }

    public Stream<Character> characterStream(String s) {
        List<Character> result = new ArrayList<>();
        for (char c : s.toCharArray()) {
            result.add(c);
        }
        return result.stream();
    }
}
