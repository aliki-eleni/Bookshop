package com.unipi.p18240.bookshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.unipi.p18240.bookshop.adapters.ViewAllAdapter;
import com.unipi.p18240.bookshop.models.ViewAllModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ViewAllActivity extends AppCompatActivity {

    // Initialize variables
    FirebaseFirestore db;

    List<ViewAllModel> viewAllModelList;
    ViewAllAdapter viewAllAdapter;
    RecyclerView recyclerView;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);

        String type = getIntent().getStringExtra("type");
        // Get fire base instance
        db = FirebaseFirestore.getInstance();
        // Define the view field based on id from the xml file
        recyclerView = findViewById(R.id.view_all_cat);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        viewAllModelList = new ArrayList<>();
        viewAllAdapter = new ViewAllAdapter(this, viewAllModelList);
        recyclerView.setAdapter(viewAllAdapter);

        if(type != null)
        {
            // Connect to data base to get all the documents from a specific collection based on an specific field
            db.collection("All_categories").whereEqualTo("type",type).get().addOnCompleteListener(task -> {
                for(DocumentSnapshot documentSnapshot : Objects.requireNonNull(task.getResult()).getDocuments())
                {
                    ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                    viewAllModelList.add(viewAllModel);
                    viewAllAdapter.notifyDataSetChanged();
                }
            });
        }
    }
}