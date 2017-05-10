package com.example.softspec.ebookapp.List;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.example.nune.ebookapp.R;
import com.example.softspec.ebookapp.model.Book;
import com.example.softspec.ebookapp.model.BookRepository;
import com.example.softspec.ebookapp.model.RemoteBookRepository;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity implements BookListView {

    ArrayAdapter<Book> bookAdapter;
    BookListPresenter presenter;
    private ListView bookListView;

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
}
