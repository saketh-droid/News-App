package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

public class profile extends AppCompatActivity {

    public static final String TAG = "profile";
    public FirebaseFirestore firestore;
    public TextView nameTextView, emailTextView, phoneNumberTextView, locationTextView,
            passwordTextView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_template);

        String name = "chandu";

        FirebaseApp.initializeApp(this);

        firestore = FirebaseFirestore.getInstance();

        nameTextView = findViewById(R.id.username);
        emailTextView = findViewById(R.id.email);
        phoneNumberTextView = findViewById(R.id.phonenumber);
        passwordTextView = findViewById(R.id.password);
        locationTextView = findViewById(R.id.location);
        ImageView home = findViewById(R.id.home);

        fetchUserDetails(name);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profile.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void fetchUserDetails(String username) {
        firestore.collection("users")
                .whereEqualTo("UserName", username)
                .get()
                .addOnSuccessListener(querySnapshot -> {
                    if (!querySnapshot.isEmpty()) {
                        for (QueryDocumentSnapshot document : querySnapshot) {
                            String name = document.getString("UserName");
                            String email = document.getString("Email");
                            String phoneNumber = document.getString("Phone_number");
                            String location = document.getString("Location");
                            String password = document.getString("Password");

                            nameTextView.setText(name);
                            emailTextView.setText(email);
                            phoneNumberTextView.setText(phoneNumber);
                            locationTextView.setText(location);

                            Log.d(TAG, "fetchUserDetails: " + name);

                            Log.d(TAG, "fetchUserDetails: " + email);

                            Log.d(TAG, "fetchUserDetails: " + phoneNumber);
                            Log.d(TAG, "fetchUserDetails: " + location);
                        }
                    } else {
                        Log.d(TAG, "No such user with username: " + username);
                    }
                })
                .addOnFailureListener(e -> Log.w(TAG, "Error getting user details", e));
    }
}