package com.anannya.quizoo;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Signup extends AppCompatActivity {
    private EditText username, email, password, phone;
    private Button btnRegister, btnGoToLogin;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private FirebaseFirestore mStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize views
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        phone = findViewById(R.id.phone);
        progressBar = findViewById(R.id.progressBar);
        btnRegister = findViewById(R.id.btn);
        btnGoToLogin = findViewById(R.id.move);

        mAuth = FirebaseAuth.getInstance();
        mStore = FirebaseFirestore.getInstance();

        // Disable register button initially
        btnRegister.setEnabled(false);

        TextWatcher watcher = new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                validateFormFields();
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
        };

        username.addTextChangedListener(watcher);
        email.addTextChangedListener(watcher);
        password.addTextChangedListener(watcher);
        phone.addTextChangedListener(watcher);

        btnGoToLogin.setOnClickListener(v -> {
            startActivity(new Intent(Signup.this, Login.class));
            finish();
        });

        btnRegister.setOnClickListener(v -> registerUser());
    }

    private void validateFormFields() {
        String u = username.getText().toString().trim();
        String e = email.getText().toString().trim();
        String p = password.getText().toString().trim();
        String ph = phone.getText().toString().trim();

        boolean allFilled = !TextUtils.isEmpty(u) &&
                !TextUtils.isEmpty(e) &&
                !TextUtils.isEmpty(p) &&
                !TextUtils.isEmpty(ph);

        btnRegister.setEnabled(allFilled);
    }

    private void registerUser() {
        String mUserName = username.getText().toString().trim();
        String mMail = email.getText().toString().trim();
        String mPass = password.getText().toString().trim();
        String mPhone = phone.getText().toString().trim();

        boolean valid = true;

        if (TextUtils.isEmpty(mUserName)) {
            username.setError("Username is required");
            valid = false;
        }

        if (TextUtils.isEmpty(mMail) || !Patterns.EMAIL_ADDRESS.matcher(mMail).matches()) {
            email.setError("A valid email is required");
            valid = false;
        }

        if (TextUtils.isEmpty(mPass) || mPass.length() < 6) {
            password.setError("Password must be at least 6 characters");
            valid = false;
        }

        if (TextUtils.isEmpty(mPhone)) {
            phone.setError("Phone number is required");
            valid = false;
        } else if (!mPhone.matches("\\d{10}")) {
            phone.setError("Enter a valid 10-digit phone number");
            valid = false;
        }

        // Optional: international format check (if you want)
        String regex = "^\\+?[0-9\\s\\-]{7,15}$";
        if (!mPhone.matches(regex)) {
            phone.setError("Enter a valid international phone number");
            valid = false;
        }

        if (!valid) {
            Toast.makeText(this, "Please fix the errors", Toast.LENGTH_SHORT).show();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(mMail, mPass)
                .addOnCompleteListener(task -> {
                    progressBar.setVisibility(View.GONE);
                    if (task.isSuccessful()) {
                        String userId = mAuth.getCurrentUser().getUid();
                        DocumentReference documentReference = mStore.collection("users").document(userId);

                        Map<String, Object> user = new HashMap<>();
                        user.put("username", mUserName);
                        user.put("email", mMail);
                        user.put("phone", mPhone);

                        documentReference.set(user)
                                .addOnSuccessListener(unused -> Log.d(TAG, "User profile created for " + userId))
                                .addOnFailureListener(e -> Log.d(TAG, "Firestore error: " + e.getMessage()));

                        Toast.makeText(Signup.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Signup.this, MainActivity.class));
                        finish();
                    } else {
                        Toast.makeText(Signup.this, "Registration failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }
}
