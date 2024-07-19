package G26.Project.ViewController.Adapter;

import static G26.Project.Resources.Constants.CHAT_ADAPTER_USER_NAME;
import static G26.Project.Resources.Constants.CHAT_ADAPTER_USER_UID;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import G26.Project.Model.P2P.Chat;
import G26.Project.R;
import G26.Project.ViewController.P2PChatActivity;

/**
 *  The `ChatAdapter` class is responsible for managing and displaying chat items within a RecyclerView.
 *  It serves as a bridge between the data source (a list of chat items) and the RecyclerView, ensuring
 *  that chat items are appropriately displayed and interacted with.
 * <p>
 * {@code @Author: Jing Li (Original)}
 *          UID : u7533831
 */
public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {

    private List<Chat> chatList; // List of chat items to be displayed.
    private Context context; // The context of the application or activity.

    /**
     * Constructs a new `ChatAdapter` instance.
     *
     * @param chatList A list of chat items to be displayed.
     * @param context  The context of the application or activity.
     */
    public ChatAdapter(List<Chat> chatList, Context context) {

        this.chatList = chatList;
        this.context = context;
    }


    /**
     * Called when the RecyclerView needs a new ViewHolder to represent an item.
     *
     * @param parent   The parent view group that holds the item views.
     * @param viewType The type of the view to be created.
     * @return A new instance of the `ChatViewHolder` class.
     */
    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat, parent, false);
        return new ChatViewHolder(view);
    }

    /**
     * Called to bind the data to a specific ViewHolder.
     *
     * @param holder   The ViewHolder to bind the data to.
     * @param position The position of the item within the RecyclerView.
     */
    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        Chat chat = chatList.get(position);

        // Set the user name, last message, and timestamp in the ViewHolder's views.
        holder.userNameTextView.setText(chat.getUserName());
        holder.lastMessageTextView.setText(chat.getLastMessage());
        holder.timestampTextView.setText(chat.getTimestamp());
        // Handle item click: Start a P2P chat activity when an item is clicked.
        holder.itemView.setOnClickListener(view -> {
            // Handle item click here
            String targetUserUid = chat.getTargetUserUid();
            // Start the P2P_Chat activity
            Intent intent = new Intent(context, P2PChatActivity.class);
            intent.putExtra(CHAT_ADAPTER_USER_UID, targetUserUid);
            intent.putExtra(CHAT_ADAPTER_USER_NAME, chat.getUserName());
            context.startActivity(intent);
        });

    }


    /**
     * Returns the total number of items in the data set.
     *
     * @return The number of chat items in the `chatList`.
     */
    @Override
    public int getItemCount() {
        return chatList.size();
    }

    /**
     * The `ChatViewHolder` class represents a view holder for chat items in the RecyclerView.
     * It holds references to various UI elements within the chat item view.
     */
    public static class ChatViewHolder extends RecyclerView.ViewHolder {
        TextView userNameTextView, lastMessageTextView, timestampTextView;
        ImageView profileImageView;

        /**
         * Constructs a new `ChatViewHolder` instance and initializes UI elements.
         *
         * @param itemView The root view of the chat item layout.
         */
        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            userNameTextView = itemView.findViewById(R.id.userNameTextView);
            lastMessageTextView = itemView.findViewById(R.id.lastMessageTextView);
            timestampTextView = itemView.findViewById(R.id.timestampTextView);
            profileImageView = itemView.findViewById(R.id.profileImageView);
        }
    }
}

