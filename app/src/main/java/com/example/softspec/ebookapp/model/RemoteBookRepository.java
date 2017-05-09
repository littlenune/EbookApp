package com.example.softspec.ebookapp.model;

import android.os.AsyncTask;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nune on 5/9/2017 AD.
 */

public class RemoteBookRepository extends BookRepository {
    private List<Book> books;

    private static RemoteBookRepository instance = null;

    public static RemoteBookRepository getInstance() {
        if (instance == null ){
            instance = new RemoteBookRepository();
        }
        return instance;
    }

    private RemoteBookRepository() {
        books = new ArrayList<Book>();
    }

    @Override
    public void fetchAllBooks() {
    BookFetcherTask task = new BookFetcherTask();
        task.execute();
    }

    @Override
    public List<Book> loadBooks() {
        return books;
    }

    public class BookFetcherTask extends AsyncTask<Void,Void,ArrayList<Book>> {

        @Override
        protected ArrayList<Book> doInBackground(Void... params) {
            String bookListJsonStr = loadBookJson();
            if ( bookListJsonStr == null ){
                return null;
            }

            ArrayList<Book> results = new ArrayList<>();

            try {
                JSONArray jsonArray = new JSONArray(bookListJsonStr);

                for ( int i = 0 ; i < jsonArray.length() ; i++ ){
                    JSONObject bookJson = jsonArray.getJSONObject(i);
                    Book book = new Book(bookJson.getString("title"),
                            bookJson.getInt("id"),bookJson.getDouble("price"),
                            bookJson.getString("pub_year"),bookJson.getString("img_url"));
                    results.add(book);
                }
            } catch (JSONException e){
                return null;
            }
            return results;
        }

        private String loadBookJson() {
            String result = "";
            try {
                URL bookUrl = new URL("https://theory.cpe.ku.ac.th/~jittat/courses/sw-spec/ebooks/books.json");
                URLConnection bookCon = bookUrl.openConnection();
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        bookCon.getInputStream()));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    result += inputLine;

                }
                return result;
            } catch (IOException e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(ArrayList<Book> results) {

            if ( results != null) {
                books.clear();
                for ( Book b : results ){
                    books.add(b);
                }
                setChanged();
                notifyObservers();
            }
        }
    }
}