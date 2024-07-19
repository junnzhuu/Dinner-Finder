package G26.Project.Model.Search;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * This class contains JUnit test cases for the QueryTokenizer class.
 *
 * {@code @Author: Jun Zhu (Original)}
 *           UID : u7602081
 */
@RunWith(JUnit4.class)
public class QueryTokenizerTest {

    /**
     * Test case for the tokenize method of the QueryTokenizer class.
     */
    @Test
    public void testTokenize() {
        // Sample query with various tokens
        String query = "@Los Angeles, $>50, !<4.5, /Italian, #\"The Great Restaurant\"";

        // Tokenize the query
        List<Pair<String, Token>> tokens = QueryTokenizer.tokenize(query);

        // Ensure the correct number of tokens is generated
        assertEquals(16, tokens.size());

        // Check each token and its type
        assertToken(tokens.get(0), "@", Token.CITY);
        assertToken(tokens.get(1), "Los Angeles", Token.CITY);
        assertToken(tokens.get(2), ",", Token.COMMA);
        assertToken(tokens.get(3), "$", Token.COST);
        assertToken(tokens.get(4), ">", Token.GT);
        assertToken(tokens.get(5), "50", Token.NUMBER);
        assertToken(tokens.get(6), ",", Token.COMMA);
        assertToken(tokens.get(7), "!", Token.RATING);
        assertToken(tokens.get(8), "<", Token.LT);
        assertToken(tokens.get(9), "4.5", Token.NUMBER);
        assertToken(tokens.get(10), ",", Token.COMMA);
        assertToken(tokens.get(11), "/", Token.CATEGORY);
        assertToken(tokens.get(12), "Italian", Token.RESTAURANT_TYPE);
        assertToken(tokens.get(13), ",", Token.COMMA);
        assertToken(tokens.get(14), "#", Token.RESTAURANT_NAME);
        assertToken(tokens.get(15), "\"The Great Restaurant\"", Token.RESTAURANT_NAME);
    }

    /**
     * Helper method to assert that a token matches the expected value and type.
     *
     * @param tokenPair     The token pair to check.
     * @param expectedValue The expected value of the token.
     * @param expectedType  The expected type of the token.
     */
    private void assertToken(Pair<String, Token> tokenPair, String expectedValue, Token expectedType) {
        assertEquals(expectedValue, tokenPair.getKey());
        assertEquals(expectedType, tokenPair.getValue());
    }

    /**
     * Test case to debug the tokenize method by printing the tokens.
     */
    @Test
    public void debugTokenize() {
        // Sample query with various tokens
        String query = "@Los Angeles, $>50, !<4.5, /Italian, #\"The Great Restaurant\"";

        // Tokenize the query
        List<Pair<String, Token>> tokens = QueryTokenizer.tokenize(query);

        // Print each token and its type
        tokens.forEach(token -> System.out.println(token.getKey() + " -> " + token.getValue()));
    }
}
