package java8.ch02;

import com.alibaba.fastjson.JSONObject;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author yuzhe
 * @since 1/22/18
 */
public class ToMapTest {

    public static void main(String[] args) {

        Stream<Locale> locales = Stream.of(Locale.getAvailableLocales());
//        languageNames(locales);
        countryLanguageSets(locales);
    }

    public static void countryLanguageSets(Stream<Locale> locales){
        Map<String, Set<String>> contryLanguageSet = locales.collect(
                Collectors.toMap(
                        locale -> locale.getDisplayCountry(),
                        locale -> Collections.singleton(locale.getDisplayLanguage()),
                        (a, b) ->{
                            //hashSet 不是线程安全的
                            Set<String> r = new HashSet<>(a);
                            r.addAll(b);
                            return r;
                        }
                )
        );
        System.out.println(JSONObject.toJSONString(contryLanguageSet));
    }

    public static void languageNames(Stream<Locale> locales) {
        Map<String, String> languageNames = locales.collect(Collectors.toMap(
                l -> l.getDisplayLanguage(),
                l -> l.getDisplayCountry(l),
                (existingValue, newValue) -> existingValue
        ));
        System.out.println(JSONObject.toJSONString(languageNames));
    }


}
