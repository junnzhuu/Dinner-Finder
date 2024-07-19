package G26.Project.ViewController.Adapter;

import G26.Project.Model.Restaurant.Rating;
import G26.Project.R;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import G26.Project.Model.Restaurant.CommentModel;

/**
 * CommentHistoryAdapter provides a binding from a {@link List<CommentModel>} to views that are displayed
 * within a {@link RecyclerView}. It simplifies the presentation of a list of comments related to a user's
 * historical activity in the context of restaurants. This includes details such as restaurant names, types,
 * cities, and their associated ratings.
 * <p>
 * The adapter manages a collection of {@link CommentViewHolder} instances, each representing a single
 * comment item in the RecyclerView. The ViewHolder pattern allows for efficient reuse of view components
 * and reduces the overhead of frequently calling the {@link View#findViewById} method.
 * <p>
 * Usage:
 * <pre>
 *     List<CommentModel> comments = ...; // Obtain list of comments.
 *     CommentHistoryAdapter adapter = new CommentHistoryAdapter(comments);
 *     RecyclerView recyclerView = findViewById(R.id.recyclerView);
 *     recyclerView.setLayoutManager(new LinearLayoutManager(context));
 *     recyclerView.setAdapter(adapter);
 * </pre>
 *
 * Note: To reflect changes in the underlying comment data, you can use the {@link #updateComments} method.
 * <p>
 * {@code @Author: Jun Zhu (Original)}
 *           UID : u7602081
 */

public class CommentHistoryAdapter extends RecyclerView.Adapter<CommentHistoryAdapter.CommentViewHolder> {

    /** A list of comments to be displayed. */
    private List<CommentModel> comments;

    /**
     * Constructs a new CommentHistoryAdapter.
     *
     * @param comments the list of comments to be displayed.
     */
    public CommentHistoryAdapter(List<CommentModel> comments) {
        this.comments = comments;
        Log.d("CommentHistoryAdapter", "Adapter initialized with " + comments.size() + " comments.");
    }

    /**
     * Called when RecyclerView needs a new ViewHolder of the given type to represent an item.
     */
    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("CommentHistoryAdapter", "Creating view holder for viewType: " + viewType);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_past_comments, parent, false);
        return new CommentViewHolder(view);
    }

    /**
     * Binds the contents of the CommentModel at the given position to the provided ViewHolder.
     */
    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        CommentModel comment = comments.get(position);
        if (comment != null) {
            Log.d("CommentHistoryAdapter", "Comment at position " + position + ": " + comment);

            // Set the details for the current comment.
            holder.restaurantName.setText(comment.getRestaurantName());
            holder.restaurantType.setText(comment.getRestaurantType());
            holder.restaurantCity.setText(comment.getRestaurantCity());

            List<Rating> ratingsList = new ArrayList<>();
            FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
            assert currentUser != null;
            Rating rating = new Rating(currentUser, comment.getRating(), comment.getText());
            Date date = new Date(comment.getUserDate());
            rating.setTimestamp(date);
            ratingsList.add(rating);

            holder.ratingAdapter = new RatingAdapter(ratingsList);
            holder.ratingRecyclerView.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext()));
            holder.ratingRecyclerView.setAdapter(holder.ratingAdapter);
        } else {
            Log.w("CommentHistoryAdapter", "Comment at position " + position + " is null.");
        }
    }

    /**
     * Returns the total number of comments.
     */
    @Override
    public int getItemCount() {
        int count = comments.size();
        Log.d("CommentHistoryAdapter", "Getting item count: " + count);
        return count;
    }

    /**
     * ViewHolder class that represents a single item in the comment history.
     */
    static class CommentViewHolder extends RecyclerView.ViewHolder {
        TextView restaurantName;
        TextView restaurantType;
        TextView restaurantCity;
        RecyclerView ratingRecyclerView;
        RatingAdapter ratingAdapter;

        /**
         * Constructs a new CommentViewHolder.
         *
         * @param itemView the view of the current item.
         */
        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            restaurantName = itemView.findViewById(R.id.comment_restaurant_name);
            restaurantType = itemView.findViewById(R.id.comment_restaurant_type);
            restaurantCity = itemView.findViewById(R.id.comment_restaurant_city);
            ratingRecyclerView = itemView.findViewById(R.id.comments_recycler_view);

            Log.d("CommentViewHolder", "ViewHolder created.");
        }
    }

    /**
     * Updates the list of comments with new comments and notifies the RecyclerView of the change.
     *
     * @param newComments the new list of comments to be displayed.
     */
    public void updateComments(List<CommentModel> newComments) {
        Log.d("CommentHistoryAdapter", "UpdateComments called. Size before: " + comments.size());

        comments.clear();
        comments.addAll(newComments);

        Log.d("CommentHistoryAdapter", "Size after: " + comments.size());

        notifyDataSetChanged();
    }
}
