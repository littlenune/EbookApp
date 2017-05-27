package com.example.softspec.ebookapp.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nune on 5/9/2017 AD.
 */

public class MockUpRepository extends BookRepository {
    private List<Book> books;
    private static  MockUpRepository instance = null;

    public static MockUpRepository getInstance() {
        if(instance == null) {
            instance = new MockUpRepository();
        }
        return instance;
    }

    private MockUpRepository() {
        books = new ArrayList<Book>();
        books.add(new Book("Hello",1,200,"2017","img"));
        books.add(new Book("Hello2",2,199,"2016","img"));
    }

    @Override
    public void fetchAllBooks() {
        setChanged();
        notifyObservers();
    }

    @Override
    public List<Book> loadBooks() {
        return books;
    }

}
