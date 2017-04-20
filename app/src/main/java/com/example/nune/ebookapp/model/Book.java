package com.example.nune.ebookapp.model;

/**
 * Created by nune on 4/20/2017 AD.
 */

public class Book {
    private String title;
    private int price;

    public Book(String title, int price){
        this.title = title;
        this.price = price;
    }

    public int getPrice() { return price; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public void setPrice(int price) { this.price = price; }

    public String toString(){
        return title + " " + price;
    }
}
