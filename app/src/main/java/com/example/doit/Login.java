package com.example.doit;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    TextView signupRedirect;
    Button loginButton;
    EditText username1, password1;
    private DatabaseClass database;
    SharedPreferences sharedPreferences;
    private boolean isPasswordVisible = false;

    @SuppressLint({"MissingInflatedId", "ClickableViewAccessibility"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username1 = findViewById(R.id.username);
        password1 = findViewById(R.id.password);
        loginButton = findViewById(R.id.login_button);
        signupRedirect = findViewById(R.id.signup_redirect);
        database = new DatabaseClass(this, "QuizApp", null, 1);


        password1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_END = 2; // Right drawable
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (password1.getRight() - password1.getCompoundDrawables()[DRAWABLE_END].getBounds().width())) {
                        togglePasswordVisibility();
                        return true;
                    }
                }
                return false;
            }
        });


        // Initialize sharedPreferences
        sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = username1.getText().toString();
                String password = password1.getText().toString();

                if (database.validateUser(username, password)) {
                    int userId = database.getUserIdByUsername(username);

                    if (userId != -1) {
                        String email = database.getEmailByUsername(username);

                        // Store user ID and email in SharedPreferences
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putInt("user_id", userId);
                        editor.putString("email", email);
                        editor.putBoolean("is_first_login", true); // Assuming first login flag
                        editor.apply();

                        // Insert default profile values for the user
                        onUserRegistered(userId);
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        startActivity(intent);
                        finish();

                    } else {
                        Toast.makeText(Login.this, "User ID not found", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Login.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });

        signupRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, SignUp.class);
                startActivity(intent);
            }
        });
    }

    public void togglePasswordVisibility() {
        if (isPasswordVisible) {
            password1.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            password1.setCompoundDrawablesWithIntrinsicBounds(R.drawable.baseline_lock_24, 0, R.drawable.ic_eye_closed, 0);
        } else {
            password1.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            password1.setCompoundDrawablesWithIntrinsicBounds(R.drawable.baseline_lock_24, 0, R.drawable.ic_eye, 0);
        }
        isPasswordVisible = !isPasswordVisible;
        password1.setSelection(password1.getText().length());
    }

    private void onUserRegistered(int userId) {
        database.insertDefaultProfile(userId);
    }
}
