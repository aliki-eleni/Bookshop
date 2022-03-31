package com.unipi.p18240.bookshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class MainPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        // Initialize variables
        FirebaseAuth auth = FirebaseAuth.getInstance();
    }
    // Method to change activity
    public void registerPage(View view)
    {
        startActivity(new Intent(MainPage.this, SignUp.class));
    }

    public void loginPage(View view)
    {
        // Method to change activity
        startActivity(new Intent(MainPage.this, LogIn.class));
    }
}