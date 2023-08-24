package com.example.farmerapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.farmerapp.Adaptor.CategoryAdaptor;
import com.example.farmerapp.Adaptor.FarmerAdaptor;
import com.example.farmerapp.Adaptor.TopProductAdaptor;
import com.example.farmerapp.Domain.CategoryDomain;
import com.example.farmerapp.Domain.FarmerDomain;
import com.example.farmerapp.Domain.TopProductDomain;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.content.SharedPreferences;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageView  cartb, bot1, buybtn, sellbtn,probtn ;
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewCategoryList;
    private RecyclerView recyclerViewTopProList;

    private RecyclerView recyclerViewFarmerList;

    private static final String PREFS_NAME = "LocationPrefs";
    private static final String SELECTED_LOCATION_KEY = "SelectedLocation";

    private SharedPreferences sharedPreferences;
    public String Select_location;
    private Spinner locationSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        locationSpinner = findViewById(R.id.locationSpinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.states_array,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSpinner.setAdapter(adapter);

        locationSpinner.setPrompt("Choose Location");

        locationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedLocation = parent.getItemAtPosition(position).toString();
                Select_location=selectedLocation;
                saveSelectedLocation(selectedLocation);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        String savedLocation = sharedPreferences.getString(SELECTED_LOCATION_KEY, "");
        if (!savedLocation.isEmpty()) {
            int spinnerPosition = adapter.getPosition(savedLocation);
            if (spinnerPosition >= 0) {
                locationSpinner.setSelection(spinnerPosition);
            }
        }

        cartb = findViewById(R.id.carticon1);

        cartb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, cart.class);
                startActivity(intent);
            }
        });


        recyclerViewCategory();
        recyclerViewTopPro();
        recyclerViewFarmer();
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
                intent.putExtra("user_location", Select_location);
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

    private void recyclerViewFarmer(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewFarmerList = findViewById(R.id.farm_RV);
        recyclerViewFarmerList.setLayoutManager(linearLayoutManager);

        ArrayList<FarmerDomain> farmer = new ArrayList<>();
        farmer.add(new FarmerDomain(" ","tomato"));

        farmer.add(new FarmerDomain(" ","fruiticon"));
        farmer.add(new FarmerDomain(" ","dairyicon"));
        farmer.add(new FarmerDomain(" ","poultryicon"));
        farmer.add(new FarmerDomain(" ","seedicon"));

        adapter = new FarmerAdaptor(farmer);
        recyclerViewFarmerList.setAdapter(adapter);

    }

    private void saveSelectedLocation(String location) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SELECTED_LOCATION_KEY, location);
        editor.apply();
    }
    void checkuserexistence(){
        SharedPreferences sp = getSharedPreferences("credentials", MODE_PRIVATE);
        if(sp.contains("username")){

        }
        else{
            startActivity(new Intent(getApplicationContext(), login.class));
        }
    }

}