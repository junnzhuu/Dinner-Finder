package G26.Project.Model.P2P;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This class contains unit tests for the Chat class.
 * It focuses on testing the getter and setter methods of the class.
 * Each method in this test class is designed to test one specific getter and setter pair.
 *
 * {@code @Author: Jun Zhu (Original)}
 *           UID : u7602081
 */
public class ChatTest {

    // Chat object that will be used for testing
    private Chat chat;

    /**
     * This method initializes the resources needed for the test.
     * It is run before each test method.
     *
     * @throws Exception if any setup operation fails.
     */
    @Before
    public void setUp() throws Exception {
        chat = new Chat();
    }

    /**
     * This method cleans up the resources after each test has been executed.
     * It is run after each test method.
     *
     * @throws Exception if any cleanup operation fails.
     */
    @After
    public void tearDown() throws Exception {
        chat = null;
    }

    /**
     * This method tests the getter and setter methods for the username property of the Chat class.
     */
    @Test
    public void getAndSetUserName() {
        String userName = "TestUser";
        chat.setUserName(userName);
        assertEquals("Expected and actual usernames should be the same", userName, chat.getUserName());
    }

    /**
     * This method tests the getter and setter methods for the last message property of the Chat class.
     */
    @Test
    public void getAndSetLastMessage() {
        String lastMessage = "TestLastMessage";
        chat.setLastMessage(lastMessage);
        assertEquals("Expected and actual last messages should be the same", lastMessage, chat.getLastMessage());
    }

    /**
     * This method tests the getter and setter methods for the timestamp property of the Chat class.
     */
    @Test
    public void getAndSetTimestamp() {
        String timestamp = "TestTimestamp";
        chat.setTimestamp(timestamp);
        assertEquals("Expected and actual timestamps should be the same", timestamp, chat.getTimestamp());
    }

    /**
     * This method tests the getter and setter methods for the target user UID property of the Chat class.
     */
    @Test
    public void getAndSetTargetUserUid() {
        String targetUserUid = "TestTargetUserUid";
        chat.setTargetUserUid(targetUserUid);
        assertEquals("Expected and actual target user UIDs should be the same", targetUserUid, chat.getTargetUserUid());
    }
}
