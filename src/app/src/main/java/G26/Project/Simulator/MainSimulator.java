package G26.Project.Simulator;


import android.os.Handler;
import android.util.Log;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import G26.Project.Model.Restaurant.Rating;
import G26.Project.ViewController.MainActivity;
import G26.Project.ViewController.Util.FireStoreService;

import java.util.Random;

/**
 * This class simulates various data fetching operations from Firebase Firestore.
 * It fetches random data for ratings, messages, and search keywords.
 * <p>
 * {@code @Author: Jing Li (Original)}
 *           UID : u7533831
 */
public class MainSimulator {
    private static final Handler handler = new Handler();
    private static final int delay = 10000; // in milliseconds
    // Global variable to hold the last document snapshot
    private static final Random random = new Random();

    private final MainActivity mainActivity;

    /**
     * Constructor to initialize the MainSimulator with a reference to the MainActivity.
     * @param mainActivity The MainActivity instance.
     */
    public MainSimulator(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    /**
     * Starts the data loading process at regular intervals.
     */
    public void loader() {
        handler.postDelayed(new Runnable() {
            public void run() {
                fetchRatingFromFirebaseAndUpdate();
                fetchMessageFromFirebaseAndUpdate();
                fetchSearchKeywordsAndSearch();
                handler.postDelayed(this, delay);
            }
        }, delay);
    }

    /**
     * Fetches a random rating from Firebase Firestore and updates the corresponding restaurant's rating.
     */
    private void fetchRatingFromFirebaseAndUpdate() {
        FirebaseFirestore db = FireStoreService.getInstance().getFirebaseDatabase();

        int randomIndex = random.nextInt(1000);

        // Attach a listener to the "simulationData" collection
        db.collection("Ratings")
                .orderBy("restaurantID")
                .limitToLast(randomIndex + 1)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        QuerySnapshot queryDocumentSnapshots = task.getResult();
                        int numberOfDocuments = queryDocumentSnapshots.size();
                        if (!queryDocumentSnapshots.isEmpty()) {
                            // Successfully retrieved the document
                            DocumentSnapshot documentSnapshot = queryDocumentSnapshots.getDocuments().get(numberOfDocuments - 1 - randomIndex);
                            String uid = documentSnapshot.getString("uid");
                            String userName = documentSnapshot.getString("userName");
                            Double ratingValue = documentSnapshot.getDouble("rating");
                            String comments = documentSnapshot.getString("comments");
                            String restaurantID = documentSnapshot.getString("restaurantID");
                            String resName = documentSnapshot.getString("resName");
                            String resCity = documentSnapshot.getString("resCity");
                            String resType = documentSnapshot.getString("resCity");
                            Rating rating = new Rating(uid,userName,ratingValue,comments);
                            FireStoreService.getInstance().updateRestaurantWithRating(restaurantID, rating);
                            FireStoreService.getInstance().storeCommentInFirebase(resName,resType,
                                    resCity,
                                    System.currentTimeMillis(),
                                    comments, (float) rating.getRating());
                        } else {
                            Log.d("Firebase", "No such document in 'rating' subcollection");
                        }
                    } else {
                        // Task was not successful, log the exception
                        Log.d("Firebase", "get failed with ", task.getException());
                    }
                });
    }

    /**
     * Fetches a random message from Firebase Firestore and sends it to the corresponding recipient.
     */
    private void fetchMessageFromFirebaseAndUpdate() {
        FirebaseFirestore db = FireStoreService.getInstance().getFirebaseDatabase();

        int randomIndex = random.nextInt(1000);

         db.collection("P2P")
                 .orderBy("senderID")
                 .limitToLast(randomIndex + 1)
                 .get()
                 .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        QuerySnapshot queryDocumentSnapshots = task.getResult();
                        int numberOfDocuments = queryDocumentSnapshots.size();
                        // Successfully retrieved the document
                        if (numberOfDocuments > randomIndex) {
                            DocumentSnapshot documentSnapshot = queryDocumentSnapshots.getDocuments().get(numberOfDocuments - 1 - randomIndex);
                            String content = documentSnapshot.getString("content");
                            String senderID = documentSnapshot.getString("senderID");
                            String targetID = documentSnapshot.getString("targetID");
                            // Successfully retrieved the document
                            FireStoreService.getInstance().sendMessage(content, senderID, targetID);
                            Log.d("Firebase", content + "xxxxxx" + senderID + "xxxxxx" + targetID);
                        }
                    } else {
                        // Task was not successful, log the exception
                        Log.d("Firebase", "get failed with ", task.getException());
                    }
                });
    }

    /**
     * Fetches a random search keyword from Firebase Firestore and initiates a search with it.
     */
    private void fetchSearchKeywordsAndSearch() {
        FirebaseFirestore db = FireStoreService.getInstance().getFirebaseDatabase();
        // Generate a random index to fetch a random document
        int randomIndex = random.nextInt(1000);

        db.collection("SearchData")
                .orderBy("searchData")
                .limitToLast(randomIndex + 1)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        QuerySnapshot queryDocumentSnapshots = task.getResult();
                        int numberOfDocuments = queryDocumentSnapshots.size();

                        if (numberOfDocuments > randomIndex) {
                            // Fetch the random document
                            DocumentSnapshot documentSnapshot = queryDocumentSnapshots.getDocuments().get(numberOfDocuments - 1 - randomIndex);
                            String keyword = documentSnapshot.getString("searchData");
                            assert keyword != null;
                            Log.d("Firebase", keyword);
                            mainActivity.searchInput(keyword);
                        } else {
                            Log.d("Firebase", "Not enough documents in the collection.");
                        }
                    } else {
                        Log.d("Firebase", "Failed to fetch search keywords: " + task.getException());
                    }
                });
    }

}
