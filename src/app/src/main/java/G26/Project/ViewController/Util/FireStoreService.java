package G26.Project.ViewController.Util;

import static android.content.ContentValues.TAG;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import G26.Project.Model.Restaurant.CommentModel;
import G26.Project.Model.Restaurant.Rating;
import G26.Project.Model.Restaurant.Restaurant;
import G26.Project.Model.Restaurant.User;

/**
 * The `FireStoreService` class provides Firestore database and Firebase Storage services.
 * It manages user data, ratings, messages, and chat metadata.
 * {@code @Author: Jing Li (Original)}
 *           UID : u7533831
 */
public class FireStoreService {
    private static FireStoreService instance;
    private final FirebaseFirestore firebaseFirestore;

    /**
     * Private constructor to create an instance of the `FireStoreService` class.
     */
    private FireStoreService() {
        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    /**
     * Get the singleton instance of the `FireStoreService` class.
     *
     * @return The `FireStoreService` instance.
     */

    public static synchronized FireStoreService getInstance() {
        if (instance == null) {
            instance = new FireStoreService();
        }
        return instance;
    }

    /**
     * Get the Firestore database instance.
     *
     * @return The Firestore database instance.
     */
    public FirebaseFirestore getFirebaseDatabase() {
        return firebaseFirestore;
    }


    /**
     * Store user data in Firestore.
     *
     * @param firebaseUser The Firebase user to store.
     */
    public void storeUserData(FirebaseUser firebaseUser) {
        DocumentReference userRef = firebaseFirestore.collection("users").document(firebaseUser.getUid());

        userRef.get().addOnSuccessListener(documentSnapshot -> {
            if (!documentSnapshot.exists()) {
                User user = new User(firebaseUser.getUid(), firebaseUser.getEmail());
                firebaseFirestore.collection("Users")
                        .document(user.getUid())
                        .set(user)
                        .addOnSuccessListener(unused -> {
                            if (documentSnapshot.exists()) {
                                String value = documentSnapshot.getString("profileImageUrl");
                                String username = documentSnapshot.getString("displayName");
                                if (value!=null) {
                                    user.setProfileAvatar(value);
                                }
                                if (username!=null) {
                                    user.setDisplayName(username);

                                }
                            }
                        })
                        .addOnFailureListener(e -> Log.w("FireStoreService", "Error adding user", e));
            }
        }).addOnFailureListener(e -> Log.w("FireStoreService", "Error fetching user", e));
    }

    /**
     * Save a user's rating and review to the database.
     *
     * @param ratingValue   The rating value given by the user.
     * @param reviewText    The text review provided by the user.
     * @param restaurantId  The ID of the restaurant being rated.
     */
    public void saveRatingToDatabase(float ratingValue, String reviewText, String restaurantId) {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser == null) {
            Log.e("RatingDialogFragment", "User is not signed in.");
            return;
        }

        Rating userRating = new Rating(currentUser, ratingValue, reviewText);
        updateRestaurantWithRating(restaurantId, userRating);
    }

    /**
     * Update a restaurant's rating with a new user rating.
     *
     * @param restaurantId The ID of the restaurant being rated.
     * @param userRating   The user's rating for the restaurant.
     */
    public void updateRestaurantWithRating(String restaurantId, Rating userRating) {
        if (restaurantId == null) {
            Log.e("RatingDialogFragment", "restaurantId is null. Cannot save rating.");
            return;
        }

        DocumentReference restaurantRef = firebaseFirestore.collection("restaurants").document(restaurantId);

        firebaseFirestore.runTransaction(transaction -> {
                    DocumentSnapshot snapshot = transaction.get(restaurantRef);
                    Restaurant restaurant = snapshot.toObject(Restaurant.class);

                    if (restaurant != null) {
                        int oldCount = restaurant.getRatingCount();
                        double oldAverage = restaurant.getMeanRating();

                        double newAverage = ((oldAverage * oldCount) + userRating.getRating()) / (oldCount + 1);
                        restaurant.setRatingCount(oldCount + 1);
                        restaurant.setMeanRating(newAverage);

                        transaction.set(restaurantRef, restaurant);

                        DocumentReference newRatingRef = restaurantRef.collection("comments").document();
                        transaction.set(newRatingRef, userRating);
                    }

                    return null;
                })
                .addOnSuccessListener(aVoid -> Log.d("RatingDialogFragment", restaurantId + "Restaurant and rating updated successfully."))
                .addOnFailureListener(e -> Log.w("RatingDialogFragment", "Error updating restaurant rating", e));
    }

    /**
     * Send a message in a chat between two users.
     *
     * @param messageContent The content of the message.
     * @param currentUserUID The UID of the current user sending the message.
     * @param userUID        The UID of the recipient user.
     */
    public void sendMessage(String messageContent, String currentUserUID, String userUID) {
        String chatId = FirebaseUtil.generateChatId(currentUserUID, userUID);

        Map<String, Object> message = new HashMap<>();
        message.put("content", messageContent);
        message.put("timestamp", System.currentTimeMillis());
        message.put("chatId", chatId);
        // Set the senderUid field
        message.put("senderUid", currentUserUID);

        FireStoreService.getInstance().getFirebaseDatabase().collection("chatMessages")
                .document(chatId)
                .collection("messages")
                .add(message)
                .addOnSuccessListener(documentReference -> handleSuccessfulMessageSend(currentUserUID, userUID));
    }

    private void handleSuccessfulMessageSend(String currentUserUID, String userUID) {
        FireStoreService.getInstance().updateChatMetadata(currentUserUID, userUID);
        FireStoreService.getInstance().updateChatMetadata(userUID, currentUserUID);
    }

    /**
     * Update chat metadata when a new message is sent.
     *
     * @param userId        The UID of the user.
     * @param chatPartnerId The UID of the chat partner.
     */
    public void updateChatMetadata(String userId, String chatPartnerId) {
        FireStoreService.getInstance().getFirebaseDatabase().collection("Users")
                .document(userId)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        Map<String, Map<String, Object>> chats = (Map<String, Map<String, Object>>) document.get("chats");
                        if (chats == null) chats = new HashMap<>();

                        Map<String, Object> chatMetadata = chats.getOrDefault(chatPartnerId, new HashMap<>());
                        assert chatMetadata != null;
                        chatMetadata.put("lastUpdated", System.currentTimeMillis());
                        chats.put(chatPartnerId, chatMetadata);

                        document.getReference().update("chats", chats);
                    }
                });
    }

    /**
     * Store a user's comment in Firebase Firestore.
     *
     * @param restaurantName  The name of the restaurant.
     * @param restaurantType  The type of the restaurant.
     * @param restaurantCity  The city where the restaurant is located.
     * @param userDate        The date when the comment was made.
     * @param userText        The text of the user's comment.
     * @param userRating      The rating given by the user.
     */
    public void storeCommentInFirebase(String restaurantName, String restaurantType,
                                       String restaurantCity, long userDate,
                                       String userText, float userRating) {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        // Create a new comment object
        assert currentUser != null;
        CommentModel newComment = new CommentModel(restaurantName, restaurantType, restaurantCity,
                currentUser.getEmail(), userDate, userRating, userText);

        // Get the user's comment history subcollection reference
        CollectionReference commentHistoryRef = FireStoreService.getInstance().getFirebaseDatabase().collection("Users").document(currentUser.getUid()).collection("commentHistory");

        // Add a new comment document to the user's commentHistory subcollection
        commentHistoryRef.add(newComment)
                .addOnSuccessListener(documentReference -> Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId()))
                .addOnFailureListener(e -> Log.w(TAG, "Error adding document", e));
    }
}

