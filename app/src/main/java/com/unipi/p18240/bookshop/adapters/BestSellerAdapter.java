package com.unipi.p18240.bookshop.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.unipi.p18240.bookshop.DetailedActivity;
import com.unipi.p18240.bookshop.R;
import com.unipi.p18240.bookshop.models.BestSellerModel;

import java.util.List;

public class BestSellerAdapter extends RecyclerView.Adapter<BestSellerAdapter.ViewHolder> {


    // Initialize variables
    private final Context context;
    private final List<BestSellerModel> bestSellerModelList;

    // Constructor for initialization
    public BestSellerAdapter(Context context, List<BestSellerModel> bestSellerModelList) {
        this.context = context;
        this.bestSellerModelList = bestSellerModelList;
    }

    @NonNull
    @Override
    // Method that creates new view holders for the list
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.best_seller_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        // Get view holders and fill them with data to display.
        Glide.with(context).load(bestSellerModelList.get(position).getImg_url()).into(holder.bsbImg);
        holder.title.setText(bestSellerModelList.get(position).getTitle());
        holder.author.setText(bestSellerModelList.get(position).getAuthor());
        holder.description.setText(bestSellerModelList.get(position).getDescription());
        holder.price.setText(context.getResources().getString(R.string.price_text_rec, bestSellerModelList.get(position).getPrice()));
        holder.quantity.setText(String.valueOf(bestSellerModelList.get(position).getQuantity()));
        holder.rating.setText(bestSellerModelList.get(position).getRating());

        // Create on click listener for the list items
        holder.itemView.setOnClickListener(v -> {

            // Change activity
            Intent intent = new Intent(context, DetailedActivity.class);
            // Get extra the type
            intent.putExtra("detail", bestSellerModelList.get(position));
            context.startActivity(intent);

        });
    }

    @Override
    // Method to return the list size
    public int getItemCount() {


        return bestSellerModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        // Initialize imageView and TextViews
        ImageView bsbImg;
        TextView title, author, description, price, quantity, rating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // Define the view fields based on id from the xml file
            title = itemView.findViewById(R.id.show_bsb_book_title);
            bsbImg = itemView.findViewById(R.id.bsb_img);
            author = itemView.findViewById(R.id.show_bsb_auth_name);
            description = itemView.findViewById(R.id.show_bsb_description);
            price = itemView.findViewById(R.id.show_bsb_price);
            quantity = itemView.findViewById(R.id.show_bsb_quantity);
            rating = itemView.findViewById(R.id.show_bsb_rating);
        }
    }
}
