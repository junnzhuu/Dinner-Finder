package G26.Project.Model.Restaurant;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * A test class for the RestaurantType enum.
 * This class validates the behavior of various operations on the RestaurantType enum.
 *
 * {@code @Author: Jun Zhu (Original)}
 *           UID : u7602081
 */
public class RestaurantTypeTest {

    /**
     * This method is executed before every test method.
     * Currently, it does not perform any setup operations.
     *
     * @throws Exception if any setup operation fails.
     */
    @Before
    public void setUp() throws Exception {
        // Initialization or setup operations can be added here if required.
    }

    /**
     * This method is executed after every test method.
     * Currently, it does not perform any teardown operations.
     *
     * @throws Exception if any teardown operation fails.
     */
    @After
    public void tearDown() throws Exception {
        // Cleanup operations can be added here if required.
    }

    /**
     * Tests the fromString method of RestaurantType with valid inputs.
     * It validates if the method correctly maps a string to its corresponding enum.
     */
    @Test
    public void testFromString_ValidInput_ShouldReturnCorrectEnum() {
        assertEquals(RestaurantType.Italian, RestaurantType.fromString("Italian"));
        assertEquals(RestaurantType.Chinese, RestaurantType.fromString("Chinese"));
        // Add more assertions for other enum values if necessary.
    }

    /**
     * Tests the fromString method of RestaurantType with invalid inputs.
     * It expects an IllegalArgumentException to be thrown.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testFromString_InvalidInput_ShouldThrowException() {
        RestaurantType.fromString("InvalidType");
    }

    /**
     * Tests if the values method returns all the correct enum values.
     */
    @Test
    public void testValues_ShouldReturnAllEnumValues() {
        RestaurantType[] expectedTypes = {
                RestaurantType.Italian, RestaurantType.Chinese,
                RestaurantType.Indian, RestaurantType.Mexican,
                RestaurantType.Thai, RestaurantType.Mediterranean,
                RestaurantType.French, RestaurantType.Japanese,
                RestaurantType.Korean
        };
        assertArrayEquals(expectedTypes, RestaurantType.values());
    }

    /**
     * Tests the valueOf method with valid inputs.
     * It validates if the method correctly maps a string to its corresponding enum.
     */
    @Test
    public void testValueOf_ValidInput_ShouldReturnCorrectEnum() {
        assertEquals(RestaurantType.Italian, RestaurantType.valueOf("Italian"));
        assertEquals(RestaurantType.Chinese, RestaurantType.valueOf("Chinese"));
        // Add more assertions for other enum values if necessary.
    }

    /**
     * Tests the valueOf method with invalid inputs.
     * It expects an IllegalArgumentException to be thrown.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testValueOf_InvalidInput_ShouldThrowException() {
        RestaurantType.valueOf("InvalidType");
    }

    /**
     * Tests if the toString method of the enum correctly returns the name of the enum.
     */
    @Test
    public void testToString_ShouldReturnCorrectString() {
        assertEquals("Italian", RestaurantType.Italian.toString());
        assertEquals("Chinese", RestaurantType.Chinese.toString());
        // Add more assertions for other enum values if necessary.
    }

    /**
     * Tests if the name method of the enum correctly returns the name of the enum.
     */
    @Test
    public void testName_ShouldReturnCorrectName() {
        assertEquals("Italian", RestaurantType.Italian.name());
        assertEquals("Chinese", RestaurantType.Chinese.name());
        // Add more assertions for other enum values if necessary.
    }

    /**
     * Tests the ordinal method to ensure it returns the correct ordinal value for each enum.
     */
    @Test
    public void testOrdinal_ShouldReturnCorrectOrdinal() {
        assertEquals(0, RestaurantType.Italian.ordinal());
        assertEquals(1, RestaurantType.Chinese.ordinal());
        // Add more assertions for other enum values if necessary.
    }

    /**
     * Tests the compareTo method to ensure it correctly compares two enums.
     */
    @Test
    public void testCompareTo_ShouldReturnCorrectComparison() {
        assertTrue(RestaurantType.Italian.compareTo(RestaurantType.Chinese) < 0);
        assertTrue(RestaurantType.Chinese.compareTo(RestaurantType.Italian) > 0);
        assertEquals(0, RestaurantType.Italian.compareTo(RestaurantType.Italian));
    }

    /**
     * Tests the getDeclaringClass method to ensure it returns the correct class object.
     */
    @Test
    public void testGetDeclaringClass_ShouldReturnEnumType() {
        assertEquals(RestaurantType.class, RestaurantType.Italian.getDeclaringClass());
    }
}
