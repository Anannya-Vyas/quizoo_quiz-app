package com.anannya.quizoo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class QuestionActivity8 extends AppCompatActivity {
    int flag = 0;
    int marks = 0;
    public static int correct = 0;
    int wrong = 0;

    TextView skip, question, totalScore, score, quecount;
    Button nextButton;
    RadioGroup radioGroup;
    RadioButton option1, option2, option3, option4, option5;

    String[] questions = {
            "What is the root element in every HTML document?",
            "Which tag is used for metadata in HTML?",
            "Which tag is used to link an external CSS file?",
            "Which tag is used to display an image?",
            "Which tag creates a hyperlink?"
    };

    String[][] options = {
            {"<html>", "<head>", "<body>", "<div>", "<main>"},
            {"<meta>", "<style>", "<script>", "<title>", "<link>"},
            {"<link>", "<css>", "<script>", "<style>", "<href>"},
            {"<img>", "<src>", "<image>", "<pic>", "<display>"},
            {"<a>", "<link>", "<href>", "<hyper>", "<url>"}
    };

    String[] answers = {
            "<html>",
            "<meta>",
            "<link>",
            "<img>",
            "<a>"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_question);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        skip = findViewById(R.id.skip);
        question = findViewById(R.id.question);
        totalScore = findViewById(R.id.totalScore);
        score = findViewById(R.id.score);
        quecount = findViewById(R.id.quecount);
        nextButton = findViewById(R.id.nextButton);
        radioGroup = findViewById(R.id.radioGroup);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        option5 = findViewById(R.id.option5);

        correct = 0;
        wrong = 0;
        marks = 0;
        flag = 0;

        setQuestionAndOptions(flag);
        score.setText("Score: " + marks);
        quecount.setText("Question " + (flag + 1) + " of " + questions.length);
        totalScore.setText("");

        nextButton.setOnClickListener(v -> {
            if (radioGroup.getCheckedRadioButtonId() == -1) {
                Toast.makeText(QuestionActivity8.this, "Please select an option", Toast.LENGTH_SHORT).show();
                return;
            }

            RadioButton uAnswered = findViewById(radioGroup.getCheckedRadioButtonId());
            String ansText = uAnswered.getText().toString();

            if (ansText.equals(answers[flag])) {
                correct++;
                marks++;
                Toast.makeText(QuestionActivity8.this, "Correct Answer!", Toast.LENGTH_SHORT).show();
                moveToNextQuestion();
            } else {
                wrong++;
                nextButton.setEnabled(false);
                Toast.makeText(QuestionActivity8.this, "Wrong Answer!\nCorrect is: " + answers[flag], Toast.LENGTH_LONG).show();
                new Handler().postDelayed(() -> {
                    nextButton.setEnabled(true);
                    moveToNextQuestion();
                }, 2000);
            }
        });

        skip.setOnClickListener(v -> {
            flag++;
            if (flag < questions.length) {
                setQuestionAndOptions(flag);
                quecount.setText("Question " + (flag + 1) + " of " + questions.length);
                // No marks/correct/wrong update, just skip
                score.setText("Score: " + marks);
                radioGroup.clearCheck();
            } else {
                totalScore.setText("Total Score: " + marks);
                nextButton.setEnabled(false);
                skip.setEnabled(false);
                Toast.makeText(QuestionActivity8.this, "Quiz Finished", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(QuestionActivity8.this, ResultActivity.class);
                intent.putExtra("attempted", flag);
                intent.putExtra("correct", correct);
                intent.putExtra("wrong", wrong);
                intent.putExtra("marks", marks);
                intent.putExtra("total", questions.length);
                startActivity(intent);
                finish();
            }
        });
    }

    private void moveToNextQuestion() {
        flag++;
        if (flag < questions.length) {
            setQuestionAndOptions(flag);
            quecount.setText("Question " + (flag + 1) + " of " + questions.length);
            score.setText("Score: " + marks);
        } else {
            totalScore.setText("Total Score: " + marks);
            nextButton.setEnabled(false);
            skip.setEnabled(false);
            Toast.makeText(QuestionActivity8.this, "Quiz Finished", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(QuestionActivity8.this, ResultActivity.class);
            intent.putExtra("attempted", flag);
            intent.putExtra("correct", correct);
            intent.putExtra("wrong", wrong);
            intent.putExtra("marks", marks);
            intent.putExtra("total", questions.length);
            startActivity(intent);
            finish();
        }
        radioGroup.clearCheck();
    }

    private void setQuestionAndOptions(int index) {
        question.setText(questions[index]);
        option1.setText(options[index][0]);
        option2.setText(options[index][1]);
        option3.setText(options[index][2]);
        option4.setText(options[index][3]);
        option5.setText(options[index][4]);
    }
}



//package com.anannya.quizoo;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.RadioButton;
//import android.widget.RadioGroup;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.activity.EdgeToEdge;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;
//
//public class QuestionActivity extends AppCompatActivity {
//    int flag = 0;
//    int marks = 0;
//    public static int correct = 0;
//    int wrong = 0;
//    TextView skip,question,totalScore,score,quecount;
//
//    Button nextButton;
//   String[] questions={
//   "Which of the following is not a primitive data type in Java\n"
//   ,"What is the default value of a boolean variable in Java\n"
//   ,"Which of the following is a valid declaration of a char variable in Java\n"
//   ,"Which of the following is not a valid keyword in Java\n"
//   ,"Which of the following is not a valid identifier in Java\n"
//
//
//};
//    String[][] options={
//            {"int", "float", "char", "String", "boolean"},
//            {"true", "false", "null", "undefined", "0"},
//            {"char c = 'a';", "char c = \"a\";", "char c = a;", "char c = 'ab';", "char c = 97;"},
//            {"class", "interface", "extends", "implements", "function"},
//            {"my-variable", "_myVariable", "$myVariable", "1myVariable", "myVariable1"}
//};
//    String[] answers={
//    "String",
//            "false",
//            "char c = 'a';",
//            "function",
//            "1myVariable"
//};
//RadioGroup radioGroup;
//RadioButton option1,option2,option3,option4,option5;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_question);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
//        skip = findViewById(R.id.skip);
//        question = findViewById(R.id.question);
//        totalScore = findViewById(R.id.totalScore);
//        score = findViewById(R.id.score);
//        quecount= findViewById(R.id.quecount);
//        nextButton = findViewById(R.id.nextButton);
//        radioGroup = findViewById(R.id.radioGroup);
//        option1 = findViewById(R.id.option1);
//        option2 = findViewById(R.id.option2);
//        option3 = findViewById(R.id.option3);
//        option4 = findViewById(R.id.option4);
//        option5 = findViewById(R.id.option5);
//        question.setText(questions[flag]);
//        option1.setText(options[flag][0]);
//        option2.setText(options[flag][1]);
//        option3.setText(options[flag][2]);
//        option4.setText(options[flag][3]);
//        option5.setText(options[flag][4]);
//        nextButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(radioGroup.getCheckedRadioButtonId() == -1) {
//                    Toast.makeText(QuestionActivity.this, "Please select an option", Toast.LENGTH_SHORT).show();
//                }
//                RadioButton uAnswered = findViewById(radioGroup.getCheckedRadioButtonId());
//                String ansText = uAnswered.getText().toString();
//                if (ansText.equals(answers[flag])) {
//                    correct++;
//                    Toast.makeText(QuestionActivity.this, "Correct Answer", Toast.LENGTH_SHORT).show();
//
//                }else {
//                    wrong++;
//                    Toast.makeText(QuestionActivity.this, "Wrong Answer", Toast.LENGTH_SHORT).show();
//                }
//                    // No option selected
//
//                flag++;
//                if(score != null) {
//                    score.setText("Score: " + marks);
//                    if(flag < questions.length) {
//                        question.setText(questions[flag]);
//                        option1.setText(options[flag][0]);
//                        option2.setText(options[flag][1]);
//                        option3.setText(options[flag][2]);
//                        option4.setText(options[flag][3]);
//                        option5.setText(options[flag][4]);
//                        quecount.setText("Question " + (flag + 1) + " of " + questions.length);
//                    } else {
//                        totalScore.setText("Total Score: " + marks);
//                        nextButton.setEnabled(false);
//                        skip.setEnabled(false);
//                        Toast.makeText(QuestionActivity.this, "Quiz Finished", Toast.LENGTH_SHORT).show();
//                        marks = correct;
//                        Intent intent = new Intent(QuestionActivity.this, ResultActivity.class);
////                        intent.putExtra("marks", marks);
//                        intent.putExtra("attempted", flag);
//                        intent.putExtra("correct", correct);
//                        intent.putExtra("wrong", wrong);
//                        startActivity(intent);
//                        finish();
//                }
//                    radioGroup.clearCheck();
//                }
//                ;
//        skip.setOnClickListener(new  View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//            Intent intent=(new Intent(QuestionActivity.this, ResultActivity.class));
//
//                intent.putExtra("attempted", flag);
//                intent.putExtra("correct", correct);
//                intent.putExtra("wrong", wrong);
//                startActivity(intent);
//                finish();
//
//                }
//        });
//
//    }
//});};}