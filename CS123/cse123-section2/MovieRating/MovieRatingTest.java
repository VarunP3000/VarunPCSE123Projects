/**
 * This is the test suite used to evaluate your solution
 * to this problem when you hit "Mark"!
 * Consider viewing this code to find out how the tests work,
 * or even editing it (the "Mark" button will use the original
 * tests, but "Check" should update to reflect any changes you make.)
 */
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

public class MovieRatingTest {
    /**
     * TA Note - oftentimes when you have tests that run more than once,
     *           it can be useful to create "helper methods" in your test
     *           file, rather than to copy-paste code in every testing method.
     *           Of course, you should be careful when doing this - if there's a bug
     *           in your regular program, your tests should catch it. If there's a bug
     *           in your tests, on the other hand, those can be very difficult to find
     *           (because the first thought is usually never "there's a problem in
     *            my tests...")!
     */
    // A helper method which helps run a test of MovieRating's
    // compareTo.
    //
    // Implementer's note:
    // Note that this method just looks at the sign of expected - e.g.,
    // if first.compareTo(other) should return -2, this method will pass if compareTo returns -1.
    // (This is because compareTo returns an int where the SIGN is what matters; -1 and -2 are essentially equivalent)
    //
    // Location first - the first MovieRating object
    // Location other - the second MovieRating object
    // int expected - the expected value of first.compareTo(other)
    private void helper(MovieRating first, MovieRating other, int expected) {
        int actual = first.compareTo(other);
        if (expected < 0) {
            assertTrue(actual < 0, "expected: < 0, actual: " + actual);
        } else if (expected == 0) {
            assertEquals(expected, actual);
        } else { // expected > 0
            assertTrue(actual > 0, "expected: > 0, actual: " + actual);
        }
    }

    @Test
    @DisplayName("(numRatings: 4, averageRating: 75) vs. (numRatings: 6, averageRating: 70)")
    public void test0() {
        MovieRating first = new MovieRating("");
        for (int i = 0; i < 4; i++) {
            first.addRating(75);
        }
        MovieRating other = new MovieRating("");
        for (int i = 0; i < 6; i++) {
            other.addRating(70);
        }
        helper(first, other, 1);
    }

    @Test
    @DisplayName("(numRatings: 4, averageRating: 75) vs. (numRatings: 6, averageRating: 75)")
    public void test1() {
        MovieRating first = new MovieRating("");
        for (int i = 0; i < 4; i++) {
            first.addRating(75);
        }
        MovieRating other = new MovieRating("");
        for (int i = 0; i < 6; i++) {
            other.addRating(75);
        }
        helper(first, other, -1);
    }

    @Test
    @DisplayName("(numRatings: 1, averageRating: 87) vs. (numRatings: 3, averageRating: 94)")
    public void test2() {
        MovieRating first = new MovieRating("");
        first.addRating(87);
        MovieRating other = new MovieRating("");
        for (int i = 0; i < 3; i++) {
            other.addRating(94);
        }
        helper(first, other, -1);
    }

    @Test
    @DisplayName("(numRatings: 0, averageRating: --) vs. (numRatings: 1, averageRating: 12)")
    public void test3() {
        MovieRating first = new MovieRating("");
        MovieRating other = new MovieRating("");
        other.addRating(12);
        helper(first, other, -1);
    }

    @Test
    @DisplayName("(numRatings: 0, averageRating: --) vs. (numRatings: 0, averageRating: --)")
    public void test4() {
        MovieRating first = new MovieRating("");
        MovieRating other = new MovieRating("");
        helper(first, other, 0);
    }

    @Test
    @DisplayName("(numRatings: 2, averageRating: 91) vs. (numRatings: 3, averageRating: 91)")
    public void test5() {
        MovieRating first = new MovieRating("");
        for (int i = 0; i < 2; i++) {
            first.addRating(91);
        }
        MovieRating other = new MovieRating("");
        for (int i = 0; i < 3; i++) {
            other.addRating(91);
        }
        helper(first, other, -1);
    }

    @Test
    @DisplayName("(numRatings: 2, averageRating: 91, title: abc) vs. (numRatings: 2, averageRating: 91, title: def)")
    public void test6() {
        MovieRating first = new MovieRating("abc");
        for (int i = 0; i < 2; i++) {
            first.addRating(91);
        }
        MovieRating other = new MovieRating("def");
        for (int i = 0; i < 2; i++) {
            other.addRating(91);
        }
        helper(first, other, -1);
    }

    @Test
    @DisplayName("(numRatings: 2, averageRating: 91, title: def) vs. (numRatings: 2, averageRating: 91, title: abc)")
    public void test7() {
        MovieRating first = new MovieRating("abc");
        for (int i = 0; i < 2; i++) {
            first.addRating(91);
        }
        MovieRating other = new MovieRating("def");
        for (int i = 0; i < 2; i++) {
            other.addRating(91);
        }
        helper(other, first, 1);
    }
}
