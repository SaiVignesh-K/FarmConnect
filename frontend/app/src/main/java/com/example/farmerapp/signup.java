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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    private EditText emailEditText, usernameEditText, passwordEditText;
    private CheckBox farmerCheckBox;
    private Button signupButton;
    private TextView alreadyUserTextView;

    private FirebaseAuth auth;
    private DatabaseReference usersRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        auth = FirebaseAuth.getInstance();
        usersRef = FirebaseDatabase.getInstance().getReference("users");

        emailEditText = findViewById(R.id.signupemail);
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.signuppassword);
        farmerCheckBox = findViewById(R.id.farmerCheckBox);
        signupButton = findViewById(R.id.signupbutton);
        alreadyUserTextView = findViewById(R.id.alreadyuser);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                final String username = usernameEditText.getText().toString();
                boolean isFarmer = farmerCheckBox.isChecked();

                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    String userId = auth.getCurrentUser().getUid();
                                    User newUser = new User(username, isFarmer);

                                    usersRef.child(userId).setValue(newUser);

                                    Toast.makeText(SignupActivity.this, "Signup successful", Toast.LENGTH_SHORT).show();
                                    // Handle successful signup, e.g., go to main activity
                                } else {
                                    Toast.makeText(SignupActivity.this, "Signup failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        alreadyUserTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to login activity
            }
        });
    }
}
