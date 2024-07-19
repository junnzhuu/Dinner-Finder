package G26.Project.Model.Restaurant;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * This class is used to test the functionalities of the {@code Restaurant} class.
 * It covers the basic getter and setter methods, as well as the {@code compareTo} method
 * which determines the order of restaurants based on their cost.
 *
 * {@code @Author: Jun Zhu (Original)}
 *           UID : u7602081
 */
public class RestaurantTest {

    // Instance of Restaurant used in the tests.
    private Restaurant restaurant;

    /**
     * Setup method that initializes the necessary components before each test.
     * Here, we're initializing a new Restaurant object.
     */
    @Before
    public void setUp() throws Exception {
        restaurant = new Restaurant();
    }

    /**
     * Tests the getter and setter for the restaurant name.
     */
    @Test
    public void getAndSetRestaurantName() {
        String name = "Bistro";
        restaurant.setRestaurantName(name);
        assertEquals(name, restaurant.getRestaurantName());
    }

    /**
     * Tests the getter and setter for the restaurant's city.
     */
    @Test
    public void getAndSetRestaurantCity() {
        String city = "New York";
        restaurant.setRestaurantCity(city);
        assertEquals(city, restaurant.getRestaurantCity());
    }

    /**
     * Tests the getter and setter for the restaurant type.
     * Assumes that the RestaurantType enum has a value called ITALIAN.
     */
    @Test
    public void getAndSetType() {
        String type = "Italian";
        restaurant.setType(type);
        assertEquals(RestaurantType.Italian, restaurant.getType());
    }

    /**
     * Tests the getter and setter for the restaurant's image URL.
     */
    @Test
    public void getAndSetImage() {
        String imageUrl = "http://example.com/image.jpg";
        restaurant.setImage(imageUrl);
        assertEquals(imageUrl, restaurant.getImage());
    }

    /**
     * Tests the getter and setter for the restaurant's cost.
     */
    @Test
    public void getAndSetCost() {
        int cost = 150;
        restaurant.setCost(cost);
        assertEquals(cost, restaurant.getCost());
    }

    /**
     * Tests the compareTo method by comparing two restaurants with different costs.
     * Assumes that the Restaurant class implements Comparable based on cost.
     */
    @Test
    public void compareTo() {
        Restaurant restaurantA = new Restaurant();
        Restaurant restaurantB = new Restaurant();

        restaurantA.setCost(100);
        restaurantB.setCost(150);

        assertNotEquals(0, restaurantA.compareTo(restaurantB));
    }

    /**
     * Tests the getter and setter for the restaurant's rating count.
     */
    @Test
    public void getAndSetRatingCount() {
        int ratingCount = 120;
        restaurant.setRatingCount(ratingCount);
        assertEquals(ratingCount, restaurant.getRatingCount());
    }

    /**
     * Tests the getter and setter for the restaurant's mean rating.
     */
    @Test
    public void getAndSetMeanRating() {
        double meanRating = 4.5;
        restaurant.setMeanRating(meanRating);
        assertEquals(meanRating, restaurant.getMeanRating(), 0.001); // Allowing a small delta for double comparison
    }

    /**
     * Tests the getter and setter for the restaurant's ID.
     */
    @Test
    public void getAndSetRestaurantId() {
        String restaurantId = "r12345";
        restaurant.setRestaurantId(restaurantId);
        assertEquals(restaurantId, restaurant.getRestaurantId());
    }

    /**
     * Tests the compareTo method when both restaurants have the same cost.
     */
    @Test
    public void compareToWithEqualCost() {
        Restaurant anotherRestaurant = new Restaurant();

        restaurant.setCost(100);
        anotherRestaurant.setCost(100);

        assertEquals(0, restaurant.compareTo(anotherRestaurant));
    }

    /**
     * Tests the compareTo method when the first restaurant has a higher cost than the second.
     */
    @Test
    public void compareToWithMoreCost() {
        Restaurant anotherRestaurant = new Restaurant();

        restaurant.setCost(200);
        anotherRestaurant.setCost(100);

        assertTrue(restaurant.compareTo(anotherRestaurant) > 0);
    }

    /**
     * Tests the compareTo method when the first restaurant has a lower cost than the second.
     */
    @Test
    public void compareToWithLessCost() {
        Restaurant anotherRestaurant = new Restaurant();

        restaurant.setCost(100);
        anotherRestaurant.setCost(200);

        assertTrue(restaurant.compareTo(anotherRestaurant) < 0);
    }
}
