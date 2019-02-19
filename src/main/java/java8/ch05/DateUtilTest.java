package java8.ch05;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Date;

/**
 * 测试 {@link DateUtil}
 *
 * @author JinZheng
 * @version 2.0
 * @since 2017/6/9
 */
public class DateUtilTest {

    public static void main(String[] args) {
    }

    private static final Date DATE0 = new Date(117, 4, 31);
    private static final String DATE0_STRING_DEFAULT = "2017-05-31";

    private static final Date DATE1 = new Date(117, 5, 1);
    private static final String DATE1_STRING_DEFAULT = "2017-06-01";
    private static final String DATE1_STRING_NUM_ONLY = "20170601";
    private static final Date DATE1_TIME = new Date(117, 5, 1, 4, 5, 7);

    private static final Date DATE2 = new Date(117, 5, 2);
    private static final String DATE2_STRING_DEFAULT = "2017-06-02";

    private static final Date DATE3 = new Date(117, 5, 3);
    private static final String DATE3_STRING_DEFAULT = "2017-06-03";

    @BeforeClass
    public static void setUpBeforeClass() {
        System.out.println("Junit4 Test DateUtil set up before class");
    }

    @Before
    public void setUp() throws Exception {
        System.out.println("Junit4 Test DateUtil set up");
    }

    /* ----- Time Formatting ----- */

    @Test
    public void testDateToStr() {
        Assert.assertEquals(
                DATE1_STRING_DEFAULT,
                DateUtil.dateToStr(DATE1)
        );

        Assert.assertEquals(
                DATE1_STRING_NUM_ONLY,
                DateUtil.dateToStr(DATE1, DateUtil.DATE_PATTERN_NUM_ONLY)
        );
    }

    /* ----- Time Parsing ----- */

    @Test
    public void testStrToDate() {
        Assert.assertEquals(
                DATE1,
                DateUtil.strToDate(DATE1_STRING_DEFAULT)
        );

        Assert.assertEquals(
                DATE1,
                DateUtil.strToDate(DATE1_STRING_NUM_ONLY, DateUtil.DATE_PATTERN_NUM_ONLY)
        );
    }

    /* ----- Calculations ----- */

    @Test
    public void testPlus() {
        Assert.assertEquals(
                DATE2,
                DateUtil.plusDays(DATE1, 1)
        );
    }

    @Test
    public void testCompareDateOnly() {
        Assert.assertEquals(
                0,
                DateUtil.compareDateOnly(DATE1, DATE1_TIME)
        );

        Assert.assertEquals(
                -1,
                DateUtil.compareDateOnly(DATE1_TIME, DATE3)
        );
    }

    @Test
    public void testDaysBetween() {
        Assert.assertEquals(
                2,
                DateUtil.daysBetween(DATE1, DATE3)
        );
    }

    /* ----- Date Range ----- */

    @Test
    public void testListDatesBetween() {
        Object[] expected = new Object[]{
                DATE0,
                DATE1,
                DATE2,
                DATE3
        };

        Assert.assertArrayEquals(
                expected,
                DateUtil.listDatesBetween(DATE0, DATE3).toArray()
        );
    }

    @Test
    public void testListDateStrBetween() {
        Object[] expected = new Object[]{
                DATE0_STRING_DEFAULT,
                DATE1_STRING_DEFAULT,
                DATE2_STRING_DEFAULT,
                DATE3_STRING_DEFAULT
        };

        Assert.assertArrayEquals(
                expected,
                DateUtil.listDateStrBetween(DATE0_STRING_DEFAULT, DATE3_STRING_DEFAULT).toArray()
        );
    }

    @Test
    public void testListDatesSince() {
        Object[] expected = new Object[]{
                DATE1,
                DATE2
        };

        Assert.assertArrayEquals(
                expected,
                DateUtil.listDatesSince(DATE1, 1).toArray()
        );
    }

    @Test
    public void getDuration(){
        LocalDate start = LocalDate.of(2018, 3, 4);
        LocalDate end = LocalDate.now();
        Duration duration = Duration.between(start.atStartOfDay(), end.atStartOfDay());
        System.out.println(duration.toDays());

        LocalDate start2 = LocalDate.of(2018, 7, 11);
        LocalDate end2 = LocalDate.now();
        Duration duration2 = Duration.between(start2.atStartOfDay(), end2.atStartOfDay());
        System.out.println(duration2.toDays());

    }

}
