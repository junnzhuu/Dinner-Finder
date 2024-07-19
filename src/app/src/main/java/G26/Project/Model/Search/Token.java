package G26.Project.Model.Search;

/**
 * Enum representing the types of tokens that can be encountered in a query.
 * <p>
 * {@code @Author: Jiawei Liu }
 *           UID : u7603069
 *{@code @Contributor: Jing Li (Contribute to line 16 - 19)}
 */
public enum Token {
    CITY,            // City name
    CATEGORY,        // Restaurant category
    COST,            // Cost attribute
    RATING,          // Rating attribute
    RESTAURANT_NAME, // Restaurant name
    COMMA,           // Comma symbol
    EQUAL,           // Equal operator
    LT,              // Less than operator
    GT,              // Greater than operator
    NUMBER,          // Numeric value
    RESTAURANT_TYPE, // Restaurant type
    UNKNOWN          // Unknown token type
}