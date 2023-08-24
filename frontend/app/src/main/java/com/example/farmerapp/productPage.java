package com.example.farmerapp;

import static com.example.farmerapp.R.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.farmerapp.Domain.ProductDomain;

public class productPage extends AppCompatActivity {
    Button cart, buybtn , negotiatebtn ;
    TextView name, price, quan;
    ImageView image;
    RecyclerView recyclerView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_product_page);

        cart = findViewById(id.addtocart);
//        buybtn = findViewById(id.buy);

        name = findViewById(id.name);
        price = findViewById(id.price);
        quan = findViewById(id.quantity);
        image=findViewById(id.image);

//        cart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(productPage.this,cart.class);
//                startActivity(intent);
//            }
//        });
//        buybtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(productPage.this,buy.class);
//                startActivity(intent);
//            }
//        });

        Intent intent = getIntent();
        if (intent != null) {
            ProductDomain product = (ProductDomain) intent.getSerializableExtra("product");
            if (product != null) {
                // Use the product details as needed
                // For example: String title = product.getTitle();
                String s= product.getTitle().toLowerCase()+"icon";
                int imageResourceId = getResources().getIdentifier(s, "drawable", getPackageName());
                if (imageResourceId != 0) {
                    image.setBackgroundResource(imageResourceId);
                }
                // Load the image using Glide
                Glide.with(this)
                        .load(product.getPic())
                        .into(image);
                name.setText("Name : "+ product.getTitle());
                price.setText("Price/kg : $"+ product.getCost());
                quan.setText("Quantity : "+ product.getAvailable()+"Kgs");



                cart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Context context = view.getContext();

                        // Get the clicked product
                        ProductDomain clickedProduct = product;

                        // Create and start the intent
                        Intent intent = new Intent(context, cart.class);

                        // Attach the product details to the intent
                        intent.putExtra("product", product);

                        context.startActivity(intent);
                    }
                });




            }
//            Toast.makeText(productPage.this, " " + product.getTitle()+"i love u nithin", Toast.LENGTH_LONG).show();
        }



//        negotiatebtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(productPage.this,bot.class);
//                startActivity(intent);
//            }
//        });
    }
}