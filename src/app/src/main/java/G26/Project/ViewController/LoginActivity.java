package G26.Project.ViewController;

import static G26.Project.Resources.Constants.AUTH_FAILURE;
import static G26.Project.Resources.Constants.LOG_IN_LOADING;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;

import G26.Project.ViewController.BackGroundService.MessageListenerService;
import G26.Project.ViewController.Util.AuthenticationService;
import G26.Project.R;
import G26.Project.ViewController.Util.FirebaseUtil;

/**
 * The `LoginActivity` class represents the user interface for logging in to the application.
 * Users can enter their email and password to authenticate and gain access to the main UI.
 * {@code @Author: Jing Li (Original)}
 *           UID : u7533831
 */
public class LoginActivity extends AppCompatActivity {
    private AuthenticationService authenticationService;
    private AlertDialog progressDialog;
    private EditText etEmail, etPassword;
    private Button btnLogin, btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initFirebase();
        initViews();
        setListeners();
    }

    /**
     * Initializes Firebase services used for authentication.
     */
    private void initFirebase() {
        FirebaseApp.initializeApp(this);
        authenticationService = AuthenticationService.getInstance();
    }

    /**
     * Initializes the UI components (EditText, Buttons) and a progress dialog.
     */
    private void initViews() {
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(new ProgressBar(this));
        builder.setMessage(LOG_IN_LOADING);
        progressDialog = builder.create();
    }

    /**
     * Sets click listeners for the login and register buttons.
     */
    private void setListeners() {
        btnLogin.setOnClickListener(v -> handleLogin());
        btnRegister.setOnClickListener(v -> navigateToRegistration());
    }

    /**
     * Handles the login process when the login button is clicked.
     */
    private void handleLogin() {
        if (FirebaseUtil.validateEmailAndPassword(etEmail, etPassword)) {
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            progressDialog.show();
            authenticationService.authenticateUser(email, password).observe(this, this::handleAuthenticationResult);
        }
    }

    /**
     * Handles the result of the authentication process.
     *
     * @param task The result of the authentication task.
     */
    private void handleAuthenticationResult(Task<AuthResult> task) {
        progressDialog.dismiss();
        if (task.isSuccessful()) {
            Intent serviceIntent = new Intent(this, MessageListenerService.class);
            startService(serviceIntent);
            navigateToMainUI();
        } else {
            String errorMessage = task.getException() != null ? task.getException().getMessage() : AUTH_FAILURE;
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Navigates to the MainActivity upon successful login.
     */
    private void navigateToMainUI() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    /**
     * Navigates to the registration activity when the register button is clicked.
     */
    private void navigateToRegistration() {
        startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
    }
}
