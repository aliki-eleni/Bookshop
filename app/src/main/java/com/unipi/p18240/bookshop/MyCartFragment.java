package com.unipi.p18240.bookshop;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.unipi.p18240.bookshop.adapters.MyCartAdapter;
import com.unipi.p18240.bookshop.models.MyCartModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MyCartFragment extends Fragment {

    // Initialise variables
    FirebaseFirestore db;
    FirebaseAuth auth;

    RecyclerView recyclerView;
    MyCartAdapter myCartAdapter;
    List<MyCartModel> myCartModelList;
    TextView totalAmount;
    Button buy;

    // Required empty public constructor
    public MyCartFragment() {

    }

    @SuppressLint("NotifyDataSetChanged")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_my_cart, container, false);

        // Get fire base instance
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        // Define the view field based on id from the xml file
        buy = root.findViewById(R.id.buy_now);
        totalAmount = root.findViewById(R.id.cart_total_price);
        recyclerView = root.findViewById(R.id.cart_rec);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setVisibility(View.GONE);

        LocalBroadcastManager.getInstance(requireActivity()).registerReceiver(mMessageReceiver, new IntentFilter("UserTotalPrice"));

        // Initialise variables
        myCartModelList = new ArrayList<>();
        myCartAdapter = new MyCartAdapter(getActivity(), myCartModelList);
        recyclerView.setAdapter(myCartAdapter);

        // Connect to data base in order to get all the documents from the collection
        db.collection("Cart").document(Objects.requireNonNull(auth.getCurrentUser()).getUid())
                .collection("Current_user").get().addOnCompleteListener(task -> {
                    if(task.isSuccessful())
                    {
                        for (DocumentSnapshot documentSnapshot : Objects.requireNonNull(task.getResult()).getDocuments())
                        {
                            MyCartModel myCartModel = documentSnapshot.toObject(MyCartModel.class);
                            Objects.requireNonNull(myCartModel).setDocID(documentSnapshot.getId());
                            myCartModelList.add(myCartModel);
                            myCartAdapter.notifyDataSetChanged();
                            recyclerView.setVisibility(View.VISIBLE);
                        }
                    }
                });

        // Set on click listener for the button when the user buy an item
        buy.setOnClickListener(v -> {

            // Clear the cart after buy
            db.collection("Cart").document(auth.getCurrentUser().getUid()).collection("Current_user").get()
                    .addOnCompleteListener(task ->
            {
                if(task.isSuccessful())
                {
                    for(DocumentSnapshot documentSnapshot : Objects.requireNonNull(task.getResult()).getDocuments())
                    {
                        db.collection("Cart").document(auth.getCurrentUser().getUid())
                                .collection("Current_user")
                                .document(documentSnapshot.getId()).delete();
                    }
                }
            });

            // Change activity
            Intent intent = new Intent(getContext(), OrdersActivity.class);
            startActivity(intent);
            // Get extra item
            intent.putExtra("item", (Serializable) myCartModelList);
        });

        return root;
    }

    // Method to calculation the total amount of all the items in the cart
    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @SuppressLint("SetTextI18n")
        @Override
        public void onReceive(Context context, Intent intent) {
            int totalBill = intent.getIntExtra("totalAmount", 0);
            totalAmount.setText("Total Price : $ " + totalBill);
        }
    };
}

