package G26.Project.Model.Search;

import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import G26.Project.Model.Restaurant.RestaurantType;
import G26.Project.ViewController.Managers.FilterDialogManager;
import G26.Project.ViewController.MainActivity;
/**
 * The `FilterParser` class is a specific parser for handling filter-related queries.
 * It extracts filter criteria from `FilterDialogManager.FilterValues` and constructs a `RestaurantFilterQuery`.
 * {@code @Author: Jing Li (Original)}
 *  *           UID : u7533831
 */
public class FilterParser extends AbstractQueryParser {
    /**
     * The filter values containing user-selected filter criteria.
     */
    private final FilterDialogManager.FilterValues filterValues;
    /**
     * The `RestaurantFilterQuery` object to store the parsed filter criteria.
     */
    private final RestaurantFilterQuery query;
    /**
     * The `MainActivity` context for geolocation.
     */
    private final MainActivity context;
    /**
     * Constructs a `FilterParser` with filter values and a `MainActivity` context.
     *
     * @param filterValues The user-selected filter criteria.
     * @param context      The `MainActivity` context.
     */
    public FilterParser(FilterDialogManager.FilterValues filterValues, MainActivity context) {
        super(null);
        this.query = new RestaurantFilterQuery();
        this.context = context;
        this.filterValues = filterValues;
    }
    /**
     * Parses filter criteria based on user-selected values and constructs a `RestaurantFilterQuery`.
     */
    @Override
    protected void doParsing() {
        // Set query parameters based on the FilterValues provided
        if (filterValues.isAroundMeSelected) {
            try {
                Geocoder geocoder = new Geocoder(context, Locale.getDefault());
                List<Address> addresses = geocoder.getFromLocation(context.getLatitude(), context.getLongitude(), 1);
                String city = "";
                if (!addresses.isEmpty()) {
                    // Get the city name (locality) from the first address in the list.
                    city = addresses.get(0).getLocality();
                }
                query.getCity().add(city);
            } catch (IOException e) {
                // Handle geolocation error
            }
        }
        // Set query parameters based on the FilterValues provided
        if (filterValues.selectedType != null) {
            try {
                query.getCategory().add(RestaurantType.valueOf(filterValues.selectedType));
            } catch (Exception e) {
                // Handle invalid category
                Log.d("Filter", "Invalid Category");
            }
        }
        if (filterValues.selectedMinCost > 0) {
            query.getCost().add(new AttributeConstraint(">", filterValues.selectedMinCost));
        }
        if (filterValues.selectedMaxCost != 0) {
            query.getCost().add(new AttributeConstraint("<", filterValues.selectedMaxCost));
        }
        if (filterValues.selectedMinRating > 0) {
            query.getRating().add(new AttributeConstraint(">", filterValues.selectedMinRating));
        }
        if (filterValues.selectedMaxRating != 0) {
            query.getRating().add(new AttributeConstraint("<", filterValues.selectedMaxRating));
        }
        if (filterValues.selectedSort != null && !filterValues.selectedSort.isEmpty()) {
            query.setSortCondition(filterValues.selectedSort);
        }
    }
    /**
     * Finalizes the parsing process and returns the constructed `RestaurantFilterQuery`.
     *
     * @return The parsed `RestaurantFilterQuery` object.
     */
    @Override
    protected RestaurantFilterQuery finalizeParsing(){
        return query;
    }
}
