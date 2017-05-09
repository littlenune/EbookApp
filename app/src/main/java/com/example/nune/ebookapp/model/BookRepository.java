package com.example.nune.ebookapp.model;

import java.util.List;
import java.util.Observable;

/**
 * Created by nune on 5/9/2017 AD.
 */

public abstract class BookRepository extends Observable {
    public abstract void fetchAllBooks();
    public abstract List<Book> loadBooks();
}
