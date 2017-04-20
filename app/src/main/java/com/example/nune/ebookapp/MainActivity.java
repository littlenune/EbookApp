package com.example.nune.ebookapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.nune.ebookapp.model.Book;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private ArrayList<Book> books;
    private ArrayAdapter<Book> bookAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initListView();
        loadTrendingTopics();
    }

    private void initListView() {
        books = new ArrayList<>();
        bookAdapter = new ArrayAdapter<Book>(this,
                android.R.layout.simple_list_item_1,
                books);

        ListView listview = (ListView) findViewById(R.id.listView_topic_list);
        listview.setAdapter(bookAdapter);
    }

    private void loadTrendingTopics() {
        for ( int i = 0 ; i < 30 ; i++ ) {
            bookAdapter.add(new Book("name",i+1));
        }
    }
}
