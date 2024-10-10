package com.example.doit;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class FinishActivity extends AppCompatActivity {

    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showResultScreen();
            }
        }, 2000);
    }

    @SuppressLint({"MissingInflatedId", "StringFormatMatches"})
    private void showResultScreen() {
        score = getIntent().getIntExtra("SCORE", 0);

        if(score == 100){
            setContentView(R.layout.first_place);
            TextView perfect_score = findViewById(R.id.perfect_score);
            perfect_score.setText(getString(R.string.you_nailed_it, score));

            ImageView nextQuizButto = findViewById(R.id.nextQuizButton);

            nextQuizButto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Start the next quiz
                    startActivity(new Intent(FinishActivity.this, MainActivity.class));
                    finish();
                }
            });


        }else if (score > 50) {
            setContentView(R.layout.winner);
            TextView winnerMessage = findViewById(R.id.winnerMessage);
            winnerMessage.setText(getString(R.string.congratulations_you_scored_1_s, score));

            ImageView nextQuizButton = findViewById(R.id.nextQuizButton);
            ImageView tryAgainButton = findViewById(R.id.tryAgainButton);

            nextQuizButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Start the next quiz
                    startActivity(new Intent(FinishActivity.this, MainActivity.class));
                    finish();
                }
            });

            tryAgainButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Start the quiz again
                    startActivity(new Intent(FinishActivity.this, MainActivity.class));
                    finish();
                }
            });

        } else {
            setContentView(R.layout.losers);
            TextView loserMessage = findViewById(R.id.loserMessage);
            loserMessage.setText(getString(R.string.sorry_you_scored_below_1_s, score));

            ImageView tryAgainButton = findViewById(R.id.tryAgainButton);

            tryAgainButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Start the quiz again
                    startActivity(new Intent(FinishActivity.this, MainActivity.class));
                    finish();
                }
            });
        }
    }
}

