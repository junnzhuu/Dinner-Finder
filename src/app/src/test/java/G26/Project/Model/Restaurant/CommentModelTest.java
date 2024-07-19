package G26.Project.Model.Restaurant;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * A test class for the CommentModel. This class tests the functionality
 * and behaviors of the CommentModel to ensure its correct operation.
 *
 * {@code @Author: Jun Zhu (Original)}
 *           UID : u7602081
 */
public class CommentModelTest {

    // Instance variable for the comment model used in tests.
    private CommentModel commentModel;

    /**
     * Sets up the test environment before each test method is executed.
     * Initializes a sample CommentModel instance with predefined values.
     *
     * @throws Exception if any error occurs during setup.
     */
    @Before
    public void setUp() throws Exception {
        commentModel = new CommentModel("RestaurantName", "RestaurantType", "RestaurantCity", "UserName", 123456789, 4.5f, "UserText");
    }

    /**
     * Cleans up the test environment after each test method completes.
     * Sets the commentModel instance to null to release resources.
     *
     * @throws Exception if any error occurs during teardown.
     */
    @After
    public void tearDown() throws Exception {
        commentModel = null;
    }

    /**
     * Tests the constructor of CommentModel which takes all parameters.
     * Ensures that the created instance has the expected values.
     */
    @Test
    public void testCommentModelWithAllParameters() {
        assertEquals("RestaurantName", commentModel.getRestaurantName());
        assertEquals("RestaurantType", commentModel.getRestaurantType());
        assertEquals("RestaurantCity", commentModel.getRestaurantCity());
        assertEquals("UserName", commentModel.getUserName());
        assertEquals(123456789, commentModel.getUserDate());
        // Add more assertions for other fields if available in Rating class.
    }

    /**
     * Tests the getter method for restaurant name.
     */
    @Test
    public void getRestaurantName() {
        assertEquals("RestaurantName", commentModel.getRestaurantName());
    }

    /**
     * Tests the setter method for restaurant name.
     */
    @Test
    public void setRestaurantName() {
        commentModel.setRestaurantName("NewName");
        assertEquals("NewName", commentModel.getRestaurantName());
    }

    /**
     * Tests the getter method for restaurant type.
     */
    @Test
    public void getRestaurantType() {
        assertEquals("RestaurantType", commentModel.getRestaurantType());
    }

    /**
     * Tests the setter method for restaurant type.
     */
    @Test
    public void setRestaurantType() {
        commentModel.setRestaurantType("NewType");
        assertEquals("NewType", commentModel.getRestaurantType());
    }

    /**
     * Tests the getter method for restaurant city.
     */
    @Test
    public void getRestaurantCity() {
        assertEquals("RestaurantCity", commentModel.getRestaurantCity());
    }

    /**
     * Tests the setter method for restaurant city.
     */
    @Test
    public void setRestaurantCity() {
        commentModel.setRestaurantCity("NewCity");
        assertEquals("NewCity", commentModel.getRestaurantCity());
    }

    /**
     * Tests the getter method for user name.
     */
    @Test
    public void getUserName() {
        assertEquals("UserName", commentModel.getUserName());
    }

    /**
     * Tests the setter method for user name.
     */
    @Test
    public void setUserName() {
        commentModel.setUserName("NewUserName");
        assertEquals("NewUserName", commentModel.getUserName());
    }

    /**
     * Tests the getter method for user date.
     */
    @Test
    public void getUserDate() {
        assertEquals(123456789, commentModel.getUserDate());
    }

    /**
     * Tests the setter method for user date.
     */
    @Test
    public void setUserDate() {
        commentModel.setUserDate(987654321);
        assertEquals(987654321, commentModel.getUserDate());
    }

    // Continue with other getter and setter tests for fields from Rating class

    /**
     * Tests the behavior of CommentModel when initialized with null values.
     * Ensures that the model correctly stores and retrieves null values.
     */
    @Test
    public void testNullValues() {
        CommentModel model = new CommentModel(null, null, null, null, 0, 0, null);
        assertNull(model.getRestaurantName());
        assertNull(model.getRestaurantType());
        assertNull(model.getRestaurantCity());
        assertNull(model.getUserName());
        // Add more assertions for other fields.
    }
}