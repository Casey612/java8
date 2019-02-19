package java8.ch05;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * @author yuzhe
 * @since 1/29/18
 */
public class TimeTest {

    public static void main(String[] args) {
        LocalDateTime startTime = LocalDateTime.of(2018, 1, 1, 12, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(2018, 1, 2, 10, 0, 0);
        Duration timeElapsed = Duration.between(startTime, endTime);
        // 0
        System.out.println(timeElapsed.toDays());

        LocalDate startDate = LocalDate.of(2017, 1, 1);
        LocalDate endDate = LocalDate.of(2018, 1, 2);
        Period dateElapsed = Period.between(startDate, endDate);
        // 1
        System.out.println(dateElapsed.getDays());
        //1
        System.out.println(dateElapsed.getYears());

        LocalDate lastDayOfJan = LocalDate.of(2018, 1, 31);
        LocalDate firstDayOfFeb = lastDayOfJan.plusDays(1);
        LocalDate lastDayOfFeb = lastDayOfJan.plusMonths(1);
        System.out.println(firstDayOfFeb); // 2018-02-01
        System.out.println(lastDayOfFeb); // 2018-02-28

        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE
                .withLocale(Locale.CHINA)
                .withZone(ZoneId.systemDefault());
        System.out.println(formatter.format(lastDayOfFeb));
    }

}
