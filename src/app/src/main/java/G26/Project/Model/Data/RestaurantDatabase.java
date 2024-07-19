package G26.Project.Model.Data;

import static G26.Project.Resources.Constants.DATABASE_COST;
import static G26.Project.Resources.Constants.DATABASE_COST_INTERVALS;
import static G26.Project.Resources.Constants.DATABASE_MEAN_RATING;
import static G26.Project.Resources.Constants.DATABASE_RATING_INTERVALS;
import static G26.Project.Resources.Constants.DATABASE_TYPE;
import static G26.Project.Resources.Constants.FIELD_CITY;
import static G26.Project.Resources.Constants.FIELD_MEAN_RATING;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Predicate;

import G26.Project.Model.Restaurant.Restaurant;
import G26.Project.Model.Restaurant.RestaurantType;
import G26.Project.Model.Search.AttributeConstraint;
import G26.Project.Model.Search.Query;
import G26.Project.Model.Search.RestaurantFilterQuery;

/**
 * Singleton class representing a restaurant database that
 * uses nested B-trees with hashmap for efficient data storage and retrieval.
 * <p>
 *      This implementation is designed for category based search, and use sorted key (by cost)
 *      to optimize the range query.
 * <p>
 * {@code @Author: Jing Li (Original)}
 *           UID : u7533831
 * {@code @Contributor: Jun Zhu}
 *           UID: u7602081
 *           Added arrays to store count of restaurants in predefined cost and rating intervals. (Line 246 - 297)
 */
public class RestaurantDatabase {

    /**
     * Singleton class instance
     */
    private static RestaurantDatabase instance;
    private final Map<RestaurantType, BTree<Restaurant>> categoryTrees;

    private RestaurantDatabase() {
        categoryTrees = new HashMap<>();
    }

    /**
     * Entry point of the database
     */
    public static synchronized RestaurantDatabase getInstance() {
        if (instance == null) {
            instance = new RestaurantDatabase();
        }
        return instance;
    }

    /**
     * Inserts a restaurant into the database.
     *
     * @param restaurant The restaurant to insert.
     */
    public void insertRestaurant(Restaurant restaurant) {
        BTree<Restaurant> tree;
        if (categoryTrees.containsKey(restaurant.getType())) {
            tree = categoryTrees.get(restaurant.getType());
        } else {
            tree = new BTree<>();
            categoryTrees.put(restaurant.getType(), tree);
        }
        assert tree != null;
        tree.insert(restaurant);
    }

    /**
     * Updates a restaurant in the database based on the provided updated restaurant object.
     *
     * @param updatedRestaurant The updated restaurant information.
     */
    public void updateRestaurant(Restaurant updatedRestaurant) {
        BTree<Restaurant> tree = categoryTrees.get(updatedRestaurant.getType());
        if (tree == null) {
            return;
        }
        tree.update(updatedRestaurant);
    }

