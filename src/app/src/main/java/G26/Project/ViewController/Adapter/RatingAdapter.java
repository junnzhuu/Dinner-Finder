package G26.Project.ViewController.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import G26.Project.Model.Restaurant.Rating;
import G26.Project.R;
import G26.Project.ViewController.Util.FirebaseUtil;

/**
 * The `RatingAdapter` class is responsible for managing and displaying a list of ratings in a RecyclerView.
 * {@code @Author: Jing Li (Original)}
 *           UID : u7533831
 */
public class RatingAdapter extends RecyclerView.Adapter<RatingAdapter.RatingViewHolder> {
    private List<Rating> ratings;

    /**
     * Constructs a new `RatingAdapter` instance.
     *
     * @param ratings A list of rating items to be displayed.
     */
    public RatingAdapter(List<Rating> ratings) {
        this.ratings = ratings;
        Log.d("RatingAdapter", "Adapter initialized with " + ratings.size() + " ratings.");
    }

    @NonNull
    @Override
    public RatingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("RatingAdapter", "Creating view holder for viewType: " + viewType);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rating, parent, false);
        return new RatingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RatingViewHolder holder, int position) {
        Log.d("RatingAdapter", "Binding view at position: " + position);
        Rating rating = ratings.get(position);

        if(rating != null) {
            Log.d("RatingAdapter", "Rating at position " + position + ": " + rating);

            holder.userNameTextView.setText(rating.getUserName());
            holder.ratingBar.setRating((float) rating.getRating());
            holder.textTextView.setText(rating.getText());
            holder.dateTextView.setText(FirebaseUtil.convertTimestampToDayMonYear(rating.getTimestamp()));
        } else {
            Log.w("RatingAdapter", "Rating at position " + position + " is null.");
        }
    }

    @Override
    public int getItemCount() {
        int count = ratings.size();
        Log.d("RatingAdapter", "Getting item count: " + count);
        return count;
    }

    /**
     * The `RatingViewHolder` class represents a view holder for rating items in the RecyclerView.
     */
    public static class RatingViewHolder extends RecyclerView.ViewHolder {
        TextView userNameTextView;
        RatingBar ratingBar;
        TextView textTextView;
        TextView dateTextView;

        public RatingViewHolder(View itemView) {
            super(itemView);
            userNameTextView = itemView.findViewById(R.id.rating_item_name);
            ratingBar = itemView.findViewById(R.id.rating_item_rating);
            textTextView = itemView.findViewById(R.id.rating_item_text);
            dateTextView = itemView.findViewById(R.id.rating_item_date);
            Log.d("RatingViewHolder", "ViewHolder created.");
        }
    }

    /**
     * Updates the ratings displayed in the adapter with a new list of ratings.
     *
     * @param newRatings The new list of ratings to be displayed.
     */
    public void updateRatings(List<Rating> newRatings) {
        Log.d("RatingAdapter", "UpdateRatings called. Size before: " + ratings.size());
        ratings.clear();
        ratings.addAll(newRatings);
        Log.d("RatingAdapter", "Size after: " + ratings.size());
        notifyDataSetChanged();
    }
}
