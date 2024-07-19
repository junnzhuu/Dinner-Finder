package G26.Project.Model.Search;

import java.util.List;

import G26.Project.Model.Data.RestaurantDatabase;
import G26.Project.Model.Restaurant.Restaurant;

/**
 * The RestaurantSearchFacade provides a simplified interface for performing restaurant searches.
 * It abstracts the complexities of tokenization, parsing, and query execution, allowing client code
 * to easily perform searches without worrying about the underlying steps.
 * <p>
 * Usage example:
 * ```
 * RestaurantSearchFacade facade = new RestaurantSearchFacade();
 * List<Restaurant> results = facade.search("@ == New York");
 * ```
 * <p>
 * {@code @Author: Jing Li (Original)}
 *           UID : u7533831
 */
public class RestaurantSearchFacade {
    private final ParserFactory parserFactory;
    /**
     * Constructs a RestaurantSearchFacade instance with a default QueryParserFactory.
     */
    public RestaurantSearchFacade() {
        this.parserFactory = new QueryParserFactory();
    }
    /**
     * Searches for restaurants based on the provided input query.
     *
     * @param input The input query string to search for restaurants.
     * @return A list of restaurants that match the query criteria.
     */
    public List<Restaurant> search(String input) {

        // Tokenization
        List<Pair<String, Token>> tokens = QueryTokenizer.tokenize(input);

        // Parsing using the factory to create a parser instance
        AbstractQueryParser parser = parserFactory.createParser(tokens, null, null);
        Query query = parser.parse();

        // Execute query
        RestaurantDatabase db = RestaurantDatabase.getInstance();
        return db.executeQuery(query);
    }
}
