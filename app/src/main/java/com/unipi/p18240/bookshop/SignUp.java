package com.unipi.p18240.bookshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {

    // Initialize variables
    private EditText user_name, email, password;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Get fire base instance
        auth = FirebaseAuth.getInstance();

        // Define the view fields based on id from the xml file
        user_name = findViewById(R.id.userName);
        email = findViewById(R.id.userEmail);
        password = findViewById(R.id.userPassword);
        Button signup = findViewById(R.id.signUp);

        // Crate on click listener when the user sign up
        signup.setOnClickListener(v -> createUser());
    }
    // Method for register the user
    private void createUser() {

        String User_name = user_name.getText().toString();
        String User_email = email.getText().toString();
        String User_password = password.getText().toString();

        // Validating the text fields if they are empty or not.
        if (User_name.isEmpty())
        {
            // Print message for user
            user_name.setError("Please enter your username");
        }
        else if (User_email.isEmpty())
        {
            // Print message for users
            email.setError("Please enter your email");
        }
        else if (User_password.isEmpty())
        {
            // Print message for user
            password.setError("Please enter your password");
        }
        // Check thw length of the password
        else if (password.length() < 6 )
        {
            password.setError("Password must be at least 6 characters long");
        }
        else {
            // Check the authentication of the user's credentials
            auth.createUserWithEmailAndPassword(User_email, User_password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(SignUp.this, "Welcome!", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(SignUp.this, MainActivity.class));
                        }
                        else
                        {
                            Toast.makeText(SignUp.this, "Wrong credentials.Please try again", Toast.LENGTH_SHORT).show();

                            // Clear the fields
                            user_name.setText("");
                            email.setText("");
                            password.setText("");
                        }
                    });
        }
    }
}