package com.example.farmerapp;

import static com.example.farmerapp.R.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class productPage extends AppCompatActivity {
    Button cart, buybtn , negotiatebtn ;
    RecyclerView recyclerView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_product_page);

        cart = findViewById(id.addtocart);
        buybtn = findViewById(id.buy);

        recyclerView = findViewById(id.productRecycle);


        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(productPage.this,cart.class);
                startActivity(intent);
            }
        });
        buybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(productPage.this,buy.class);
                startActivity(intent);
            }
        });
//        negotiatebtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(productPage.this,bot.class);
//                startActivity(intent);
//            }
//        });
    }
}