package G26.Project.ViewController;

import static G26.Project.Resources.Constants.P2P_CHAT_MESSAGE;
import static G26.Project.Resources.Constants.P2P_ERROR_FETCH_MESSAGE;
import static G26.Project.Resources.Constants.P2P_MESSAGE;
import static G26.Project.Resources.Constants.P2P_MESSAGE_CONTENT;
import static G26.Project.Resources.Constants.P2P_MESSAGE_RECEIVE;
import static G26.Project.Resources.Constants.P2P_SENDER_ID;
import static G26.Project.Resources.Constants.P2P_TIMESTAMP;
import static G26.Project.Resources.Constants.P2P_USER_ID;
import static G26.Project.Resources.Constants.P2P_USER_NAME;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import G26.Project.ViewController.Adapter.MessageAdapter;
import G26.Project.ViewController.Util.AuthenticationService;
import G26.Project.ViewController.Util.FireStoreService;
import G26.Project.Model.P2P.Message;
import G26.Project.R;
import G26.Project.ViewController.Util.FirebaseUtil;

/**
 * The `P2PChatActivity` class represents a chat activity for a one-on-one conversation between users.
 * It allows users to send and receive text messages in real-time.
 * {@code @Author: Jing Li (Original)}
 *           UID : u7533831
 */
public class P2PChatActivity extends AppCompatActivity{
    private String userUID;
    private String currentUserUID;
    private List<Message> chatMessages;
    private MessageAdapter messageAdapter;
    private RecyclerView chatRecyclerView;
    private EditText messageEditText;

    /**
     * BroadcastReceiver to listen for incoming messages.
     */
    private final BroadcastReceiver messageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (Objects.equals(intent.getAction(), P2P_MESSAGE_RECEIVE)) {
                Message newMessage = new Message();
                newMessage.setMessageContent(intent.getStringExtra(P2P_MESSAGE_CONTENT));
                newMessage.setSenderUid(intent.getStringExtra(P2P_SENDER_ID));
                updateChatUI(newMessage);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p2p_chat);
        // Register as a listener for messageReceived broadcasts
        LocalBroadcastManager.getInstance(this).registerReceiver(messageReceiver, new IntentFilter(P2P_MESSAGE_RECEIVE));
        initializeUIComponents();
        fetchChatMessages();
    }

    /**
     * Initializes UI components and sets up the chat view.
     */
    private void initializeUIComponents() {
        // Retrieve the UID from the intent
        userUID = getIntent().getStringExtra(P2P_USER_ID);
        currentUserUID = AuthenticationService.getInstance().getFirebaseUser().getUid();
        chatMessages = new ArrayList<>();

        messageEditText = findViewById(R.id.messageEditText);
        TextView userNameTextView = findViewById(R.id.friendNameTextView);
        userNameTextView.setText(getIntent().getStringExtra(P2P_USER_NAME));

        Button sendMessageButton = findViewById(R.id.sendMessageButton);
        sendMessageButton.setOnClickListener(v -> handleSendMessage());

        // Initialize RecyclerView
        chatRecyclerView = findViewById(R.id.chatRecyclerView);
        messageAdapter = new MessageAdapter(chatMessages, currentUserUID);
        chatRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        chatRecyclerView.setAdapter(messageAdapter);
    }

    /**
     * Handles sending a new message.
     */
    private void handleSendMessage() {
        String messageContent = messageEditText.getText().toString().trim();
        if (!messageContent.isEmpty()) {
            FireStoreService.getInstance().sendMessage(messageContent, currentUserUID,userUID);
            messageEditText.setText("");
        }
    }

    /**
     * Fetches chat messages from the Firestore database.
     */
    private void fetchChatMessages() {
        String chatId = FirebaseUtil.generateChatId(currentUserUID, userUID);

        FireStoreService.getInstance().getFirebaseDatabase().collection(P2P_CHAT_MESSAGE)
                .document(chatId)
                .collection(P2P_MESSAGE)
                .orderBy(P2P_TIMESTAMP)
                .get()
                .addOnCompleteListener(this::handleChatMessagesFetchResult);
    }

    /**
     * Handles the result of fetching chat messages from the Firestore database.
     *
     * @param task The Firestore query task result.
     */
    private void handleChatMessagesFetchResult(com.google.android.gms.tasks.Task<com.google.firebase.firestore.QuerySnapshot> task) {
        if (task.isSuccessful()) {
            chatMessages.clear();
            for (DocumentSnapshot document : task.getResult()) {
                Message message = document.toObject(Message.class);
                chatMessages.add(message);
            }
            messageAdapter.notifyDataSetChanged();
            scrollToBottom();
        } else {
            Toast.makeText(P2PChatActivity.this, P2P_ERROR_FETCH_MESSAGE + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Updates the chat UI with a new message.
     *
     * @param newMessage The new message to display.
     */
    private void updateChatUI(Message newMessage) {
        chatMessages.add(newMessage);
        messageAdapter.notifyDataSetChanged();
        chatRecyclerView.scrollToPosition(chatMessages.size() - 1); // Scroll to the bottom
    }

    /**
     * Scrolls the chat view to the bottom to display the most recent messages.
     */
    private void scrollToBottom() {
        chatRecyclerView.scrollToPosition(chatMessages.size() - 1); // Scroll to the bottom
    }
}
