package G26.Project.Model.Search;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This class contains JUnit test cases for the Pair class.
 *
 * {@code @Author: Jun Zhu (Original)}
 *           UID : u7602081
 */
public class PairTest {

    private Pair<String, Integer> pair;
    private String testKey = "TestKey";
    private Integer testValue = 123;

    /**
     * Initializes a new Pair object with test key and value before each test.
     *
     * @throws Exception if there is an issue with setup.
     */
    @Before
    public void setUp() throws Exception {
        pair = new Pair<>(testKey, testValue);
    }

    /**
     * Cleans up resources after each test by setting the pair object to null.
     *
     * @throws Exception if there is an issue with teardown.
     */
    @After
    public void tearDown() throws Exception {
        pair = null;
    }

    /**
     * Test case for getting the key of the Pair.
     */
    @Test
    public void getKey() {
        assertEquals("The key is incorrect.", testKey, pair.getKey());
    }

    /**
     * Test case for getting the value of the Pair.
     */
    @Test
    public void getValue() {
        assertEquals("The value is incorrect.", testValue, pair.getValue());
    }

    /**
     * Test case for checking the toString method of the Pair class.
     */
    @Test
    public void testToString() {
        String expectedString = "<" + testKey + ", " + testValue + ">";
        assertEquals("The toString method does not return the expected string.", expectedString, pair.toString());
    }
}
