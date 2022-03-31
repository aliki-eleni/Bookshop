package com.unipi.p18240.bookshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class LogIn extends AppCompatActivity {

    // Initialize variables
    private EditText email, password;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        // Get fire base instance
        auth = FirebaseAuth.getInstance();

        // Define the view fields based on id from the xml file
        email = findViewById(R.id.mailField);
        password = findViewById(R.id.passwordField);
        Button logIn = findViewById(R.id.loginButton);

        // Crate on click listener when the user login
        logIn.setOnClickListener(v -> loginUser());
    }

    // Method for login the user
    public void loginUser()
    {

        String UserEmail = email.getText().toString();
        String UserPassword = password.getText().toString();

        // Validating the text fields if they are empty or not.
        if (UserEmail.isEmpty())
        {
            // Print message for users
            email.setError("Please enter your email");
        }
        else if (UserPassword.isEmpty())
        {
            // Print message for user
            password.setError("Please enter your password");
        }
        else
        {
            // Check the authentication of the user's credentials
            auth.signInWithEmailAndPassword(UserEmail, UserPassword)
                    .addOnCompleteListener(task -> {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(LogIn.this,"Welcome back !",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LogIn.this, MainActivity.class));
                        }
                        else
                        {
                            Toast.makeText(LogIn.this,"Wrong credentials. Please try again" + task.getException(),Toast.LENGTH_SHORT).show();

                            email.setText("");
                            password.setText("");
                        }
                    });
        }
    }
}