package G26.Project.Simulator.HelperScripts;

import android.util.Log;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Locale;
import java.util.Random;

import G26.Project.Model.Restaurant.Restaurant;
import G26.Project.ViewController.Util.FireStoreService;

/**
 * This class generates random restaurant data and initializes it in Firestore.
 * The restaurant names are generated using ChatGPT 4.0.
 * <p>
 * It is list here for check purpose
 * <p>
 *  {@code @Author: Jing Li}
 *            UID : u7533831
 */
public class RestaurantGenerator {
    private static final String RESTAURANTS = "restaurants";
    private static final String RESTAURANT_IMAGE_URL_FORMAT = "https://firebasestorage.googleapis.com/v0/b/gourmet-restaurant-search.appspot.com/o/restaurant%d.jpeg?alt=media";
    private static final int MAX_IMAGE_NUM = 200;

    /**
     * The restaurant names are generated using ChatGPT 4.0.
     */
    private static final String[] RESTAURANT_NAMES = {
            "Tasty Delight",
            "Gourmet Haven",
            "Flavor Fusion",
            "Dine Divine",
            "Culinary Magic",
            "Savory Palace",
            "Epicurean Hub",
            "Gastronomic Delight",
            "Dine Fine",
            "Sizzling Bites",
            "Aroma Junction",
            "Taste Oasis",
            "Mouthwatering Delights",
            "Culinary Bliss",
            "Gourmet Elegance",
            "Delicious Dreams",
            "Savor Paradise",
            "Epicurean Treasures",
            "Flavorful Creations"
    };

    private static final String[] CITIES = {
            "New York",
            "Los Angeles",
            "Chicago",
            "Houston",
            "Phoenix",
            "Philadelphia",
            "San Antonio",
            "San Diego",
            "Dallas",
            "Sydney",
            "Melbourne",
            "Brisbane",
            "Perth",
            "Adelaide",
            "Gold Coast",
            "Canberra",
            "Newcastle",
            "Hobart",
            "Darwin"
    };

    private static final String[] CATEGORIES = {
            "Italian",
            "Chinese",
            "Indian",
            "Mexican",
            "Thai",
            "Mediterranean",
            "French",
            "Japanese",
            "Korean"
    };

    /**
     * search keywords
     */
    private static final String[] SEARCH_KEYWORDS = {
            "Tasty Delight",
            "Chinese",
            "Philadelphia",
    };

    /**
     * Initialize a random restaurant to Firestore.
     */
    public static void initializeRandomRestaurant() {
        for(int i = 0; i < 76; i++) {
            Restaurant randomRestaurant = generateRandomRestaurant(i);
            addRestaurantToFireStore(randomRestaurant);
        }
    }

    /**
     * Generate a random restaurant.
     */
    private static Restaurant generateRandomRestaurant(int i) {
        Random random = new Random();

        String name = RESTAURANT_NAMES[random.nextInt(RESTAURANT_NAMES.length)];
        String city = CITIES[random.nextInt(CITIES.length)];
        String category = CATEGORIES[random.nextInt(CATEGORIES.length)];
        String photo = String.format(Locale.getDefault(), RESTAURANT_IMAGE_URL_FORMAT, i);
        int price = random.nextInt(100) + 20;
        int numRatings = random.nextInt(400);
        double avgRating = 1 + (random.nextDouble() * 4);

        return new Restaurant(name, city, category, photo, price, numRatings, avgRating);
    }

    /**
     * Add a restaurant object to Firestore.
     */
    private static void addRestaurantToFireStore(Restaurant restaurant) {
        FirebaseFirestore fireStore = FireStoreService.getInstance().getFirebaseDatabase();

        fireStore.collection(RESTAURANTS)
                .add(restaurant)
                .addOnSuccessListener(documentReference -> {
                    Log.d("FireStoreInitializer", "Restaurant successfully written with ID: " + documentReference.getId());
                    // Set the restaurantId for the Restaurant object
                    restaurant.setRestaurantId(documentReference.getId());

                    // If you want, you can also update the restaurant in Firestore with this ID
                    documentReference.update("restaurantId", documentReference.getId())
                            .addOnSuccessListener(aVoid -> Log.d("FireStoreInitializer", "Restaurant ID updated successfully"))
                            .addOnFailureListener(e -> Log.w("FireStoreInitializer", "Error updating restaurant ID", e));
                })
                .addOnFailureListener(e -> Log.w("FireStoreInitializer", "Error adding restaurant", e));
    }

    /**
     * Delete all restaurants from Firestore.
     */
    public static void deleteAllRestaurants() {
        FirebaseFirestore fireStore = FireStoreService.getInstance().getFirebaseDatabase();
        fireStore.collection(RESTAURANTS)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (DocumentSnapshot document : task.getResult()) {
                            fireStore.collection(RESTAURANTS).document(document.getId()).delete()
                                    .addOnSuccessListener(aVoid -> Log.d("FireStoreInitializer", "Successfully deleted restaurant with ID: " + document.getId()))
                                    .addOnFailureListener(e -> Log.w("FireStoreInitializer", "Error deleting restaurant", e));
                        }
                    } else {
                        Log.w("FireStoreInitializer", "Error fetching restaurants for deletion", task.getException());
                    }
                });
    }

}