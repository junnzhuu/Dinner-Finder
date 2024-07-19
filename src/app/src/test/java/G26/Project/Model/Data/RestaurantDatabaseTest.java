package G26.Project.Model.Data;

import static org.junit.Assert.*;


import static G26.Project.Resources.Constants.DATABASE_COST;
import static G26.Project.Resources.Constants.DATABASE_TYPE;
import static G26.Project.Resources.Constants.FIELD_CITY;
import static G26.Project.Resources.Constants.FIELD_MEAN_RATING;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import G26.Project.Model.Restaurant.Restaurant;
import G26.Project.Model.Restaurant.RestaurantType;
import G26.Project.Model.Search.AttributeConstraint;
import G26.Project.Model.Search.RestaurantQuery;
import G26.Project.Model.Search.RestaurantSearchFacade;

/**
 * A test class for the RestaurantDatabase. This class tests the functionality
 * and behaviors of the RestaurantDatabase to ensure its correct operation.
 *
 * {@code @Author: Jun Zhu (Original)}
 *           UID : u7602081
 */
public class RestaurantDatabaseTest {

    // Instance variable to store the facade for restaurant searches.
    private RestaurantSearchFacade searchFacade;

    @Before
    public void setUp() {
        // Ensures a fresh RestaurantDatabase instance before each test.
        RestaurantDatabase.resetInstance();
    }

    @After
    public void tearDown() {
        // Cleanup after each test run.
        RestaurantDatabase.resetInstance();
    }

    /**
     * Test case for ensuring that the singleton pattern is correctly implemented
     * in the RestaurantDatabase.
     */
    @Test
    public void getInstance() {
        RestaurantDatabase instance1 = RestaurantDatabase.getInstance();
        RestaurantDatabase instance2 = RestaurantDatabase.getInstance();

        // Asserting that both instances obtained are the same, ensuring the singleton pattern.
        assertSame(instance1, instance2);
    }

    /**
     * Test case for executing a query against the database and
     * ensuring that the expected results are returned.
     */
    @Test
    public void executeQuery() {
        RestaurantDatabase db = RestaurantDatabase.getInstance();
        Restaurant restaurant1 = new Restaurant("TestRestaurant1", "TestCity1", "ITALIAN",
                "testImage", 100, 0, 0.0);
        Restaurant restaurant2 = new Restaurant("TestRestaurant2", "TestCity2", "CHINESE",
                "testImage", 150, 0, 0.0);

        db.insertRestaurant(restaurant1);
        db.insertRestaurant(restaurant2);

        RestaurantQuery query = new RestaurantQuery();
        query.setTypes(Collections.singletonList(RestaurantType.Italian));

        // This block sets a private field via reflection which is not recommended for production use.
        // Reflection is used here for the purpose of testing.
        try {
            Field costsField = RestaurantQuery.class.getDeclaredField("costs");
            costsField.setAccessible(true);
            costsField.set(query, Collections.singletonList(new AttributeConstraint("==", 100)));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            fail("Failed to set costs via reflection: " + e.getMessage());
        }

        List<Restaurant> searchResults = db.executeQuery(query);

        assertEquals(1, searchResults.size());
        assertEquals(restaurant1, searchResults.get(0));
    }

    /**
     * Test case to ensure the database returns an empty list when
     * the query doesn't match any restaurant.
     */
    @Test
    public void queryNoMatches() {
        RestaurantDatabase db = RestaurantDatabase.getInstance();
        Restaurant restaurant = new Restaurant("TestRestaurant", "TestCity", "ITALIAN",
                "testImage", 100, 0, 0.0);

        db.insertRestaurant(restaurant);

        RestaurantQuery query = new RestaurantQuery();
        query.setTypes(Collections.singletonList(RestaurantType.Chinese)); // Different type

        List<Restaurant> searchResults = db.executeQuery(query);

        assertTrue(searchResults.isEmpty());
    }

    /**
     * Test case for inserting a restaurant and ensuring that
     * the restaurant is correctly inserted into the database.
     */
    @Test
    public void insertRestaurant() {
        RestaurantDatabase db = RestaurantDatabase.getInstance();
        Restaurant restaurant = new Restaurant("TestRestaurant", "TestCity", "ITALIAN",
                "testImage", 100, 0, 0.0);

        db.insertRestaurant(restaurant);
        List<Restaurant> allRestaurants = db.toList();

        assertTrue(allRestaurants.contains(restaurant));
    }

    /**
     * Test case for updating a restaurant in the database.
     * This test checks if the old restaurant record still exists after an update.
     */
    @Test
    public void updateRestaurant() {
        RestaurantDatabase db = RestaurantDatabase.getInstance();
        Restaurant restaurant = new Restaurant("TestRestaurant", "TestCity", "ITALIAN",
                "testImage", 100, 0, 0.0);
        db.insertRestaurant(restaurant);

        Restaurant updatedRestaurant = new Restaurant("UpdatedTestRestaurant", "TestCity", "ITALIAN",
                "testImage", 120, 0, 0.0);
        db.updateRestaurant(updatedRestaurant);

        List<Restaurant> allRestaurants = db.toList();

        assertTrue(allRestaurants.contains(restaurant));
        assertFalse(allRestaurants.contains(updatedRestaurant));
    }


    /**
     * Test case for performing a search with multiple conditions.
     * This test ensures that the database returns correct results
     * when multiple conditions are specified in a query.
     */
    @Test
    public void multipleConditionsSearch() {
        RestaurantDatabase db = RestaurantDatabase.getInstance();

        // Insert multiple restaurants
        Restaurant restaurant1 = new Restaurant("TestRestaurant1", "TestCity1", "ITALIAN",
                "testImage", 100, 0, 0.0);
        Restaurant restaurant2 = new Restaurant("TestRestaurant2", "TestCity2", "ITALIAN",
                "testImage", 150, 0, 0.0);

        db.insertRestaurant(restaurant1);
        db.insertRestaurant(restaurant2);

        RestaurantQuery query = new RestaurantQuery();
        query.setTypes(Collections.singletonList(RestaurantType.Italian));
        query.setCity(Collections.singletonList("TestCity1"));

        List<Restaurant> searchResults = db.executeQuery(query);

        assertEquals(1, searchResults.size());
        assertTrue(searchResults.contains(restaurant1));
        assertFalse(searchResults.contains(restaurant2));
    }

    /**
     * Test case for converting the database's contents into a list.
     */
    @Test
    public void toListTest() {
        RestaurantDatabase db = RestaurantDatabase.getInstance();

        assertTrue(db.toList().isEmpty()); // initially empty

        Restaurant restaurant = new Restaurant("TestRestaurant", "TestCity", "ITALIAN",
                "testImage", 100, 0, 0.0);
        db.insertRestaurant(restaurant);

        assertEquals(1, db.toList().size());
        assertTrue(db.toList().contains(restaurant));
    }

    /**
     * Test case for extracting certain fields of data from the database.
     */
    @Test
    public void extractDataTest() {
        RestaurantDatabase db = RestaurantDatabase.getInstance();

        Restaurant restaurant = new Restaurant("TestRestaurant", "TestCity", "ITALIAN",
                "testImage", 100, 5, 3.5);
        db.insertRestaurant(restaurant);

        Map<String, List<Object>> dataMap = db.extractData();

        assertTrue(dataMap.get(DATABASE_COST).contains(100));
        assertTrue(dataMap.get(FIELD_MEAN_RATING).contains(3.5));
        assertTrue(dataMap.get(FIELD_CITY).contains("TestCity"));
        assertTrue(dataMap.get(DATABASE_TYPE).contains(RestaurantType.Italian));
    }
}