package G26.Project.ViewController;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import G26.Project.ViewController.Adapter.ViewPagerAdapter;
import G26.Project.R;

/**
 * The ChartsActivity is responsible for displaying a set of charts to the user.
 * It uses a combination of a TabLayout and a ViewPager2 to allow the user to switch
 * between different chart views by selecting the appropriate tab.
 *
 * {@code @Author: Jun Zhu (Original)}
 *           UID : u7602081
 */
public class ChartsActivity extends AppCompatActivity {

    // Tab layout for chart selection
    private TabLayout tabLayout;

    // ViewPager2 to display chart content for the selected tab
    private ViewPager2 viewPager2;

    // Adapter for the ViewPager2, responsible for providing the chart views
    private ViewPagerAdapter vpAdapter;

    /**
     * Called when the activity is starting.
     * This method sets up the UI and initializes interactions.
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *                           previously being shut down then this Bundle contains the data it most
     *                           recently supplied. Otherwise it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the layout for this activity
        setContentView(R.layout.activity_charts);

        // Initialize UI components
        setupUIComponents();

        // Setup interaction listeners between tabLayout and viewPager2
        setupListeners();
    }

    /**
     * Initializes the UI components of the activity.
     */
    private void setupUIComponents() {
        tabLayout = findViewById(R.id.tabLayout);
        viewPager2 = findViewById(R.id.viewpager);
        vpAdapter = new ViewPagerAdapter(this);

        // Setting the adapter for the viewPager2
        viewPager2.setAdapter(vpAdapter);
    }

    /**
     * Sets up the interactions between the tabLayout and viewPager2.
     * Ensures that selecting a tab switches to the appropriate page in the viewPager2
     * and vice-versa.
     */
    private void setupListeners() {
        // Listener to handle tab selection
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // When a tab is selected, switch to the corresponding page in the viewPager2
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // Do nothing when a tab is unselected
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // Do nothing when a tab is reselected
            }
        });

        // Listener to handle page changes in the viewPager2
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                // When a page in the viewPager2 is selected, highlight the corresponding tab
                tabLayout.getTabAt(position).select();
            }
        });
    }
}
