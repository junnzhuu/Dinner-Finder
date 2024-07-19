package G26.Project.Model.Restaurant;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import G26.Project.Model.Restaurant.Rating;

/**
 * This class contains JUnit test cases for the Rating class.
 *
 * {@code @Author: Jun Zhu (Original)}
 *           UID : u7602081
 */
public class RatingTest {

    private Rating rating;

    /**
     * Initializes a new Rating object before each test.
     *
     * @throws Exception if there is an issue with setup.
     */
    @Before
    public void setUp() throws Exception {
        rating = new Rating();
    }

    /**
     * Cleans up resources after each test by setting the rating object to null.
     *
     * @throws Exception if there is an issue with teardown.
     */
    @After
    public void tearDown() throws Exception {
        rating = null;
    }

    /**
     * Test case for getting and setting the user ID in the Rating object.
     */
    @Test
    public void getUserIdAndSetUserId() {
        String userId = "user123";
        rating.setUserId(userId);
        assertEquals(userId, rating.getUserId());
    }

    /**
     * Test case for getting and setting the user name in the Rating object.
     */
    @Test
    public void getUserNameAndSetUserName() {
        String userName = "testUser";
        rating.setUserName(userName);
        assertEquals(userName, rating.getUserName());
    }

    /**
     * Test case for getting and setting the rating value in the Rating object.
     */
    @Test
    public void getRatingAndSetRating() {
        double userRating = 4.5;
        rating.setRating(userRating);
        // Delta is used for floating-point comparisons to allow for a small margin of error.
        assertEquals(userRating, rating.getRating(), 0.001);
    }

    /**
     * Test case for getting and setting the review text in the Rating object.
     */
    @Test
    public void getTextAndSetText() {
        String text = "This is a test review.";
        rating.setText(text);
        assertEquals(text, rating.getText());
    }

    /**
     * Test case for getting and setting the timestamp in the Rating object.
     */
    @Test
    public void getTimestampAndSetTimestamp() {
        java.util.Date now = new java.util.Date();
        rating.setTimestamp(now);
        assertEquals(now, rating.getTimestamp());
    }
}
