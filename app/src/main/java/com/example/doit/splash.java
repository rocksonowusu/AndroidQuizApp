package com.example.doit;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Animation animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);

        // Apply the animation to the layout
        RelativeLayout relativeLayout = findViewById(R.id.relativeLayout_splash);
        relativeLayout.startAnimation(animFadeIn);

        // Set a listener to know when the animation ends
        animFadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // Animation started, do nothing here
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // Animation ended, launch the login page
                Intent intent = new Intent(splash.this, Login.class);
                startActivity(intent);
                finish(); // Finish the splash activity so that it's not shown again when back button is pressed
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }
}