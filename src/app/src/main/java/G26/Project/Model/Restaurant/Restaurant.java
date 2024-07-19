package G26.Project.Model.Restaurant;

import java.io.Serializable;

/**
 * The `Restaurant` class represents a restaurant with various attributes.
 * <p>
 *      This implementation sort the restaurants by 'cost' in the Btree
 * <p>
 * {@code @Author: Jing Li (Original)}
 *          UID : u7533831
 */
public class Restaurant implements Comparable<Restaurant>, Serializable {
    private String restaurantName;
    private String restaurantCity;
    private RestaurantType type;
    private String image;
    private int cost;
    private int ratingCount;
    private double meanRating;
    private String restaurantId;
    public Restaurant() {}
    public Restaurant(String restaurantName, String restaurantCity, String type, String image,
                      int cost, int ratingCount, double meanRating) {
        this.restaurantName = restaurantName;
        this.restaurantCity = restaurantCity;
        this.type = RestaurantType.fromString(type);
        this.image = image;
        this.cost = cost;
        this.ratingCount = ratingCount;
        this.meanRating = meanRating;
    }
    public String getRestaurantName() {
        return restaurantName;
    }
    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }
    public String getRestaurantCity() {
        return restaurantCity;
    }
    public void setRestaurantCity(String restaurantCity) {
        this.restaurantCity = restaurantCity;
    }
    public RestaurantType getType() {
        return type;
    }
    public void setType(String type) {
        this.type = RestaurantType.fromString(type);
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public int getCost() {
        return cost;
    }
    public void setCost(int cost) {
        this.cost = cost;
    }
    public int getRatingCount() {
        return ratingCount;
    }
    public void setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
    }
    public double getMeanRating() {
        return meanRating;
    }
    public void setMeanRating(double meanRating) {
        this.meanRating = meanRating;
    }
    public String getRestaurantId() {
        return restaurantId;
    }
    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    /**
     * Compares two restaurants based on their cost.
     * Used for sort the key in Btree
     *
     * @param other The other `Restaurant` object to compare to.
     * @return A negative integer, zero, or a positive integer if this restaurant's cost
     *         is less than, equal to, or greater than the cost of the other restaurant.
     */
    @Override
    public int compareTo(Restaurant other) {
        return Double.compare(this.cost, other.cost);
    }
}

