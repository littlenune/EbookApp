package com.example.softspec.ebookapp.List;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.nune.ebookapp.R;
import com.example.softspec.ebookapp.model.Book;
import com.example.softspec.ebookapp.model.BookRepository;
import com.example.softspec.ebookapp.model.RemoteBookRepository;

import java.text.Collator;
import java.util.ArrayList;

import java.util.Comparator;


public class BookActivity extends AppCompatActivity implements BookListView {

    ArrayAdapter<Book> bookAdapter;
    BookListPresenter presenter;
    ListView bookListView;
    EditText editText;
    Button titleBtn;
    Button yearBtn;
    RadioGroup radioGroup;
    RadioGroup radioGroup2;
    Button sortTitle;
    Button sortYear;
    int id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BookRepository repository = RemoteBookRepository.getInstance();

        bookListView = (ListView) findViewById(R.id.listView_booklist);
        bookAdapter = createAdapter(new ArrayList<Book>());
        bookListView.setAdapter(bookAdapter);

        presenter = new BookListPresenter(repository,this);
        presenter.initialize();

        titleBtn = (Button) findViewById(R.id.titleBtn);
        yearBtn = (Button) findViewById(R.id.yearBtn);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        sortTitle = (Button) findViewById(R.id.sortTitle);
        sortYear = (Button) findViewById(R.id.sortYear);
        radioGroup2 = (RadioGroup) findViewById(R.id.radioGroup2);


        id = titleBtn.getId();

        editText = (EditText) findViewById(R.id.editText);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if ( s.toString().equals("")){
                    presenter.initialize();
                }
                else {
                    if ( id == titleBtn.getId()) {
                        searchByTitle(s.toString());
                    }
                    else if ( id == yearBtn.getId()){
                        searchByYear(s.toString());
                    }
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    public int searchID(View view) {
        if ( ((RadioButton) view).isChecked()) {
            id = view.getId();
        }
        return id;
    }



    public void searchByTitle(String textToSearch){
        ArrayList<Book> searchBook = new ArrayList<Book>();
        for ( Book b : presenter.books){
            if ( b.getTitle().toLowerCase().contains(textToSearch.toLowerCase())){
                searchBook.add(b);
            }
        }
        updateAdapter(searchBook);
    }

    public void searchByYear(String textToSearch){
        ArrayList<Book> searchBook = new ArrayList<Book>();
        for ( Book b : presenter.books){
            if ( b.getYear().contains(textToSearch)){
                searchBook.add(b);
            }
        }
        updateAdapter(searchBook);
    }

    public void updateAdapter(ArrayList<Book> b) {
        bookAdapter = createAdapter(b);
        bookListView.setAdapter(bookAdapter);
    }


    public void sortByTitle(){
        final Collator col = Collator.getInstance();
        bookAdapter.sort(new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return col.compare(o1.getTitle(), o2.getTitle());
            }
        });
    }

    public void sortByYear(){
        final Collator col = Collator.getInstance();
        bookAdapter.sort(new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return col.compare(o1.getYear(), o2.getYear());
            }
        });
    }


    @Override
    public void setBookList(ArrayList<Book> books) {
        bookAdapter = createAdapter(books);
        bookListView.setAdapter(bookAdapter);
    }

    private ArrayAdapter<Book> createAdapter(ArrayList<Book> books){
        return new ArrayAdapter<Book>(this,android.R.layout.simple_list_item_1,books);
    }

    public void loadBooks(View view) {
        presenter.initialize();
    }


    public void sortTitleBtn(View v) {
        if (((RadioButton) v).isChecked()) {
            Toast.makeText(this,
                    "Sort by title", Toast.LENGTH_SHORT).show();
            sortByTitle();
        }else {
            searchByTitle(editText.getText().toString());
        }
    }
    public void sortYearBtn(View v) {
        if (((RadioButton) v).isChecked()) {
            Toast.makeText(this,
                    "Sort by year", Toast.LENGTH_SHORT).show();
            sortByYear();
        }else {
            searchByTitle(editText.getText().toString());
        }
    }


    }




