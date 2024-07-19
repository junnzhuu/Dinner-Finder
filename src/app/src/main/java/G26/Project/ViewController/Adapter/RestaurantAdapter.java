package G26.Project.ViewController.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import G26.Project.Model.Restaurant.Restaurant;
import G26.Project.R;
import G26.Project.ViewController.RestaurantDetailActivity;


/**
 * The `RestaurantAdapter` class is responsible for managing and displaying a list of restaurants in a RecyclerView.
 * {@code @Author: Jing Li (Original)}
 *           UID : u7533831
 */
public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantViewHolder> {
    private List<Restaurant> restaurantList;
    /**
     * Constructs a new `RestaurantAdapter` instance.
     *
     * @param restaurantList A list of restaurant items to be displayed.
     */
    public RestaurantAdapter(List<Restaurant> restaurantList) {
        this.restaurantList = restaurantList;
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_item, parent, false);
        Log.d("RecyclerViewAdapter", "onCreateViewHolder called");
        return new RestaurantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position) {
        // Fetch the restaurant data for the current position
        Restaurant restaurant = restaurantList.get(position);
        // Debug logs
        Log.d("RecyclerViewAdapter", "onBindViewHolder called for position: " + position);
        Log.d("RecyclerViewAdapter", "Binding restaurant: " + restaurant.getRestaurantName());
        // Bind the data to the ViewHolder
        holder.title.setText(restaurant.getRestaurantName());
        holder.metropolis.setText(restaurant.getRestaurantCity());
        holder.type.setText(restaurant.getType().toString());
        Context context = holder.image.getContext(); // Get context from ImageView
        Glide.with(context)
                .load(restaurant.getImage()) // URL of the image
                .into(holder.image); // ImageView where the image will be loaded
        holder.cost.setText(restaurant.getCost() + "$");
        holder.ratingCount.setText("(" + restaurant.getRatingCount() + ")");
        holder.meanRating.setText(String.format("%.1f", restaurant.getMeanRating()) + " / 5.0");
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), RestaurantDetailActivity.class);
            intent.putExtra("restaurant_key", restaurantList.get(position));
            v.getContext().startActivity(intent);
        });

        // Additional debug log to confirm data binding
        Log.d("RecyclerViewAdapter", "Data bound for position: " + position);
        Log.d("RecyclerViewAdapter", "getItemCount called, count: " + restaurantList.size());

    }

    @Override
    public int getItemCount() {
        Log.d("RecyclerViewAdapter", "getItemCount: " + restaurantList.size());
        return restaurantList.size();
    }

    /**
     * Updates the data displayed in the adapter with a new list of restaurants.
     *
     * @param newData The new list of restaurants to be displayed.
     */
    public void updateData(List<Restaurant> newData) {
        this.restaurantList = newData;
        notifyDataSetChanged();
        Log.d("RestaurantAdapter", "Data updated. New size: " + restaurantList.size());
    }

    /**
     * Adds a new restaurant to the adapter.
     *
     * @param newRestaurant The new restaurant to be added.
     */
    public void addData(Restaurant newRestaurant) {
        restaurantList.add(newRestaurant);
        notifyItemInserted(restaurantList.size() - 1);
        Log.d("RestaurantAdapter", "Restaurant added. New size: " + restaurantList.size());
    }

    /**
     * Updates the data for a specific restaurant item in the adapter.
     *
     * @param updatedRestaurant The updated restaurant item.
     */
    public void updateDataForItem(Restaurant updatedRestaurant) {
        int positionToUpdate = -1;

        for (int i = 0; i < restaurantList.size(); i++) {
            if (restaurantList.get(i).getRestaurantId().equals(updatedRestaurant.getRestaurantId())) {
                positionToUpdate = i;
                break;
            }
        }
        if (positionToUpdate != -1) {
            restaurantList.set(positionToUpdate, updatedRestaurant);
            notifyItemChanged(positionToUpdate);
            Log.d("RestaurantAdapter", "Restaurant at position " + positionToUpdate + " updated.");
        } else {
            Log.e("RestaurantAdapter", "Failed to update. Restaurant not found.");
        }
    }
}
