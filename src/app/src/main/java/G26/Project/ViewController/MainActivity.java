package G26.Project.ViewController;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static G26.Project.Resources.Constants.BROADCAST_DATA_UPDATE;
import static G26.Project.Resources.Constants.EXTRA_RESTAURANT_UPDATE;
import static G26.Project.Resources.Constants.FIELD_MEAN_RATING;
import static G26.Project.Resources.Constants.LOGOUT_DIALOG_CANCEL;
import static G26.Project.Resources.Constants.LOGOUT_DIALOG_CONFIRM;
import static G26.Project.Resources.Constants.LOGOUT_DIALOG_MESSAGE;
import static G26.Project.Resources.Constants.LOGOUT_DIALOG_TITLE;
import static G26.Project.Resources.Constants.LOGOUT_SUCCESS;
import static G26.Project.Resources.Constants.PATH_RESTAURANT;
import static G26.Project.Resources.Constants.SEARCH_INPUT_FIND;
import static G26.Project.Resources.Constants.SEARCH_INPUT_INVALID;
import static G26.Project.Resources.Constants.SEARCH_INPUT_RESULT;
import static G26.Project.Resources.Constants.SEARCH_INPUT_TEXT;
import static G26.Project.Resources.Constants.TUT_CONFIRM;
import static G26.Project.Resources.Constants.TUT_INSTRUCTION;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import G26.Project.Model.Data.RestaurantDatabase;
import G26.Project.Model.Restaurant.Restaurant;
import G26.Project.Model.Search.AbstractQueryParser;
import G26.Project.Model.Search.ParserFactory;
import G26.Project.Model.Search.QueryParserFactory;
import G26.Project.Model.Search.RestaurantSearchFacade;
import G26.Project.R;
import G26.Project.Simulator.MainSimulator;
import G26.Project.ViewController.Adapter.RestaurantAdapter;
import G26.Project.ViewController.Managers.FilterDialogManager;
import G26.Project.ViewController.Managers.NavigationManager;
import G26.Project.ViewController.Util.FireStoreService;

/**
 * The main activity of the application.
 * <p>
 * {@code @Author: Jing Li}
 *           UID : u7533831
 * <p>
 * {@code @Author: Jun Zhu}
 *          UID : u7602081
 * <p>
 * {@code @Author: Catherine Jiawei Ye}
 *          UID : u7574419
 * <p>
 * {@code @Author: Jiawei Liu}
 *           UID : u7603069
 */
public class MainActivity extends AppCompatActivity{

    private static final String TAG = "MainActivity";

    private DrawerLayout drawerLayout;
    private RestaurantDatabase restaurantDatabase;
    private RestaurantAdapter restaurantAdapter;
    private Query query;
    private EditText searchBox;

    private LocationManager locationManager;

    private FilterDialogManager filterDialogManager;

    private NavigationManager navigationActivity;
    private ListenerRegistration registration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        setContentView(R.layout.activity_main);

        // Set the content view and initialize various components
        initViews();
        initFireStore();
        setupFireStoreListener();
        initFabButton();
        initNavigationView();
        setupBottomNavigation();
        setupFilterDialogManager();
        setupDrawerNavigation();
        setupTutorialButton();

        // start the load data stream to simulate the user actions
        MainSimulator mainSimulator = new MainSimulator(MainActivity.this);
        mainSimulator.loader();

