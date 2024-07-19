package G26.Project.Model.Restaurant;

/**
 * The `CommentModel` class represents a user's comment or review for a restaurant.
 * {@code @Author: Jun Zhu}
 *            UID: u7602081
 * {@code @Contributor: Jing Li : refactor it to extends the 'Rating' class}
 *            UID: u7533831
 */
public class CommentModel extends Rating{
    private String restaurantName;
    private String restaurantType;
    private String restaurantCity;
    private long userDate;

    public CommentModel() {
    }
    public CommentModel(String restaurantName, String restaurantType, String restaurantCity,
                        String userName, long userDate, float userRating, String userText) {
        super(null, userName, userRating, userText);
        this.restaurantName = restaurantName;
        this.restaurantType = restaurantType;
        this.restaurantCity = restaurantCity;
        this.userDate = userDate;
    }
    public CommentModel(String restaurantName, String restaurantType, String restaurantCity,
                        String userName, long userDate, float userRating) {
        this(restaurantName, restaurantType, restaurantCity, userName, userDate, userRating, "");
    }

    public CommentModel(String restaurantName, String restaurantType, String restaurantCity,
                        String userName, long userDate, String userText) {
        this(restaurantName, restaurantType, restaurantCity, userName, userDate, 0, userText);
    }

    /*
     * Used for firebase constructing objects
     */
    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantType() {
        return restaurantType;
    }

    public void setRestaurantType(String restaurantType) {
        this.restaurantType = restaurantType;
    }

    public String getRestaurantCity() {
        return restaurantCity;
    }

    public void setRestaurantCity(String restaurantCity) {
        this.restaurantCity = restaurantCity;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getUserDate() {
        return userDate;
    }

    public void setUserDate(long userDate) {
        this.userDate = userDate;
    }

}