    /**
     * Executes a query on the restaurant database and returns a list of matching restaurants.
     *
     * @param query The query to execute.
     * @return A list of restaurants matching the query criteria.
     */
    public List<Restaurant> executeQuery(Query query) {
        List<Restaurant> result = new ArrayList<>();
        Predicate<Restaurant> finalPredicate = buildPredicate(query);
        // Define range check based on the cost conditions in the query.
        BiFunction<Restaurant, Restaurant, Boolean> rangeCheck = (currentKey, comparisonKey) -> {
            for (AttributeConstraint costConstraint : query.getCost()) {
                switch (costConstraint.getOperator()) {
                    case "<":
                        // If the currentKey or the comparisonKey has a cost less than the constraint value, we need to explore further.
                        return currentKey.getCost() < costConstraint.getValue() || comparisonKey.getCost() < costConstraint.getValue();
                    case ">":
                        // If the currentKey or the comparisonKey has a cost greater than the constraint value, we need to explore further.
                        return currentKey.getCost() > costConstraint.getValue() || comparisonKey.getCost() > costConstraint.getValue();
                    case "==":
                        // For equality, we can't make a definitive decision based on two keys, so we return true to explore further by default.
                        return true;
                }
            }
            return true; // If no cost condition is present, default to true to explore all nodes.
        };
        if (query.getCategory().isEmpty()) { // If no category is specified, search all trees.
            for (BTree<Restaurant> tree : categoryTrees.values()) {
                result.addAll(tree.searchWithPredicate(finalPredicate, rangeCheck));
            }
        } else { // Otherwise, only search the trees of the specified categories.
            for (RestaurantType type : query.getCategory()) {
                BTree<Restaurant> tree = categoryTrees.get(type);
                if (tree != null) {
                    result.addAll(tree.searchWithPredicate(finalPredicate, rangeCheck));
                }
            }
        }
        if (query instanceof RestaurantFilterQuery) {
            // Sort the results before returning, based on the condition from the query.
            Comparator<Restaurant> comparator = null;
            switch (((RestaurantFilterQuery) query).getSortCondition()) {
                case "Most Reviews First":
                    comparator = Comparator.comparing(Restaurant::getRatingCount).reversed();
                    break;
                case "Lowest Price First":
                    comparator = Comparator.comparingDouble(Restaurant::getCost);
                    break;
                case "Highest Price First":
                    comparator = Comparator.comparingDouble(Restaurant::getCost).reversed();
                    break;
                case "Highest Rating First":
                    comparator = Comparator.comparingDouble(Restaurant::getMeanRating).reversed();
                    break;
            }
            if (comparator != null) {
                result.sort(comparator);
            }
        }
        return result;
    }

    /**
     * Helper function to build a predicate based on the query to filter restaurants.
     *
     * @param query The query containing filtering criteria.
     * @return A predicate for filtering restaurants.
     */
    private Predicate<Restaurant> buildPredicate(Query query) {
        // Predicate for filtering restaurants by city
        Predicate<Restaurant> cityPredicate = restaurant -> {
            if (!query.getCity().isEmpty()) {
                return query.getCity().contains(restaurant.getRestaurantCity());
            }
            return true;
        };
        // Predicate for filtering restaurants by name
        Predicate<Restaurant> namePredicate = restaurant -> {
            if (!query.getRestaurantNames().isEmpty()) {
                return query.getRestaurantNames().contains(restaurant.getRestaurantName());
            }
            return true;
        };
        // Predicate for filtering restaurants by cost constraints
        Predicate<Restaurant> costPredicate = restaurant -> {
            for (AttributeConstraint costConstraint : query.getCost()) {
                switch (costConstraint.getOperator()) {
                    case "<":
                        if (restaurant.getCost() >= costConstraint.getValue()) return false;
                        break;
                    case ">":
                        if (restaurant.getCost() <= costConstraint.getValue()) return false;
                        break;
                    case "==":
                        if (restaurant.getCost() != costConstraint.getValue()) return false;
                        break;
                    // Add other cases if necessary
                }
            }
            return true;
        };
        // Predicate for filtering restaurants by rating constraints
        Predicate<Restaurant> ratingPredicate = restaurant -> {
            for (AttributeConstraint ratingConstraint : query.getRating()) {
                switch (ratingConstraint.getOperator()) {
                    case "<":
                        if (restaurant.getMeanRating() >= ratingConstraint.getValue()) return false;
                        break;
                    case ">":
                        if (restaurant.getMeanRating() <= ratingConstraint.getValue()) return false;
                        break;
                    case "==":
                        if (restaurant.getMeanRating() != ratingConstraint.getValue()) return false;
                        break;
                }
            }
            return true;
        };
        // Combine all individual predicates using 'and' logic to create the final predicate.
        return cityPredicate.and(namePredicate).and(costPredicate).and(ratingPredicate);
    }

