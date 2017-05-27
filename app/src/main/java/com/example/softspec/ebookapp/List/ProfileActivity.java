package com.example.softspec.ebookapp.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.nune.ebookapp.R;
import com.example.softspec.ebookapp.model.Profile;

import static com.example.softspec.ebookapp.List.BookActivity.user;

/**
 * Created by rakkant on 5/19/2017 AD.
 */

public class ProfileActivity extends AppCompatActivity {

    TextView txtBalance;
    Button fundBtn;
    Button homeBtn;
    Button cartBtn;
    Button mybookBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_main);

        txtBalance = (TextView) findViewById(R.id.showBalance);
        fundBtn = (Button) findViewById(R.id.btnAddMoney);

        txtBalance.setText(""+user.getBalance());
        homeBtn = (Button) findViewById(R.id.homeBtn);
        cartBtn = (Button) findViewById(R.id.cartBtn);
        mybookBtn = (Button) findViewById(R.id.mybooklist);
        
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),BookActivity.class);
                startActivity(i);
            }
        });

        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),CartListActivity.class);
                startActivity(i);
            }
        });

        mybookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),MyBookList.class);
                startActivity(i);
            }
        });

        if(fundBtn.isClickable()) {
            addMoney(fundBtn);
        }
    }

    public void addMoney(Button b){
        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                user.setBalance(user.getBalance()+500);
                txtBalance.setText(""+user.getBalance());
            }
        });
    }

}


