package com.example.farmerapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class sell extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private EditText itemNameEditText, pricePerKgEditText, kgAvailableEditText;
    private Spinner categorySpinner , locationSpinner;
    private Button addButton;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);

        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("products");

        itemNameEditText = findViewById(R.id.editTextNumber);
        pricePerKgEditText = findViewById(R.id.editTextNumber2);
        kgAvailableEditText = findViewById(R.id.editTextNumber3);
        categorySpinner = findViewById(R.id.categorySpinner);
        locationSpinner = findViewById(R.id.locationEditText);
        addButton = findViewById(R.id.button6);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.category_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addProductDetails();
            }
        });
    }

    private void addProductDetails() {
        String itemName = itemNameEditText.getText().toString();
        String pricePerKg = pricePerKgEditText.getText().toString();
        String kgAvailable = kgAvailableEditText.getText().toString();
        String selectedCategory = categorySpinner.getSelectedItem().toString();
        String selectedLocation =
        String farmerId = fetchFarmerIdFromDatabase();

        // Here you can create a Product object and store it in Firebase
        Product product = new Product(itemName, pricePerKg, kgAvailable, selectedCategory, farmerId);

        String productId = databaseReference.push().getKey();
        databaseReference.child(productId).setValue(product);

        // Clear input fields
        itemNameEditText.setText("");
        pricePerKgEditText.setText("");
        kgAvailableEditText.setText("");
    }

    private String fetchFarmerIdFromDatabase() {
        String userId = mAuth.getCurrentUser().getUid();

        // Assuming you have a reference to the user's farmer ID in Firebase
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("users");
        DatabaseReference farmerIdRef = usersRef.child(userId).child("farmerId");

        // Read the farmer ID from the database
        final String[] farmerId = {null};
        farmerIdRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    farmerId[0] = dataSnapshot.getValue(String.class);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle errors here
            }
        });

        return farmerId[0];
    }
}
