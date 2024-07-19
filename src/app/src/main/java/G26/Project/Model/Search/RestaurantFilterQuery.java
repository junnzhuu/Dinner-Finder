package G26.Project.Model.Search;

/**
 * The RestaurantFilterQuery class represents a specific query for filtering restaurants.
 * It extends the RestaurantQuery class and adds a sorting condition for the query results.
 *
 * {@code @Author: Jing Li (Original)}
 *           UID : u7533831
 */
public class RestaurantFilterQuery extends RestaurantQuery{
    private String sortCondition;

    /**
     * Constructs a new RestaurantFilterQuery with an empty sort condition.
     */
    public RestaurantFilterQuery() {
       super();
       this.sortCondition = "";
    }
    /**
     * Gets the sorting condition for the query results.
     *
     * @return The sorting condition.
     */
    public String getSortCondition() {
        return sortCondition;
    }
    /**
     * Sets the sorting condition for the query results.
     *
     * @param sortCondition The sorting condition to set.
     */
    public void setSortCondition(String sortCondition) {
        this.sortCondition = sortCondition;
    }
}
