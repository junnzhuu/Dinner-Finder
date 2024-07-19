package G26.Project.ViewController.Fragment;

import static G26.Project.Model.Data.RestaurantDatabase.extractData;
import static G26.Project.Resources.Constants.CHART_COST_INTERVALS;
import static G26.Project.Resources.Constants.CHART_RESTAURANT_COUNT_PER_INTERVAL;
import static G26.Project.Resources.Constants.CHART_RESTAURANT_RECORD;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import G26.Project.R;

/**
 * Represents a fragment that displays a line chart visualization of restaurant data.
 *
 * {@code @Author: Jun Zhu (Original)}
 *           UID : u7602081
 */
public class LineChartFragment extends Fragment {

    private LineChart lineChart;
    private List<String> xValues;

    /**
     * Called to have the fragment instantiate its user interface view.
     *
     * @param inflater           The LayoutInflater object that can be used to inflate any views in the fragment.
     * @param container          The parent view that the fragment's UI should be attached to.
     * @param savedInstanceState Bundle containing data that was saved from a previous instance.
     * @return Return the View for the fragment's UI, or null.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_line_chart, container, false);

        // Extract data from the database
        Map<String, List<Object>> dataMap = extractData();
        List<Object> costIntervals = dataMap.get(CHART_COST_INTERVALS);

        // Process data points and X-axis labels
        List<Entry> entries = extractEntriesAndXValues(costIntervals);

        // Initialize and configure the appearance and data of the line chart
        setupLineChart(view, entries, costIntervals.size() - 1);

        return view;
    }

    /**
     * Extracts chart entries and X-axis values from the provided cost intervals.
     *
     * @param costIntervals The intervals of costs to process.
     * @return A list of chart entries.
     */
    private List<Entry> extractEntriesAndXValues(List<Object> costIntervals) {
        List<Entry> entries = new ArrayList<>();
        xValues = new ArrayList<>();
        for (int i = 0; i < costIntervals.size(); i++) {
            String[] parts = costIntervals.get(i).toString().split(": ");
            xValues.add(parts[0]);
            float restaurantCount = Float.parseFloat(parts[1]);
            entries.add(new Entry(i, restaurantCount));
        }
        return entries;
    }

    /**
     * Configures the line chart with the provided entries and data size.
     *
     * @param view     The view containing the line chart.
     * @param entries  The chart entries.
     * @param dataSize The number of data entries.
     */
    private void setupLineChart(View view, List<Entry> entries, int dataSize) {
        lineChart = view.findViewById(R.id.lineChart);

        // Set description for the chart
        Description description = new Description();
        description.setText(CHART_RESTAURANT_RECORD);
        description.setPosition(150f, 15f);
        lineChart.setDescription(description);

        // Disable right axis labels
        lineChart.getAxisRight().setDrawLabels(false);

        // Configure X-axis
        configureXAxis(dataSize);

        // Configure left Y-axis
        configureLeftYAxis();

        // Configure and set data set to the chart
        configureAndSetData(entries);
    }

    /**
     * Configures the X-axis properties of the line chart to ensure it represents the data accurately.
     *
     * @param dataSize The number of data points or entries that will be plotted on the chart.
     */
    private void configureXAxis(int dataSize) {
        // Retrieve the X-axis from the line chart
        XAxis xAxis = lineChart.getXAxis();

        // Set the X-axis labels to appear at the bottom of the chart
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        // Use the provided xValues for labeling the X-axis
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xValues));

        // Define the range of the X-axis. From 0 to the number of data entries
        xAxis.setAxisMinimum(0f);
        xAxis.setAxisMaximum(dataSize);

        // Display one label per data entry
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(dataSize + 1);

        // Set the font size for X-axis labels
        xAxis.setTextSize(12f);

        // To prevent the first and last label from being clipped off the screen
        xAxis.setAvoidFirstLastClipping(true);
    }

    /**
     * Configures the left Y-axis properties of the line chart.
     * This method defines how the vertical axis will appear and how data is represented vertically.
     */
    private void configureLeftYAxis() {
        // Retrieve the left Y-axis from the line chart
        YAxis yAxis = lineChart.getAxisLeft();

        // Define the range of the Y-axis. From 0 to 200
        yAxis.setAxisMinimum(0f);
        yAxis.setAxisMaximum(200f);

        // Set the intervals at which labels appear. Here, every 5 units on the scale
        yAxis.setGranularity(5f);

        // Set the number of labels that will be drawn on the Y-axis
        yAxis.setLabelCount(11);

        // Define the width and color of the Y-axis line
        yAxis.setAxisLineWidth(2f);
        yAxis.setAxisLineColor(getResources().getColor(R.color.black));

        // Set the font size for Y-axis labels
        yAxis.setTextSize(14f);
    }

    /**
     * Sets up the dataset properties for the line chart and applies this data to the chart.
     * The method also refreshes the chart to reflect the newly applied data.
     *
     * @param entries List of chart data points to be plotted.
     */
    private void configureAndSetData(List<Entry> entries) {
        // Create a dataset from the provided entries and set its label
        LineDataSet dataSet = new LineDataSet(entries, CHART_RESTAURANT_COUNT_PER_INTERVAL);

        // Define visual properties of the data line such as its color and width
        dataSet.setColor(Color.BLUE);
        dataSet.setLineWidth(3f);

        // Set the font size for data value labels that appear above data points
        dataSet.setValueTextSize(16f);

        // Create a data object from the dataset and apply it to the line chart
        LineData lineData = new LineData(dataSet);
        lineChart.setData(lineData);

        // Define the properties of the chart legend, including its font size
        lineChart.getLegend().setTextSize(16f);

        // Force the chart to redraw itself to reflect the newly applied data and settings
        lineChart.invalidate();
    }
}
