package G26.Project.Model.Search;

import java.util.List;

/**
 * The `AbstractQueryParser` class serves as a template for parsing queries based on tokenized input.
 * Concrete parser classes should extend this class and implement specific parsing steps.
 * <p>
 * {@code @Author: Jing Li (Original)}
 *           UID : u7533831
 */
public abstract class AbstractQueryParser {
    /**
     * The list of token pairs to be parsed.
     */
    protected final List<Pair<String, Token>> tokens;

    /**
     * The index used to iterate through the tokens.
     */
    protected int index = 0;

    /**
     * Initializes the parser with a list of token pairs.
     *
     * @param tokens The list of token pairs to be parsed.
     */
    public AbstractQueryParser(List<Pair<String, Token>> tokens) {
        this.tokens = tokens;
    }

    /**
     * Template method for parsing a query. Calls initialization, parsing, and finalization steps.
     *
     * @return The parsed query object.
     */
    public final Query parse() {
        initialize();
        doParsing();
        return finalizeParsing();
    }

    /**
     * Performs common initialization before parsing. Override if needed in concrete parsers.
     */
    protected void initialize() {}

    /**
     * The core parsing logic, to be defined by concrete parser classes.
     */
    protected abstract void doParsing();

    /**
     * Finalizes the parsing process and returns the parsed query object.
     *
     * @return The parsed query object.
     */
    protected Query finalizeParsing() {
        return null;
    }
}
