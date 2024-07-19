package G26.Project.ViewController;

import static G26.Project.Resources.Constants.SIGN_IN_FAILURE;
import static G26.Project.Resources.Constants.SIGN_IN_LOADING;
import static G26.Project.Resources.Constants.SIGN_IN_SUCCESS;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

import G26.Project.ViewController.Util.AuthenticationService;
import G26.Project.ViewController.Util.FireStoreService;
import G26.Project.R;
import G26.Project.ViewController.Util.FirebaseUtil;

/**
 * The `RegistrationActivity` class represents the user interface for user registration.
 * Users can enter their email and password to create an account and sign up for the application.
 * {@code @Author: Jing Li (Original)}
 *           UID : u7533831
 */
public class RegistrationActivity extends AppCompatActivity {
    private AuthenticationService authenticationService;
    private EditText etEmail, etPassword;
    private AlertDialog progressDialog;
    private Button btnRegister, btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        initService();
        initViews();
        setListeners();
    }

    /**
     * Initializes the AuthenticationService for user registration.
     */
    private void initService() {
        authenticationService = AuthenticationService.getInstance();
    }

    /**
     * Initializes the UI components (EditText, Buttons) and a progress dialog.
     */
    private void initViews() {
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnRegister = findViewById(R.id.btnRegister);
        btnLogin = findViewById(R.id.btnLogin);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(new ProgressBar(this));
        builder.setMessage(SIGN_IN_LOADING);
        progressDialog = builder.create();
    }

    /**
     * Sets click listeners for the register and login buttons.
     */
    private void setListeners() {
        btnRegister.setOnClickListener(v -> handleRegistration());
        btnLogin.setOnClickListener(v -> navigateToLogin());
    }

    /**
     * Handles the user registration process when the register button is clicked.
     */
    private void handleRegistration() {
        if (FirebaseUtil.validateEmailAndPassword(etEmail, etPassword)) {
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            progressDialog.show();
            authenticationService.registerUser(email, password).observe(this, this::handleRegistrationResult);
        }
    }

    /**
     * Handles the result of the registration process.
     *
     * @param task The result of the registration task.
     */
    private void handleRegistrationResult(Task<AuthResult> task) {
        progressDialog.dismiss();
        if (task.isSuccessful()) {
            FirebaseUser firebaseUser = authenticationService.getFirebaseUser();
            FireStoreService.getInstance().storeUserData(firebaseUser);
            Toast.makeText(this, SIGN_IN_SUCCESS, Toast.LENGTH_SHORT).show();
            navigateToLoginUI();
        } else {
            String errorMessage = task.getException() != null ? task.getException().getMessage() : SIGN_IN_FAILURE;
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Navigates to the login UI upon successful registration.
     */
    private void navigateToLoginUI() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    /**
     * Navigates to the login activity when the login button is clicked.
     */
    private void navigateToLogin() {
        startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
    }

}
