package com.anannya.quizoo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

public class ResultActivity extends AppCompatActivity {
    TextView correct, wrong, total, result;
    Button home;
    CircularProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        correct = findViewById(R.id.correct);
        result = findViewById(R.id.result);
        wrong = findViewById(R.id.wrong);
        total = findViewById(R.id.total);
        home = findViewById(R.id.home);
        progressBar = findViewById(R.id.circularProgressBar);

        // Get intent values, defaulting safely
        Intent intent = getIntent();
        int attempted = intent.getIntExtra("attempted", 0);
        int correctAns = intent.getIntExtra("correct", 0);
        int wrongAns = intent.getIntExtra("wrong", 0);
        int marks = intent.getIntExtra("marks", 0);
        int totalQuestions = intent.getIntExtra("total", attempted);

        correct.setText("Correct Answers: " + correctAns);
        wrong.setText("Wrong Answers: " + wrongAns);
        total.setText("Total Questions: " + totalQuestions);

        // Circular Progress Bar setup
        progressBar.setProgress(correctAns); // Set progress
        progressBar.setProgressMax(totalQuestions); // This should match your XML cpb_progress_max as well

        // Result summary
        if (marks >= 3) {
            result.setText("You Passed!");
        } else {
            result.setText("You Failed!");
        }

        home.setOnClickListener(v -> {
            Intent intent1 = new Intent(ResultActivity.this, MainActivity.class);
            startActivity(intent1);
            finish();
        });
    }
}
//package com.anannya.quizoo;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.widget.Button;
//import android.widget.TextView;
//
//import androidx.activity.EdgeToEdge;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;
//
//import com.mikhaellopez.circularprogressbar.CircularProgressBar;
//
//public class ResultActivity extends AppCompatActivity {
//    TextView correct, wrong, total,result;
//    Button home;
//    CircularProgressBar progressBar;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_result);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
//
//        correct = findViewById(R.id.correct);
//        result = findViewById(R.id.result);
//        wrong = findViewById(R.id.wrong);
//        total = findViewById(R.id.total);
//        home = findViewById(R.id.home);
//        progressBar = findViewById(R.id.circularProgressBar);
//        int marks = getIntent().getIntExtra("marks", 0);
//        int totalQuestions = getIntent().getIntExtra("total", 0);
//        int wrongAnswers = totalQuestions - marks;
//        correct.setText(String.valueOf(marks));
//        wrong.setText(String.valueOf(wrongAnswers));
//        total.setText(String.valueOf(totalQuestions));
//     progressBar.setProgress(QuestionActivity.correct);
//        Intent intent=getIntent();
//        int attempted1 = intent.getIntExtra("attempted", 0);
//        int correct1 = intent.getIntExtra("correct", 0);
//        int wrong1 = intent.getIntExtra("wrong", 0);
//       correct.setText("Correct Answers: " + correct1);
//        wrong.setText("Wrong Answers: " + wrong1);
//        total.setText("Total Questions: " + attempted1);
//        result.setText(correct1);
//
//        if (marks >= 3) {
//            result.setText("You Passed");
//        } else {
//            result.setText("You Failed");
//        }
//
//        home.setOnClickListener(v -> {
//            Intent intent1 = new Intent(ResultActivity.this, MainActivity.class);
//            startActivity(intent1);
//            finish();
//        });
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//    }
//}