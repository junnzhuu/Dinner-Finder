package G26.Project.ViewController;

import static G26.Project.Resources.Constants.P2P_MESSAGE_RECEIVE;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import G26.Project.Model.P2P.Chat;
import G26.Project.Model.P2P.Message;
import G26.Project.R;
import G26.Project.ViewController.Adapter.ChatAdapter;
import G26.Project.ViewController.Util.AuthenticationService;
import G26.Project.ViewController.Util.FireStoreService;
import G26.Project.ViewController.Util.FirebaseUtil;

/**
 * The `RecentChatsActivity` class represents the user interface for managing recent chat conversations.
 * Users can start new chats by entering an email address and view their existing chats with last messages.
 * {@code @Author: Jing Li (Original)}
 *          UID : u7533831
 * {@code @Contributor: Jun Zhu}
 *         UID : u7602081
 *         (Wrote the 'navigateToMainActivity' method)
 */
public class RecentChatsActivity extends AppCompatActivity{
    private EditText emailInput;
    private ChatAdapter chatAdapter;
    private final List<Chat> recentChats = new ArrayList<>();
    String currentUid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_chats);
        initializeUIComponents();
        setupBottomNavigation();
        setupChatRecyclerView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(messageReceiver, new IntentFilter("messageReceived"));
        updateRecentChats();
    }

    /**
     * Initializes UI components and event handlers.
     */
    private void initializeUIComponents() {
        currentUid = AuthenticationService.getInstance().getFirebaseUser().getUid();
        emailInput = findViewById(R.id.emailInput);
        Button startChatButton = findViewById(R.id.startChatButton);
        startChatButton.setOnClickListener(this::onStartChatButtonClick);
    }

    /**
     * Handles the "Start Chat" button click event.
     *
     * @param v The clicked view.
     */
    private void onStartChatButtonClick(View v) {
        String email = emailInput.getText().toString().trim();
        if (!email.isEmpty()) {
            checkIfUserExists(email);
        }
    }

    /**
     * Sets up the bottom navigation bar and handles navigation events.
     */
    private void setupBottomNavigation() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.bottom_chat);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            // Already on the chat screen, no need to do anything
            if (itemId == R.id.bottom_home) {
                navigateToMainActivity();
                return true;
            } else return itemId == R.id.bottom_chat;
        });
    }

    /**
     * Navigates to the main activity with transition animations.
     */
    private void navigateToMainActivity() {
        Intent intent = new Intent(RecentChatsActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    /**
     * Sets up the RecyclerView for displaying recent chat conversations.
     */
    private void setupChatRecyclerView() {
        RecyclerView recentChatsRecyclerView = findViewById(R.id.recentChatsRecyclerView);
        chatAdapter = new ChatAdapter(recentChats, RecentChatsActivity.this);
        recentChatsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        recentChatsRecyclerView.setAdapter(chatAdapter);
    }

    /**
     * Checks if a user with the provided email exists in the database.
     *
     * @param email The email to check.
     */
    private void checkIfUserExists(String email) {
        FireStoreService.getInstance().getFirebaseDatabase().collection("Users")
                .whereEqualTo("email", email)
                .get()
                .addOnCompleteListener(this::handleUserCheckResult);
    }

    /**
     * Handles the result of checking if a user exists with the provided email.
     *
     * @param task The result of the user check task.
     */
    private void handleUserCheckResult(com.google.android.gms.tasks.Task<com.google.firebase.firestore.QuerySnapshot> task) {
        if (task.isSuccessful() && !task.getResult().isEmpty()) {
            String uid = task.getResult().getDocuments().get(0).getString("uid");
            assert uid != null;
            if (!uid.equals(currentUid)) {
                startChatWithUser(uid);
            } else {
                Toast.makeText(RecentChatsActivity.this, "You cannot start a chat with yourself!", Toast.LENGTH_SHORT).show();
            }
        } else {
            handleUserCheckError(task);
        }
    }

    /**
     * Handles errors when checking if a user exists with the provided email.
     *
     * @param task The result of the user check task.
     */
    private void handleUserCheckError(com.google.android.gms.tasks.Task<com.google.firebase.firestore.QuerySnapshot> task) {
        if (task.isSuccessful()) {
            Toast.makeText(RecentChatsActivity.this, "No user found with this email!", Toast.LENGTH_SHORT).show();
            Log.d("FirestoreResult", "Documents: " + task.getResult().getDocuments());
        } else {
            Toast.makeText(RecentChatsActivity.this, "Error checking user: "
                                    + Objects.requireNonNull(task.getException()).getMessage(),
                            Toast.LENGTH_SHORT)
                    .show();
        }
    }

    /**
     * Initiates a chat with a user identified by their UID.
     *
     * @param targetUid The UID of the target user.
     */
    private void startChatWithUser(String targetUid) {
        // Update the current user's chat metadata
        FireStoreService.getInstance().updateChatMetadata(currentUid, targetUid);

        // Update the target user's chat metadata
        FireStoreService.getInstance().updateChatMetadata(targetUid, currentUid);

        // Start the P2P_Chat activity
        Intent intent = new Intent(RecentChatsActivity.this, P2PChatActivity.class);
        intent.putExtra("userUID", targetUid);
        startActivity(intent);
    }

    /**
     * Updates the list of recent chat conversations and their last messages.
     */
    private void updateRecentChats() {
        FireStoreService.getInstance().getFirebaseDatabase().collection("Users")
                .document(currentUid)
                .get()
                .addOnCompleteListener(this::handleRecentChatsUpdate);
    }

    /**
     * Handles the result of updating recent chat conversations.
     *
     * @param task The result of the update task.
     */
    private void handleRecentChatsUpdate(com.google.android.gms.tasks.Task<com.google.firebase.firestore.DocumentSnapshot> task) {
        if (task.isSuccessful()) {
            DocumentSnapshot document = task.getResult();
            Map<String, Map<String, Object>> chats = (Map<String, Map<String, Object>>) document.get("chats");
            if (chats != null) {
                recentChats.clear();
                for (String targetUid : chats.keySet()) {
                    String combinedUid = FirebaseUtil.generateChatId(currentUid, targetUid);
                    fetchLastMessageForChat(combinedUid, targetUid);
                }
            }
        } else {
            Toast.makeText(RecentChatsActivity.this, "Error fetching chats: " + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Fetches the last message for a chat conversation.
     *
     * @param combinedUid The combined UID of the chat.
     * @param targetUid   The UID of the target user.
     */
    private void fetchLastMessageForChat(String combinedUid, String targetUid) {
        FireStoreService.getInstance().getFirebaseDatabase().collection("chatMessages")
                .document(combinedUid)
                .collection("messages")
                .orderBy("timestamp", Query.Direction.DESCENDING)
                .limit(1)
                .get()
                .addOnCompleteListener(task -> handleLastMessageFetch(task, targetUid));
    }

    /**
     * Handles the result of fetching the last message for a chat conversation.
     *
     * @param task      The result of the fetch task.
     * @param targetUid The UID of the target user.
     */
    private void handleLastMessageFetch(com.google.android.gms.tasks.Task<com.google.firebase.firestore.QuerySnapshot> task, String targetUid) {
        if (task.isSuccessful() && !task.getResult().isEmpty()) {
            Message lastMessage = task.getResult().getDocuments().get(0).toObject(Message.class);
            Chat chat = new Chat();
            assert lastMessage != null;
            chat.setLastMessage(lastMessage.getMessageContent());
            chat.setTimestamp(FirebaseUtil.convertTimestampToReadableDate(lastMessage.getTimestamp()));
            chat.setTargetUserUid(targetUid);
            FireStoreService.getInstance().getFirebaseDatabase().collection("Users")
                    .document(targetUid)
                    .get()
                    .addOnSuccessListener(documentSnapshot -> {
                        String friendEmail = documentSnapshot.getString("email");  // assuming the field is named "email"
                        if (friendEmail != null) {
                            chat.setUserName(friendEmail);
                            recentChats.add(chat);
                            chatAdapter.notifyDataSetChanged();
                        }
                    }).addOnFailureListener(e -> Toast.makeText(this,
                                    "Failed to fetch friend's details: "
                                            + e.getMessage(),
                                    Toast.LENGTH_SHORT)
                            .show());
        }
    }

    // Broadcast receiver to listen for new messages
    private final BroadcastReceiver messageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (Objects.equals(intent.getAction(), P2P_MESSAGE_RECEIVE)) {
                updateRecentChats();
            }
        }
    };

}
