package G26.Project.Model.Search;

import java.util.List;

import G26.Project.ViewController.Managers.FilterDialogManager;
import G26.Project.ViewController.MainActivity;
/**
 * The ParserFactory interface defines a method for creating query parsers based on input parameters.
 * Implementing classes should provide logic for creating specific query parser instances.
 * {@code @Author: Jing Li (Original)}
 *           UID : u7533831
 */
public interface ParserFactory {
    /**
     * Creates a query parser based on input parameters.
     *
     * @param tokens       The list of tokens to parse (if available).
     * @param filterValues The filter values (if available).
     * @param context      The context or activity associated with the parser (if needed).
     * @return An instance of AbstractQueryParser for parsing queries.
     * @throws IllegalArgumentException if no valid arguments are provided for parser creation.
     */
    AbstractQueryParser createParser(List<Pair<String, Token>> tokens, FilterDialogManager.FilterValues filterValues, MainActivity context);
}
