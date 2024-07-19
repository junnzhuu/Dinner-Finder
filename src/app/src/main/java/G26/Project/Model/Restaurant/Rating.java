package G26.Project.Model.Restaurant;

import android.text.TextUtils;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.ServerTimestamp;
import java.util.Date;
/**
 * The `Rating` class represents a user's rating and review for a restaurant.
 * <p>
 * {@code @Author: Jing Li (Original)}
 *           UID : u7533831
 */
public class Rating {
    protected String userId;
    protected String userName;
    protected double rating;
    protected String text;
    protected @ServerTimestamp Date timestamp;
    public Rating() {}

    /**
     * Constructor for creating a `Rating` object with Firebase user information, rating, and text.
     *
     * @param user   The Firebase user who submitted the rating.
     * @param rating The numeric rating given by the user.
     * @param text   The text review provided by the user.
     */
    public Rating(FirebaseUser user, double rating, String text) {
        this.userId = user.getUid();
        this.userName = user.getDisplayName();
        if (TextUtils.isEmpty(this.userName)) {
            this.userName = user.getEmail();
        }

        this.rating = rating;
        this.text = text;
    }

    /**
     * Constructor for creating a `Rating` object with custom user ID, user name, rating, and text.
     *
     * @param UID      The custom user ID associated with the rating.
     * @param UserName The custom user name associated with the rating.
     * @param rating   The numeric rating given by the user.
     * @param text     The text review provided by the user.
     */
    public Rating(String UID, String UserName, double rating, String text) {
        this.userId = UID;
        this.userName = UserName;

        this.rating = rating;
        this.text = text;
    }
    public String getUserId() {
        return userId;
    }
    public String getUserName() {
        return userName;
    }
    public double getRating() {
        return rating;
    }
    public String getText() {
        return text;
    }
    public Date getTimestamp() {
        return timestamp;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setRating(double rating) {
        this.rating = rating;
    }
    public void setText(String text) {
        this.text = text;
    }
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}

