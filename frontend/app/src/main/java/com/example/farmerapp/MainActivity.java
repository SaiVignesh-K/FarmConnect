package com.example.farmerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.farmerapp.Adaptor.CategoryAdaptor;
import com.example.farmerapp.Adaptor.TopProductAdaptor;
import com.example.farmerapp.Domain.CategoryDomain;
import com.example.farmerapp.Domain.TopProductDomain;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageView  cartb, bot1, buybtn, sellbtn,probtn ;
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewCategoryList;
    private RecyclerView recyclerViewTopProList;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cartb = findViewById(R.id.carticon1);

        cartb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, cart.class);
                startActivity(intent);
            }
        });

//        cartb.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, cart.class);
//                startActivity(intent);
//            }
//        });

        recyclerViewCategory();
        recyclerViewTopPro();
        bot1 =  findViewById(R.id.imageview3);
        buybtn =findViewById(R.id.imageview2);
        sellbtn =findViewById(R.id.imageview4);


        probtn = findViewById(R.id.imageView3);
        bot1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,bot.class);
                startActivity(intent);
            }
        });
        buybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, buy.class);
                startActivity(intent);
            }
        });
        sellbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, sell.class);
                startActivity(intent);
            }
        });
        cartb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, sscart.class);
                startActivity(intent);
            }
        });
        probtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, profile.class);
                startActivity(intent);
            }
        });
    }
    private void recyclerViewCategory(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewCategoryList = findViewById(R.id.cat_RV);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryDomain> category = new ArrayList<>();
        category.add(new CategoryDomain("Vegetable","vegicon"));
        category.add(new CategoryDomain("Fruits","fruiticon"));
        category.add(new CategoryDomain("Dairy","dairyicon"));
        category.add(new CategoryDomain("Poultry","poultryicon"));
        category.add(new CategoryDomain("Seeds","seedicon"));

        adapter = new CategoryAdaptor(category);
        recyclerViewCategoryList.setAdapter(adapter);

    }
    private void recyclerViewTopPro(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewTopProList = findViewById(R.id.topPro_RV);
        recyclerViewTopProList.setLayoutManager(linearLayoutManager);

        ArrayList<TopProductDomain> toppro = new ArrayList<>();
        toppro.add(new TopProductDomain("Tomatoes","tomatoicon"));
        toppro.add(new TopProductDomain("Potatoes","potatoicon"));
        toppro.add(new TopProductDomain("Onions","onionicon"));
        toppro.add(new TopProductDomain("Eggs","eggicon"));
        toppro.add(new TopProductDomain("Bananas","bananaicon"));

        adapter = new TopProductAdaptor(toppro);
        recyclerViewTopProList.setAdapter(adapter);

    }


}