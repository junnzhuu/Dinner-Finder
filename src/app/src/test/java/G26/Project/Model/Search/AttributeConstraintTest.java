package G26.Project.Model.Search;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This class tests the functionalities of the {@code AttributeConstraint} class.
 * The {@code AttributeConstraint} class represents a constraint on a specific attribute
 * using a certain operator (like "<", ">", "=") and a value.
 *
 * {@code @Author: Jun Zhu (Original)}
 *           UID : u7602081
 */
public class AttributeConstraintTest {

    // The instance of AttributeConstraint that will be tested.
    private AttributeConstraint attributeConstraint;

    // Test data to initialize the AttributeConstraint object.
    private String testOperator = ">";
    private double testValue = 1.0;

    /**
     * Setup method to initialize necessary components before each test.
     * In this case, it initializes the AttributeConstraint with test data.
     */
    @Before
    public void setUp() throws Exception {
        attributeConstraint = new AttributeConstraint(testOperator, testValue);
    }

    /**
     * Cleanup method to run after each test.
     * It sets the attributeConstraint to null to free up resources.
     */
    @After
    public void tearDown() throws Exception {
        attributeConstraint = null;
    }

    /**
     * Tests the getter method for the operator in the AttributeConstraint.
     */
    @Test
    public void getOperator() {
        assertEquals(testOperator, attributeConstraint.getOperator());
    }

    /**
     * Tests the getter method for the value in the AttributeConstraint.
     */
    @Test
    public void getValue() {
        assertEquals(testValue, attributeConstraint.getValue(), 0.0);
    }

    /**
     * Tests the equals method of the AttributeConstraint class.
     * This method checks:
     * - Identity comparison
     * - Null value comparison
     * - Different class comparison
     * - Logical equality comparison
     * - Logical inequality comparison
     */
    @Test
    public void testEquals() {
        // Tests that the same object is equal to itself.
        assertTrue(attributeConstraint.equals(attributeConstraint));

        // Tests that the object is not equal to null.
        assertFalse(attributeConstraint.equals(null));

        // Tests that the object is not equal to an instance of a different class.
        assertFalse(attributeConstraint.equals("some string"));

        // Tests that two logically equal AttributeConstraint objects are considered equal.
        AttributeConstraint equalAttributeConstraint = new AttributeConstraint(testOperator, testValue);
        assertTrue(attributeConstraint.equals(equalAttributeConstraint));

        // Tests that two logically different AttributeConstraint objects are considered not equal.
        AttributeConstraint differentAttributeConstraint = new AttributeConstraint("<", 2.0);
        assertFalse(attributeConstraint.equals(differentAttributeConstraint));
    }

    /**
     * Tests the hashCode method of the AttributeConstraint class.
     * Ensures that:
     * - Two logically equal AttributeConstraint objects return the same hashcode.
     * - Two logically different AttributeConstraint objects return different hashcodes.
     */
    @Test
    public void testHashCode() {
        // Tests that two logically equal AttributeConstraint objects return the same hashcode.
        AttributeConstraint equalAttributeConstraint = new AttributeConstraint(testOperator, testValue);
        assertEquals(attributeConstraint.hashCode(), equalAttributeConstraint.hashCode());

        // Tests that two logically different AttributeConstraint objects return different hashcodes.
        AttributeConstraint differentAttributeConstraint = new AttributeConstraint("<", 2.0);
        assertNotEquals(attributeConstraint.hashCode(), differentAttributeConstraint.hashCode());
    }

    /**
     * Tests the toString method of the AttributeConstraint class.
     * This method ensures that the returned string representation of the object is as expected.
     */
    @Test
    public void testToString() {
        String expectedToString = "AttributeConstraint{" +
                "operator='" + testOperator + '\'' +
                ", value=" + testValue +
                '}';
        assertEquals(expectedToString, attributeConstraint.toString());
    }
}
