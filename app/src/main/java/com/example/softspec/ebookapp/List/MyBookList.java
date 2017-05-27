package com.example.softspec.ebookapp.List;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.nune.ebookapp.R;
import com.example.softspec.ebookapp.model.Book;

import java.util.ArrayList;

public class MyBookList extends AppCompatActivity {
    ArrayAdapter<Book> listadapter;
    Button homeBtn;
    ListView myList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_book_list);

        homeBtn = (Button) findViewById(R.id.homeBtn2);

        myList = (ListView) findViewById(R.id.listView_cart);
        listadapter = new ArrayAdapter<Book>(this, android.R.layout.simple_list_item_1, BookActivity.user.getMylist());
        myList.setAdapter(listadapter);

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),BookActivity.class);
                startActivity(i);
            }
        });

    }
}
