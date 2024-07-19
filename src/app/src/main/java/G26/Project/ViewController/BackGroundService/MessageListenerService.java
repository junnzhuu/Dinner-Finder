package G26.Project.ViewController.BackGroundService;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

import G26.Project.ViewController.Util.AuthenticationService;
import G26.Project.ViewController.Util.FireStoreService;
import G26.Project.Model.P2P.Message;
import G26.Project.ViewController.Util.FirebaseUtil;

/**
 * The `MessageListenerService` class is responsible for managing real-time message updates and notifications.
 * It listens to changes in chat messages, detects new messages, and triggers notifications for the user.
 *
 * {@code @Author: Jing Li (Original)}
 *           UID: u7533831
 */
public class MessageListenerService extends Service{
    private FirebaseFirestore db;
    private String currentUid;
    private Map<String, Long> lastUpdatedTimestamps = new HashMap<>();

    /**
     * Called when the service is created. Initializes Firebase and loads initial chat timestamps.
     */
    @Override
    public void onCreate() {
        super.onCreate();
        db = FireStoreService.getInstance().getFirebaseDatabase();
        currentUid = AuthenticationService.getInstance().getFirebaseUser().getUid();
        loadInitialChatTimestamps();
    }

    /**
     * Loads the initial chat timestamps for the current user from Firestore.
     */
    private void loadInitialChatTimestamps() {
        db.collection("Users").document(currentUid).get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document != null) {
                            Map<String, Map<String, Object>> chats = (Map<String, Map<String, Object>>) document.get("chats");
                            if (chats != null) {
                                for (Map.Entry<String, Map<String, Object>> entry : chats.entrySet()) {
                                    String targetUid = entry.getKey();
                                    Long lastUpdated = (Long) entry.getValue().get("lastUpdated");
                                    lastUpdatedTimestamps.put(targetUid, lastUpdated);
                                }
                            }
                        }
                        setupFirebaseListener(); // Move this inside the completion handler
                    } else {
                        Log.w("FirebaseLoad", "Error loading initial timestamps.", task.getException());
                    }
                });
    }

    /**
     * Sets up a Firebase listener to detect changes in chat messages.
     */
    private void setupFirebaseListener() {
        db.collection("Users").document(currentUid)
                .addSnapshotListener((snapshot, e) -> {
                    if (e != null) {
                        Log.w("FirebaseListener", "Listen failed.", e);
                        return;
                    }

                    if (snapshot != null && snapshot.exists()) {
                        Map<String, Map<String, Object>> chats = (Map<String, Map<String, Object>>) snapshot.get("chats");
                        if (chats != null) {
                            for (Map.Entry<String, Map<String, Object>> entry : chats.entrySet()) {
                                String targetUid = entry.getKey();
                                Long newTimestamp = (Long) entry.getValue().get("lastUpdated");

                                Long oldTimestamp = lastUpdatedTimestamps.get(targetUid);
                                if (oldTimestamp == null || !oldTimestamp.equals(newTimestamp)) {
                                    handleNewMessageNotification(targetUid);
                                    Toast.makeText(this, "new message", Toast.LENGTH_SHORT).show();
                                    lastUpdatedTimestamps.put(targetUid, newTimestamp);
                                }
                            }
                        }
                    }
                });
    }


    /**
     * Handles notifications for new messages.
     *
     * @param targetUid The user ID of the target chat participant.
     */
    private void handleNewMessageNotification(String targetUid) {
        String combinedUid = FirebaseUtil.generateChatId(currentUid, targetUid);
        db.collection("chatMessages")
                .document(combinedUid)
                .collection("messages")
                .orderBy("timestamp", Query.Direction.DESCENDING)
                .limit(1)
                .get()  // Used get() here instead of a listener to fetch the latest message once.
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        QuerySnapshot queryDocumentSnapshots = task.getResult();
                        if (!queryDocumentSnapshots.isEmpty()) {
                            DocumentSnapshot latestMessageSnapshot = queryDocumentSnapshots.getDocuments().get(0);
                            Message latestMessage = latestMessageSnapshot.toObject(Message.class);
                            notifyMessageReceived(latestMessage);
                        }
                    } else {
                        Log.w("FetchLatestMessage", "Error getting documents: ", task.getException());
                    }
                });
    }

    /**
     * Binds the service to an intent.
     *
     * @param intent The intent to bind.
     * @return A binder interface, but in this case, it returns null.
     */
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * Notifies the UI about the received message.
     *
     * @param message The received message.
     */
    private void notifyMessageReceived(Message message) {
        Intent intent = new Intent("messageReceived");

        intent.putExtra("messageContent", message.getMessageContent());
        intent.putExtra("senderUid", message.getSenderUid());

        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }
}
