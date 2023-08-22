
package com.example.farmerapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

public class sell extends AppCompatActivity {

    private EditText itemNameEditText, priceEditText, quantityEditText;
    private Spinner categorySpinner, locationSpinner;
    private Button addButton;

    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseApp.initializeApp(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);

        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("products");

        itemNameEditText = findViewById(R.id.editTextNumber);
        priceEditText = findViewById(R.id.editTextNumber2);
        quantityEditText = findViewById(R.id.editTextNumber3);
        categorySpinner = findViewById(R.id.categorySpinner);
        locationSpinner = findViewById(R.id.locationEditText);
        addButton = findViewById(R.id.button6);

        ArrayAdapter<CharSequence> categoryAdapter = ArrayAdapter.createFromResource(this,
                R.array.category_options, android.R.layout.simple_spinner_item);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(categoryAdapter);

        ArrayAdapter<CharSequence> locationAdapter = ArrayAdapter.createFromResource(this,
                R.array.states_array, android.R.layout.simple_spinner_item);
        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSpinner.setAdapter(locationAdapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadProduct();
            }
        });
    }

    private void uploadProduct() {
        String itemName = itemNameEditText.getText().toString();
        double price = Double.parseDouble(priceEditText.getText().toString());
        double quantity = Double.parseDouble(quantityEditText.getText().toString());
        String category = categorySpinner.getSelectedItem().toString();
        String location = locationSpinner.getSelectedItem().toString();
        String farmerId = mAuth.getCurrentUser().getUid(); // Get the farmer's UID

        Product product = new Product(itemName, price, quantity, category, location, farmerId);
        String productId = databaseReference.push().getKey();
        databaseReference.child(productId).setValue(product).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(sell.this, "Product added successfully", Toast.LENGTH_SHORT).show();
                    clearInputFields();
                } else {
                    Toast.makeText(sell.this, "Failed to add product", Toast.LENGTH_SHORT).show();
                }
            }
        });

}
    private void clearInputFields() {
        itemNameEditText.getText().clear();
        priceEditText.getText().clear();
        quantityEditText.getText().clear();
    }
}


