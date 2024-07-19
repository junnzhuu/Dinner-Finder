package G26.Project.ViewController.Managers;

import static G26.Project.Resources.Constants.FILTER_INPUT_INVALID_TEXT;
import static G26.Project.Resources.Constants.FILTER_SHOW_TEXT;
import static G26.Project.Resources.Constants.FILTER_TEXT;

import android.content.DialogInterface;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.lang.reflect.Field;

import G26.Project.R;
import G26.Project.ViewController.MainActivity;

/**
 *  The `FilterDialogManager` class is responsible for managing and displaying a filter dialog
 *  for restaurant search options. It allows users to customize various filters such as location,
 *  cuisine type, sorting, cost, and rating.
 *
 * {@code @Author: Jiawei Liu}
 * <p>
 * {@code @Contributor & Refactor: Jing Li : abstract and refactor this class from MainActivity,
 * and rewrite and document it in modules}
 */
public class FilterDialogManager {

    private final MainActivity context;  // The MainActivity context
    private FilterValues currentFilterValues;  // Current filter values to initialize the dialog with

    /**
     * Constructs a FilterDialogManager instance.
     *
     * @param context       The MainActivity context.
     * @param filterValues  The current filter values to initialize the dialog with.
     */
    public FilterDialogManager(MainActivity context, FilterValues filterValues) {
        this.context = context;
        this.currentFilterValues = filterValues != null ? filterValues : new FilterValues();
    }

    /**
     * Displays the filter dialog to the user.
     */
    public void showFilterDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(FILTER_TEXT);

        // Inflate the custom dialog layout
        View dialogView = View.inflate(context, R.layout.option_box, null);

