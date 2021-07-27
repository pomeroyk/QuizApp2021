package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {
    TextView scoreTV;
    Intent incomingIntent;
    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        scoreTV = (TextView) findViewById(R.id.scoreTV);
        incomingIntent = getIntent();
        score = incomingIntent.getIntExtra("scoreLBL",0);//0 is the default value, in case it can't find the real value.

        scoreTV.setText("Score: " + score); //setText is expecting a string!!


    }
}