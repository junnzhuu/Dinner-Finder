package G26.Project.ViewController.Fragment;

import static G26.Project.Resources.Constants.CHART_CITY;
import static G26.Project.Resources.Constants.FIELD_CITY;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import G26.Project.Model.Data.RestaurantDatabase;
import G26.Project.R;
import G26.Project.Resources.Colors;

/**
 * Fragment to display a bar chart visualization of the number of restaurants per city.
 *
 * {@code @Author: Jun Zhu (Original)}
 *           UID : u7602081
 */
public class BarChartFragment extends Fragment {

    /**
     * Inflates the view for the BarChartFragment and sets up the bar chart visualization.
     *
     * @param inflater           LayoutInflater for inflating the view.
     * @param container          The parent view that the fragment's UI should be attached to.
     * @param savedInstanceState Bundle containing data that was saved from a previous instance.
     * @return The view for the fragment's UI or null.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for the fragment
        View view = inflater.inflate(R.layout.fragment_bar_chart, container, false);

        // Initialize the bar chart
        BarChart barChart = view.findViewById(R.id.barChart);
        setupBarChart(barChart);

        return view;
    }

    /**
     * Sets up the bar chart visualization.
     *
     * @param barChart The BarChart to be set up.
     */
    private void setupBarChart(BarChart barChart) {
        // Disable right axis labels
        barChart.getAxisRight().setDrawLabels(false);

        // Extract city data and count the number of restaurants for each city
        Map<String, Long> cityCounts = getCityRestaurantCounts();

        // Ensure only the top 20 cities are displayed
        List<Map.Entry<String, Long>> cityCountList = new ArrayList<>(cityCounts.entrySet());
        int sizeToShow = Math.min(cityCountList.size(), 20);

        // Populate data for the bar chart
        String[] xValues = new String[sizeToShow];
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        populateBarChartData(cityCountList, xValues, barEntries, sizeToShow);

        // Configure Y-axis settings
        setupYAxis(barChart);

        // Create the dataset and set its properties
        BarDataSet barDataSet = new BarDataSet(barEntries, CHART_CITY);
        configureDataSet(barDataSet, sizeToShow, xValues);

        // Set data for the bar chart and refresh it
        BarData barData = new BarData(barDataSet);
        barChart.setData(barData);
        barChart.getDescription().setEnabled(false);
        barChart.invalidate();

        // Configure the legend for the bar chart
        setupLegend(barChart, sizeToShow, barDataSet.getColors(), xValues);
    }

    /**
     * Extracts city data from the database and counts the number of restaurants for each city.
     *
     * @return A map of city names to their respective restaurant counts.
     */
    private Map<String, Long> getCityRestaurantCounts() {
        // Fetch data from the database
        Map<String, List<Object>> dataMap = RestaurantDatabase.getInstance().extractData();

        // Filter and convert city data
        List<String> cities = dataMap.get(FIELD_CITY).stream()
                .filter(obj -> obj instanceof String)
                .map(obj -> (String) obj)
                .collect(Collectors.toList());

        // Count the number of restaurants for each city
        return cities.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));
    }

    /**
     * Populates the data for the bar chart.
     *
     * @param cityCountList List of city names and their respective restaurant counts.
     * @param xValues       Array to store city names.
     * @param barEntries    List to store entries for the bar chart.
     * @param sizeToShow    The number of cities to display on the chart.
     */
    private void populateBarChartData(List<Map.Entry<String, Long>> cityCountList, String[] xValues, ArrayList<BarEntry> barEntries, int sizeToShow) {
        for (int i = 0; i < sizeToShow; i++) {
            xValues[i] = cityCountList.get(i).getKey();
            barEntries.add(new BarEntry(i, cityCountList.get(i).getValue().floatValue()));
        }
    }

    /**
     * Configures the Y-axis settings for the bar chart.
     *
     * @param barChart The BarChart to configure.
     */
    private void setupYAxis(BarChart barChart) {
        YAxis yAxis = barChart.getAxisLeft();
        yAxis.setAxisMinimum(0f);   // Start value for the Y-axis
        yAxis.setAxisMaximum(50f); // End value for the Y-axis
        yAxis.setAxisLineWidth(2f);
        yAxis.setAxisLineColor(Color.BLACK);
        yAxis.setGranularity(50f);  // Set the interval of the Y-axis to 50
        yAxis.setLabelCount(11, true); // Set the number of Y-axis labels, ensuring all labels are displayed
        yAxis.setTextSize(15f);    // Increase the font size for the Y-axis numbers
    }

    /**
     * Configures the dataset properties for the bar chart.
     *
     * @param barDataSet The BarDataSet to configure.
     * @param sizeToShow The number of cities to display on the chart.
     * @param xValues    Array of city names.
     */
    private void configureDataSet(BarDataSet barDataSet, int sizeToShow, String[] xValues) {
        List<Integer> colors = new ArrayList<>();
        int[] baseColors = Colors.BASE_COLORS;

        for (int i = 0; i < sizeToShow; i++) {
            colors.add(baseColors[i % baseColors.length]);  // Cycle through baseColors for color assignment
        }

        barDataSet.setColors(colors);  // Set colors for the bars
        barDataSet.setValueTextSize(10f);  // Reduce the font size for numbers on the bars
    }

    /**
     * Configures the legend settings for the bar chart.
     *
     * @param barChart The BarChart to configure.
     * @param sizeToShow The number of cities to display on the chart.
     * @param colors List of colors used for the bars.
     * @param xValues Array of city names.
     */
    private void setupLegend(BarChart barChart, int sizeToShow, List<Integer> colors, String[] xValues) {
        com.github.mikephil.charting.components.Legend legend = barChart.getLegend();

        // Create custom legend entries
        LegendEntry[] legendEntries = new LegendEntry[sizeToShow];
        for (int i = 0; i < sizeToShow; i++) {
            LegendEntry entry = new LegendEntry();
            entry.formColor = colors.get(i);
            entry.label = xValues[i];  // Assign the city name as the label for the color
            legendEntries[i] = entry;
        }

        // Set and adjust the legend properties
        legend.setCustom(legendEntries);
        legend.setWordWrapEnabled(true);
        legend.setTextSize(14f);
        legend.setXEntrySpace(10f);
        legend.setYEntrySpace(5f);
        legend.setOrientation(com.github.mikephil.charting.components.Legend.LegendOrientation.HORIZONTAL);
        legend.setVerticalAlignment(com.github.mikephil.charting.components.Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(com.github.mikephil.charting.components.Legend.LegendHorizontalAlignment.CENTER);
        legend.setYOffset(-10f); // Add a 10-pixel gap at the bottom of the legend
    }
}
