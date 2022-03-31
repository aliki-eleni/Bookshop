package com.unipi.p18240.bookshop.ui.category;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.unipi.p18240.bookshop.R;
import com.unipi.p18240.bookshop.adapters.NavCategoryAdapter;
import com.unipi.p18240.bookshop.models.NavCategoryModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CategoryFragment extends Fragment {

    // Initialise variables
    RecyclerView recyclerView;
    List<NavCategoryModel> navCategoryModelList;
    NavCategoryAdapter navCategoryAdapter;

    FirebaseFirestore db;

    @SuppressLint("NotifyDataSetChanged")
    // Method that creates new view holders for the list
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_category, container, false);

        // Get fire base instance
        db= FirebaseFirestore.getInstance();

        // Define the view field based on id from the xml file
        recyclerView = root.findViewById(R.id.cat_rec);

        // Create recycler viewer to display all the documents from a specific collection
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));

        // Initialise variables
        navCategoryModelList = new ArrayList<>();
        navCategoryAdapter = new NavCategoryAdapter(getActivity(), navCategoryModelList);
        recyclerView.setAdapter(navCategoryAdapter);

        // Connect to data base in order to get all the documents from the collection
        db.collection("Book_Categories")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()){
                        for (QueryDocumentSnapshot doc : Objects.requireNonNull(task.getResult())){
                            NavCategoryModel nc = doc.toObject(NavCategoryModel.class);
                            navCategoryModelList.add(nc);
                            navCategoryAdapter.notifyDataSetChanged();
                        }
                    }
                    else{
                        Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                    }
                });
        return root;
    }
}