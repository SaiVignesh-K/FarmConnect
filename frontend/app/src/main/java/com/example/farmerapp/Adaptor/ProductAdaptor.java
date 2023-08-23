package com.example.farmerapp.Adaptor;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.example.farmerapp.Domain.CategoryDomain;
import com.example.farmerapp.Domain.ProductDomain;
import com.example.farmerapp.MainActivity;
import com.example.farmerapp.R;
import com.example.farmerapp.cart;
import com.example.farmerapp.productPage;

import java.time.Instant;
import java.util.ArrayList;
import android.util.Log;import java.io.Serializable;
public class ProductAdaptor extends RecyclerView.Adapter<ProductAdaptor.ViewHolder> {


    ArrayList<ProductDomain> productDomains;


    public ProductAdaptor(ArrayList<ProductDomain> productDomains) {
//
        this.productDomains = productDomains;
    }

//    Toast.makeText(this.getClass().newInstance(), "Database " + product.getItemName(), Toast.LENGTH_SHORT).show();

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_product,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        // Use position to get the corresponding ProductDomain from the array
//        ProductDomain product = productDomains.get(position);
//
//        // Set the data to the views in your ViewHolder
//        holder.productName.setText(product.getTitle());
//
//        // ... other view bindings ...
//
//        // Load image using Glide or any other image loading library
//        Glide.with(holder.itemView.getContext())
//                .load(product.getImageUrl())
//                .into(holder.productPic);
//    }

    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ProductDomain product = productDomains.get(position);
        holder.productName.setText(product.getTitle());
        holder.quantity.setText("Quan. Available: "+String.valueOf((int) product.getAvailable())+" Kgs");
        holder.price.setText("Price/kg: $"+String.valueOf((int) product.getCost()));
        holder.farmerid.setText("id"+product.getFarmer());
//        holder.productPic.setBackground(Drawable.createFromPath("fruiticon"));
////        Log.d("TAG", "Your log message here");
//        Glide.with(holder.itemView.getContext())
//                .load(product.getPic())
//                .into(holder.productPic);

//        holder.farmerid.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Obtain the context from the clicked view
//                Context context = view.getContext();
//
//                // Create and start the intent
//                Intent intent = new Intent(context, productPage.class);
//                context.startActivity(intent);
//            }
//        });
        holder.farmerid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtain the context from the clicked view
                Context context = view.getContext();

                // Get the clicked product
                ProductDomain clickedProduct = productDomains.get(holder.getAdapterPosition());

                // Create and start the intent
                Intent intent = new Intent(context, productPage.class);

                // Attach the product details to the intent
                intent.putExtra("product", product);

                context.startActivity(intent);
            }
        });



        String s= product.getTitle().toLowerCase()+"icon";
        int drawableResId = holder.itemView.getContext().getResources().getIdentifier(s, "drawable", holder.itemView.getContext().getPackageName());
        if (drawableResId != 0) {
            holder.productPic.setBackgroundResource(drawableResId);
        }

        // Load the image using Glide from the provided URL
        Glide.with(holder.itemView.getContext())
                .load(product.getPic())
                .into(holder.productPic);
//        String picUrl="";
//        switch (position){
//            case 0: {
//                picUrl = "vegicon";
//                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.cat_background1));
//                break;
//            }
//            case 1: {
//                picUrl = "fruiticon";
//                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.cat_background1));
//                break;
//            }
//            case 2: {
//                picUrl = "dairyicon";
//                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.cat_background1));
//                break;
//            }
//            case 3: {
//                picUrl = "poultryicon";
//                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.cat_background1));
//                break;
//            }
//            case 4: {
//                picUrl = "seedicon";
//                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.cat_background1));
//                break;
//            }
//        }
//        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(picUrl,"drawable",holder.itemView.getContext().getPackageName());
//
//
//        Glide.with(holder.itemView.getContext())
//                .load(drawableResourceId)
//                .into(holder.productPic);
    }

    @Override
    public int getItemCount() {
        return productDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView productName, price, quantity,farmerid;
        ImageView productPic;

        ConstraintLayout mainLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.productNameTextView);
            productPic = itemView.findViewById(R.id.productImageView);
            price = itemView.findViewById(R.id.productPriceTextView);
            quantity = itemView.findViewById(R.id.productDescriptionTextView);
            farmerid = itemView.findViewById(R.id.productPriceTextView2);
            mainLayout = itemView.findViewById(R.id.mainlayout1);

        }
    }
}
