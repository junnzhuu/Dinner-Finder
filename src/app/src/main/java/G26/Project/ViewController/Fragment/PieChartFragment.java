package G26.Project.ViewController.Fragment;

import static G26.Project.Resources.Constants.CHART_RESTAURANT_TYPE;
import static G26.Project.Resources.Constants.DATABASE_TYPE;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import G26.Project.Model.Restaurant.RestaurantType;
import G26.Project.R;
import G26.Project.Model.Data.RestaurantDatabase;
import G26.Project.Resources.Colors;

/**
 * Represents a fragment that displays a pie chart visualization of restaurant types.
 *
 * {@code @Author: Jun Zhu (Original)}
 *           UID : u7602081
 */
public class PieChartFragment extends Fragment {

    /**
     * Called to have the fragment instantiate its user interface view.
     *
     * @param inflater           The LayoutInflater object that can be used to inflate any views in the fragment.
     * @param container          The parent view that the fragment's UI should be attached to.
     * @param savedInstanceState Bundle containing data that was saved from a previous instance.
     * @return Return the View for the fragment's UI, or null.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pie_chart, container, false);

        // Get pie chart view from the layout
        PieChart pieChart = view.findViewById(R.id.pieChart);

        // Process and set data for the pie chart
        ArrayList<PieEntry> pieEntries = processRestaurantData();
        setupPieChart(pieChart, pieEntries);

        return view;
    }

    /**
     * Processes restaurant data and returns pie entries representing the percentage of each restaurant type.
     *
     * @return A list of pie entries.
     */
    private ArrayList<PieEntry> processRestaurantData() {
        ArrayList<PieEntry> pieEntries = new ArrayList<>();

        // Extract data from the database
        Map<String, List<Object>> extractedData = RestaurantDatabase.extractData();
        List<Object> types = extractedData.get(DATABASE_TYPE);

        // Calculate the frequency for each restaurant type
        Map<RestaurantType, Integer> frequencyMap = new HashMap<>();
        for (Object obj : types) {
            RestaurantType type = (RestaurantType) obj;
            frequencyMap.put(type, frequencyMap.getOrDefault(type, 0) + 1);
        }

        // Convert the frequency to percentage and add to pie entries
        for (Map.Entry<RestaurantType, Integer> entry : frequencyMap.entrySet()) {
            float percentage = ((float) entry.getValue() / types.size()) * 100;
            pieEntries.add(new PieEntry(percentage, entry.getKey().toString()));
        }
        return pieEntries;
    }

    /**
     * Configures the pie chart with the provided entries.
     *
     * @param pieChart  The pie chart to configure.
     * @param pieEntries The pie chart entries.
     */
    private void setupPieChart(PieChart pieChart, ArrayList<PieEntry> pieEntries) {
        PieDataSet pieDataSet = new PieDataSet(pieEntries, CHART_RESTAURANT_TYPE);

        // Get color array from resources and set it to the data set
        int[] selectedColors = Colors.SELECTED_COLORS;
        pieDataSet.setColors(selectedColors);

        // Increase the font size of the numbers on the pie chart
        pieDataSet.setValueTextSize(16f);

        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);

        // Disable the pie chart description
        pieChart.getDescription().setEnabled(false);

        // Animate the pie chart
        pieChart.animateY(1000);

        // Configure the pie chart's legend
        configurePieChartLegend(pieChart);

        // Set pie chart's padding
        pieChart.setExtraOffsets(10f, 0f, 10f, 0f);

        // Refresh the chart
        pieChart.invalidate();
    }

    /**
     * Configures the legend of the pie chart.
     *
     * @param pieChart The pie chart whose legend needs to be configured.
     */
    private void configurePieChartLegend(PieChart pieChart) {
        com.github.mikephil.charting.components.Legend legend = pieChart.getLegend();

        // Enable word wrapping for the legend
        legend.setWordWrapEnabled(true);

        // Set font size, entry spacing, and orientation for the legend
        legend.setTextSize(14f);
        legend.setXEntrySpace(10f);
        legend.setYEntrySpace(5f);
        legend.setOrientation(com.github.mikephil.charting.components.Legend.LegendOrientation.HORIZONTAL);
        legend.setVerticalAlignment(com.github.mikephil.charting.components.Legend.LegendVerticalAlignment.TOP);
        legend.setHorizontalAlignment(com.github.mikephil.charting.components.Legend.LegendHorizontalAlignment.CENTER);
    }
}
