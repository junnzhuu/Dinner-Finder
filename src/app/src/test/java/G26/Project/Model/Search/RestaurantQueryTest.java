package G26.Project.Model.Search;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import G26.Project.Model.Restaurant.RestaurantType;

/**
 * This class contains unit tests for the RestaurantQuery class.
 *
 * {@code @Author: Jun Zhu (Original)}
 *           UID : u7602081
 */
public class RestaurantQueryTest {

    private RestaurantQuery query;
    private List<String> names;
    private List<String> cities;
    private List<RestaurantType> types;

    /**
     * Set up the test environment before each test case.
     *
     * @throws Exception if there's an issue during setup.
     */
    @Before
    public void setUp() throws Exception {
        query = new RestaurantQuery();

        names = new ArrayList<>(Arrays.asList("McDonald's", "Subway"));
        cities = new ArrayList<>(Arrays.asList("New York", "Los Angeles"));
        types = new ArrayList<>(Arrays.asList(RestaurantType.Italian, RestaurantType.Mexican)); // using the enum types directly

        query.setRestaurantNames(names);
        query.setRestaurantCities(cities);
        query.setTypes(types);
    }

    /**
     * Clean up the test environment after each test case.
     *
     * @throws Exception if there's an issue during cleanup.
     */
    @After
    public void tearDown() throws Exception {
        query = null;
    }

    /**
     * Test case for the getCity() method.
     * It checks if the retrieved cities match the expected values.
     */
    @Test
    public void getCity() {
        assertEquals("Cities do not match.", cities, query.getCity());
    }

    /**
     * Test case for the getCategory() method.
     * It checks if the retrieved categories match the expected values.
     */
    @Test
    public void getCategory() {
        assertEquals("Categories do not match.", types, query.getCategory());
    }

    // ... [Similar test methods for other getters]

    /**
     * Test case for the setRestaurantNames() method.
     * It sets new restaurant names and checks if they were set correctly.
     */
    @Test
    public void setRestaurantNames() {
        List<String> newNames = Arrays.asList("KFC", "Burger King");
        query.setRestaurantNames(newNames);
        assertEquals("Restaurant names were not set correctly.", newNames, query.getRestaurantNames());
    }

    // ... [Similar tests for other setters]

    /**
     * Test case for the toString() method.
     * It checks if the generated string representation matches the expected format.
     */
    @Test
    public void testToString() {
        String expectedString = "RestaurantQuery{" +
                "restaurantNames=" + names +
                ", cities=" + cities +
                ", types=" + types +
                ", costs=" + new ArrayList<AttributeConstraint>() +
                ", meanRatings=" + new ArrayList<AttributeConstraint>() +
                '}';
        assertEquals("toString does not match.", expectedString, query.toString());
    }

    /**
     * Test case for the equals() method.
     * It compares two queries with the same values and checks if they are considered equal.
     */
    @Test
    public void testEquals() {
        RestaurantQuery anotherQuery = new RestaurantQuery();
        anotherQuery.setRestaurantNames(names);
        anotherQuery.setRestaurantCities(cities);
        anotherQuery.setTypes(types);

        assertTrue("Queries should be equal.", query.equals(anotherQuery));
    }

    /**
     * Test case for the hashCode() method.
     * It compares the hash codes of two queries with the same values and checks if they are equal.
     */
    @Test
    public void testHashCode() {
        RestaurantQuery anotherQuery = new RestaurantQuery();
        anotherQuery.setRestaurantNames(names);
        anotherQuery.setRestaurantCities(cities);
        anotherQuery.setTypes(types);

        assertEquals("Hash codes should be equal.", query.hashCode(), anotherQuery.hashCode());
    }
}
