package G26.Project.Model.Search;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The `QueryTokenizer` class is responsible for tokenizing input query strings, breaking them down into
 * individual tokens, and categorizing each token based on its type. Tokens represent components of a query,
 * such as operators, keywords, numbers, and restaurant-related attributes.
 * <p>
 * It uses regular expressions to identify and extract tokens from the input query string and assigns a
 * corresponding token type to each token. The identified token types are used for query parsing and processing.
 * <p>
 * {@code @Author: Jiawei Liu}
 *           UID : u7603069
 * {@code @Contributor: Jing Li (Contribute to the token type : line 50 - 57)}
 */
public class QueryTokenizer {
    private static final String REGEX =
            "([@/!#$,])|" +                                       // Special symbols including comma
                    "(==|<|>)|" +                                      // Operators
                    "\"[^\"]+\"|" +                                    // Quoted strings
                    "\\d+(\\.\\d+)?|" +                                // Numbers
                    "(Italian|Chinese|Indian|Mexican|Thai|Mediterranean|French|Japanese|Korean)|" + // Restaurant types
                    "[a-zA-Z ]+";  // Strings like city names without attribute indicators

    private static final Pattern PATTERN = Pattern.compile(REGEX);

    /**
     * Returns the token type for a given token string.
     *
     * @param str The token string to determine the type for.
     * @return The corresponding token type.
     */
    private static Token getTokenType(String str) {
        switch (str) {
            case "@":
                return Token.CITY;
            case "/":
                return Token.CATEGORY;
            case "$":
                return Token.COST;
            case "!":
                return Token.RATING;
            case "#":
                return Token.RESTAURANT_NAME;
            case ",":
                return Token.COMMA;
            case "==":
                return Token.EQUAL;
            case "<":
                return Token.LT;
            case ">":
                return Token.GT;
            default:
                if (str.matches("\\d+(\\.\\d+)?")) {
                    return Token.NUMBER;
                } else if (str.matches("(Italian|Chinese|Indian|Mexican|Thai|Mediterranean|French|Japanese|Korean)")) {
                    return Token.RESTAURANT_TYPE;
                } else if (str.matches("\"[^\"]*\"")) {
                    return Token.RESTAURANT_NAME;
                } else if (str.matches("[a-zA-Z ]+")) {
                    return Token.CITY;
                }
                return Token.UNKNOWN;
        }
    }

    /**
     * Tokenizes an input query string and returns a list of token pairs, where each pair consists of a token
     * string and its corresponding token type.
     *
     * @param input The input query string to tokenize.
     * @return A list of token pairs representing the tokens found in the input query string.
     */
    public static List<Pair<String, Token>> tokenize(String input) {
        List<Pair<String, Token>> tokens = new ArrayList<>();

        Matcher matcher = PATTERN.matcher(input);

        while (matcher.find()) {
            String match = Objects.requireNonNull(matcher.group(0)).trim();
            Token tokenType = getTokenType(match);

            if (tokenType != Token.UNKNOWN && !match.isEmpty()) {
                tokens.add(new Pair<>(match, tokenType));
            }
        }
        return tokens;
    }
}