package com.example.farmerapp;


import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;

public class sell extends AppCompatActivity {

    private DatabaseReference databaseReference;

    private EditText itemNameEditText, pricePerKgEditText, kgAvailableEditText, descriptionEditText, farmerIdEditText;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell); // Make sure to use the correct layout name

        databaseReference = FirebaseDatabase.getInstance().getReference("products");

        itemNameEditText = findViewById(R.id.editTextNumber);
        pricePerKgEditText = findViewById(R.id.editTextNumber2);
        kgAvailableEditText = findViewById(R.id.editTextNumber3);
        descriptionEditText = findViewById(R.id.editTextNumber4);
        farmerIdEditText = findViewById(R.id.editTextNumber5);
        addButton = findViewById(R.id.button6);

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
        String description = descriptionEditText.getText().toString();
        String farmerId = farmerIdEditText.getText().toString();

        String productId = databaseReference.push().getKey();

        Product product = new Product(productId, itemName, pricePerKg, kgAvailable, description, farmerId);

        databaseReference.child(productId).setValue(product);

        // Clear input fields
        itemNameEditText.setText("");
        pricePerKgEditText.setText("");
        kgAvailableEditText.setText("");
        descriptionEditText.setText("");
        farmerIdEditText.setText("");
    }
}

