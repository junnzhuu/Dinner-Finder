package G26.Project.Model.P2P;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This class contains unit tests for the Message class.
 *
 * {@code @Author: Jun Zhu (Original)}
 *           UID : u7602081
 */
public class MessageTest {

    private Message message;

    /**
     * Sets up a Message instance with initial values before each test.
     *
     * @throws Exception if there's an issue during setup.
     */
    @Before
    public void setUp() throws Exception {
        // Create a Message instance with predefined values for testing.
        message = new Message("senderId", "receiverId", "Hello, Test!", 1634303452000L);
    }

    /**
     * Cleans up and resets the Message instance after each test.
     *
     * @throws Exception if there's an issue during cleanup.
     */
    @After
    public void tearDown() throws Exception {
        message = null; // Reset the message to null.
    }

    /**
     * Test case for the getMessageContent() method.
     */
    @Test
    public void getMessageContent() {
        // Check if the message content matches the expected value.
        assertEquals("Hello, Test!", message.getMessageContent());
    }

    /**
     * Test case for the setMessageId() method.
     */
    @Test
    public void setMessageId() {
        // Set a new message ID and check if it matches the expected value.
        message.setMessageId("newMessageId");
        assertEquals("newMessageId", message.getMessageId());
    }

    /**
     * Test case for the setSenderUid() method.
     */
    @Test
    public void setSenderUid() {
        // Set a new sender UID and check if it matches the expected value.
        message.setSenderUid("newSenderId");
        assertEquals("newSenderId", message.getSenderUid());
    }

    /**
     * Test case for the setReceiverUid() method.
     */
    @Test
    public void setReceiverUid() {
        // Set a new receiver UID and check if it matches the expected value.
        message.setReceiverUid("newReceiverId");
        assertEquals("newReceiverId", message.getReceiverUid());
    }

    /**
     * Test case for the setMessageContent() method.
     */
    @Test
    public void setMessageContent() {
        // Set new message content and check if it matches the expected value.
        message.setMessageContent("New Content");
        assertEquals("New Content", message.getMessageContent());
    }

    /**
     * Test case for the setTimestamp() method.
     */
    @Test
    public void setTimestamp() {
        // Set a new timestamp and check if it matches the expected value.
        long newTimestamp = 1634303452999L;
        message.setTimestamp(newTimestamp);
        assertEquals(newTimestamp, message.getTimestamp());
    }

    /**
     * Test case for the getMessageId() method when the message ID is not set.
     */
    @Test
    public void getMessageId() {
        // Check if the message ID is null since it's not initialized.
        assertNull(message.getMessageId());
    }

    /**
     * Test case for the getSenderUid() method.
     */
    @Test
    public void getSenderUid() {
        // Check if the sender UID matches the expected value.
        assertEquals("senderId", message.getSenderUid());
    }

    /**
     * Test case for the getReceiverUid() method.
     */
    @Test
    public void getReceiverUid() {
        // Check if the receiver UID matches the expected value.
        assertEquals("receiverId", message.getReceiverUid());
    }

    /**
     * Test case for the getTimestamp() method.
     */
    @Test
    public void getTimestamp() {
        // Check if the timestamp matches the expected value.
        assertEquals(1634303452000L, message.getTimestamp());
    }
}
