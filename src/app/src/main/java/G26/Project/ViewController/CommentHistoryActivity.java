package G26.Project.ViewController;

import static G26.Project.Resources.Constants.DATABASE_COMMENT_HISTORY;
import static G26.Project.Resources.Constants.DATABASE_USERS;
import static G26.Project.Resources.Constants.DATABASE_USER_DATE;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import G26.Project.ViewController.Util.AuthenticationService;
import G26.Project.ViewController.Util.FireStoreService;
import G26.Project.ViewController.Adapter.CommentHistoryAdapter;
import G26.Project.Model.Restaurant.CommentModel;
import G26.Project.R;

/**
 * CommentHistoryActivity displays a user's comment history on various restaurants.
 * Comments are fetched from Firebase Firestore and displayed in a RecyclerView.
 * <p>
 * {@code @Author: Jun Zhu (Original)}
 *           UID : u7602081
 * <p>
 * {@code @Contributor: Jing Li : wrote the 'loadCommentsFromFirebase' method}
 */
public class CommentHistoryActivity extends AppCompatActivity {

    private static final String TAG = "CommentHistoryActivity";

    private RecyclerView recyclerView;
    private CommentHistoryAdapter commentAdapter;
    private FirebaseFirestore db;

    /**
     * This method sets up the UI and initializes interactions when the activity is created.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously
     *                           being shut down then this Bundle contains the data it most recently supplied.
     *                           Otherwise it is null.
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments_history);

        // Get the Firestore instance
        db = FireStoreService.getInstance().getFirebaseDatabase();

        // Set up the RecyclerView
        setupRecyclerView();

        // Load user comments from Firebase Firestore
        loadCommentsFromFirebase(AuthenticationService.getInstance().getFirebaseUser().getUid());
    }

    /**
     * Initializes and sets up the RecyclerView for displaying comment history.
     */
    private void setupRecyclerView() {
        recyclerView = findViewById(R.id.recycler_past_comments);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        commentAdapter = new CommentHistoryAdapter(new ArrayList<>());
        recyclerView.setAdapter(commentAdapter);
    }

    /**
     * Fetches and loads the comments from Firebase Firestore based on the user's unique identifier (UID).
     *
     * @param uid The unique identifier for the user in Firebase.
     */
    private void loadCommentsFromFirebase(String uid) {
        // Reference to the user's commentHistory subcollection in Firestore
        CollectionReference commentHistoryRef = db.collection(DATABASE_USERS).document(uid).collection(DATABASE_COMMENT_HISTORY);

        // Log the start of the fetch operation
        Log.d(TAG, "Attempting to load comments for user ID: " + uid);

        // Fetch the comments in descending order of comment date
        commentHistoryRef.orderBy(DATABASE_USER_DATE, Query.Direction.DESCENDING).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                List<CommentModel> comments = new ArrayList<>();
                QuerySnapshot documents = task.getResult();

                // Check and log if documents are empty
                if (documents.isEmpty()) {
                    Log.d(TAG, "No comments found for user.");
                } else {
                    Log.d(TAG, "Number of comments received: " + documents.size());

                    // Convert each document into CommentModel and add to the list
                    for (QueryDocumentSnapshot document : documents) {
                        CommentModel comment = document.toObject(CommentModel.class);
                        comments.add(comment);
                        Log.d(TAG, "Comment data: " + comment);
                    }
                }

                // Update the RecyclerView adapter with the fetched comments
                commentAdapter.updateComments(comments);
                Log.d(TAG, "Comments loaded into adapter successfully.");
            } else {
                // Log any errors that occurred during the fetch
                Log.w(TAG, "Error getting documents.", task.getException());
            }
        });
    }
}
