package G26.Project.ViewController.Adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import G26.Project.R;

/**
 * The `RestaurantViewHolder` class represents a view holder for restaurant items in the RecyclerView.
 * {@code @Author: Jing Li (Original)}
 *            UID : u7533831
 */
public class RestaurantViewHolder extends RecyclerView.ViewHolder {
    TextView title;        // Restaurant title
    TextView metropolis;   // Restaurant metropolis
    TextView type;         // Restaurant type
    ImageView image;       // Restaurant image
    TextView cost;         // Restaurant cost
    TextView ratingCount;  // Restaurant rating count
    TextView meanRating;   // Restaurant mean rating

    /**
     * Constructs a new `RestaurantViewHolder` instance.
     *
     * @param itemView The view representing a restaurant item.
     */
    public RestaurantViewHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.title);
        metropolis = itemView.findViewById(R.id.metropolis);
        type = itemView.findViewById(R.id.type);
        image = itemView.findViewById(R.id.image);
        cost = itemView.findViewById(R.id.cost);
        ratingCount = itemView.findViewById(R.id.ratingCount);
        meanRating = itemView.findViewById(R.id.meanRating);
    }
}
