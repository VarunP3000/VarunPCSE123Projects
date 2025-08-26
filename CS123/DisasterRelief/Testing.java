import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class Testing {

    @Test
    @DisplayName("STUDENT TEST - Case #1")
    public void firstCaseTest() {
        int budget = 500;
        List<Location> loci = new ArrayList<>();
        Location firstLoc = new Location("Location #1", 100, 400);
        Location secondLoc = new Location("Location #2", 150, 600);
        loci.add(firstLoc);
        loci.add(secondLoc);


        Set<Location> expected = new HashSet<Location>();
        expected.add(firstLoc);

        Set<Location> actual = Client.allocateRelief(budget, loci).getLocations();
        assertEquals(expected, actual, "Allocate Relief picked " + actual + " instead of " + expected);
    }

    @Test
    @DisplayName("STUDENT TEST - Case #2")
    public void secondCaseTest() {
        int budget = 800;
        List<Location> loci = new ArrayList<>();
        Location firstLoc = new Location("Location #1", 120, 300);
        Location secondLoc = new Location("Location #2", 150, 400);
        Location thirdLoc = new Location("Location #2", 150, 200);
        loci.add(firstLoc);
        loci.add(secondLoc);
        loci.add(thirdLoc);
        Set<Location> expected = new HashSet<Location>();
        expected.add(secondLoc);
        expected.add(thirdLoc);
        
        Set<Location> actual = Client.allocateRelief(budget, loci).getLocations();
        assertEquals(expected, actual, "Allocate Relief picked " + actual + " instead of " + expected);
    }

    @Test
    @DisplayName("STUDENT TEST - Case #3")
    public void thirdCaseTest() {
        int budget = 500;
        List<Location> loci = new ArrayList<>();
        Location firstLoc = new Location("Location #1", 120, 300);
        Location secondLoc = new Location("Location #2", 120, 400);
        loci.add(firstLoc);
        loci.add(secondLoc);
        Set<Location> expected = new HashSet<Location>();
        expected.add(firstLoc);
        
        Set<Location> actual = Client.allocateRelief(budget, loci).getLocations();
        assertEquals(expected, actual, "Allocate Relief picked " + actual + " instead of " + expected);
    }
}
