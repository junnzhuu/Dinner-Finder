package G26.Project.ViewController;

import static G26.Project.Resources.Constants.BROADCAST_DATA_UPDATE;
import static G26.Project.Resources.Constants.SEARCH_BROADCAST_RECEIVER_UPDATE;
import static G26.Project.Resources.Constants.SEARCH_RESTAURANT_KEY;
import static G26.Project.Resources.Constants.SearchTAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import G26.Project.ViewController.Adapter.RatingAdapter;
import G26.Project.ViewController.Fragment.RatingDialogFragment;
import G26.Project.ViewController.Util.FireStoreService;
import G26.Project.Model.Restaurant.Rating;
import G26.Project.Model.Restaurant.Restaurant;
import G26.Project.R;

/**
 * The `RestaurantDetailActivity` class represents the user interface for displaying details
 * of a restaurant, including its name, city, image, price, average rating, category, and user ratings.
 * Users can view restaurant details and add their own ratings and reviews for the restaurant.
 * {@code @Author: Jing Li (Original)}
 *           UID : u7533831
 */
public class RestaurantDetailActivity extends AppCompatActivity {
    private static final String TAG = SearchTAG;

    private String restaurantId;
    private Restaurant restaurant;
    private RecyclerView ratingsRecyclerView;

    private RatingAdapter ratingAdapter;
    private List<Rating> ratingsList;
    private FirebaseFirestore db;
    private ListenerRegistration ratingsListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = FireStoreService.getInstance().getFirebaseDatabase();

