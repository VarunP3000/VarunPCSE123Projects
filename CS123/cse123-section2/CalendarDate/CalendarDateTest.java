/**
 * This is the test suite used to evaluate your solution
 * to this problem when you hit "Mark"!
 * Consider viewing this code to find out how the tests work,
 * or even editing it (the "Mark" button will use the original
 * tests, but "Check" should update to reflect any changes you make.)
 */

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

public class CalendarDateTest {
   /**
     * This helps construct similar test cases for the compareTo of two CalendarDates!
     * int y1 - the year of the first CalendarDate
     * int m1 - the month of the first CalendarDate
     * int d1 - the day of the first CalendarDate
     * int h2 - the hour of the second CalendarDate
     * int m2 - the month of the second CalendarDate
     * int d1 - the day of the second CalendarDate
     * int expected - the expected value of the first CalendarDate compared to the second,
     *                i.e., first.compareTo(second);
     */
    private void helper(int y1, int m1, int d1, int y2, int m2, int d2, int expected) {
        CalendarDate date1 = new CalendarDate(y1, m1, d1);
        CalendarDate date2 = new CalendarDate(y2, m2, d2);
        int actual = date1.compareTo(date2);
        if (expected < 0) {
            assertTrue(actual < 0, "should be < 0, but found " + actual);
        } else if (expected == 0) {
            assertEquals(expected, actual, "should be 0: " + actual);
        } else { // expected > 0
            assertTrue(actual > 0, "should be > 0, but found " + actual);
        }
    }

    @Test
    @DisplayName("1979/09/19 vs. 1981/05/08")
    public void test0() {
        helper(1979, 9, 19, 1981, 5, 8, -1);
    }

    @Test
    @DisplayName("1981/05/08 vs. 1979/09/19")
    public void test1() {
        helper(1981, 5, 8, 1979, 9, 19, 1);
    }

    @Test
    @DisplayName("1981/05/08 vs. 1981/09/19")
    public void test2() {
        helper(1981, 5, 8, 1981, 9, 19, -1);
    }

    @Test
    @DisplayName("1979/09/19 vs. 1979/09/11")
    public void test3() {
        helper(1979, 9, 19, 1979, 9, 11, 1);
    }

    @Test
    @DisplayName("1981/05/08 vs. 1981/05/19")
    public void test4() {
        helper(1981, 5, 8, 1981, 5, 19, -1);
    }

    @Test
    @DisplayName("1950/07/11 vs. 1950/07/11")
    public void test5() {
        helper(1950, 7, 11, 1950, 7, 11, 0);
    }

    @Test
    @DisplayName("getYear()")
    public void test6() {
        CalendarDate d = new CalendarDate(2000, 1, 30);
        assertEquals(2000, d.getYear());
    }

    @Test
    @DisplayName("getMonth()")
    public void test7() {
        CalendarDate d = new CalendarDate(2000, 1, 30);
        assertEquals(1, d.getMonth());
    }

    @Test
    @DisplayName("getDay()")
    public void test8() {
        CalendarDate d = new CalendarDate(2000, 1, 30);
        assertEquals(30, d.getDay());
    }

    @Test
    @DisplayName("toString()")
    public void test9() {
        CalendarDate d = new CalendarDate(2000, 1, 12);
        assertEquals("2000/01/12", d.toString());
    }

    @Test
    @DisplayName("impements Comparable interface")
    public void test10() {
        SortedSet<CalendarDate> s = new TreeSet<>();
        s.add(new CalendarDate(2000, 1, 30));
    }
}
