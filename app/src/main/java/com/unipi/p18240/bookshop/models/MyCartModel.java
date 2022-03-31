package com.unipi.p18240.bookshop.models;

import java.io.Serializable;

public class MyCartModel implements Serializable {

    // Initialize variables
    String author, date, title, time, docID, description, type, rating, search_type, img_url;
    int price, total_quantity, total_price, quantity;

    // Required empty public constructor
    public MyCartModel() {}

    // Constructor for initialization
    public MyCartModel(String author, String date, String title, String time, String docID, String description, String type, String rating, String search_type, String img_url, int price, int total_quantity, int total_price, int quantity) {
        this.author = author;
        this.date = date;
        this.title = title;
        this.time = time;
        this.docID = docID;
        this.price = price;
        this.total_quantity = total_quantity;
        this.total_price = total_price;
        this.quantity = quantity;
        this.description = description;
        this.type = type;
        this.rating = rating;
        this.search_type = search_type;
        this.img_url = img_url;
    }

    // Getters and Setters
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDocID() {
        return docID;
    }

    public void setDocID(String docID) {
        this.docID = docID;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTotal_quantity() {
        return total_quantity;
    }

    public void setTotal_quantity(int total_quantity) {
        this.total_quantity = total_quantity;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getSearch_type() {
        return search_type;
    }

    public void setSearch_type(String search_type) {
        this.search_type = search_type;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}
