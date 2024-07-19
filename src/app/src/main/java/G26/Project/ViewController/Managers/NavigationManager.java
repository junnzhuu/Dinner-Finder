package G26.Project.ViewController.Managers;

import static G26.Project.Resources.Constants.NAVIGATION_DISPLAY_NAME;
import static G26.Project.Resources.Constants.NAVIGATION_FAIL_TEXT;
import static G26.Project.Resources.Constants.NAVIGATION_PROFILE_IMAGE_URL;
import static G26.Project.Resources.Constants.NAVIGATION_SUCCESS_TEXT;

import android.location.Address;
import android.location.Geocoder;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

import G26.Project.ViewController.MainActivity;
import G26.Project.ViewController.Util.AuthenticationService;
import G26.Project.ViewController.Util.FireStoreService;
import G26.Project.R;
/**
 * Manages the initialisation and handling of navigation view elements including user details
 * and location data. It utilises Firebase for user data retrieval and update, as well as
 * Geocoder for reverse geocoding the user's location.
 * This class is specifically tied to the MainActivity class for context-related operations
 * and assumes the existence of specific UI elements within the navigation view it's instructed
 * to manage.
 * {@code @Author: Catherine Jiawei Ye (Original)}
 *           UID : u7574419
 */
public class NavigationManager {
    //Initialise the display of user email, location, avatar and username
    public void initNavigationView(NavigationView navigationView, MainActivity context) throws IOException {
        FirebaseUser user = AuthenticationService.getInstance().getFirebaseUser();
        DocumentReference docRef = FireStoreService.getInstance().getFirebaseDatabase().collection("Users").document(user.getUid());
        View navigationHeader = navigationView.getHeaderView(0);

        TextView email = navigationHeader.findViewById(R.id.userEmail);
        TextView location = navigationHeader.findViewById(R.id.location);
        ImageView avatar = navigationHeader.findViewById(R.id.userAvatar);
        EditText username = navigationHeader.findViewById(R.id.userName);

        email.setText(user.getEmail());
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    //Fetch profile image url and display name (i.e. username) from firebase
                    String profileImageUrl = documentSnapshot.getString(NAVIGATION_PROFILE_IMAGE_URL);
                    String displayName = documentSnapshot.getString(NAVIGATION_DISPLAY_NAME);
                    if (profileImageUrl != null) {
                        //Set profile image to UI
                        Glide.with(avatar.getContext())
                                .load(profileImageUrl)
                                .into(avatar);
                    }
                    if (displayName != null) {
                        //Set display name (i.e. username) to UI
                        username.setText(displayName);
                    }
                }
            }
        });
        username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String name = String.valueOf(username.getText()).trim();
                //When the username EditText does not have focus and new input from Edit Text is not empty, sync new data into Firebase.
                if (!hasFocus && !name.equals("")) {
                    docRef.update("displayName", name)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    // Display a short duration toast message indicating the username was successfully updated.
                                    Toast.makeText(context, NAVIGATION_SUCCESS_TEXT, Toast.LENGTH_SHORT).show();

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    // Display a short duration toast message indicating the username update failed.
                                    Toast.makeText(context, NAVIGATION_FAIL_TEXT, Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });

        try {

            Geocoder geocoder = new Geocoder(context, Locale.getDefault());
            // Get a list of addresses by reverse geocoding using the latitude and longitude coordinates.
            List<Address> addresses = geocoder.getFromLocation(context.getLatitude(), context.getLongitude(), 1);
            if (!addresses.isEmpty()) {
                // Get the city name (locality) from the first address in the list.
                String city = addresses.get(0).getLocality();
                location.setText(city);
            } else {
                // Handle the case when no addresses are found
                location.setText("Unknown");
            }
        } catch (IOException e) {
            // Handle the IOException
            e.printStackTrace();
        }

    }
}
