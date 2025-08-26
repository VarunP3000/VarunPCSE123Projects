import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class RepositoryTest {
    private Repository repo1;
    private Repository repo2;

    /**
     * NOTE: The following test suite assumes that getRepoHead(), commit(), and size()
     *       are implemented correctly.
     */

    @BeforeEach
    public void setUp() {
        repo1 = new Repository("repo1");
        repo2 = new Repository("repo2");
        Repository.Commit.resetIds();
    }

    @Test
    @DisplayName("Test getHistory()")
    public void getHistory() {
        // Initialize commit messages
        String[] commitMessages = new String[]{"Initial commit.", "Updated method documentation.",
                                                "Removed unnecessary object creation."};

        // Commit the commit messages to repo1
        for (int i = 0; i < commitMessages.length; i++) {
            String commitMessage = commitMessages[i];
            repo1.commit(commitMessage);

            // Assert that the current commit id is at the repository's head
            // We know our ids increment from 0, meaning we can just use i as our id
            assertEquals("" + i, repo1.getRepoHead());
        }

        assertEquals(repo1.getRepoSize(), commitMessages.length);

        // This is the method we are testing for. First, we'll obtain the 2 most recent commits
        // that have been made to repo1.
        String repositoryHistory = repo1.getHistory(2);
        String[] commits = repositoryHistory.split("\n");

        // Verify that getHistory() only returned 2 commits.
        assertEquals(commits.length, 2);

        // Verify that the 2 commits have the correct commit message and commit id
        for (int i = 0; i < commits.length; i++) {
            String commit = commits[i];

            // Old commit messages/ids are on the left and the more recent commit messages/ids are
            // on the right so need to traverse from right to left to ensure that 
            // getHistory() returned the 2 most recent commits.
            int backwardsIndex = (commitMessages.length - 1) - i;
            String commitMessage = commitMessages[backwardsIndex];

            assertTrue(commit.contains(commitMessage));
            assertTrue(commit.contains("" + backwardsIndex));
        }
    }

    @Test
    @DisplayName("Test drop() (empty case)")
    public void testDropEmpty() {
        assertFalse(repo1.drop("123"));
    }

    @Test
    @DisplayName("Test drop() (front case)")
    public void testDropFront() {
        assertEquals(repo1.getRepoSize(), 0);
        // Initialize commit messages
        String[] commitMessages = new String[]{"First commit.", "Added unit tests."};

        // Commit to repo1 - ID = "0"
        repo1.commit(commitMessages[0]);

        // Commit to repo2 - ID = "1"
        repo2.commit(commitMessages[1]);

        // Assert that repo1 successfully dropped "0"
        assertTrue(repo1.drop("0"));
        assertEquals(repo1.getRepoSize(), 0);
        
        // Assert that repo2 does not drop "0" but drops "1"
        // (Note that the commit ID increments regardless of the repository!)
        assertFalse(repo2.drop("0"));
        assertTrue(repo2.drop("1"));
        assertEquals(repo2.getRepoSize(), 0);
    }

    /* TODO - Add additional unit tests */

    @Test
    @DisplayName("Test SynchronizeTest() (alternate repo2 head)")
    public void testSynchronizeRepo2RecentAlternate() throws InterruptedException{
        repo1.commit("First commit");
        Thread.sleep(100);
        repo2.commit("Second commit");
        Thread.sleep(100);
        repo1.commit("Third commit");
        Thread.sleep(100);
        repo2.commit("Fourth commit");
        String[] finalMessages = new String[]{"0", "1", "2", "3"};
        repo1.synchronize(repo2);
        assertEquals(repo2.getRepoSize(), 0);
        String[] checkMessages = new String[repo1.getRepoSize()];
        int i = repo1.getRepoSize()-1;
        while(repo1.getRepoHead() != null){
            checkMessages[i] = repo1.getRepoHead();
            String id = "" + i;
            repo1.drop(id);
            i--;
        }
        assertArrayEquals(finalMessages, checkMessages);
    }

    @Test
    @DisplayName("Test SynchronizeTest() (alternate repo1 head)")
    public void testSynchronizeRepo1RecentAlternate() throws InterruptedException{
        repo2.commit("First commit");
        Thread.sleep(100);
        repo1.commit("Second commit");
        Thread.sleep(100);
        repo2.commit("Third commit");
        Thread.sleep(100);
        repo1.commit("Fourth commit");
        String[] finalMessages = new String[]{"0", "1", "2", "3"};
        repo1.synchronize(repo2);
        assertEquals(repo2.getRepoSize(), 0);
        String[] checkMessages = new String[repo1.getRepoSize()];
        int i = repo1.getRepoSize()-1;
        while(repo1.getRepoHead() != null){
            checkMessages[i] = repo1.getRepoHead();
            String id = "" + i;
            repo1.drop(id);
            i--;
        }
        assertArrayEquals(finalMessages, checkMessages);
    }

    @Test
    @DisplayName("Test SynchronizeTest() (repo1 in the middle of repo2)")
    public void testSynchronizeRepo1InBetween() throws InterruptedException{
        repo2.commit("First commit");
        Thread.sleep(100);
        repo1.commit("Second commit");
        Thread.sleep(100);
        repo1.commit("Third commit");
        Thread.sleep(100);
        repo2.commit("Fourth commit");
        String[] finalMessages = new String[]{"0", "1", "2", "3"};
        repo1.synchronize(repo2);
        assertEquals(repo2.getRepoSize(), 0);
        String[] checkMessages = new String[repo1.getRepoSize()];
        int i = repo1.getRepoSize()-1;
        while(repo1.getRepoHead() != null){
            checkMessages[i] = repo1.getRepoHead();
            String id = "" + i;
            repo1.drop(id);
            i--;
        }
        assertArrayEquals(finalMessages, checkMessages);
    }

    @Test
    @DisplayName("Test SynchronizeTest() (repo2 in the middle of repo1)")
    public void testSynchronizeRepo2InBetween() throws InterruptedException{
        repo1.commit("First commit");
        Thread.sleep(100);
        repo2.commit("Second commit");
        Thread.sleep(100);
        repo2.commit("Third commit");
        Thread.sleep(100);
        repo1.commit("Fourth commit");
        String[] finalMessages = new String[]{"0", "1", "2", "3"};
        repo1.synchronize(repo2);
        assertEquals(repo2.getRepoSize(), 0);
        String[] checkMessages = new String[repo1.getRepoSize()];
        int i = repo1.getRepoSize()-1;
        while(repo1.getRepoHead() != null){
            checkMessages[i] = repo1.getRepoHead();
            String id = "" + i;
            repo1.drop(id);
            i--;
        }
        assertArrayEquals(finalMessages, checkMessages);
    }

    @Test
    @DisplayName("Test SynchronizeTest() (repo1Null)")
    public void testSynchronizeRepo1Null() throws InterruptedException{
        repo2.commit("First commit");
        Thread.sleep(100);
        repo2.commit("Second commit");
        String[] finalMessages = new String[]{"0", "1"};
        repo1.synchronize(repo2);
        assertEquals(repo2.getRepoSize(), 0);
        String[] checkMessages = new String[repo1.getRepoSize()];
        int i = repo1.getRepoSize()-1;
        while(repo1.getRepoHead() != null){
            checkMessages[i] = repo1.getRepoHead();
            String id = "" + i;
            repo1.drop(id);
            i--;
        }
        assertArrayEquals(finalMessages, checkMessages);
    }

    @Test
    @DisplayName("Test SynchronizeTest() (repo2Null)")
    public void testSynchronizeRepo2Null() throws InterruptedException{
        repo1.commit("First commit");
        Thread.sleep(100);
        repo1.commit("Secondcommit");
        String[] finalMessages = new String[]{"0", "1"};
        repo1.synchronize(repo2);
        assertEquals(repo2.getRepoSize(), 0);
        String[] checkMessages = new String[repo1.getRepoSize()];
        int i = repo1.getRepoSize()-1;
        while(repo1.getRepoHead() != null){
            checkMessages[i] = repo1.getRepoHead();
            String id = "" + i;
            repo1.drop(id);
            i--;
        }
        assertArrayEquals(finalMessages, checkMessages);
    }
}