        // Register a local broadcast receiver to listen for data updates
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter(BROADCAST_DATA_UPDATE));

        // Initialize UI components, fetch restaurant ratings, and set up ratings listener
        setupViews();
        fetchRatings();
        setupRatingsListener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (ratingsListener != null) {
            ratingsListener.remove(); // Detach the listener when the activity is destroyed
        }
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
    }

    /**
     * BroadcastReceiver to listen for updates in restaurant data.
     */
    private final BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Restaurant updatedRestaurant = (Restaurant) intent.getSerializableExtra(SEARCH_BROADCAST_RECEIVER_UPDATE);

            TextView restaurantNumRatings = findViewById(R.id.restaurant_num_ratings);
            assert updatedRestaurant != null;
            restaurantNumRatings.setText("(" + updatedRestaurant.getRatingCount() + ")");

            RatingBar restaurantRatingBar = findViewById(R.id.restaurant_rating);
            restaurantRatingBar.setRating((float) updatedRestaurant.getMeanRating());
        }
    };

    /**
     * Initializes and sets up the UI components and views.
     */
    private void setupViews() {
        setContentView(R.layout.activity_restaurant_detail);
        ratingsList = new ArrayList<>();
        ratingAdapter = new RatingAdapter(ratingsList);

        ratingsRecyclerView = findViewById(R.id.recycler_rating);
        ratingsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ratingsRecyclerView.setAdapter(ratingAdapter);

        restaurant = (Restaurant) getIntent().getSerializableExtra(SEARCH_RESTAURANT_KEY);
        assert restaurant != null;
        restaurantId = restaurant.getRestaurantId();
        if (restaurant != null) {
            populateRestaurantDetails(restaurant);
        } else {
            Log.e(TAG, "restaurant == null");
        }

        FloatingActionButton fab = findViewById(R.id.fab_show_rating_dialog);
        fab.setOnClickListener(view -> showRatingDialog());
    }

    /**
     * Populates the restaurant details on the UI.
     *
     * @param restaurant The restaurant object containing details to be displayed.
     */
    private void populateRestaurantDetails(Restaurant restaurant) {
        // Set Restaurant Name
        TextView restaurantName = findViewById(R.id.restaurant_name);
        restaurantName.setText(restaurant.getRestaurantName());

        // Set Restaurant City
        TextView restaurantCityView = findViewById(R.id.restaurant_city);
        restaurantCityView.setText(restaurant.getRestaurantCity());

        // Set Restaurant Image
        ImageView restaurantImage = findViewById(R.id.restaurant_image);
        Glide.with(this)
                .load(restaurant.getImage())
                .into(restaurantImage);

        // Set Restaurant Price
        TextView restaurantPrice = findViewById(R.id.restaurant_price);
        restaurantPrice.setText(restaurant.getCost() + "$");

        // Set Restaurant Rating Count
        TextView restaurantNumRatings = findViewById(R.id.restaurant_num_ratings);
        restaurantNumRatings.setText("(" + restaurant.getRatingCount() + ")");

        // Set Restaurant Average Rating
        RatingBar restaurantRatingBar = findViewById(R.id.restaurant_rating);
        restaurantRatingBar.setRating((float) restaurant.getMeanRating());

        // Set Restaurant Category or Type
        TextView restaurantCategory = findViewById(R.id.restaurant_category);
        restaurantCategory.setText(restaurant.getType().toString());

    }

    /**
     * Displays the rating dialog for the restaurant.
     */
    private void showRatingDialog() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        RatingDialogFragment newFragment = new RatingDialogFragment();

        Bundle args = new Bundle();
        args.putString("restaurantId", restaurantId);
        args.putString("restaurantName", restaurant.getRestaurantName());
        args.putString("restaurantCategory", restaurant.getType().toString());
        args.putString("restaurantCity", restaurant.getRestaurantCity());

        newFragment.setArguments(args);

        newFragment.show(fragmentManager, "rating");
    }

    /**
     * Fetches and displays user ratings and reviews for the restaurant.
     */
    private void fetchRatings() {
        if (restaurantId == null) {
            Log.e(TAG, "restaurantId is null. Cannot fetch ratings.");
            return;
        }

        Log.d(TAG, "Attempting to fetch ratings for restaurantId: " + restaurantId);

        db.collection("restaurants").document(restaurantId).collection("comments")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<Rating> fetchedRatings = parseRatingsFromTask(task);
                        ratingAdapter.updateRatings(fetchedRatings);
                        Log.d(TAG, "Successfully fetched " + fetchedRatings.size() + " ratings.");
                    } else {
                        Log.d(TAG, "Error getting ratings: ", task.getException());
                    }
                });
    }

    /**
     * Parses ratings and reviews from the Firestore task result.
     *
     * @param task The Firestore query task result.
     * @return A list of Rating objects.
     */
    private List<Rating> parseRatingsFromTask(Task<QuerySnapshot> task) {
        List<Rating> fetchedRatings = new ArrayList<>();
        for (QueryDocumentSnapshot document : task.getResult()) {
            Rating rating = document.toObject(Rating.class);
            fetchedRatings.add(rating);
            logRatingDetails(rating);
        }
        return fetchedRatings;
    }

    /**
     * Logs details of a fetched rating.
     *
     * @param rating The Rating object to log.
     */
    private void logRatingDetails(Rating rating) {
        Log.d(TAG, String.format("Fetched Rating - userId: %s, userName: %s, rating: %.1f, text: %s, timestamp: %s",
                rating.getUserId(), rating.getUserName(), rating.getRating(), rating.getText(), rating.getTimestamp()));
    }

    /**
     * Sets up a Firestore listener to receive real-time updates for restaurant ratings and reviews.
     */
    private void setupRatingsListener() {
        if (restaurantId == null) {
            Log.e(TAG, "restaurantId is null. Cannot setup listener.");
            return;
        }

        ratingsListener = db.collection("restaurants").document(restaurantId).collection("comments")
                .orderBy("timestamp", Query.Direction.DESCENDING)
                .limit(1)
                .addSnapshotListener((snapshots, e) -> {
                    if (e != null) {
                        Log.w(TAG, "Listen failed.", e);
                        return;
                    }

                    assert snapshots != null;
                    for (DocumentChange dc : snapshots.getDocumentChanges()) {
                        switch (dc.getType()) {
                            case ADDED:
                                Rating newRating = dc.getDocument().toObject(Rating.class);
                                ratingsList.add(0, newRating); // Add new rating to the top of the list
                                ratingAdapter.notifyItemInserted(0);
                                ratingsRecyclerView.smoothScrollToPosition(0);
                                break;
                            case MODIFIED:
                            case REMOVED:
                                break;
                                // Future refactor should modify this to support rating data modification
                        }
                    }
                });
    }
}
