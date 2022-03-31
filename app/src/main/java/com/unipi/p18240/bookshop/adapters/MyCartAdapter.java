package com.unipi.p18240.bookshop.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.unipi.p18240.bookshop.R;
import com.unipi.p18240.bookshop.models.MyCartModel;

import java.util.List;
import java.util.Objects;

public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.ViewHolder>{

    // Initialise variables
    Context context;
    List<MyCartModel> myCartModelList;
    int total_price;
    FirebaseFirestore db;
    FirebaseAuth auth;

    // Constructor for initialization
    public MyCartAdapter(Context context, List<MyCartModel> cartsModelList) {
        this.context = context;
        this.myCartModelList = cartsModelList;
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
    }

    @NonNull
    @Override
    // Method that creates new view holders for the list
    public MyCartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false));
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override

    public void onBindViewHolder(@NonNull MyCartAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        // Get view holders and fill them with data to display.
        holder.title.setText(myCartModelList.get(position).getTitle());
        holder.author.setText(myCartModelList.get(position).getAuthor());
        holder.date.setText(myCartModelList.get(position).getDate());
        holder.time.setText(myCartModelList.get(position).getTime());
        holder.quantity.setText(String.valueOf(myCartModelList.get(position).getQuantity())+ " copies");
        holder.total_price.setText("$ "+ String.valueOf(myCartModelList.get(position).getTotal_price()));
        holder.price.setText("$ "+ String.valueOf(myCartModelList.get(position).getPrice()));

        // Create on click to delete items from cart
        holder.deleteItem.setOnClickListener(v -> db.collection("Cart").document(Objects.requireNonNull(auth.getCurrentUser()).getUid())
                .collection("Current_user")
                .document(myCartModelList.get(position).getDocID())
                .delete()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()){
                        myCartModelList.remove(myCartModelList.get(position));
                        notifyDataSetChanged();
                        Toast.makeText(context, "Item successfully removed", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(context, "Something went wrong  " + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }));

        // Calculation of the final price of all products in the cart
        total_price += myCartModelList.get(position).getTotal_price();
        Intent intent = new Intent("UserTotalPrice");
        intent.putExtra("totalAmount", total_price);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    @Override
    // Method to return the list size
    public int getItemCount() {
        return myCartModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        // Initialize imageView and TextViews
        TextView author, date, title, time, total_price, quantity, price;
        ImageView deleteItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // Define the view fields based on id from the xml file
            deleteItem = itemView.findViewById(R.id.detailed_delete);
            title = itemView.findViewById(R.id.cart_item_book_name);
            author = itemView.findViewById(R.id.cart_item_book_author);
            date = itemView.findViewById(R.id.cart_item_product_current_date);
            time = itemView.findViewById(R.id.cart_item_product_current_time);
            total_price = itemView.findViewById(R.id.cart_item_total_price);
            quantity = itemView.findViewById(R.id.cart_item_total_quantity);
            price = itemView.findViewById(R.id.cart_item_book_price);
        }
    }
}
