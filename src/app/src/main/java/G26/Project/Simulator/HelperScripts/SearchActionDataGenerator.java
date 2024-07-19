package G26.Project.Simulator.HelperScripts;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Collections;
import java.util.Random;

import G26.Project.Model.Restaurant.RestaurantType;
import G26.Project.ViewController.Util.FireStoreService;

/**
 * This class generates random restaurant data and initializes it in Firestore.
 * It is list here for check purpose
 * <p>
 *  {@code @Author: Jing Li (Original)}
 *            UID : u7533831
 */
public class SearchActionDataGenerator {
    private static final Random random = new Random();
    private static final String[] ATTRIBUTES = { "@", "#", "$", "!", "/" };
    private static final String[] OPERATORS = { "==", "<", ">" };
    private static final FirebaseFirestore db = FireStoreService.getInstance().getFirebaseDatabase();

    private static final String[] ValidateTypeKeyWords = {
            "/ == Italian",
            "/ == Chinese",
            "/ == Indian",
            "/ == Mexican",
            "/ == Thai",
            "/ == Mediterranean",
            "/ == French",
            "/ == Japanese",
            "/ == Korean"
    };

    private static final String[] ValidateCityKeyWords = {
            "@ == New York",
            "@ == Los Angeles",
            "@ == Chicago",
            "@ == Houston",
            "@ == Phoenix",
            "@ == Philadelphia",
            "@ == San Antonio",
            "@ == San Diego",
            "@ == Dallas"
    };

    private static final String[] ValidateRangeKeyWords = {
            "$<20",
            "$>10",
            "$<30, $>20",
            "! < 4",
            "! > 3",
            "! > 2",
            "! > 1"
    };

    public static String generateSearchKeyword() {
        return generateExpressions();
    }

    private static String generateExpressions() {
        String expressions = generateFactors();

        if (random.nextBoolean()) {
            expressions += ", " + generateExpressions();
        }

        return expressions;
    }

    private static String generateFactors() {
        String factors = generateFactor();

        if (random.nextBoolean()) {
            factors += " " + generateFactors();
        }

        return factors;
    }

    private static String generateFactor() {
        return generateAttribute() + generateOperator() + generateValue();
    }

    private static String generateAttribute() {
        return ATTRIBUTES[random.nextInt(ATTRIBUTES.length)];
    }

    private static String generateOperator() {
        return OPERATORS[random.nextInt(OPERATORS.length)];
    }

    private static String generateValue() {
        switch (generateAttribute()) {
            case "/":
                return RestaurantType.values()[random.nextInt(RestaurantType.values().length)].toString();
            case "$":
                return String.valueOf(random.nextInt(100) + 1);  // Assuming a range from 1 to 100 for cost.
            default:
                return generateRandomString();  // You can define this method to return a random string.
        }
    }

    private static String generateRandomString() {
        int length = random.nextInt(10) + 1; // Generates string of length 1 to 10
        StringBuilder randomString = new StringBuilder();

        for (int i = 0; i < length; i++) {
            char c = (char) (random.nextInt(26) + 'a');  // Generate random lowercase char
            randomString.append(c);
        }

        return randomString.toString();
    }

    /**
     * Generate a valid search keyword for testing purposes.
     *
     * @return Valid search keyword.
     */
    public static String generateValidKeyword() {
        int category = random.nextInt(3); // Randomly select a category: 0 for type, 1 for city, 2 for range

        switch (category) {
            case 0:
                // Randomly select a type keyword
                return ValidateTypeKeyWords[random.nextInt(ValidateTypeKeyWords.length)];
            case 1:
                // Randomly select a city keyword
                return ValidateCityKeyWords[random.nextInt(ValidateCityKeyWords.length)];
            case 2:
                // Randomly select a range keyword
                String rangeKeyword = ValidateRangeKeyWords[random.nextInt(ValidateRangeKeyWords.length)];

                // Add a comma and another keyword with a certain probability (e.g., 50% chance)
                if (random.nextBoolean()) {
                    String anotherKeyword = generateValidKeyword();
                    return rangeKeyword + ", " + anotherKeyword;
                }

                return rangeKeyword;
            default:
                return ""; // Handle invalid category
        }
    }


    /**
     * Upload the generated search data to Firestore.
     */
    public static void uploadSearchData() {
        String searchData = generateSearchKeyword();
        //String searchData = generateValidKeyword(); // If you want to generate valid keywords

        // Reference to the "SearchData" collection
        CollectionReference searchDataRef = db.collection("SearchData");

        // Add the search data to the "search" subcollection without specifying a document ID
        searchDataRef.add(Collections.singletonMap("searchData", searchData))
                .addOnSuccessListener(documentReference -> System.out.println("DocumentSnapshot successfully written with ID: " + documentReference.getId()))
                .addOnFailureListener(e -> System.out.println("Error writing document: " + e));
    }
}
