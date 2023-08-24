package com.example.farmerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.farmerapp.Domain.ProductDomain;

public class cart extends AppCompatActivity {
    ImageView bot1, buybtn, sellbtn, homebtn;
    ImageView image;
    TextView price;
    TextView name,cost;
    EditText number;
    Button checkout;
    double c=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        bot1 =  findViewById(R.id.imageview3);
        buybtn =findViewById(R.id.imageview2);
        sellbtn =findViewById(R.id.imageview4);
        homebtn = findViewById(R.id.imageview1);
        number = findViewById(R.id.quan);
        price = findViewById(R.id.totalPrice);
//        Toast.makeText(cart.this, "Please Enter Quantity", Toast.LENGTH_SHORT).show();


        name = findViewById(R.id.name);
        cost = findViewById(R.id.cost);
        image = findViewById(R.id.image);
        checkout = findViewById(R.id.checkoutButton);
        Intent intent = getIntent();
//        double c=0;
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
                cost.setText("Price/kg : "+ product.getCost());
                c =product.getCost();
                Toast.makeText(cart.this, "Please Enter Quantity", Toast.LENGTH_SHORT).show();

            }

//            int a = Integer.parseInt(number.getText().toString());
//            price.setText("Price : $"+ Integer.toString((int)(a*c)));
//            Toast.makeText(cart.this, " " + product.getTitle()+"i love u nithin", Toast.LENGTH_LONG).show();
        }
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Calculate and display total price when the checkout button is clicked
                try {
                    int a = Integer.parseInt(number.getText().toString());
                    price.setText("Price : $" + Integer.toString((int) (a * c)));
                } catch (NumberFormatException e) {
                    Toast.makeText(cart.this, "Please enter a valid quantity", Toast.LENGTH_SHORT).show();
                }

                Toast.makeText(cart.this, " Taking to Payment Page", Toast.LENGTH_LONG).show();
            }
        });
        bot1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cart.this,bot.class);
                startActivity(intent);
            }
        });
        buybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cart.this, buy.class);
                startActivity(intent);
            }
        });
        sellbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cart.this, sell.class);
                startActivity(intent);
            }
        });
        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cart.this, MainActivity.class);
                startActivity(intent);
            }});

//        int a = Integer.parseInt(number.getText().toString());
//        price.setText("Price : $"+ Integer.toString((int)(a*c)));

}
}
