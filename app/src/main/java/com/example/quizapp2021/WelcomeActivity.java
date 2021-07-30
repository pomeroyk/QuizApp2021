package com.example.quizapp2021;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {
    TextView messageView;
    Button btnSpanish, btnEnglish;
    Context context;
    Resources resources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // referencing the text and button views
        messageView = (TextView) findViewById(R.id.welcomeTV);
        btnSpanish = findViewById(R.id.btnSpanish);
        btnEnglish = findViewById(R.id.btnEnglish);

        // setting up on click listener event over the button
        // in order to change the language with the help of
        // com.example.quizapp2021.LocaleHelper class
        btnEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context = LocaleHelper.setLocale(WelcomeActivity.this, "en");
                resources = context.getResources();
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });

        btnSpanish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context = LocaleHelper.setLocale(WelcomeActivity.this, "es");
                resources = context.getResources();
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });

    }
}
