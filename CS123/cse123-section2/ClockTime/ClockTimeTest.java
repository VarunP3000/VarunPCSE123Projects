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

public class ClockTimeTest {
    /**
     * This helps construct similar test cases for the compareTo of two ClockTimes!
     * int h1 - the hour of the first ClockTime
     * int m1 - the minute of the first ClockTime
     * String am1 - the am/pm of the first ClockTime
     * int h2 - the hour of the second ClockTime
     * int m2 - the minute of the second ClockTime
     * String am2 - the am/pm of the second ClockTime
     * int expected - the expected value of the first ClockTime compared to the second,
     *                i.e., first.compareTo(second);
     */
    private void helper(int h1, int m1, String am1, int h2, int m2, String am2, int expected) {
        ClockTime ct1 = new ClockTime(h1, m1, am1);
        ClockTime ct2 = new ClockTime(h2, m2, am2);
        int actual = ct1.compareTo(ct2);
        if (expected < 0) {
            assertTrue(actual < 0, "expected: < 0, actual: " + actual);
        } else if (expected == 0) {
            assertEquals(expected, actual, "expected: 0, actual");
        } else { // expected > 0
            assertTrue(actual > 0, "expected: > 0, actual: " + actual);
        }
    }

    @Test
    @DisplayName("3:15 pm vs. 5:45 pm")
    public void test0() {
        helper(3, 15, "pm", 5, 45, "pm", -1);
    }

    @Test
    @DisplayName("3:47 pm vs. 4:18 pm")
    public void test1() {
        helper(3, 15, "pm", 5, 45, "pm", -1);
    }

    @Test
    @DisplayName("5:55 am vs. 5:55 am")
    public void test2() {
        helper(5, 55, "am", 5, 55, "am", 0);
    }

    @Test
    @DisplayName("12:34 pm vs. 12:34 pm")
    public void test3() {
        helper(12, 34, "pm", 12, 34, "pm", 0);
    }

    @Test
    @DisplayName("8:49 am vs. 2:11 pm")
    public void test4() {
        helper(8, 49, "am", 2, 11, "pm", -1);
    }

    @Test
    @DisplayName("10:00 pm vs. 9:01 am")
    public void test5() {
        helper(10, 00, "pm", 9, 01, "am", 1);
    }

    @Test
    @DisplayName("11:59 pm vs. 12:00 am")
    public void test6() {
        helper(11, 59, "pm", 12, 00, "am", 1);
    }

    @Test
    @DisplayName("11:59 am vs. 12:00 pm")
    public void test7() {
        helper(11, 59, "am", 12, 00, "pm", -1);
    }

    @Test
    @DisplayName("11:59 pm vs. 12:00 pm")
    public void test8() {
        helper(11, 59, "pm", 12, 00, "pm", 1);
    }

    @Test
    @DisplayName("12:59 am vs. 1:00 am")
    public void test9() {
        helper(12, 59, "am", 1, 00, "am", -1);
    }

    @Test
    @DisplayName("1:00 am vs. 12:59 am")
    public void test10() {
        helper(1, 00, "am", 12, 59, "am", 1);
    }

    @Test
    @DisplayName("12:59 pm vs. 1:00 pm")
    public void test11() {
        helper(12, 59, "pm", 1, 00, "pm", -1);
    }

    @Test
    @DisplayName("1:00 pm vs. 12:59 pm")
    public void test12() {
        helper(1, 00, "pm", 12, 59, "pm", 1);
    }

    @Test
    @DisplayName("5:55 am vs. 5:55 pm")
    public void test13() {
        helper(5, 55, "am", 5, 55, "pm", -1);
    }

    @Test
    @DisplayName("getHours()")
    public void test14() {
        ClockTime t = new ClockTime(5, 50, "pm");
        assertEquals(5, t.getHours());
    }

    @Test
    @DisplayName("getMinutes()")
    public void test15() {
        ClockTime t = new ClockTime(5, 50, "pm");
        assertEquals(50, t.getMinutes());
    }

    @Test
    @DisplayName("getAmPm()")
    public void test16() {
        ClockTime t = new ClockTime(5, 50, "pm");
        assertEquals("pm", t.getAmPm());
        ClockTime t2 = new ClockTime(5, 50, "am");
        assertEquals("am", t2.getAmPm());
    }

    @Test
    @DisplayName("toString() is correct")
    public void test17() {
        ClockTime t = new ClockTime(3, 10, "am");
        assertEquals("3:10 am", t.toString());
    }  

    @Test
    @DisplayName("implements Comparable interface")
    public void test18() {
        SortedSet<ClockTime> s = new TreeSet<>();
        s.add(new ClockTime(3, 10, "am")); // this will fail if 
    }  

}
