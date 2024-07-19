package G26.Project.Model.Search;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import G26.Project.Model.Restaurant.RestaurantType;

/**
 * This class tests the functionalities of the {@code BasicQueryParser} class.
 * The parser aims to transform a list of tokens into a structured {@code RestaurantQuery} object.
 *
 * {@code @Author: Jun Zhu (Original)}
 *           UID : u7602081
 */
public class BasicQueryParserTest {
    // List of tokens that the parser will use as input
    private List<Pair<String, Token>> tokens;

    /**
     * Setup method called before each test to initialize necessary components.
     */
    @Before
    public void setUp() {
        tokens = new ArrayList<>();
        // Tokens list can be initialized here or in each specific test method.
    }

    /**
     * Tests basic parsing functionalities using a simple query.
     */
    @Test
    public void testBasicParsing() {
        // Populate tokens with simple city and restaurant name query
        tokens.add(new Pair<>("@", Token.CITY));
        tokens.add(new Pair<>("==", Token.EQUAL));
        tokens.add(new Pair<>("Los Angeles", Token.CITY));

        tokens.add(new Pair<>(",", Token.COMMA));

        tokens.add(new Pair<>("#", Token.RESTAURANT_NAME));
        tokens.add(new Pair<>("==", Token.EQUAL));
        tokens.add(new Pair<>("The Great Restaurant", Token.RESTAURANT_NAME));

        // Initialize parser and parse tokens
        BasicQueryParser parser = new BasicQueryParser(tokens);
        RestaurantQuery result = (RestaurantQuery) parser.parse();

        // Assert that parsed RestaurantQuery object has expected values
        assertEquals(Collections.singletonList("Los Angeles"), result.getCity());
        assertEquals(Collections.singletonList("The Great Restaurant"), result.getRestaurantNames());
    }

    /**
     * Tests parsing of multiple city values.
     */
    @Test
    public void testMultipleCitiesParsing() {
        // Populate tokens with query of multiple cities
        tokens.add(new Pair<>("@", Token.CITY));
        tokens.add(new Pair<>("==", Token.EQUAL));
        tokens.add(new Pair<>("Los Angeles", Token.CITY));

        tokens.add(new Pair<>(",", Token.COMMA));

        tokens.add(new Pair<>("@", Token.CITY));
        tokens.add(new Pair<>("==", Token.EQUAL));
        tokens.add(new Pair<>("Sydney", Token.CITY));

        // Initialize parser and parse tokens
        BasicQueryParser parser = new BasicQueryParser(tokens);
        RestaurantQuery result = (RestaurantQuery) parser.parse();

        // Assert that parsed RestaurantQuery object contains both cities
        List<String> expectedCities = Arrays.asList("Los Angeles", "Sydney");
        assertEquals(expectedCities, result.getCity());
    }

    /**
     * Tests parsing of multiple restaurant names.
     */
    @Test
    public void testMultipleRestaurantNamesParsing() {
        // Tokenize a given query with multiple restaurant names
        String inputQuery = "# == \"Burger King\", # == \"McDonald's\", # == \"Subway\"";
        List<Pair<String, Token>> tokens = QueryTokenizer.tokenize(inputQuery);

        // Initialize parser and parse tokens
        BasicQueryParser parser = new BasicQueryParser(tokens);
        RestaurantQuery query = (RestaurantQuery) parser.parse();

        // Assert that parsed RestaurantQuery object has the expected restaurant names
        List<String> expectedNames = Arrays.asList("Burger King", "McDonald's", "Subway");
        assertEquals(expectedNames, query.getRestaurantNames());
    }

    /**
     * Tests parsing of a query with mixed attributes.
     */
    @Test
    public void testMixedAttributesParsing() {
        // Tokenize a query with mixed attributes such as city, cost, rating, and type
        String inputQuery = "@ == Los Angeles, $ > 20, ! < 4.5, / == Italian";
        List<Pair<String, Token>> tokens = QueryTokenizer.tokenize(inputQuery);

        // Initialize parser and parse tokens
        BasicQueryParser parser = new BasicQueryParser(tokens);
        RestaurantQuery query = (RestaurantQuery) parser.parse();

        // Define expected parsed values for each attribute
        List<String> expectedCities = Arrays.asList("Los Angeles");
        List<AttributeConstraint> expectedCost = Arrays.asList(new AttributeConstraint(">", 20));
        List<AttributeConstraint> expectedRating = Arrays.asList(new AttributeConstraint("<", 4.5));
        List<RestaurantType> expectedType = Arrays.asList(RestaurantType.Italian);

        // Assert that parsed RestaurantQuery object has expected values for each attribute
        assertEquals(expectedCities, query.getCity());
        assertEquals(expectedCost, query.getCost());
        assertEquals(expectedRating, query.getRating());
        assertEquals(expectedType, query.getCategory());
    }
}
