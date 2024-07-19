package G26.Project.Resources;

/**
 * This class contains constants used throughout the application.
 *
 * {@code @Author: Jun Zhu & Jiawei Liu }
 *          UID : u7602081 & u7603069
 */
public class Constants {

    // Constants for Restaurant
    public static final String FIELD_CITY = "city";  // Field name for city in the database

    // Constants for sign-in and registration messages
    public static final String SIGN_IN_LOADING = "Registering...";  // Message displayed during registration
    public static final String SIGN_IN_SUCCESS = "Registration Successful!";  // Successful registration message
    public static final String SIGN_IN_FAILURE = "Registration Failed.";  // Registration failure message

    // Constants for login and authentication
    public static final String LOG_IN_LOADING = "Login...";  // Message displayed during login
    public static final String AUTH_FAILURE = "Authentication Failed.";  // Authentication failure message

    // Tutorial constants
    public static final String TUT_INSTRUCTION = "Instruction";  // Tutorial instruction title
    public static final String TUT_CONFIRM = "OK";  // Confirmation button label

    // Search input constants
    public static final String SEARCH_INPUT_FIND = "Find ";  // Prefix for search results
    public static final String SEARCH_INPUT_RESULT = " results!";  // Suffix for search results
    public static final String SEARCH_INPUT_INVALID = "Invalid Input";  // Invalid input message
    public static final String SEARCH_INPUT_TEXT = "Search Text: ";  // Search input label

    // Database path constants
    public static final String PATH_RESTAURANT = "restaurants";  // Path for restaurant data in the database
    public static final String FIELD_MEAN_RATING = "meanRating";  // Field name for mean rating in the database
    public static final String DATABASE_COST = "cost";  // Database field for restaurant cost
    public static final String DATABASE_MEAN_RATING = "meanRating";  // Database field for restaurant mean rating
    public static final String DATABASE_TYPE = "type";  // Database field for restaurant type
    public static final String DATABASE_COST_INTERVALS = "costIntervals";  // Database field for cost intervals
    public static final String DATABASE_RATING_INTERVALS = "ratingIntervals";  // Database field for rating intervals

    // Broadcast constants
    public static final String BROADCAST_DATA_UPDATE = "restaurant-data-updated";  // Data update broadcast
    public static final String EXTRA_RESTAURANT_UPDATE = "updated_restaurant";  // Extra for updated restaurant data

    // Logout dialog constants
    public static final String LOGOUT_DIALOG_TITLE = "Logout";  // Title for the logout dialog
    public static final String LOGOUT_DIALOG_MESSAGE = "Are you sure you want to logout?";  // Logout confirmation message
    public static final String LOGOUT_DIALOG_CONFIRM = "Yes";  // Confirm button label
    public static final String LOGOUT_DIALOG_CANCEL = "No";  // Cancel button label
    public static final String LOGOUT_SUCCESS = "Logout Successful!";  // Logout success message

    // Database constants
    public static final String DATABASE_USERS = "Users";  // Database path for user data
    public static final String DATABASE_COMMENT_HISTORY = "commentHistory";  // Database path for comment history
    public static final String DATABASE_USER_DATE = "userDate";  // Database field for user registration date

    // Peer-to-peer (P2P) messaging constants
    public static final String P2P_MESSAGE_RECEIVE = "messageReceived";  // P2P message received action
    public static final String P2P_MESSAGE_CONTENT = "messageContent";  // P2P message content
    public static final String P2P_SENDER_ID = "senderUid";  // P2P sender ID
    public static final String P2P_USER_ID = "userUID";  // P2P user ID
    public static final String P2P_USER_NAME = "userName";  // P2P user name
    public static final String P2P_CHAT_MESSAGE = "chatMessages";  // P2P chat messages
    public static final String P2P_MESSAGE = "messages";  // P2P messages
    public static final String P2P_TIMESTAMP = "timestamp";  // P2P message timestamp
    public static final String P2P_ERROR_FETCH_MESSAGE = "Error fetching messages: ";  // Error message for message fetching

    // Search
    public static final String SearchTAG = "RestaurantDetailActivity";  // Search tag for restaurant detail activity
    public static final String SEARCH_BROADCAST_RECEIVER_UPDATE = "updated_restaurant";  // Search broadcast receiver update action
    public static final String SEARCH_RESTAURANT_KEY = "restaurant_key";  // Key for restaurant data in search


    //Chat
    public static final String CHAT_ADAPTER_USER_UID = "userUID";  // User ID for chat adapter
    public static final String CHAT_ADAPTER_USER_NAME = "userName";  // User name for chat adapter
    public static final String FILTER_INPUT_INVALID_TEXT ="Input invalid: Minimum value must not be greater than maximum value.\n";  // Filter input invalid error message

    //Filter
    public static final String FILTER_TEXT = "Filters";  // Filter title
    public static final String FILTER_SHOW_TEXT = "mShowing";  // Filter show text

    //Navigation
    public static final String NAVIGATION_PROFILE_IMAGE_URL = "profileImageUrl";  // Profile image URL in navigation
    public static final String NAVIGATION_DISPLAY_NAME = "displayName";  // Display name in navigation
    public static final String NAVIGATION_SUCCESS_TEXT ="Successfully updating username";  // Successful username update message
    public static final String NAVIGATION_FAIL_TEXT = "Failed updating username";  // Failed username update message

    //Chart
    public static final String CHART_CITY = "Cities";
    public static final String CHART_COST_INTERVALS = "costIntervals";
    public static final String CHART_RESTAURANT_RECORD = "Restaurant Record";
    public static final String CHART_RESTAURANT_COUNT_PER_INTERVAL = "Restaurant Count per Cost Interval";
    public static final String CHART_RESTAURANT_TYPE = "Restaurant Types";
    public static final String CHART_RESTAURANT_NUM = "Restaurant Numbers";
    public static final String CHART_COLOR_ORANGE = "#FFA500";
}
