package G26.Project.ViewController.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import G26.Project.ViewController.Fragment.BarChartFragment;
import G26.Project.ViewController.Fragment.LineChartFragment;
import G26.Project.ViewController.Fragment.PieChartFragment;
import G26.Project.ViewController.Fragment.RadarChartFragment;

/**
 * A custom adapter class for ViewPager2 that provides fragments for visualization in charts.
 * This adapter supports BarChart, LineChart, PieChart, and RadarChart fragments.
 *
 * {@code @Author: Jun Zhu (Original)}
 *          UID : u7602081
 */
public class ViewPagerAdapter extends FragmentStateAdapter {

    /**
     * Constructor for ViewPagerAdapter.
     *
     * @param fragmentActivity The context in which the adapter operates, typically the hosting activity.
     */
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    /**
     * Provides the fragment to be displayed at a given position.
     *
     * @param position The position in the ViewPager2.
     * @return A Fragment corresponding to the specified position.
     */
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                // Return BarChartFragment for position 0.
                return new BarChartFragment();
            case 1:
                // Return LineChartFragment for position 1.
                return new LineChartFragment();
            case 2:
                // Return PieChartFragment for position 2.
                return new PieChartFragment();
            case 3:
                // Return RadarChartFragment for position 3.
                return new RadarChartFragment();
            default:
                // By default, return BarChartFragment.
                return new BarChartFragment();
        }
    }

    /**
     * Returns the total count of fragments to be displayed in the ViewPager2.
     *
     * @return Number of fragments.
     */
    @Override
    public int getItemCount() {
        // As we have 4 chart types (Bar, Line, Pie, and Radar), we return 4.
        return 4;
    }
}
