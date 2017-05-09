package com.example.softspec.ebookapp.model;

/**
 * Created by nune on 4/20/2017 AD.
 */

public class Book {
    private String title;
    private double price;
    private String imageSrc;
    private String year;
    private int id;


    public Book(String title,int id, double price,String year, String imageSrc){
        this.title = title;
        this.price = price;
        this.year = year;
        this.id = id;
        this.imageSrc = imageSrc;
    }

    public double getPrice() {
        return price;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public String getTitle() {
        return title;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getYear() {
        return year;
    }

    public int getId() {
        return id;
    }

    public String toString(){
        return "TITLE : "+ title + "\n ID : " + id + "\n PRICE: "  + price + "\n YEAR: " + year;
    }


}
