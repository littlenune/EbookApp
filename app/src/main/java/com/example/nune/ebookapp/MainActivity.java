package com.example.nune.ebookapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.nune.ebookapp.model.Book;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Book> books;
    private ArrayAdapter<Book> bookAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initListView();
        loadBooks();

    }

    private void initListView() {
        books = new ArrayList<>();
        bookAdapter = new ArrayAdapter<Book>(this,
                android.R.layout.simple_list_item_1,
                books);

        ListView listview = (ListView) findViewById(R.id.listView_book_list);
        listview.setAdapter(bookAdapter);
    }

    private void loadBooks() {
        BookFetcherTask task = new BookFetcherTask();
        task.execute();
    }

    public void refreshBooks(View view) {
        loadBooks();
    }

    public class BookFetcherTask extends AsyncTask<Void,Void,ArrayList<Book>> {

        @Override
        protected ArrayList<Book> doInBackground(Void... params) {
            String bookListJsonStr = loadBookJson();

            Log.d("none", "do in back" + bookListJsonStr);
            if ( bookListJsonStr == null ){
                Log.d("none","null book");
                return null;
            }

            ArrayList<Book> results = new ArrayList<>();

            try {
                JSONArray jsonArray = new JSONArray(bookListJsonStr);

                for ( int i = 0 ; i < jsonArray.length() ; i++ ){
                    JSONObject bookJson = jsonArray.getJSONObject(i);
                    Book book = new Book(bookJson.getString("title"),
                            bookJson.getInt("id"),bookJson.getDouble("prize"),
                            bookJson.getString("pub_year"),bookJson.getString("img_url"));
                    results.add(book);
                    Log.d("none", book.toString());
                    Log.d("none" , "run test");
                }
            } catch (JSONException e){
                Log.d("none", "catch");
                return null;
            }
            return results;
        }

        private String loadBookJson() {
            String result = "";
            try {
                URL bookUrl = new URL("https://theory.cpe.ku.ac.th/~jittat/courses/sw-spec/ebooks/books.json");
                Log.d("none", "Load1");
                URLConnection bookCon = bookUrl.openConnection();
                Log.d("none", "Load2");
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        bookCon.getInputStream()));
                Log.d("none", "Load3");

                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    result += inputLine;

                }
                Log.d("none", "Load4");
                return result;
            } catch (IOException e) {
                Log.d("none","exception loadBookJson");
                return null;
            }
        }

        @Override
        protected void onPostExecute(ArrayList<Book> results) {

            if ( results != null) {
                bookAdapter.clear();
                for ( Book b : results ){
                    bookAdapter.add(b);
                }
            }
        }
    }
}