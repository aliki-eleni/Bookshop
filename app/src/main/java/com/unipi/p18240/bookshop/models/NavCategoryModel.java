package com.unipi.p18240.bookshop.models;

public class NavCategoryModel {

    // Initialize variables
    String name, imageView, type;

    // Required empty public constructor
    public NavCategoryModel() {}

    // Constructor for initialization
    public NavCategoryModel(String name, String imageView, String type) {
        this.name = name;
        this.imageView = imageView;
        this.type = type;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageView() {
        return imageView;
    }

    public void setImageView(String imageView) {
        this.imageView = imageView;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
