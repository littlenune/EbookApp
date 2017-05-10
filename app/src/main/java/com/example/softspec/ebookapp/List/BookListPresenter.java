package com.example.softspec.ebookapp.List;

import com.example.softspec.ebookapp.model.Book;
import com.example.softspec.ebookapp.model.BookRepository;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by nune on 5/9/2017 AD.
 */

public class BookListPresenter implements Observer {
    private BookListView view;
    private BookRepository repository;

    ArrayList<Book> books;

    public BookListPresenter(BookRepository repository, BookListView view){
        this.repository = repository;
        this.view = view;
    }

    public void initialize(){
        repository.addObserver(this);
        repository.fetchAllBooks();
    }

    @Override
    public void update(Observable o, Object arg) {
        if ( o == repository ){
            books = new ArrayList<Book>(repository.loadBooks());
            view.setBookList(books);
        }
    }


}
