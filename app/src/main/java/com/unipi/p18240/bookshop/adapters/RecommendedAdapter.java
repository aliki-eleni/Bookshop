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
import com.unipi.p18240.bookshop.models.RecommendedModel;

import java.util.List;

public class RecommendedAdapter extends RecyclerView.Adapter<RecommendedAdapter.ViewHolder>
{
    // Initialise variables
    private final Context context;
    private final List<RecommendedModel> recommendedModelList;

    // Constructor for initialization
    public RecommendedAdapter(Context context, List<RecommendedModel> recommendedModelList) {
        this.context = context;
        this.recommendedModelList = recommendedModelList;
    }

    @NonNull
    @Override
    // Method that creates new view holders for the list
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recomended_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendedAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        // Get view holders and fill them with data to display.
        Glide.with(context).load(recommendedModelList.get(position).getImg_url()).into(holder.recImg);
        holder.title.setText(recommendedModelList.get(position).getTitle());
        holder.author.setText(recommendedModelList.get(position).getAuthor());
        holder.description.setText(recommendedModelList.get(position).getDescription());
        holder.price.setText(context.getResources().getString(R.string.price_text_rec, recommendedModelList.get(position).getPrice()));
        holder.quantity.setText(String.valueOf(recommendedModelList.get(position).getQuantity()));
        holder.rating.setText(recommendedModelList.get(position).getRating());

        // Create on click to view the book
        holder.itemView.setOnClickListener(v -> {

            // Change activity
            Intent intent = new Intent(context, DetailedActivity.class);
            // Get extra the detail
            intent.putExtra("detail", recommendedModelList.get(position));
            context.startActivity(intent);
        });
    }

    @Override
    // Method to return the list size
    public int getItemCount() {

        return recommendedModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        // Initialize imageView and TextViews
        ImageView recImg;
        TextView title, author,description, price, quantity, rating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // Define the view fields based on id from the xml file
            title = itemView.findViewById(R.id.show_book_title);
            recImg = itemView.findViewById(R.id.rec_img);
            author = itemView.findViewById(R.id.show_rec_auth_name);
            description = itemView.findViewById(R.id.show_rec_description);
            quantity = itemView.findViewById(R.id.show_rec_quantity);
            price = itemView.findViewById(R.id.show_rec_price);
            quantity = itemView.findViewById(R.id.show_rec_quantity);
            rating = itemView.findViewById(R.id.show_rec_rating);
        }
    }
}
