package G26.Project.ViewController.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RatingBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import G26.Project.ViewController.Util.FireStoreService;
import G26.Project.R;

/**
 * A dialog fragment for users to submit ratings and reviews for a restaurant.
 * {@code @Author: Jun Zhu (Original)}
 *           UID : u7602081
 * {@code @Contributor: Jing Li}
 *                UID : u7533831
 */
public class RatingDialogFragment extends DialogFragment {

    private RatingBar ratingBar;
    private EditText editTextReview;
    private String restaurantId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            restaurantId = getArguments().getString("restaurantId");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_rating_dialog, container, false);

        initializeUIElements(view);
        return view;
    }

    /**
     * Initializes UI elements within the dialog fragment.
     *
     * @param view The view of the dialog fragment.
     */
    private void initializeUIElements(@NonNull View view) {
        ratingBar = view.findViewById(R.id.ratingBar);
        editTextReview = view.findViewById(R.id.reviewInput);

        view.findViewById(R.id.submit).setOnClickListener(this::handleRatingSubmission);
        view.findViewById(R.id.cancelButton).setOnClickListener(v -> dismiss());
    }

    /**
     * Handles the submission of a rating and review for the restaurant.
     *
     * @param v The view that triggered the rating submission.
     */
    private void handleRatingSubmission(View v) {
        float rating = ratingBar.getRating();
        String review = editTextReview.getText().toString();

        FireStoreService.getInstance().saveRatingToDatabase(rating, review, restaurantId);
        assert getArguments() != null;
        FireStoreService.getInstance().storeCommentInFirebase(getArguments().getString("restaurantName"),
                getArguments().getString("restaurantCategory"),
                getArguments().getString("restaurantCity"),
                System.currentTimeMillis(),
                review,
                rating);
        dismiss();
    }
}
