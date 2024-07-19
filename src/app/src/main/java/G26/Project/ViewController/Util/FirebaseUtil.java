package G26.Project.ViewController.Util;

import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * The `FirebaseUtil` class provides utility methods for Firebase-related operations.
 *  {@code @Author: Jing Li (Original)}
 *            UID : u7533831
 */
public class FirebaseUtil {

    /**
     * Generate a unique chat ID based on user IDs.
     *
     * @param user1 The first user's ID.
     * @param user2 The second user's ID.
     * @return A unique chat ID based on user IDs.
     */
    public static String generateChatId(String user1, String user2) {
        return user1.compareTo(user2) < 0 ? user1 + "_" + user2 : user2 + "_" + user1;
    }

    /**
     * Convert a timestamp to a human-readable date format.
     *
     * @param timestamp The timestamp to convert.
     * @return A human-readable date string.
     */
    public static String convertTimestampToReadableDate(long timestamp) {
        Date date = new Date(timestamp);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
        return sdf.format(date);
    }

    /**
     * Convert a Date to a human-readable date format.
     *
     * @param timestamp The Date to convert.
     * @return A human-readable date string.
     */
    public static String convertTimestampToDayMonYear(Date timestamp) {
        if (timestamp != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
            return sdf.format(timestamp);
        } else {
            return ("Date not available");
        }
    }

    /**
     * Validate email and password fields.
     *
     * @param etEmail    The email input field.
     * @param etPassword The password input field.
     * @return `true` if both email and password are valid, otherwise `false`.
     */
    public static boolean validateEmailAndPassword(EditText etEmail, EditText etPassword) {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (email.isEmpty()) {
            etEmail.setError("Email is required");
            return false;
        }

        if (password.isEmpty()) {
            etPassword.setError("Password is required");
            return false;
        }
        return true;
    }

}
