package G26.Project.Model.Search;

import java.util.List;
import java.util.Objects;

import G26.Project.Model.Restaurant.RestaurantType;
/**
 * The `BasicQueryParser` class parses queries based on the provided grammar rules
 * and constructs a `RestaurantQuery` object to represent the filtering criteria.
 * <p>
 * Grammar:
 * <expressions> ::= <factors> | <factors> , <expressions>
 *     <factors> ::= <factor> | <factor> <factors>
 *         <factor> ::= <attribute> <operator> <value>
 *             <attribute> ::= @ | # | $ | ! | /
 *             <operator> ::= == | < | >
 *             <value> ::= STRING | NUMBER | RESTAURANT_TYPE
 * <p>
 * {@code @Author: Jing Li (Original)}
 *           UID : u7533831
 */

public class BasicQueryParser extends AbstractQueryParser {
    /**
     * The `RestaurantQuery` object to store the parsed searching criteria.
     */
    private final RestaurantQuery restaurantQuery;
    /**
     * Initializes the parser with a list of token pairs.
     *
     * @param tokens The list of token pairs to parse.
     */
    public BasicQueryParser(List<Pair<String, Token>> tokens) {
        super(tokens);
        restaurantQuery = new RestaurantQuery();
    }
    /**
     * Initializes the parsing process. Checks if there are tokens to parse.
     */
    @Override
    protected void initialize() {
        if (!moreTokens()) throw new IllegalArgumentException("No tokens!");
    }
    /**
     * Main parsing routine. Parses expressions separated by commas.
     */
    @Override
    protected void doParsing() {
        while (moreTokens()) {
            expression();
            if (moreTokens() && Objects.requireNonNull(peekToken()).getValue() == Token.COMMA) {
                consumeToken(); // consume comma
            }
        }
    }

    /**
     * Parses an individual expression, consisting of an attribute, operator, and value.
     */
    private void expression() {
        Pair<String, Token> attributeToken = consumeToken();
        Pair<String, Token> operatorToken = consumeToken();
        Pair<String, Token> valueToken = consumeToken();
        if (attributeToken == null || operatorToken == null || valueToken == null) {
            throw new IllegalArgumentException("Malformed query");
        }
        applyAttribute(attributeToken, operatorToken, valueToken);
    }

    /**
     * Applies the parsed attribute, operator, and value to the `restaurantQuery` object.
     *
     * @param attributeToken The token representing the attribute.
     * @param operatorToken  The token representing the operator.
     * @param valueToken     The token representing the value.
     */
    private void applyAttribute(Pair<String, Token> attributeToken, Pair<String, Token> operatorToken, Pair<String, Token> valueToken) {
        String operator = operatorToken.getKey();
        String value = valueToken.getKey().replaceAll("^\"|\"$", "");  // Removing quotes
        switch (attributeToken.getValue()) {
            case CITY:
                restaurantQuery.getCity().add(value);
                break;
            case RESTAURANT_NAME:
                restaurantQuery.getRestaurantNames().add(value);
                break;
            case COST:
                double costValue = Double.parseDouble(value);
                restaurantQuery.getCost().add(new AttributeConstraint(operator, costValue));
                break;
            case RATING:
                double ratingValue = Double.parseDouble(value);
                restaurantQuery.getRating().add(new AttributeConstraint(operator, ratingValue));
                break;
            case CATEGORY:
                restaurantQuery.getCategory().add(RestaurantType.fromString(value));
                break;
            default:
                throw new IllegalArgumentException("Unsupported attribute token: " + attributeToken.getValue());
        }
    }

    /**
     * Finalizes the parsing process and returns the constructed `restaurantQuery`.
     *
     * @return The parsed `restaurantQuery` object.
     */
    @Override
    protected Query finalizeParsing(){
        return restaurantQuery;
    }

    /**
     * Checks if there are more tokens to parse.
     *
     * @return `true` if there are more tokens, `false` otherwise.
     */
    private boolean moreTokens() {
        return index < tokens.size();
    }

    /**
     * Retrieves the next token without consuming it.
     *
     * @return The next token or `null` if there are no more tokens.
     */
    private Pair<String, Token> peekToken() {
        if (moreTokens()) {
            return tokens.get(index);
        }
        return null;
    }

    /**
     * Consumes the next token and advances the token index.
     *
     * @return The consumed token or `null` if there are no more tokens.
     */
    private Pair<String, Token> consumeToken() {
        if (moreTokens()) {
            return tokens.get(index++);
        }
        return null;
    }
}