        // Set status bar color (for Android 5.0 and above)
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.lavender));
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (registration != null) {
            registration.remove(); // Stop the Firestore listener
            Log.d(TAG, "Firestore listener stopped.");
        }
    }

    /**
     * Set up the navigation UI
     * {@code @Author: Jing Li}
     */
    private void setupDrawerNavigation() {
        drawerLayout = findViewById(R.id.drawer_layout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    /**
     *  Sets up the filter dialog manager, its associated button, and the behavior when filter values change.
     * {@code @Author: Jiawei Liu}
     */
    private void setupFilterDialogManager() {
        filterDialogManager = new FilterDialogManager(this, new FilterDialogManager.FilterValues());
        ImageButton imageButton = findViewById(R.id.imageButton);
        imageButton.setOnClickListener(v -> filterDialogManager.showFilterDialog());

        filterDialogManager.setOnFilterValuesChangedListener(newFilterValues -> {
            ParserFactory parserFactory = new QueryParserFactory();
            AbstractQueryParser parser = parserFactory.createParser(null, newFilterValues, MainActivity.this);
            restaurantAdapter.updateData(RestaurantDatabase
                    .getInstance()
                    .executeQuery(parser.parse()));

        });
    }
    /**
     * Sets up the tutorial button and its click listener.
     * {@code @Author: Jiawei Liu}
     */
    private void setupTutorialButton() {
        FloatingActionButton fab = findViewById(R.id.questionTutorial);
        fab.setOnClickListener(view -> showTutorialDialog());
    }

    /**
     * // Displays an alert dialog with tutorial instructions.
     * {@code @Author: Jiawei Liu}
     */
    private void showTutorialDialog() {
        new AlertDialog.Builder(MainActivity.this)
                .setTitle(TUT_INSTRUCTION)
                .setMessage(getString(R.string.question_button_description))
                .setPositiveButton(TUT_CONFIRM, null)
                .show();
    }

    /**
     * Reset the list view to its initial state
     * {@code @Author: Jing Li}
     */
    public void resetListView() {
        restaurantAdapter.updateData(RestaurantDatabase.getInstance().toList());
    }

    /**
     *  Initialize the navigation view
     *  {@code @Author: Jun Zhu & Catherine Jiawei Ye}
     */
    private void initNavigationView() {
        NavigationView navigationView = findViewById(R.id.nav_view);
        try {
            navigationActivity.initNavigationView(navigationView, MainActivity.this);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            Menu menu = navigationView.getMenu();
            MenuItem commentsHistoryItem = menu.findItem(R.id.nav_comments_history); // 请确保你的ID匹配
            if (commentsHistoryItem != null) {
                commentsHistoryItem.setOnMenuItemClickListener(item -> {
                    Intent intent = new Intent(MainActivity.this, CommentHistoryActivity.class);
                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);
                    return true;
                });
            }

            /*
             * Find the logout item in the navigation menu using its ID and set an on-click listener for the logout menu item.
             * {@code @Author: Catherine Jiawei Ye}
             */
            MenuItem logoutItem = menu.findItem(R.id.nav_logout);
            logoutItem.setOnMenuItemClickListener(item -> {
                logoutMenu(MainActivity.this);
                return true;
            });
        }

    }

    /**
     * Overrides the onBackPressed method to handle the behavior when the back button is pressed.
     * If the navigation drawer is open, it will close the drawer.
     * If the navigation drawer is already closed, it will call the default back button behavior.
     * <p>
     * {@code @Author: Jun Zhu}
     */
    @Override
    public void onBackPressed() {
        // Check if the navigation drawer is open on the start side (left side for LTR languages)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            // If the drawer is open, close it.
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            // If the drawer is already closed, call the default onBackPressed behavior.
            super.onBackPressed();
        }
    }


    /**
     * Set up the bottom navigation view
     * {@code @Author: Jing Li}
     * {@code @Contributor: Jun Zhu}
     */
    private void setupBottomNavigation() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.bottom_home) {
                // If already on the Home screen, no need to do anything
                return true;
            } else if (itemId == R.id.bottom_chat) {
                startActivity(new Intent(MainActivity.this, RecentChatsActivity.class));
                return false;
            }
            return false;
        });
    }

    /**
     * Retrieves the latitude of the device's last known location.
     * {@code @Author: Catherine Jiawei Ye}
     */
    public double getLatitude() {
        if (ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this, ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            if (lastKnownLocation != null) {
                return lastKnownLocation.getLatitude();
            }
        }
        return 0.0;
    }

    /**
     * Retrieves the longitude of the device's last known location.
     * {@code @Author: Catherine Jiawei Ye}
     */
    public double getLongitude() {
        if (ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this, ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            if (lastKnownLocation != null) {
                return lastKnownLocation.getLongitude();
            }
        }
        return 0.0;
    }

    /**
     * Initialize various views and components
     * {@code @Author: Jing Li}
     * {@code @Contributor: Jun Zhu}
     */
    private void initViews() {
        Log.d(TAG, "Initializing views...");
        restaurantDatabase = RestaurantDatabase.getInstance();
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        restaurantAdapter = new RestaurantAdapter(new ArrayList<>());
        recyclerView.setAdapter(restaurantAdapter);
        Button btnSearch = findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(v -> searchInput());
        searchBox = findViewById(R.id.searchBox);
        navigationActivity= new NavigationManager();
    }

    /**
     * Initialize the floating action button
     * {@code @Author: Jun Zhu}
     */
    private void initFabButton() {
        FloatingActionButton fabCharts = findViewById(R.id.fab_charts);
        fabCharts.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ChartsActivity.class);
            startActivity(intent);
        });
    }

    /**
     * Handle user input for searching restaurants
     * {@code @Author: Jing Li}
     */
    private void searchInput() {
        String searchText = searchBox.getText().toString().trim();
        if (!searchText.isEmpty()) {
            // Search function
            RestaurantSearchFacade facade = new RestaurantSearchFacade();
            try {
                List<Restaurant> results = facade.search(searchText);
                restaurantAdapter.updateData(results);
                Toast.makeText(this, "Keywords :" + searchText + " \n"
                        + SEARCH_INPUT_FIND + results.size() + SEARCH_INPUT_RESULT, Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(this, SEARCH_INPUT_INVALID, Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * helper function to for the simulator to easy calling to perform the search action
     * @param searchText: the keywords to be searched
     * {@code @Author: Jing Li}
     */
    public void searchInput(String searchText) {
        if (!searchText.isEmpty()) {
            // Search function
            RestaurantSearchFacade facade = new RestaurantSearchFacade();
            try {
                List<Restaurant> results = facade.search(searchText);
                restaurantAdapter.updateData(results);
                Toast.makeText(this, "Keywords :" + searchText + " \n"
                        + SEARCH_INPUT_FIND + results.size() + SEARCH_INPUT_RESULT, Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(this, "Keywords :" + searchText + " \n"
                        + SEARCH_INPUT_INVALID, Toast.LENGTH_SHORT).show();
            }
            Toast.makeText(MainActivity.this, SEARCH_INPUT_TEXT + searchText, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Initialize Firestore and set up a query
     * {@code @Author: Jing Li}
     */
    private void initFireStore() {
        //FireStoreInitializerUtil.initializeRandomRestaurant();
        Log.d(TAG, "Initializing Firestore...");

        FireStoreService fireStoreService = FireStoreService.getInstance();
        query = fireStoreService.getFirebaseDatabase().collection(PATH_RESTAURANT)
                .orderBy(FIELD_MEAN_RATING, Query.Direction.DESCENDING);
    }

    /**
     * Set up Firestore listener for restaurant data changes
     * {@code @Author: Jing Li}
     */
    private void setupFireStoreListener() {
        Log.d(TAG, "Setting up Firestore listener...");

        registration = query.addSnapshotListener((queryDocumentSnapshots, e) -> {
            if (e != null) {
                Log.e(TAG, "Listen failed.", e);
                return;
            }
            assert queryDocumentSnapshots != null;
            for (DocumentChange docChange : queryDocumentSnapshots.getDocumentChanges()) {
                Restaurant restaurant = docChange.getDocument().toObject(Restaurant.class);
                switch (docChange.getType()) {
                    case ADDED:
                        Log.d(TAG, "New restaurant: " + restaurant.getRestaurantName());
                        restaurantDatabase.insertRestaurant(restaurant);
                        restaurantAdapter.addData(restaurant);
                        break;
                    case MODIFIED:
                        Log.d(TAG, "Modified restaurant: " + restaurant.getRestaurantName());
                        restaurantDatabase.updateRestaurant(restaurant);
                        restaurantAdapter.updateDataForItem(restaurant);
                        Intent intent = new Intent(BROADCAST_DATA_UPDATE);
                        intent.putExtra(EXTRA_RESTAURANT_UPDATE, restaurant);
                        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
                        break;
                }
            }
        });
    }

    /**
     * Displays a logout confirmation dialog. If the user confirms, it will log out the user,
     * navigate to the LoginActivity, and show a toast indicating successful logout.
     *
     * @param mainActivity The instance of the MainActivity where this dialog will be displayed.
     * <p>
     * {@code @Author: Jun Zhu}
     */
    private void logoutMenu(MainActivity mainActivity) {
        // Initialize an AlertDialog.Builder with the current activity context.
        AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);

        // Set the title for the logout confirmation dialog.
        builder.setTitle(LOGOUT_DIALOG_TITLE);

        // Set the message to display inside the logout confirmation dialog.
        builder.setMessage(LOGOUT_DIALOG_MESSAGE);

        // Set the positive (confirmation) button behavior.
        builder.setPositiveButton(LOGOUT_DIALOG_CONFIRM, (dialog, which) -> {
            // Create an intent to navigate to LoginActivity.
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);

            // Add flags to clear the activity stack and start a new task.
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Start the LoginActivity.
            startActivity(intent);

            // Close the current activity (MainActivity).
            finish();

            // Show a toast message indicating a successful logout.
            Toast.makeText(MainActivity.this, LOGOUT_SUCCESS, Toast.LENGTH_SHORT).show();
        });

        // Set the negative (cancellation) button behavior. Currently, it does nothing when pressed.
        builder.setNegativeButton(LOGOUT_DIALOG_CANCEL, (dialog, which) -> {
            // No action is taken if the cancel button is pressed.
        });

        // Show the logout confirmation dialog to the user.
        builder.show();
    }

}