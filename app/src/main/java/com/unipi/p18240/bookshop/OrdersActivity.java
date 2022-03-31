package com.unipi.p18240.bookshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;


public class OrdersActivity extends AppCompatActivity {

    // Initialize variables
    Button return_to_main_page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        // Define the view field based on id from the xml file
        return_to_main_page = findViewById(R.id.return_to_main_page);
        //Create new on click listener to change page
        return_to_main_page.setOnClickListener(v -> startActivity(new Intent(OrdersActivity.this, MainActivity.class)));
    }
}