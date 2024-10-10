package com.example.doit;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.List;

public class QuizPage extends AppCompatActivity {

    private TextView questionTextView, progressCountTextView;
    private Button answer1Button, answer2Button, answer3Button, answer4Button;
    private List<Question> questions;
    private int currentQuestionIndex = 0;
    ImageView imageBack;
    private int score = 0;
    int userId;
    String category;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_page);

        questionTextView = findViewById(R.id.question);
        progressCountTextView = findViewById(R.id.progress_count);
        answer1Button = findViewById(R.id.answer1);
        answer2Button = findViewById(R.id.answer2);
        answer3Button = findViewById(R.id.answer3);
        answer4Button = findViewById(R.id.answer4);
        imageBack = findViewById(R.id.back);

        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle back button click here
                onBackPressed();
            }
        });

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("QUIZ_TITLE")) {
            String quizTitle = intent.getStringExtra("QUIZ_TITLE");
            TextView quizHeader = findViewById(R.id.quiz_header);
            quizHeader.setText(quizTitle);
        }

        String category = getIntent().getStringExtra("category");
        if (category == null) {
            // Handle the null case
            Toast.makeText(this, "Category not provided", Toast.LENGTH_SHORT).show();
            finish(); // Close the activity to prevent further issues
            return;
        }
        QuizManager quizManager = new QuizManager(category);
        questions = quizManager.getQuestions();

        loadQuestion();

        answer1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(answer1Button.getText().toString(), answer1Button);
            }
        });

        answer2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(answer2Button.getText().toString(), answer2Button);
            }
        });

        answer3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(answer3Button.getText().toString(), answer3Button);
            }
        });

        answer4Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(answer4Button.getText().toString(), answer4Button);
            }
        });
    }

    private void loadQuestion() {
        Log.d("QuizPage", "Current index: " + currentQuestionIndex);
        Log.d("QuizPage", "Questions size: " + questions.size());

        if (currentQuestionIndex < questions.size()) {
            Question currentQuestion = questions.get(currentQuestionIndex);
            questionTextView.setText(currentQuestion.getQuestion());
            List<String> options = currentQuestion.getOptions();

            if (options.size() >= 4) {
                answer1Button.setText(options.get(0));
                answer2Button.setText(options.get(1));
                answer3Button.setText(options.get(2));
                answer4Button.setText(options.get(3));
            } else {
                Log.e("QuizPage", "Options size is less than 4: " + options.size());
            }

            resetButtonColors();

            // Update the progress count
            String progress = (currentQuestionIndex + 1) + "/" + questions.size();
            progressCountTextView.setText(progress);
        } else {
            showResults();
        }
    }


    private void checkAnswer(String selectedAnswer, Button selectedButton) {
        Question currentQuestion = questions.get(currentQuestionIndex);

        if (currentQuestion.getCorrectAnswer().equals(selectedAnswer)) {
            selectedButton.setBackgroundColor(ContextCompat.getColor(this, R.color.color_forest_green));
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
            score += 10;
        } else {
            selectedButton.setBackgroundColor(ContextCompat.getColor(this, R.color.color_dark_red));
            highlightCorrectAnswer(currentQuestion);
            Toast.makeText(this, "Wrong!", Toast.LENGTH_SHORT).show();
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                currentQuestionIndex++;
                if (currentQuestionIndex < questions.size()) {
                    loadQuestion();
                } else {
                    showResults();
                }
            }
        }, 1500);
    }

    private void showResults() {
        Toast.makeText(this, "Quiz Finished", Toast.LENGTH_SHORT).show();
        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        userId = sharedPreferences.getInt("user_id", -1);
        category = getIntent().getStringExtra("category");
        if (userId != -1) {
            DatabaseClass database = new DatabaseClass(this, "QuizApp", null, 1);
            database.addScore(userId, score, category);

            int currentHighScore = database.getHighScoreForCategory(category);
            if (score > currentHighScore) {
                database.updateHighScore(category, score);
            }
        }
        Intent intent = new Intent(QuizPage.this, FinishActivity.class);
        intent.putExtra("SCORE", score);
        startActivity(intent);
        finish();
    }


    private void resetButtonColors() {
        answer1Button.setBackgroundColor(ContextCompat.getColor(this, R.color.black));
        answer2Button.setBackgroundColor(ContextCompat.getColor(this, R.color.black));
        answer3Button.setBackgroundColor(ContextCompat.getColor(this, R.color.black));
        answer4Button.setBackgroundColor(ContextCompat.getColor(this, R.color.black));
    }

    private void highlightCorrectAnswer(Question currentQuestion) {
        if (answer1Button.getText().toString().equals(currentQuestion.getCorrectAnswer())) {
            answer1Button.setBackgroundColor(ContextCompat.getColor(this, R.color.color_forest_green));
        } else if (answer2Button.getText().toString().equals(currentQuestion.getCorrectAnswer())) {
            answer2Button.setBackgroundColor(ContextCompat.getColor(this, R.color.color_forest_green));
        } else if (answer3Button.getText().toString().equals(currentQuestion.getCorrectAnswer())) {
            answer3Button.setBackgroundColor(ContextCompat.getColor(this, R.color.color_forest_green));
        } else if (answer4Button.getText().toString().equals(currentQuestion.getCorrectAnswer())) {
            answer4Button.setBackgroundColor(ContextCompat.getColor(this, R.color.color_forest_green));
        }
    }
}
