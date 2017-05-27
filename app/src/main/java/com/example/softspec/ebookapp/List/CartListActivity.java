package com.example.softspec.ebookapp.List;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nune.ebookapp.R;
import com.example.softspec.ebookapp.model.Book;

import java.util.ArrayList;

import static com.example.softspec.ebookapp.List.BookActivity.cartArrayList;
import static com.example.softspec.ebookapp.List.BookActivity.user;

public class CartListActivity extends AppCompatActivity {

    ArrayAdapter<Book> cartAdapter;

    Button homeBtn, purchaseBtn;
    TextView totalPurchase;
    ListView cartList;

    double sum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);

        cartList = (ListView) findViewById(R.id.listView_cart);
        cartAdapter = new ArrayAdapter<Book>(this, android.R.layout.simple_list_item_1, BookActivity.cartArrayList);
        cartList.setAdapter(cartAdapter);

        totalPurchase = (TextView) findViewById(R.id.showSum);
        totalPurchase.setText(""+sumPrice(BookActivity.cartArrayList));

        homeBtn = (Button) findViewById(R.id.homeBtn2);
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),BookActivity.class);
                startActivity(i);
            }
        });

      purchase();

    }

    public void purchase(){
        purchaseBtn = (Button) findViewById(R.id.purchaseBtn);
        purchaseBtn.setOnClickListener(new AdapterView.OnClickListener() {

            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(CartListActivity.this);
                alertDialog.setTitle("Alert");
                alertDialog.setMessage("Buy?");
                alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if ( sum > user.getBalance() ){
                            Toast.makeText(getApplicationContext(),"Purchase not completed", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Purchase completed", Toast.LENGTH_SHORT).show();
                            user.setBalance(user.getBalance()-sum);

                            for ( int i = 0 ; i < cartArrayList.size() ; i++) {
                                user.getMylist().add(cartArrayList.get(i));
                            }

                            cartArrayList.clear();
                        }
                    }
                });

                alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"Cancel", Toast.LENGTH_SHORT).show();
                    }
                });

                alertDialog.show();
            }

        });
    }

    public double sumPrice(ArrayList<Book> cartBook){
        for ( Book b : cartBook ){
            sum += b.getPrice();
        }
        return sum;
    }
}
