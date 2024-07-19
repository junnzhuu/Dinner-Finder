package G26.Project.ViewController.Util;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * The `AuthenticationService` class provides authentication services using Firebase Authentication.
 * {@code @Author: Jing Li (Original)}
 *           UID : u7533831
 */
public class AuthenticationService {
    private static AuthenticationService instance = null;
    private final FirebaseAuth mAuth;

    private AuthenticationService() {
        mAuth = FirebaseAuth.getInstance();
    }

    /**
     * Get the singleton instance of the `AuthenticationService` class.
     *
     * @return The `AuthenticationService` instance.
     */
    public static synchronized AuthenticationService getInstance() {
        if (instance == null) {
            instance = new AuthenticationService();
        }
        return instance;
    }

    /**
     * Authenticate a user with the provided email and password.
     *
     * @param email    The user's email.
     * @param password The user's password.
     * @return A LiveData object containing the authentication task result.
     */
    public LiveData<Task<AuthResult>> authenticateUser(String email, String password) {
        MutableLiveData<Task<AuthResult>> liveData = new MutableLiveData<>();
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(liveData::setValue);
        return liveData;
    }


    /**
     * Register a new user with the provided email and password.
     *
     * @param email    The new user's email.
     * @param password The new user's password.
     * @return A LiveData object containing the registration task result.
     */
    public LiveData<Task<AuthResult>> registerUser(String email, String password) {
        MutableLiveData<Task<AuthResult>> liveData = new MutableLiveData<>();
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(liveData::setValue);
        return liveData;
    }

    /**
     * Get the currently authenticated Firebase user.
     *
     * @return The FirebaseUser object representing the authenticated user, or null if not authenticated.
     */
    public FirebaseUser getFirebaseUser () {
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        if (firebaseUser != null) {
            return firebaseUser;
        } else {
            return null;
        }
    }
}

