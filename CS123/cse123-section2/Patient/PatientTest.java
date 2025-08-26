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

public class PatientTest {
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
    // A helper method which helps run a test of Patient's
    // compareTo.
    //
    // Implementer's note:
    // Note that this method just looks at the sign of expected - e.g.,
    // if first.compareTo(other) should return -2, this method will pass if compareTo returns -1.
    // (This is because compareTo returns an int where the SIGN is what matters; -1 and -2 are essentially equivalent)
    //
    // Patient first - the first MovieRating object
    // Patient other - the second MovieRating object
    // int expected - the expected value of first.compareTo(other)
    private void helper(Patient first, Patient other, int expected) {
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
    @DisplayName("25 year old patient and 61 year old patient, same discomfort")
    public void initialTest() {
        Patient younger = new Patient("name", "papercut", 25, 3);
        Patient older = new Patient("name", "papercut", 61, 3);
        helper(younger, older, 1);
    }

    @Test
    @DisplayName("25 year old patient and 61 year old patient, younger has more discomfort")
    public void laterTest() {
        Patient younger = new Patient("name", "papercut", 25, 7);
        Patient older = new Patient("name", "papercut", 61, 3);
        helper(younger, older, -1);
    }
}
