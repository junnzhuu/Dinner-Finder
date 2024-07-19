package G26.Project.Model.Search;

import java.util.List;

import G26.Project.ViewController.Managers.FilterDialogManager;
import G26.Project.ViewController.MainActivity;
/**
 * The QueryParserFactory class implements the ParserFactory interface and
 * provides logic for creating query parser instances.
 * {@code @Author: Jing Li (Original)}
 *           UID : u7533831
 */
public class QueryParserFactory implements ParserFactory {
    @Override
    public AbstractQueryParser createParser(List<Pair<String, Token>> tokens, FilterDialogManager.FilterValues filterValues, MainActivity context) {
        if (tokens != null) { //BasicQueryParser if there are tokens
            return new BasicQueryParser(tokens);
        } else if (filterValues != null) {//FilterParser if there the token is null
            return new FilterParser(filterValues, context);
        } else {
            // default error handling
            throw new IllegalArgumentException("No valid arguments provided for parser creation.");
        }
    }
}