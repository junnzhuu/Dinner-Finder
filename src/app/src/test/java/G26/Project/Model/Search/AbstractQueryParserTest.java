package G26.Project.Model.Search;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * A test class for AbstractQueryParser.
 * This class validates the behavior of the abstract parser through a concrete subclass (TestParser).
 *
 * {@code @Author: Jun Zhu (Original)}
 *           UID : u7602081
 */
public class AbstractQueryParserTest {

    /**
     * A concrete subclass of AbstractQueryParser to facilitate testing.
     * This subclass simulates the behavior of the abstract class.
     */
    private static class TestParser extends AbstractQueryParser {

        /** A flag indicating whether the doParsing method was called successfully. */
        boolean parsedSuccessfully = false;  // State variable

        /**
         * Constructor for the TestParser.
         *
         * @param tokens A list of pairs representing tokens to be parsed.
         */
        public TestParser(List<Pair<String, Token>> tokens) {
            super(tokens);
        }

        /**
         * Overrides the abstract doParsing method.
         * For this test, it simply sets the parsedSuccessfully flag to true.
         */
        @Override
        protected void doParsing() {
            parsedSuccessfully = true;  // Set state when doParsing is called
        }
    }

    /** An instance of the TestParser to be used in tests. */
    private TestParser testParser;

    /**
     * Set up for the tests.
     * Initializes the testParser with a predefined token list.
     *
     * @throws Exception if any setup operation fails.
     */
    @Before
    public void setUp() throws Exception {
        testParser = new TestParser(Arrays.asList(new Pair<>("unknown", Token.UNKNOWN)));
    }

    /**
     * Clean up after the tests.
     * Currently, it does not perform any cleanup operations.
     *
     * @throws Exception if any cleanup operation fails.
     */
    @After
    public void tearDown() throws Exception {
        // Cleanup operations can be added here if required.
    }

    /**
     * Tests the parse method of the AbstractQueryParser.
     * Verifies that it returns null for the given setup.
     */
    @Test
    public void parse() {
        assertNull(testParser.parse());
    }

    /**
     * Tests the initialize method of the AbstractQueryParser.
     * Currently, no specific behavior is tested for this method.
     */
    @Test
    public void initialize() {
        // No specific tests for initialize as of now.
    }

    /**
     * Tests the doParsing method of the AbstractQueryParser.
     * Verifies that the method sets the parsedSuccessfully flag to true.
     */
    @Test
    public void doParsing() {
        // Before calling doParsing, the parsedSuccessfully flag should be false.
        assertFalse(testParser.parsedSuccessfully);

        // Call doParsing
        testParser.doParsing();

        // After calling doParsing, the parsedSuccessfully flag should be true.
        assertTrue(testParser.parsedSuccessfully);
    }

    /**
     * Tests the finalizeParsing method of the AbstractQueryParser.
     * Verifies that it returns null for the given setup.
     */
    @Test
    public void finalizeParsing() {
        assertNull(testParser.finalizeParsing());
    }
}