        // Set up UI elements for the filter dialog
        setUpUIElements(dialogView, builder);
        builder.create().show();
    }

    /**
     * Sets up UI elements for the filter dialog.
     *
     * @param dialogView The dialog's view.
     * @param builder    The dialog builder.
     */
    private void setUpUIElements(View dialogView, AlertDialog.Builder builder) {
        // Initialize and configure UI elements
        CheckBox checkBoxAroundMe = dialogView.findViewById(R.id.checkbox_restaurantcity);
        checkBoxAroundMe.setChecked(currentFilterValues.isAroundMeSelected);

        Spinner spinnerType = initializeSpinner(dialogView, R.id.spinner_type, context.getResources().getStringArray(R.array.cuisine_types));
        spinnerType.setSelection(((ArrayAdapter<String>) spinnerType.getAdapter()).getPosition(currentFilterValues.selectedType));

        Spinner spinnerSort = initializeSpinner(dialogView, R.id.spinner_sort, context.getResources().getStringArray(R.array.sorting_methods));
        spinnerSort.setSelection(((ArrayAdapter<String>) spinnerSort.getAdapter()).getPosition(currentFilterValues.selectedSort));

        TextView tvMinCostValue = dialogView.findViewById(R.id.tv_min_cost_value);
        TextView tvMaxCostValue = dialogView.findViewById(R.id.tv_max_cost_value);
        TextView tvMinRatingValue = dialogView.findViewById(R.id.tv_min_rating_value);
        TextView tvMaxRatingValue = dialogView.findViewById(R.id.tv_max_rating_value);

        SeekBar seekBarMinCost = setUpSeekBarForCost(dialogView, R.id.seekBar_min_cost, tvMinCostValue);
        seekBarMinCost.setProgress(currentFilterValues.selectedMinCost);

        SeekBar seekBarMaxCost = setUpSeekBarForCost(dialogView, R.id.seekBar_max_cost, tvMaxCostValue);
        seekBarMaxCost.setProgress(currentFilterValues.selectedMaxCost);

        SeekBar seekBarMinRating = setUpSeekBarForRating(dialogView, R.id.seekBar_min_rating, tvMinRatingValue);
        seekBarMinRating.setProgress((int) (currentFilterValues.selectedMinRating * 10.0));

        SeekBar seekBarMaxRating = setUpSeekBarForRating(dialogView, R.id.seekBar_max_rating, tvMaxRatingValue);
        seekBarMaxRating.setProgress((int) (currentFilterValues.selectedMaxRating * 10.0));

        // Set the dialog's view
        builder.setView(dialogView);

        // Set click listeners for dialog buttons
        builder.setPositiveButton("Apply", (dialog, which) -> {
            handleApplyButton(checkBoxAroundMe, spinnerType, spinnerSort, seekBarMinCost, seekBarMaxCost, seekBarMinRating, seekBarMaxRating, dialog);
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());
        builder.setNeutralButton("Reset", (dialog, which) -> {
            currentFilterValues = new FilterValues();
            context.resetListView();
        });
    }

    /**
     * Initializes a spinner with items and returns the spinner instance.
     *
     * @param view   The view containing the spinner.
     * @param id     The ID of the spinner.
     * @param items  An array of items to populate the spinner.
     * @return The initialized spinner.
     */
    private Spinner initializeSpinner(View view, int id, String[] items) {
        Spinner spinner = view.findViewById(id);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        return spinner;
    }


    /**
     * Sets up a SeekBar for cost-related filters and returns the SeekBar instance.
     *
     * @param view                 The view containing the SeekBar.
     * @param id                   The ID of the SeekBar.
     * @param correspondingTextView The TextView that displays the SeekBar's value.
     * @return The initialized SeekBar.
     */
    private SeekBar setUpSeekBarForCost(View view, int id, TextView correspondingTextView) {
        SeekBar seekBar = view.findViewById(id);
        correspondingTextView.setText(String.valueOf(seekBar.getProgress()));
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                correspondingTextView.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        return seekBar;
    }

    /**
     * Sets up a SeekBar for rating-related filters and returns the SeekBar instance.
     *
     * @param view                 The view containing the SeekBar.
     * @param id                   The ID of the SeekBar.
     * @param correspondingTextView The TextView that displays the SeekBar's value.
     * @return The initialized SeekBar.
     */
    private SeekBar setUpSeekBarForRating(View view, int id, TextView correspondingTextView) {
        SeekBar seekBar = view.findViewById(id);
        correspondingTextView.setText(String.valueOf(seekBar.getProgress()));
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                correspondingTextView.setText(String.valueOf(progress/10.0));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        return seekBar;
    }

    private OnFilterValuesChangedListener onFilterValuesChangedListener;

    /**
     * Sets a listener for filter value changes.
     *
     * @param listener The listener to be set.
     */
    public void setOnFilterValuesChangedListener(OnFilterValuesChangedListener listener) {
        this.onFilterValuesChangedListener = listener;
    }

    /**
     * Notifies the listener when filter values are changed.
     */

    private void notifyFilterValuesChanged() {
        if (onFilterValuesChangedListener != null) {
            onFilterValuesChangedListener.onFilterValuesChanged(currentFilterValues);
        }
    }


    /**
     * Handles the Apply button click, updates filter values, and notifies the listener.
     *
     * @param checkBox           The CheckBox for "Around Me" option.
     * @param typeSpinner        The Spinner for cuisine type selection.
     * @param sortSpinner        The Spinner for sorting method selection.
     * @param minCostSeekBar     The SeekBar for minimum cost selection.
     * @param maxCostSeekBar     The SeekBar for maximum cost selection.
     * @param minRatingSeekBar   The SeekBar for minimum rating selection.
     * @param maxRatingSeekBar   The SeekBar for maximum rating selection.
     * @param dialog             The AlertDialog instance.
     */
    private void handleApplyButton(CheckBox checkBox, Spinner typeSpinner,
                                   Spinner sortSpinner, SeekBar minCostSeekBar,
                                   SeekBar maxCostSeekBar, SeekBar minRatingSeekBar,
                                   SeekBar maxRatingSeekBar, DialogInterface dialog) {
        // Update current filter values with user selections
        currentFilterValues.isAroundMeSelected = checkBox.isChecked();
        currentFilterValues.selectedType = sanitizeSpinnerSelection((String) typeSpinner.getSelectedItem());
        currentFilterValues.selectedSort = sanitizeSpinnerSelection((String) sortSpinner.getSelectedItem());
        currentFilterValues.selectedMinCost = minCostSeekBar.getProgress();
        currentFilterValues.selectedMaxCost = maxCostSeekBar.getProgress();
        currentFilterValues.selectedMinRating = minRatingSeekBar.getProgress()/10.0;
        currentFilterValues.selectedMaxRating = maxRatingSeekBar.getProgress()/10.0;

        if (currentFilterValues.selectedMinCost > currentFilterValues.selectedMaxCost
                || currentFilterValues.selectedMinRating > currentFilterValues.selectedMaxRating) {
            Toast.makeText(context, FILTER_INPUT_INVALID_TEXT, Toast.LENGTH_LONG).show();
            preventDialogClose(dialog);
            return;
        }

        // Validate cost and rating inputs
        notifyFilterValuesChanged();
        dialog.dismiss();
    }

    /**
     * Sanitizes the selected item from a Spinner. Returns an empty string if "Empty" is selected.
     *
     * @param selection The selected item from a Spinner.
     * @return The sanitized selection.
     */
    private String sanitizeSpinnerSelection(String selection) {
        return "Empty".equals(selection) ? "" : selection;
    }

    /**
     * Prevents the dialog from being closed programmatically by modifying a private field.
     *
     * @param dialog The AlertDialog instance.
     */
    private void preventDialogClose(DialogInterface dialog) {
        try {
            Field field = dialog.getClass().getSuperclass().getDeclaredField(FILTER_SHOW_TEXT);
            field.setAccessible(true);
            field.set(dialog, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Interface for notifying filter value changes to listeners.
     */
    public interface OnFilterValuesChangedListener {
        /**
         * Called when filter values are changed.
         *
         * @param newFilterValues The new filter values.
         */
        void onFilterValuesChanged(FilterValues newFilterValues);
    }

    /**
     * Filter values from the UI
     */
    public static class FilterValues {
        public boolean isAroundMeSelected;
        public int selectedMinCost;
        public int selectedMaxCost;
        public double selectedMinRating;
        public double selectedMaxRating;
        public String selectedType;
        public String selectedSort;

        public FilterValues() {
            this(false, 0, 0, 0, 0, "", "");
        }

        public FilterValues(boolean isAroundMeSelected, int selectedMinCost, int selectedMaxCost, double selectedMinRating, double selectedMaxRating,  String selectedType, String selectedSort) {
            this.isAroundMeSelected = isAroundMeSelected;
            this.selectedMinCost = selectedMinCost;
            this.selectedMaxCost = selectedMaxCost;
            this.selectedMinRating = selectedMinRating;
            this.selectedMaxRating = selectedMaxRating;
            this.selectedType = selectedType;
            this.selectedSort = selectedSort;
        }

        @Override
        public String toString() {
            return "Around Me: " + isAroundMeSelected +
                    "\nSelected Min Cost: " + selectedMinCost +
                    "\nSelected Max Cost: " + selectedMaxCost +
                    "\nSelected Min Rating: " + selectedMinRating +
                    "\nSelected Max Rating: " + selectedMaxRating +
                    "\nSelected Type: " + selectedType +
                    "\nSelected Sort: " + selectedSort;
        }
    }
}
