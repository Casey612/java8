package java8.ch05;

import com.google.common.base.Preconditions;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * DateUtil
 *
 * @author JinZheng
 * @version 2.0
 * @since 6/9/17
 */
public final class DateUtil {


    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final String DATE_PATTERN_NUM_ONLY = "yyyyMMdd";
    public static final String MINUTE_PATTERN_NUM_ONLY = "yyyyMMddHHmm";
    public static final String SECOND_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static final String DEFAULT_PATTERN = DATE_PATTERN;

    private static ZoneId zone = ZoneId.systemDefault();

    /* ----- Time Formatting ----- */

    /**
     * Transform date to string for default pattern 'yyyy-MM-dd' with Locale.CHINA
     *
     * @return string pattern 'yyyy-MM-dd'
     * @throws IllegalArgumentException if date was null
     */
    public static String dateToStr(Date date) {
        return dateToStr(date, DEFAULT_PATTERN);
    }

    /**
     * Transform date to string with pattern 'yyyy-MM-dd HH:mm:ss'
     */
    public static String timeToStr(Date date) {
        return dateToStr(date, SECOND_PATTERN);
    }

    /**
     * Transform date to string for specific pattern with Locale.CHINA
     */
    public static String dateToStr(Date date, String pattern) {
        if (null == date) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern)
                .withLocale(Locale.CHINA)
                .withZone(zone);
        return formatter.format(date.toInstant());
    }

    public static String dateToStrWithoutDash(Date date){
        return dateToStr(date, DATE_PATTERN_NUM_ONLY);
    }


    /* ----- Time Parsing ----- */

    /**
     * Parse string to date for default pattern 'yyyy-MM-dd' with Locale.CHINA
     *
     * @throws IllegalArgumentException if str was blank
     */
    public static Date strToDate(String str) {
        return strToDate(str, DEFAULT_PATTERN);
    }

    /**
     * Parse string to date for specific pattern with Locale.CHINA
     *
     * @throws IllegalArgumentException if str was blank or pattern was null
     */
    public static Date strToDate(String str, String pattern) {
        Preconditions.checkArgument(null != str && !"".equals(str.trim()),
                "Blank string cannot be parsed to date");
        Preconditions.checkArgument(null != pattern,
                "Null pattern cannot be used for date parsing");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern, Locale.CHINA)
                .withLocale(Locale.CHINA).withZone(zone);
        LocalDate localDate = LocalDate.parse(str, formatter);
        return Date.from(localDate.atStartOfDay().atZone(zone).toInstant());
    }

    /**
     * 从{@code patterns}候选格式列表中，找到合适的pattern解析{@code str}，返回对应的Date
     *
     * @param str      待转换的日期
     * @param patterns pattern列表,从该列表中找到适合的pattern解析str
     * @return 返回对应的{@code str}的日期
     * @throws IllegalArgumentException if str was blank, pattern was null or the given pattern is
     *                                  invalid
     */
    public static Date strToDate(String str, String[] patterns) {
        Preconditions.checkArgument(null != str && !"".equals(str.trim()),
                "Blank string cannot be parsed to date");
        Preconditions.checkArgument(null != patterns, "Null pattern cannot be used for date parsing");

        SimpleDateFormat parser = null;
        ParsePosition pos = new ParsePosition(0);

        for (int i = 0; i < patterns.length; ++i) {
            if (i == 0) {
                parser = new SimpleDateFormat(patterns[0]);
            } else {
                parser.applyPattern(patterns[i]);
            }

            pos.setIndex(0);
            Date date = parser.parse(str, pos);
            if (date != null && pos.getIndex() == str.length()) {
                return date;
            }
        }

        return null;
    }

    /* ----- Timestamp ----- */

    /**
     * 获得当前时间的时间戳
     */
    public static long getCurrentTimestamp() {
        return System.currentTimeMillis() / 1000;
    }

    /* ----- Calculations ----- */

    public static Date plus(
            Date date,
            int years,
            int months,
            int days
    ) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), zone);
        LocalDate localDate = localDateTime.toLocalDate();
        localDate = localDate.plusYears(years)
                .plusMonths(months)
                .plusDays(days);
        Instant result = localDate.atStartOfDay().atZone(zone).toInstant();
        return Date.from(result);
    }

    public static Date plusDays(Date date, int days) {
        return plus(date, 0, 0, days);
    }

    public static Date plusMonths(Date date, int months) {
        return plus(date, 0, months, 0);
    }

    public static Date plusYears(Date date, int years) {
        return plus(date, years, 0, 0);
    }

    /**
     * @return 返回当前日期的前一天
     */
    public static Date yesterday() {
        Duration duration = Duration.ofDays(1);
        return Date.from(Instant.now().minus(duration));
    }

    /**
     * 返回当前日期的后一天
     *
     * @return 当前日期的后一天
     */
    public static Date tomorrow() {
        Duration duration = Duration.ofDays(1);
        return Date.from(Instant.now().plus(duration));
    }

    /**
     * 返回当前日期的前n天
     *
     * Deprecated: Use {@code plusDays} instead
     *
     * @param n 前n天
     * @return 返回当前日期的前{@code n}天对应的日期
     */
    @Deprecated
    public static Date dateBeforeNDays(int n) {
        Duration duration = Duration.ofDays(n);
        return Date.from(Instant.now().minus(duration));
    }

    /**
     * {@code date1}与{@code date2}比较，等价于<pre> date1 < date2 ? -1 : ( date1 == date2 ? 0 : 1)</pre>
     * <pre> e.g.
     *  date1=20170601;
     *  date2=20170602;
     *  this.compareDateOnly(date1, date2) == -1
     * </pre>
     *
     * @param date1 日期1
     * @param date2 日期2
     * @return {@code date1}与{@code date2}比较结果
     */
    public static int compareDateOnly(Date date1, Date date2) {
        LocalDate localDate1 = LocalDateTime.ofInstant(date1.toInstant(), zone).toLocalDate();
        LocalDate localDate2 = LocalDateTime.ofInstant(date2.toInstant(), zone).toLocalDate();
        return localDate1.compareTo(localDate2) < 0 ? -1 : ( localDate1.equals(localDate2) ? 0 : 1);
    }

    /**
     * 得到{@code start}与{@code end}之间以天为单位的时间间隔
     * <pre> e.g.
     *  start = "20170601";
     *  end = "20170602";
     *  this.daysBetween(start, end) == 1
     * </pre>
     *
     * @param start 开始日期
     * @param end   结束日期
     * @return {@code start}与{@code end}以天为单位的时间间隔
     */
    public static int daysBetween(Date start, Date end) {
        LocalDate startDate = LocalDateTime.ofInstant(start.toInstant(), zone).toLocalDate();
        LocalDate endDate = LocalDateTime.ofInstant(end.toInstant(), zone).toLocalDate();
        return startDate.until(endDate).getDays();
    }

    /**
     * 返回{@code date}的后一天
     *
     * Deprecated: {@code DateTime} is preferred over {@code Calendar}. use {@code plusDays} instead
     *
     * @param date 指定某一天
     * @return {@code date}的后一天
     */
    @Deprecated
    public static Date nextDay(Date date) {
        Duration oneDay = Duration.ofDays(1);
        Instant instant = date.toInstant();
        Instant result = instant.plus(oneDay);
        return Date.from(result);
    }

    /**
     * 得到从当前日期向前N天的日期.
     *
     * @param dayNum 指定前{@code dayNum}天
     * @return {@code dayNum}天前的日期
     */
    @Deprecated
    public static Date nDayBefore(int dayNum) {
        Duration duration = Duration.ofDays(dayNum);
        return Date.from(Instant.now().minus(duration));
    }

    /**
     * 根据传入的日期，得到一个月前的日期
     *
     * Deprecated: {@code DateTime} is preferred over {@code Calendar}. use {@code plusMonths}
     * instead
     *
     * @param date 指定日期
     * @return {@code date}上一个月对应的日期
     */
    @Deprecated
    public static Date getLastMonthDay(Date date) {
        LocalDate localDate = LocalDateTime.ofInstant(date.toInstant(), zone).toLocalDate();
        LocalDate result = localDate.minusMonths(1);
        return Date.from(result.atStartOfDay().atZone(zone).toInstant());
    }

    /**
     * 将{@code date}转变为yyyy-MM-dd格式的日期
     *
     * Deprecated: use {@code getDayStartSecond} instead
     *
     * @param date 需要转换的日期
     * @return {@code date} 转换为yyyy-MM-dd格式对应的日期
     */
    @Deprecated
    public static Date getDateOnly(Date date) {
        String str = dateToStr(date, DEFAULT_PATTERN);
        return strToDate(str);
    }

    /**
     * 计算指定时间，对应当天的最后一秒
     * <pre> e.g.
     *  date = "2016-08-30 10:42:09"
     *  this.getDayEndSecond(date) == "2016-08-30 23:59:59"
     *  </pre>
     *
     * @param date 需要计算的日期
     * @return 返回指定日期对应当天的最后一秒
     */
    public static Date getDayEndSecond(Date date) {
        LocalDate localDate = LocalDateTime.ofInstant(date.toInstant(), zone).toLocalDate();
        return Date.from(LocalDateTime.of(localDate, LocalTime.MAX).atZone(zone).toInstant());
    }

    /**
     * 得到指定时间，对应当天的第一秒
     * <pre> e.g.
     *  date = "2016-08-30 10:42:09"
     *  this.getDayEndSecond(date) == "2016-08-30 00:00:00"
     *  </pre>
     *
     * @param date 需要计算的日期
     * @return 返回指定日期对应当天的第一秒
     */
    public static Date getDayStartSecond(Date date) {
        LocalDate localDate = LocalDateTime.ofInstant(date.toInstant(), zone).toLocalDate();
        return Date.from(localDate.atStartOfDay().atZone(zone).toInstant());
    }

    /* ----- Date Range ----- */

    private static class DateTimeIterable implements Iterable<LocalDateTime> {

        private final LocalDateTime start;
        private final LocalDateTime end;
        private final int step;

        private DateTimeIterable(Date start, Date end) {
            this.start = LocalDateTime.ofInstant(start.toInstant(), zone);
            this.end = LocalDateTime.ofInstant(end.toInstant(), zone);
            step = start.before(end) ? 1 : -1;
        }

        private DateTimeIterable(Date start, int n) {
            this.start = LocalDateTime.ofInstant(start.toInstant(), zone);
            this.end = this.start.plusDays(n);
            step = n < 0 ? -1 : 1;
        }

        @Override
        public Iterator<LocalDateTime> iterator() {

            return new Iterator<LocalDateTime>() {

                private LocalDateTime cursor = start.plusDays(-step);

                @Override
                public boolean hasNext() {
                    if (step < 0) {
                        return cursor.isAfter(end);
                    } else {
                        return cursor.isBefore(end);
                    }
                }

                @Override
                public LocalDateTime next() {
                    cursor = cursor.plusDays(step);
                    return cursor;
                }

                @Override
                public void remove() {
                }
            };
        }

        public List<Date> toDateList() {
            List<Date> list = new ArrayList<>();

            for (LocalDateTime dateTime : this) {
                list.add(Date.from(dateTime.atZone(zone).toInstant()));
            }

            return list;
        }

        public List<String> toDateStrList(String pattern) {
            return toDateStrList(DateTimeFormatter.ofPattern(pattern, Locale.CHINA));
        }

        public List<String> toDateStrList(DateTimeFormatter formatter) {
            List<String> list = new ArrayList<>();

            for (LocalDateTime dateTime : this) {
                list.add(formatter.format(dateTime));
            }

            return list;
        }
    }

    /**
     * 列出 {@code beginDate} 与 {@code endDate} 闭区间的日期
     * 当前者大于后者时，结果为倒序
     *
     * @param beginDate 开始日期
     * @param endDate   结束日期
     * @return {@code beginDate} 与 {@code endDate} 闭区间的日期
     * @see DateTimeIterable
     */
    public static List<Date> listDatesBetween(
            Date beginDate,
            Date endDate
    ) {
        return new DateTimeIterable(beginDate, endDate).toDateList();
    }

    /**
     * 列出 {@code beginDate} 与 {@code endDate} 闭区间的日期
     * 当前者大于后者时，结果为倒序
     *
     * @param beginDate    开始日期字符串，格式为 {@code parsePattern}
     * @param endDate      结束日期字符串，格式为 {@code parsePattern}
     * @param parsePattern 日期参数的格式
     * @return {@code beginDate} 与 {@code endDate} 闭区间的日期
     * @see DateTimeIterable
     */
    public static List<Date> listDatesBetween(
            String beginDate,
            String endDate,
            String parsePattern
    ) {
        return listDatesBetween(
                strToDate(beginDate, parsePattern),
                strToDate(endDate, parsePattern)
        );
    }

    /**
     * 列出 {@code beginDate} 与 {@code endDate} 闭区间的日期
     * 当前者大于后者时，结果为倒序
     *
     * @param beginDate 开始日期字符串，格式为 {@code DEFAULT_PATTERN}
     * @param endDate   结束日期字符串，格式为 {@code DEFAULT_PATTERN}
     * @return {@code beginDate} 与 {@code endDate} 闭区间的日期
     * @see DateTimeIterable
     */
    public static List<Date> listDatesBetween(
            String beginDate,
            String endDate
    ) {
        return listDatesBetween(beginDate, endDate, DEFAULT_PATTERN);
    }

    /**
     * Deprecated: use {@code listDatesBetween} instead
     */
    @Deprecated
    public static List<Date> getDateRange(Date beginDate, Date endDate) {
        return listDatesBetween(beginDate, endDate);
    }

    /**
     * 列出 {@code beginDate} 与 {@code endDate} 闭区间的格式为 {@code pattern} 的日期字符串
     * 当前者大于后者时，结果为倒序
     *
     * @param beginDate 开始日期
     * @param endDate   结束日期
     * @param pattern   生成日期的格式
     * @return {@code beginDate} 与 {@code endDate} 闭区间的格式为 {@code pattern} 的日期字符串
     * @see DateTimeIterable
     */
    public static List<String> listDateStrBetween(
            Date beginDate,
            Date endDate,
            String pattern
    ) {
        return new DateTimeIterable(beginDate, endDate).toDateStrList(pattern);
    }

    /**
     * 列出 {@code beginDate} 与 {@code endDate} 闭区间的格式为 {@code DEFAULT_PATTERN} 的日期字符串
     * 当前者大于后者时，结果为倒序
     *
     * @param beginDate 开始日期
     * @param endDate   结束日期
     * @return {@code beginDate} 与 {@code endDate} 闭区间的格式为 {@code DEFAULT_PATTERN} 的日期字符串
     * @see DateTimeIterable
     */
    public static List<String> listDateStrBetween(
            Date beginDate,
            Date endDate
    ) {
        return listDateStrBetween(beginDate, endDate, DEFAULT_PATTERN);
    }

    /**
     * 列出 {@code beginDate} 与 {@code endDate} 闭区间的格式为 {@code pattern} 的日期字符串
     * 当前者大于后者时，结果为倒序
     *
     * @param beginDate    开始日期字符串，格式为 {@code parsePattern}
     * @param endDate      结束日期字符串，格式为 {@code parsePattern}
     * @param parsePattern 日期参数的格式
     * @param pattern      生成日期的格式
     * @return {@code beginDate} 与 {@code endDate} 闭区间的格式为 {@code pattern} 的日期字符串
     * @see DateTimeIterable
     */
    public static List<String> listDateStrBetween(
            String beginDate,
            String endDate,
            String parsePattern,
            String pattern
    ) {
        return listDateStrBetween(
                strToDate(beginDate, parsePattern),
                strToDate(endDate, parsePattern),
                pattern
        );
    }

    /**
     * 列出 {@code beginDate} 与 {@code endDate} 闭区间的格式为 {@code pattern} 的日期字符串
     * 当前者大于后者时，结果为倒序
     *
     * @param beginDate 开始日期字符串，格式为 {@code pattern}
     * @param endDate   结束日期字符串，格式为 {@code pattern}
     * @param pattern   日期的格式
     * @return {@code beginDate} 与 {@code endDate} 闭区间的格式为 {@code pattern} 的日期字符串
     * @see DateTimeIterable
     */
    public static List<String> listDateStrBetween(
            String beginDate,
            String endDate,
            String pattern
    ) {
        return listDateStrBetween(beginDate, endDate, pattern, pattern);
    }

    /**
     * 列出 {@code beginDate} 与 {@code endDate} 闭区间的格式为 {@code DEFAULT_PATTERN} 的日期字符串
     * 当前者大于后者时，结果为倒序
     *
     * @param beginDate 开始日期字符串，格式为 {@code DEFAULT_PATTERN}
     * @param endDate   结束日期字符串，格式为 {@code DEFAULT_PATTERN}
     * @return {@code beginDate} 与 {@code endDate} 闭区间的格式为 {@code DEFAULT_PATTERN} 的日期字符串
     * @see DateTimeIterable
     */
    public static List<String> listDateStrBetween(
            String beginDate,
            String endDate
    ) {
        return listDateStrBetween(beginDate, endDate, DEFAULT_PATTERN);
    }

    /**
     * Deprecated: use {@code listDateStrBetween} instead
     */
    @Deprecated
    public static List<String> getDateRangeStr(Date beginDate, Date endDate, String pattern) {
        return listDateStrBetween(beginDate, endDate, pattern);
    }

    /**
     * 列出 {@code beginDate} 与之后 {@code days} 天的闭区间的日期
     * 当 {@code days} 小于 {@code 0} 时，列出的是 {@code beginDate} 之前的日期，且结果为倒序
     *
     * @param beginDate 开始日期
     * @param days      天数
     * @return {@code beginDate} 与之后 {@code days} 天的闭区间的日期
     * @see DateTimeIterable
     */
    public static List<Date> listDatesSince(
            Date beginDate,
            int days
    ) {
        return new DateTimeIterable(beginDate, days).toDateList();
    }

    /**
     * 列出 {@code beginDate} 与之后 {@code days} 天的闭区间的日期
     * 当 {@code days} 小于 {@code 0} 时，列出的是 {@code beginDate} 之前的日期，且结果为倒序
     *
     * @param beginDate    开始日期字符串，格式为 {@code parsePattern}
     * @param days         天数
     * @param parsePattern 日期参数的格式
     * @return {@code beginDate} 与之后 {@code days} 天的闭区间的日期
     * @see DateTimeIterable
     */
    public static List<Date> listDatesSince(
            String beginDate,
            int days,
            String parsePattern
    ) {
        return listDatesSince(
                strToDate(beginDate, parsePattern),
                days
        );
    }

    /**
     * 列出 {@code beginDate} 与之后 {@code days} 天的闭区间的日期
     * 当 {@code days} 小于 {@code 0} 时，列出的是 {@code beginDate} 之前的日期，且结果为倒序
     *
     * @param beginDate 开始日期字符串，格式为 {@code DEFAULT_PATTERN}
     * @param days      天数
     * @return {@code beginDate} 与之后 {@code days} 天的闭区间的日期
     * @see DateTimeIterable
     */
    public static List<Date> listDatesSince(
            String beginDate,
            int days
    ) {
        return listDatesSince(beginDate, days, DEFAULT_PATTERN);
    }

    /**
     * 列出 {@code beginDate} 与之后 {@code days} 天的格式为 {@code pattern} 的闭区间的日期字符串
     * 当 {@code days} 小于 {@code 0} 时，列出的是 {@code beginDate} 之前的日期，且结果为倒序
     *
     * @param beginDate 开始日期
     * @param days      天数
     * @param pattern   生成日期的格式
     * @return {@code beginDate} 与之后 {@code days} 天的格式为 {@code pattern} 的闭区间的日期字符串
     * @see DateTimeIterable
     */
    public static List<String> listDateStrSince(
            Date beginDate,
            int days,
            String pattern
    ) {
        return new DateTimeIterable(beginDate, days).toDateStrList(pattern);
    }

    /**
     * 列出 {@code beginDate} 与之后 {@code days} 天的格式为 {@code DEFAULT_PATTERN} 的闭区间的日期字符串
     * 当 {@code days} 小于 {@code 0} 时，列出的是 {@code beginDate} 之前的日期，且结果为倒序
     *
     * @param beginDate 开始日期
     * @param days      天数
     * @return {@code beginDate} 与之后 {@code days} 天的格式为 {@code DEFAULT_PATTERN} 的闭区间的日期字符串
     * @see DateTimeIterable
     */
    public static List<String> listDateStrSince(
            Date beginDate,
            int days
    ) {
        return listDateStrSince(beginDate, days, DEFAULT_PATTERN);
    }

    /**
     * 列出 {@code beginDate} 与之后 {@code days} 天的格式为 {@code pattern} 的闭区间的日期字符串
     * 当 {@code days} 小于 {@code 0} 时，列出的是 {@code beginDate} 之前的日期，且结果为倒序
     *
     * @param beginDate    开始日期字符串，格式为 {@code parsePattern}
     * @param days         天数
     * @param parsePattern 日期参数的格式
     * @param pattern      生成日期的格式
     * @return {@code beginDate} 与之后 {@code days} 天的格式为 {@code pattern} 的闭区间的日期字符串
     * @see DateTimeIterable
     */
    public static List<String> listDateStrSince(
            String beginDate,
            int days,
            String parsePattern,
            String pattern
    ) {
        return listDateStrSince(
                strToDate(beginDate, parsePattern),
                days,
                pattern
        );
    }

    /**
     * 列出 {@code beginDate} 与之后 {@code days} 天的格式为 {@code pattern} 的闭区间的日期字符串
     * 当 {@code days} 小于 {@code 0} 时，列出的是 {@code beginDate} 之前的日期，且结果为倒序
     *
     * @param beginDate 开始日期字符串，格式为 {@code pattern}
     * @param days      天数
     * @param pattern   日期的格式
     * @return {@code beginDate} 与之后 {@code days} 天的格式为 {@code pattern} 的闭区间的日期字符串
     * @see DateTimeIterable
     */
    public static List<String> listDateStrSince(
            String beginDate,
            int days,
            String pattern
    ) {
        return listDateStrSince(beginDate, days, pattern, pattern);
    }

    /**
     * 列出 {@code beginDate} 与之后 {@code days} 天的格式为 {@code DEFAULT_PATTERN} 的闭区间的日期字符串
     * 当 {@code days} 小于 {@code 0} 时，列出的是 {@code beginDate} 之前的日期，且结果为倒序
     *
     * @param beginDate 开始日期字符串，格式为 {@code DEFAULT_PATTERN}
     * @param days      天数
     * @return {@code beginDate} 与之后 {@code days} 天的格式为 {@code DEFAULT_PATTERN} 的闭区间的日期字符串
     * @see DateTimeIterable
     */
    public static List<String> listDateStrSince(
            String beginDate,
            int days
    ) {
        return listDateStrSince(beginDate, days, DEFAULT_PATTERN);
    }

    /**
     * 得到从当前日期向前N天的日期的字符串列表.不包括当前。倒序
     *
     * Deprecated: use {@code listDateStrSince} instead
     *
     * @param dayNum  前{@code dayNum}天
     * @param pattern 结果日期格式
     * @return 日期字符串数组
     */
    @Deprecated
    public static List<String> listNDaysBefore(int dayNum, String pattern) {
        return listDateStrSince(yesterday(), -dayNum, pattern);
    }

    /**
     * 返回{@code startDay}与{@code endDay}闭区间的yyyy-MM-dd格式的日期集合
     *
     * Deprecated: use {@code listDateStrBetween} instead
     *
     * @param startDay yyyy-MM-dd格式的开始时间
     * @param endDay   yyyy-MM-dd格式的结束时间
     * @return {@code startDay}与{@code endDay}闭区间内的yyyy-MM-dd格式的日期集合
     */
    @Deprecated
    public static List<String> listDaysBetween(String startDay, String endDay) {
        return listDateStrBetween(startDay, endDay);
    }

    /**
     * 获取{@code startDay}与{@code endDay}闭间内的日期,
     *
     * Deprecated: use {@code listDateStrBetween} instead
     *
     * @param startDay     开始日期
     * @param endDay       结束日期
     * @param formatBefore startDay和endDay的日期格式
     * @param formatAfter  返回结果的日期格式
     * @return {@code startDay}与{@code endDay}闭区间内的{@code formatAfter}格式的String类型的日期集合
     */
    @Deprecated
    public static List<String> listDaysBetween(
            String startDay,
            String endDay,
            String formatBefore,
            String formatAfter
    ) {
        return listDateStrBetween(startDay, endDay, formatBefore, formatAfter);
    }

    /**
     * 获取 {@code startDay}与{@code endDay}闭区间内的yyyy-MM-dd格式的日期集合
     *
     * Deprecated: use {@code listDateStrBetween} instead
     *
     * @param startDay yyyy-MM-dd格式的开始时间
     * @param endDay   yyyy-MM-dd格式的结束时间
     * @return {@code startDay}与{@code endDay}闭区间内的yyyy-MM-dd格式的日期集合
     */
    @Deprecated
    public static List<String> listDaysBetween(Date startDay, Date endDay) {
        return listDateStrBetween(startDay, endDay);
    }

    /**
     * 获取{@code startDay}与{@code endDay}闭区间内的{@code patten}格式的日期集合
     *
     * Deprecated: use {@code listDateStrBetween} instead
     *
     * @param startDay yyyy-MM-dd格式的开始日期
     * @param endDay   yyyy-MM-dd格式的结束日期
     * @param patten   返回日期的格式
     * @return {@code startDay}与{@code endDay}闭区间内的{@code patten}格式的日期集合
     */
    @Deprecated
    public static List<String> listDaysBetween(Date startDay, Date endDay, String patten) {
        return listDateStrBetween(startDay, endDay, patten);
    }

}
