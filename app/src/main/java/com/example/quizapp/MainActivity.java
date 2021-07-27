package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView question;
    Button trueButton;
    Button falseButton;
    int score;
    Button nextButton;
    Question q1,q2,q3,q4,q0;
    Question[] questions;
    int currentQIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        question = (TextView) findViewById(R.id.questionTV);

        trueButton = (Button) findViewById(R.id.TrueButton);
        falseButton = (Button) findViewById(R.id.FalseButton);
        nextButton = (Button) findViewById(R.id.NextButton);
        score = 0;
        currentQIndex = 0;
        q1 = new Question("Green is a color", true);
        q2 = new Question("Summer is a month", false);
        q3 = new Question("Sunday is the first day of the week", true);
        q4 = new Question("October is month 8", false);
        q0 = new Question("There are 12 months in the year", true);
        questions = new Question[5];
        questions[0] = q0;
        questions[1] = q1;
        questions[2] = q2;
        questions[3] = q3;
        questions[4] = q4;
        question.setText(questions[currentQIndex].getQuestionText());


        trueButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String text;
                Context context = getApplicationContext();
                if (questions[currentQIndex].isCorrectAns() == true) {
                    text = "That's correct!";
                    score += 1;
                } else
                    text = "Try again!";

                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });

        falseButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String text;
                Context context = getApplicationContext();
                if (questions[currentQIndex].isCorrectAns() == false) {
                    text = "That's correct!";
                    score += 1;
                } else
                    text = "Try again!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });


        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (currentQIndex < questions.length - 1) {
                    currentQIndex++;
                    question.setText(questions[currentQIndex].getQuestionText());
                } else {

                    Intent intent = new Intent(MainActivity.this, ScoreActivity.class);
                    intent.putExtra("scoreLBL", score);
                    startActivity(intent);
                }
            }
        });
    }
}

