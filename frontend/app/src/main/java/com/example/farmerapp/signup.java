package com.example.farmerapp;


import android.content.Intent;
import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.CheckBox;
        import android.widget.EditText;
        import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;
        import com.google.android.gms.tasks.OnCompleteListener;
        import com.google.android.gms.tasks.Task;
        import com.google.firebase.auth.AuthResult;
        import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;

public class signup extends AppCompatActivity {

    private EditText emailEditText, usernameEditText, passwordEditText;
    private CheckBox farmerCheckBox;
    private Button signupButton;
    private TextView alreadyUserTextView;

    private FirebaseAuth mAuth;
    private DatabaseReference usersReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();
        usersReference = FirebaseDatabase.getInstance().getReference("users");

        emailEditText = findViewById(R.id.signupemail);
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.signuppassword);
        farmerCheckBox = findViewById(R.id.farmercheck);
        signupButton = findViewById(R.id.signupbutton);
        alreadyUserTextView = findViewById(R.id.alreadyuser);
        alreadyUserTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(signup.this, login.class);
                startActivity(intent);
            }
        });
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUpUser();
            }
        });
    }

    private void signUpUser() {
        String email = emailEditText.getText().toString();
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        boolean isFarmer = farmerCheckBox.isChecked();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            String userId = mAuth.getCurrentUser().getUid();
                            User user = new User(email, username, isFarmer);

                            usersReference.child(userId).setValue(user)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> databaseTask) {
                                            if (databaseTask.isSuccessful()) {
                                                Toast.makeText(signup.this, "User registration successful!", Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(signup.this, login.class));
                                                finish();
                                            } else {
                                                Exception exception = databaseTask.getException();
                                                if (exception != null) {
                                                    Toast.makeText(signup.this, "Database storage failed: " + exception.getMessage(), Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        }
                                    });
                        } else {
                            Exception exception = task.getException();
                            if (exception != null) {
                                Toast.makeText(signup.this, "User registration failed: " + exception.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }


}

