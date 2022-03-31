package com.unipi.p18240.bookshop.models;

import java.io.Serializable;

public class RecommendedModel implements Serializable {

    // Initialize variables
    String title, author, img_url, rating, description;
    int quantity, price;

    // Required empty public constructor
    public RecommendedModel(){}

    // Constructor for initialization
    public RecommendedModel(String title, String author, String img_url, String rating, String description, int quantity, int price) {
        this.title = title;
        this.author = author;
        this.img_url = img_url;
        this.rating = rating;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
