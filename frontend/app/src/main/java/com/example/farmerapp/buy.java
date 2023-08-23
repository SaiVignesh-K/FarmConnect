package com.example.farmerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.example.farmerapp.Adaptor.ProductAdaptor;
import com.example.farmerapp.Adaptor.TopProductAdaptor;
import com.example.farmerapp.Domain.ProductDomain;
import com.example.farmerapp.Domain.TopProductDomain;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class buy extends AppCompatActivity {
    private RecyclerView recyclerViewProList;
    private RecyclerView.Adapter adapter;
    private ArrayList<Product> productList;
    private ProductAdaptor productAdapter;
    ImageView bot1, homebtn, sellbtn, cartbtn;

    ImageView backbtn, probtn;
    RadioGroup radioGroup;
    private String userLocation;
    DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();

    // Assuming you have a "products" node under your root reference
    DatabaseReference productsRef = rootRef.child("products");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        backbtn = findViewById(R.id.backButton);
        probtn = findViewById(R.id.imageView3);

        radioGroup = findViewById(R.id.radioGroup);


        ArrayList productList = new ArrayList<>();
        recyclerViewProList = findViewById(R.id.cat_RV);
        productAdapter = new ProductAdaptor(productList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewProList.setLayoutManager(linearLayoutManager);
        recyclerViewProList.setAdapter(productAdapter);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Handle category selection
//                Toast.makeText(buy.this, "Database storage failed: " + checkedId+"nithin", Toast.LENGTH_SHORT).show();
                ArrayList<Product> filteredProducts = new ArrayList<>();
                String selectedCategory = null;
                if (checkedId == 2131231135) {
                    selectedCategory = "Vegetables";
                } else if (checkedId == 2) {
                    selectedCategory = "Fruits";
                } else if (checkedId == 3) {
                    selectedCategory = "Poultry";
                }
//                Toast.makeText(buy.this, "Database storage failed: " + selectedCategory+"nithin", Toast.LENGTH_SHORT).show();
                userLocation = getIntent().getStringExtra("user_location");
//                Toast.makeText(buy.this, "Database storage failed: " + userLocation+"nithin", Toast.LENGTH_SHORT).show();
                Query query = productsRef.orderByChild("category").equalTo(selectedCategory);

                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        filteredProducts.clear();
                        for (DataSnapshot productSnapshot : dataSnapshot.getChildren()) {
                            Product product = productSnapshot.getValue(Product.class);
                            Toast.makeText(buy.this, "Database storage failed: " + product.getItemName(), Toast.LENGTH_SHORT).show();
                            if (product.getLocation().equals(userLocation)) {
                                filteredProducts.add(product);
                            }
                        }
                        updateAdapterData(filteredProducts);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Getting data failed, log a message
                        Log.w("Firebase", "loadPost:onCancelled", databaseError.toException());
                    }
                });
            }
        });

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(buy.this, MainActivity.class);
                startActivity(intent);
            }
        });
        probtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(buy.this, profile.class);
                startActivity(intent);
            }
        });

        bot1 = findViewById(R.id.imageview3);
        homebtn = findViewById(R.id.imageview1);
        sellbtn = findViewById(R.id.imageview4);
        cartbtn = findViewById(R.id.imageview5);

        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(buy.this, MainActivity.class);
                startActivity(intent);
            }
        });
        sellbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(buy.this, sell.class);
                startActivity(intent);
            }
        });
        cartbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(buy.this, cart.class);
                startActivity(intent);
            }
        });

    }

    private void updateAdapterData(ArrayList<Product> newData) {
        // Update your adapter data with the new filtered list
        productList.clear();
        productList.addAll(newData);
        // Notify the adapter that the data has changed
        productAdapter.notifyDataSetChanged();
        recyclerViewPro();
    }
    private void recyclerViewPro(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewProList = findViewById(R.id.cat_RV);
        recyclerViewProList.setLayoutManager(linearLayoutManager);

        ArrayList<ProductDomain> pro = new ArrayList<>();
//        pro.add(new ProductDomain("Vegetable","tomatoicon",56,351,"rajjeevv"));
        for (Product product : productList) {
            // Create a new ProductDomain object with the relevant data from the product
//            Toast.makeText(this.getClass().newInstance(), "Database " + product.getItemName(), Toast.LENGTH_SHORT).show();
            pro.add(new ProductDomain(product.getItemName() ,"tomatoicon",product.getPricePerKg(),product.getQuantityAvailable(),product.getFarmerId()));

        }

        adapter = new ProductAdaptor(pro);
        recyclerViewProList.setAdapter(adapter);
    }
}
