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
import com.unipi.p18240.bookshop.models.PopularModel;

import java.util.List;

public class PopularAdapters extends RecyclerView.Adapter<PopularAdapters.ViewHolder>
{

    // Initialise variables
    private final Context context;
    private final List<PopularModel> popularModelList;

    // Constructor for initialization
    public PopularAdapters(Context context, List<PopularModel> popularModelList) {
        this.context = context;
        this.popularModelList = popularModelList;
    }

    @NonNull
    @Override
    // Method that creates new view holders for the list
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        // Get view holders and fill them with data to display.
        Glide.with(context).load(popularModelList.get(position).getImg_url()).into(holder.popImg);
        holder.title.setText(popularModelList.get(position).getTitle());
        holder.author.setText(popularModelList.get(position).getAuthor());
        holder.description.setText(popularModelList.get(position).getDescription());
        holder.price.setText(context.getResources().getString(R.string.price_text_rec, popularModelList.get(position).getPrice()));
        holder.quantity.setText(String.valueOf(popularModelList.get(position).getQuantity()));
        holder.rating.setText(popularModelList.get(position).getRating());

        // Create on click to view the book
        holder.itemView.setOnClickListener(v -> {

            // Change activity
            Intent intent = new Intent(context, DetailedActivity.class);
            // Get extra the detail
            intent.putExtra("detail", popularModelList.get(position));
            context.startActivity(intent);
        });
    }

    @Override
    // Method to return the list size
    public int getItemCount() {

        return popularModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        // Initialize imageView and TextViews
        ImageView popImg;
        TextView title, author,description, price, quantity, rating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // Define the view fields based on id from the xml file
            title = itemView.findViewById(R.id.show_book_title);
            popImg = itemView.findViewById(R.id.pop_img);
            author = itemView.findViewById(R.id.show_auth_name);
            description = itemView.findViewById(R.id.show_pop_description);
            price = itemView.findViewById(R.id.show_pop_price);
            quantity = itemView.findViewById(R.id.show_pop_quantity);
            rating = itemView.findViewById(R.id.show_pop_rating);
        }
    }
}



