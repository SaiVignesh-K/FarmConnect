package com.example.farmerapp;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class sell extends AppCompatActivity {
    ImageView bot1, buybtn, homebtn, cartbtn, probtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);
        bot1 =  findViewById(R.id.imageview3);
        buybtn =findViewById(R.id.imageview2);
        homebtn =findViewById(R.id.imageview1);
        cartbtn = findViewById(R.id.imageview5);
        probtn = findViewById(R.id.imageView3);
        bot1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(sell.this,bot.class);
                startActivity(intent);
            }
        });
        buybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(sell.this, buy.class);
                startActivity(intent);
            }
        });
        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(sell.this, MainActivity.class);
                startActivity(intent);
            }
        });
        cartbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(sell.this, cart.class);
                startActivity(intent);
            }
        });
        probtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(sell.this, profile.class);
                startActivity(intent);
            }
        });
    }
}