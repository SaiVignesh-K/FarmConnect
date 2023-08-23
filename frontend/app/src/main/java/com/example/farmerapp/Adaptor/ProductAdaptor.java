package com.example.farmerapp.Adaptor;

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
import com.example.farmerapp.R;

import java.time.Instant;
import java.util.ArrayList;

public class ProductAdaptor extends RecyclerView.Adapter<ProductAdaptor.ViewHolder> {


    ArrayList<ProductDomain> productDomains;

    public ProductAdaptor(ArrayList<ProductDomain> productDomains) {
        this.productDomains = productDomains;
    }

//    Toast.makeText(this.getClass().newInstance(), "Database " + product.getItemName(), Toast.LENGTH_SHORT).show();

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_product,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.productName.setText(productDomains.get(position).getTitle());
        String picUrl="";
        switch (position){
            case 0: {
                picUrl = "vegicon";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.cat_background1));
                break;
            }
            case 1: {
                picUrl = "fruiticon";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.cat_background1));
                break;
            }
            case 2: {
                picUrl = "dairyicon";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.cat_background1));
                break;
            }
            case 3: {
                picUrl = "poultryicon";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.cat_background1));
                break;
            }
            case 4: {
                picUrl = "seedicon";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.cat_background1));
                break;
            }
        }
        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(picUrl,"drawable",holder.itemView.getContext().getPackageName());


        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.productPic);
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
