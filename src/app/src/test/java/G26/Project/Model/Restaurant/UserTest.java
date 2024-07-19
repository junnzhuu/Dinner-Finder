package G26.Project.Model.Restaurant;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import G26.Project.Model.P2P.Message;

/**
 * This class contains unit tests for the User class.
 *
 * {@code @Author: Jun Zhu (Original)}
 *           UID : u7602081
 */
public class UserTest {
    private User user;
    private static final String UID = "12345";
    private static final String EMAIL = "test@example.com";
    private static final String DEFAULT_PROFILE_AVATAR = "https://firebasestorage.googleapis.com/v0/b/gourmet-restaurant-search.appspot.com/o/user_profile%2Fdefault.jpeg?alt=media&token=4ec75769-2255-4ddb-ab2e-ffed85ef6834&_gl=1*lkgre3*_ga*MTc0NTc4OTM2NS4xNjk2NDc4MDMz*_ga_CW55HF8NVT*MTY5NzI5NDEzNC4xNC4xLjE2OTcyOTQ5MDcuNjAuMC4w";

    /**
     * Sets up a User instance with initial values before each test.
     *
     * @throws Exception if there's an issue during setup.
     */
    @Before
    public void setUp() throws Exception {
        user = new User(UID, EMAIL);
    }

    /**
     * Cleans up and resets the User instance after each test.
     *
     * @throws Exception if there's an issue during cleanup.
     */
    @After
    public void tearDown() throws Exception {
        user = null; // Reset the user to null.
    }

    /**
     * Test case for the getUid() method. It checks if the UID matches the expected value.
     */
    @Test
    public void getUid() {
        assertEquals("Failed to get UID", UID, user.getUid());
    }

    /**
     * Test case for the getEmail() method. It checks if the email matches the expected value.
     */
    @Test
    public void getEmail() {
        assertEquals("Failed to get Email", EMAIL, user.getEmail());
    }

    /**
     * Test case for adding a message to the user's messages. It creates a message, adds it to the user,
     * and checks if the added message is present in the user's message list.
     */
    @Test
    public void addMessage() {
        Message msg = new Message(); // Assuming Message has a default constructor.
        user.addMessage(msg);

        // Assuming Message class overrides equals, if not, compare relevant properties.
        assertEquals("Failed to add message", msg, user.getMessages().get(0));
    }

    /**
     * Test case for the default constructor of User. It checks if the default constructor sets UID and Email to null
     * and if the default profile avatar URL matches the expected default URL.
     */
    @Test
    public void defaultConstructorTest() {
        User defaultUser = new User();
        assertNull("UID should be null in default constructor", defaultUser.getUid());
        assertNull("Email should be null in default constructor", defaultUser.getEmail());
        assertEquals("Default profile avatar URL mismatch", DEFAULT_PROFILE_AVATAR, defaultUser.getProfileAvatar());
    }

    /**
     * Test case for setting and getting the display name of the user. It sets a display name and checks if it can be retrieved correctly.
     */
    @Test
    public void displayNameTest() {
        String name = "Test Name";
        user.setDisplayName(name);
        assertEquals("Failed to set and get display name", name, user.getDisplayName());
    }

    /**
     * Test case for setting and getting the profile avatar URL of the user. It sets a new avatar URL and checks if it can be retrieved correctly.
     */
    @Test
    public void profileAvatarTest() {
        String newAvatar = "https://example.com/newavatar.jpg";
        user.setProfileAvatar(newAvatar);
        assertEquals("Failed to set and get profile avatar", newAvatar, user.getProfileAvatar());
    }

    /**
     * Test case for adding multiple messages to the user's messages. It adds multiple messages to the user's message list
     * and checks if all added messages are present in the list.
     */
    @Test
    public void addMultipleMessagesTest() {
        Message msg1 = new Message();
        Message msg2 = new Message();
        user.addMessage(msg1);
        user.addMessage(msg2);
        assertTrue("Failed to add multiple messages", user.getMessages().contains(msg1) && user.getMessages().contains(msg2));
    }
}
