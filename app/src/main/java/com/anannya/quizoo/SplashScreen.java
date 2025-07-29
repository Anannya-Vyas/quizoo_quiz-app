package com.anannya.quizoo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;

public class SplashScreen extends AppCompatActivity {

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash_screen);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mAuth = FirebaseAuth.getInstance();

        new Handler().postDelayed(() -> {
            if (mAuth.getCurrentUser() != null) {
                // User is signed in, proceed to MainActivity
                startActivity(new Intent(SplashScreen.this, MainActivity.class));
            } else {
                // User is not signed in, proceed to Signup screen
                startActivity(new Intent(SplashScreen.this, Signup.class));
            }
            finish();
        }, 3000); // 3 seconds splash delay
    }
}
//package com.anannya.quizoo;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.os.Handler;
//import android.view.animation.Animation;
//import android.view.animation.AnimationUtils;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.activity.EdgeToEdge;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;
//
//public class SplashScreen extends AppCompatActivity {
//
//TextView appname, myname;
//    Animation topAnim, bottomAnim;
//
//ImageView logo;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_splash_screen);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
//        appname = findViewById(R.id.appname);
//        myname = findViewById(R.id.myname);
//        logo = findViewById(R.id.logo);
//        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
//        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
//        logo.setAnimation(topAnim);
//        appname.setAnimation(bottomAnim);
//        myname.setAnimation(bottomAnim);
//        new Handler().postDelayed((new Runnable() {
//            @Override
//            public void run() {
//                Intent intent = new Intent(SplashScreen.this, Signup.class);
//                startActivity(intent);
//                finish(); // Close the splash screen activity so it can't be returned to
//
//            }
//        }), 3000); // 3 seconds delay before proceeding to the next activity
//
//    }
//}