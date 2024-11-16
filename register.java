package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class register extends AppCompatActivity {

    public static final String TAG = "register";
    public TextView nameTextView, phoneNumberTextView, locationTextView,
            passwordTextView;
    public FirebaseFirestore db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        db = FirebaseFirestore.getInstance();

        nameTextView = findViewById(R.id.username_field);
        phoneNumberTextView = findViewById(R.id.phno_field);
        passwordTextView = findViewById(R.id.password_field);
        locationTextView = findViewById(R.id.location_field);
        Button register = findViewById(R.id.registration);
        Button login = findViewById(R.id.login_button);
        Button explore = findViewById(R.id.Explore_Freely);

        explore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(register.this, MainActivity.class);
                startActivity(intent);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(register.this, login.class);
                startActivity(intent);
            }
        });
    }

    public void register(){
        Map<String, Object> user = new HashMap<>();
        String username = nameTextView.getText().toString();
        String phonenumber = phoneNumberTextView.getText().toString();
        String password = passwordTextView.getText().toString();
        String location = locationTextView.getText().toString();
        user.put("Location", location);
        user.put("Password", password);
        user.put("Phone_number", phonenumber);
        user.put("UserName", username);
        db.collection("users").add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.d(TAG, "onSuccess: "+ documentReference.getId());
                Toast.makeText(register.this, "Registered Successfully.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(register.this, login.class);
                startActivity(intent);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, "Error handling document", e);
                Toast.makeText(register.this, "System Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
