package com.example.softspec.ebookapp.model;

import android.widget.ArrayAdapter;

import java.util.ArrayList;

import static android.R.attr.format;

/**
 * Created by rakkant on 5/19/2017 AD.
 */

public class Profile {

    private  ArrayList<Book> mylist = new ArrayList<Book>();
    private double balance ;
    public Profile(){
        balance = 0;
    }

    public ArrayList<Book> getMylist() {
        return mylist;
    }

    public void setBalance(double balance){
        this.balance = balance;
    }

    public double getBalance(){
        return balance;
    }



}
