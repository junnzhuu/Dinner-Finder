package G26.Project.Model.Search;

import java.util.List;

import G26.Project.Model.Restaurant.RestaurantType;

/**
 * The Query interface defines methods for retrieving various criteria for restaurant searches.
 * Implementing classes should provide specific criteria for filtering and searching restaurants.
 * <p>
 * {@code @Author: Jing Li (Original)}
 *           UID : u7533831
 */
public interface Query {
    List<String> getCity();
    List<RestaurantType> getCategory();
    List<AttributeConstraint> getCost();
    List<String> getRestaurantNames();
    List<AttributeConstraint> getRating();
}

