package com.unipi.p18240.bookshop.models;

import java.io.Serializable;

public class ViewAllModel implements Serializable{

    // Initialize variables
    String title, author, type, description, img_url, rating;
    int price, quantity;

    // Required empty public constructor
    public  ViewAllModel(){}

    // Constructor for initialization
    public ViewAllModel(String title, String author, String type, String description, String img_url, String rating, int price, int quantity) {
        this.title = title;
        this.author = author;
        this.type = type;
        this.description = description;
        this.img_url = img_url;
        this.rating = rating;
        this.price = price;
        this.quantity = quantity;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
