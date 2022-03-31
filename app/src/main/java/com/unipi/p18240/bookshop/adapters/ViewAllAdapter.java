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
import com.unipi.p18240.bookshop.models.ViewAllModel;

import java.util.List;

public class ViewAllAdapter extends RecyclerView.Adapter<ViewAllAdapter.ViewHolder> {

    // Initialise variables
    Context context;
    List<ViewAllModel> viewAllModelList;

    // Constructor for initialization
    public ViewAllAdapter(Context context, List<ViewAllModel> viewAllModelList) {
        this.context = context;
        this.viewAllModelList = viewAllModelList;
    }

    @NonNull
    @Override
    // Method that creates new view holders for the list
    public ViewAllAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_all_item, parent, false));
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        // Initialize imageView and TextViews
        ImageView viewImg;
        TextView title, author,description, price, quantity, rating;

        public ViewHolder(View inflate) {
            super(inflate);

            // Define the view fields based on id from the xml file
            title = itemView.findViewById(R.id.view_item_title);
            viewImg = itemView.findViewById(R.id.view_item_img);
            author = itemView.findViewById(R.id.view_item_author);
            description = itemView.findViewById(R.id.view_item_description);
            price = itemView.findViewById(R.id.view_item_price);
            quantity = itemView.findViewById(R.id.view_item_quantity);
            rating = itemView.findViewById(R.id.view_item_rating);
        }
    }
    @Override
    public void onBindViewHolder(@NonNull ViewAllAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        // Get view holders and fill them with data to display.
        Glide.with(context).load(viewAllModelList.get(position).getImg_url()).into(holder.viewImg);
        holder.author.setText(context.getResources().getString(R.string.author_by, viewAllModelList.get(position).getAuthor()));
        holder.description.setText(viewAllModelList.get(position).getDescription());
        holder.title.setText(viewAllModelList.get(position).getTitle());
        holder.rating.setText(viewAllModelList.get(position).getRating());
        holder.quantity.setText((String.valueOf(viewAllModelList.get(position).getQuantity())));
        holder.price.setText(context.getResources().getString(R.string.price_text_rec, viewAllModelList.get(position).getPrice()));

        // Create on click to view the book
        holder.itemView.setOnClickListener(v -> {

            // Change activity
            Intent intent = new Intent(context, DetailedActivity.class);
            // Get extra the detail
            intent.putExtra("detail", viewAllModelList.get(position));
            context.startActivity(intent);

        });
    }
    @Override
    // Method to return the list size
    public int getItemCount() {
        return viewAllModelList.size();
    }
}
