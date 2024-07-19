package G26.Project.Model.Search;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import G26.Project.Model.Restaurant.RestaurantType;

/**
 * The RestaurantQuery class represents a general query for filtering restaurants.
 * It implements the Query interface and provides criteria such as restaurant names, cities, types, costs, and ratings.
 *
 * {@code @Author: Jing Li (Original)}
 *           UID : u7533831
 */
public class RestaurantQuery implements Query {
    private List<String> restaurantNames = new ArrayList<>();
    private List<String> restaurantCities = new ArrayList<>();
    private List<RestaurantType> types = new ArrayList<>();
    private List<AttributeConstraint> costs = new ArrayList<>();
    private List<AttributeConstraint> meanRatings = new ArrayList<>();

    /**
     * Get the list of restaurant cities in the query.
     *
     * @return List of restaurant cities.
     */
    @Override
    public List<String> getCity() {
        return restaurantCities;
    }

    /**
     * Get the list of restaurant types (categories) in the query.
     *
     * @return List of restaurant types.
     */
    @Override
    public List<RestaurantType> getCategory() {
        return types;
    }

    /**
     * Get the list of cost constraints in the query.
     *
     * @return List of cost constraints.
     */
    @Override
    public List<AttributeConstraint> getCost() {
        return costs;
    }

    public void setRestaurantNames(List<String> restaurantNames) {
        this.restaurantNames = restaurantNames;
    }
    public void setRestaurantCities(List<String> restaurantCities) {
        this.restaurantCities = restaurantCities;
    }
    public void setTypes(List<RestaurantType> types) {
        this.types = types;
    }

    // For testing purposes
    public void setCity(List<String> restaurantCities) {
        this.restaurantCities = restaurantCities;
    }
    @Override
    public List<String> getRestaurantNames() {
        return restaurantNames;
    }
    @Override
    public List<AttributeConstraint> getRating() {
        return meanRatings;
    }
    @Override
    public String toString() {
        return "RestaurantQuery{" +
                "restaurantNames=" + restaurantNames +
                ", cities=" + restaurantCities +
                ", types=" + types +
                ", costs=" + costs +
                ", meanRatings=" + meanRatings +
                '}';
    }

    /**
     * Compare two RestaurantQuery objects for equality.
     *
     * @param o The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RestaurantQuery that = (RestaurantQuery) o;
        return Objects.equals(restaurantNames, that.restaurantNames) &&
                Objects.equals(restaurantCities, that.restaurantCities) &&
                Objects.equals(types, that.types) &&
                Objects.equals(costs, that.costs) &&
                Objects.equals(meanRatings, that.meanRatings);
    }
    @Override
    public int hashCode() {
        return Objects.hash(restaurantNames, restaurantCities, types, costs, meanRatings);
    }
}