    /**
     * Retrieves a list of all restaurants in the database.
     * Used to feed the UI when resetting and initializing
     *
     * @return A list of all restaurants in the database.
     */
    public List<Restaurant> toList () {
        RestaurantDatabase database = RestaurantDatabase.getInstance();
        List<Restaurant> list = new ArrayList<>();
        for (Map.Entry<RestaurantType, BTree<Restaurant>> entry : database.categoryTrees.entrySet()) {
            BTree<Restaurant> tree = entry.getValue();
            List<Restaurant> restaurants = tree.toList();
            list.addAll(restaurants);
        }
        return list;
    }

    /**
     * Extracts a comprehensive set of information from the stored restaurant data.
     * This data can be used for graphical reporting or other forms of analytics.
     *
     * @return A map containing various restaurant-related data points categorized
     *         by keys such as "cost", "meanRating", "city", "type", "costIntervals",
     *         and "ratingIntervals".
     */
    public static Map<String, List<Object>> extractData() {
        // Obtain an instance of the restaurant database.
        RestaurantDatabase database = RestaurantDatabase.getInstance();

        // Lists to collect specific attributes of restaurants.
        List<Integer> costs = new ArrayList<>();
        List<Double> meanRatings = new ArrayList<>();
        List<String> cities = new ArrayList<>();
        List<RestaurantType> types = new ArrayList<>();

        // Arrays to represent the number of restaurants falling into specific cost and rating intervals.
        int[] restaurantCounts = new int[6];
        int[] ratingCounts = new int[5];

        // Iterate through the categorized trees of restaurants.
        for (Map.Entry<RestaurantType, BTree<Restaurant>> entry : database.categoryTrees.entrySet()) {
            BTree<Restaurant> tree = entry.getValue();
            List<Restaurant> restaurants = tree.toList();

            // Extract data from individual restaurants.
            for (Restaurant restaurant : restaurants) {
                // Calculate the cost interval and increment the count.
                int cost = restaurant.getCost();
                int intervalIndex = cost / 20;
                if (cost == 120) intervalIndex = 5;
                restaurantCounts[intervalIndex]++;

                // Calculate the rating interval and increment the count.
                int ratingIntervalIndex = (int) Math.floor(restaurant.getMeanRating());
                if (restaurant.getMeanRating() == 5.0) ratingIntervalIndex = 4;
                ratingCounts[ratingIntervalIndex]++;

                // Collect specific attributes.
                costs.add(restaurant.getCost());
                meanRatings.add(restaurant.getMeanRating());
                cities.add(restaurant.getRestaurantCity());
                types.add(restaurant.getType());
            }
        }

        // Convert cost intervals to human-readable format.
        List<Object> costIntervals = new ArrayList<>();
        for (int i = 0; i < restaurantCounts.length; i++) {
            String interval;
            if (i == 0) {
                interval = "0-" + (i + 1) * 20;
            } else {
                interval = i * 20 + "-" + (i + 1) * 20;
            }
            costIntervals.add(interval + ": " + restaurantCounts[i]);
        }

        // Convert rating intervals to human-readable format.
        List<Object> ratingIntervals = new ArrayList<>();
        for (int i = 0; i < ratingCounts.length; i++) {
            String interval = i + "-" + (i + 1);
            ratingIntervals.add(interval + ": " + ratingCounts[i]);
        }

        // Create the result map and populate with the extracted data.
        Map<String, List<Object>> dataMap = new HashMap<>();
        dataMap.put(DATABASE_COST, new ArrayList<>(costs));
        dataMap.put(FIELD_MEAN_RATING, new ArrayList<>(meanRatings));
        dataMap.put(FIELD_CITY, new ArrayList<>(cities));
        dataMap.put(DATABASE_TYPE, new ArrayList<>(types));
        dataMap.put(DATABASE_COST_INTERVALS, new ArrayList<>(costIntervals));
        dataMap.put(DATABASE_RATING_INTERVALS, new ArrayList<>(ratingIntervals));

        return dataMap;
    }

    // For testing purpose: reset the instance
    protected static void resetInstance() {
        instance = null;
    }
}
