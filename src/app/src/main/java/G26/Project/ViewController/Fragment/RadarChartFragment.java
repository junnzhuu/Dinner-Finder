package G26.Project.ViewController.Fragment;

import static G26.Project.Resources.Constants.CHART_COLOR_ORANGE;
import static G26.Project.Resources.Constants.CHART_RESTAURANT_NUM;
import static G26.Project.Resources.Constants.DATABASE_RATING_INTERVALS;

import androidx.fragment.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;
import com.github.mikephil.charting.components.Legend;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import G26.Project.Model.Data.RestaurantDatabase;
import G26.Project.R;

/**
 * Fragment that displays a radar chart to visualize restaurant ratings.
 * The chart provides insights into the distribution of ratings across different intervals.
 *
 * {@code @Author: Jun Zhu (Original)}
 *           UID : u7602081
 */
public class RadarChartFragment extends Fragment {

    /**
     * This method is triggered when creating the view for the fragment.
     * It sets up the layout and initializes the radar chart with data.
     *
     * @param inflater           Used to inflate the fragment's UI.
     * @param container          Parent view to attach fragment's UI.
     * @param savedInstanceState Contains data from a previously saved instance.
     * @return The view for the fragment's UI.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout associated with this fragment
        View view = inflater.inflate(R.layout.fragment_radar_chart, container, false);

        // Find the RadarChart view in the inflated layout
        RadarChart radarChart = view.findViewById(R.id.radarChart);

        // Extract data and set up the radar chart
        List<RadarEntry> entries = extractRadarData();
        setupRadarChart(radarChart, entries);

        return view;
    }

    /**
     * Extracts rating data from the RestaurantDatabase.
     * This method parses the rating intervals and converts them into radar chart entries.
     *
     * @return List of radar entries representing the distribution of restaurant ratings.
     */
    private List<RadarEntry> extractRadarData() {
        List<RadarEntry> entries = new ArrayList<>();

        // Retrieve rating data from the database
        Map<String, List<Object>> dataMap = RestaurantDatabase.extractData();
        List<Object> ratingIntervals = dataMap.get(DATABASE_RATING_INTERVALS);

        // Convert the rating intervals into RadarEntry objects
        for (int i = 0; i < 5; i++) {
            String[] splitData = ratingIntervals.get(i).toString().split(": ");
            entries.add(new RadarEntry(Float.parseFloat(splitData[1])));
        }

        return entries;
    }

    /**
     * Configures the radar chart display properties and sets the provided data.
     *
     * @param radarChart The radar chart view to be configured.
     * @param entries    The data entries to be plotted on the radar chart.
     */
    private void setupRadarChart(RadarChart radarChart, List<RadarEntry> entries) {
        // Create a dataset from the radar entries and configure its appearance
        RadarDataSet radarDataSet = new RadarDataSet(entries, CHART_RESTAURANT_NUM);
        radarDataSet.setColor(Color.parseColor(CHART_COLOR_ORANGE));  // Line color
        radarDataSet.setFillColor(Color.WHITE);               // Fill color inside the line
        radarDataSet.setDrawFilled(true);                     // Enable filled area beneath the line
        radarDataSet.setFillAlpha(180);                       // Set transparency of the filled area
        radarDataSet.setLineWidth(4f);                        // Set the width of the radar line
        radarDataSet.setDrawHighlightCircleEnabled(true);     // Highlight circles on touch
        radarDataSet.setDrawHighlightIndicators(false);       // Disable default indicators on touch

        List<IRadarDataSet> dataSets = new ArrayList<>();
        dataSets.add(radarDataSet);

        RadarData data = new RadarData(dataSets);
        data.setValueTextSize(20f);  // Font size for the value labels
        data.setDrawValues(true);    // Display value labels

        radarChart.setData(data);    // Set the radar data to the radar chart

        // Configure the appearance of the x-axis labels
        setupRadarChartXAxis(radarChart);

        // Set properties for the legend of the radar chart
        Legend legend = radarChart.getLegend();
        legend.setTextSize(14f);

        // Disable chart description and initiate animations
        radarChart.getDescription().setEnabled(false);
        radarChart.animateXY(1000, 1000);
        radarChart.invalidate();  // Refresh the chart
    }

    /**
     * Configures the XAxis of the radar chart.
     * This sets the labels and appearance of the x-axis on the radar chart.
     *
     * @param radarChart The radar chart whose XAxis needs to be configured.
     */
    private void setupRadarChartXAxis(RadarChart radarChart) {
        XAxis xAxis = radarChart.getXAxis();
        xAxis.setTextSize(14f);  // Font size for the x-axis labels
        // Custom formatter to convert numeric indices to rating intervals
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                String[] ratings = {"0-1", "1-2", "2-3", "3-4", "4-5"};
                int index = (int) Math.floor(value);
                if (index >= 0 && index < ratings.length) {
                    return ratings[index];
                } else {
                    return "";  // Return empty for out of bounds values
                }
            }
        });
    }
}
