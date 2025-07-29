package com.anannya.quizoo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        toolbar = findViewById(R.id.toolbar);
    }
    public void java(View view){
startActivity(new Intent(MainActivity.this, QuestionActivity.class));
    finish();
    }
    public void python(View view){
        startActivity(new Intent(MainActivity.this, QuestionActivity2.class));
        finish();

    }
    public void cp (View view){ startActivity(new Intent(MainActivity.this, QuestionActivity3.class));
        finish();
    }
    public void xml (View view){ startActivity(new Intent(MainActivity.this, QuestionActivity4.class));
        finish();
    }
    public void javascript (View view){ startActivity(new Intent(MainActivity.this, QuestionActivity5.class));
        finish();
    }
    public void clang (View view){ startActivity(new Intent(MainActivity.this, QuestionActivity6.class));
        finish();
    }
    public void sql (View view){ startActivity(new Intent(MainActivity.this, QuestionActivity7.class));
        finish();
}
    public void html (View view){ startActivity(new Intent(MainActivity.this, QuestionActivity8.class));
        finish();
    }}