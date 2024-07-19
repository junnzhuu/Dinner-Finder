package G26.Project.Model.Restaurant;

import androidx.annotation.NonNull;

/**
 * The `RestaurantType` enum represents different types of restaurants.
 * {@code @Author: Jing Li (Original)}
 *           UID : u7533831
 */
public enum RestaurantType {
    Italian,
    Chinese,
    Indian,
    Mexican,
    Thai,
    Mediterranean,
    French,
    Japanese,
    Korean;

    /**
     * Converts a string representation of a restaurant type to the corresponding enum value.
     *
     * @param typeStr The string representation of the restaurant type.
     * @return The `RestaurantType` enum value.
     * @throws IllegalArgumentException if the input string does not match any restaurant type.
     */
    public static RestaurantType fromString(String typeStr) {
        for (RestaurantType type : RestaurantType.values()) {
            if (type.name().equalsIgnoreCase(typeStr)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No RestaurantType with text " + typeStr + " found");
    }

    @NonNull
    @Override
    public String toString() {
        return name();
    }
}
