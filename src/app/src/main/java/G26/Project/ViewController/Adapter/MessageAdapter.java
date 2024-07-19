package G26.Project.ViewController.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import android.widget.TextView;

import G26.Project.Model.P2P.Message;
import G26.Project.R;

/**
 * The `MessageAdapter` class is responsible for managing and displaying a list of chat messages
 * within a RecyclerView. It provides a way to adapt chat message data to the view and handles
 * the differentiation between sender and receiver messages.
 *
 * {@code @Author: Jing Li (Original)}
 * UID: u7533831
 */
public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ChatViewHolder> {
    private List<Message> messages; // List of chat messages to be displayed.
    private String currentUserId; // User ID of the current user.

    /**
     * Constructs a new `MessageAdapter` instance.
     *
     * @param messages      A list of chat messages to be displayed.
     * @param currentUserId The user ID of the current user.
     */
    public MessageAdapter(List<Message> messages, String currentUserId) {
        this.messages = messages;
        this.currentUserId = currentUserId;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_message_item, parent, false);
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
        Message message = messages.get(position);

        if (message.getSenderUid().equals(currentUserId)) {
            // Sender message
            holder.senderMessageTextView.setVisibility(View.VISIBLE);
            holder.receiverMessageTextView.setVisibility(View.GONE);
            holder.senderMessageTextView.setText(message.getMessageContent());
        } else {
            // Receiver message
            holder.senderMessageTextView.setVisibility(View.GONE);
            holder.receiverMessageTextView.setVisibility(View.VISIBLE);
            holder.receiverMessageTextView.setText(message.getMessageContent());
        }
    }

    /**
     * Returns the total number of items in the data set.
     *
     * @return The number of chat messages in the `messages` list.
     */
    @Override
    public int getItemCount() {
        return messages.size();
    }

    /**
     * The `ChatViewHolder` class represents a view holder for chat messages in the RecyclerView.
     * It holds references to UI elements for displaying sender and receiver messages.
     */
    public static class ChatViewHolder extends RecyclerView.ViewHolder {
        TextView senderMessageTextView, receiverMessageTextView;
        /**
         * Constructs a new `ChatViewHolder` instance and initializes UI elements.
         *
         * @param itemView The root view of the chat message item layout.
         */
        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            senderMessageTextView = itemView.findViewById(R.id.senderMessageTextView);
            receiverMessageTextView = itemView.findViewById(R.id.receiverMessageTextView);
        }
    }
}
