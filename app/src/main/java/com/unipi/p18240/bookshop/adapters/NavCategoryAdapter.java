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
import com.unipi.p18240.bookshop.R;
import com.unipi.p18240.bookshop.ViewAllActivity;
import com.unipi.p18240.bookshop.models.NavCategoryModel;

import java.util.List;

public class NavCategoryAdapter extends RecyclerView.Adapter<NavCategoryAdapter.ViewHolder> {

    // Initialise variables
    private final Context context;
    private final List<NavCategoryModel> navCategoryModelList;

    // Constructor for initialization
    public NavCategoryAdapter(Context context, List<NavCategoryModel> navCategoryModelList) {
        this.context = context;
        this.navCategoryModelList = navCategoryModelList;
    }

    @NonNull
    @Override
    // Method that creates new view holders for the list
    public NavCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_cat_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NavCategoryAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        // Get view holders and fill them with data to display.
        Glide.with(context).load(navCategoryModelList.get(position).getImageView()).into(holder.imageView);
        holder.name.setText(navCategoryModelList.get(position).getName());

        // Create on click to view the book
        holder.itemView.setOnClickListener(v -> {

            // Change activity
            Intent intent = new Intent(context, ViewAllActivity.class);
            // Get extra the type
            intent.putExtra("type", navCategoryModelList.get(position).getType());
            context.startActivity(intent);
        });
    }
    @Override
    // Method to return the list size
    public int getItemCount() {
        return navCategoryModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        // Initialize imageView and TextViews
        ImageView imageView;
        TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // Define the view fields based on id from the xml file
            imageView = itemView.findViewById(R.id.cat_nav_img);
            name = itemView.findViewById(R.id.nav_cat_name);
        }
    }
}
