package com.example.quizapp2021;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.quizapp2021.R.drawable.colors;
import static com.example.quizapp2021.R.drawable.ellieimg;

public class MainActivity extends AppCompatActivity {

    TextView question;
    Button trueButton;
    Button falseButton;


    int score;
    Button nextButton;
    Question q1, q2, q3, q4, q0;
    Question[] questions;
    int currentQIndex;
    ImageView imageIC;

    Drawable drawable;
    Resources res;
    ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        question = (TextView) findViewById(R.id.questionTV);

        trueButton = (Button) findViewById(R.id.TrueButton);
        falseButton = (Button) findViewById(R.id.FalseButton);
        nextButton = (Button) findViewById(R.id.NextButton);
        imageIC = (ImageView) findViewById(R.id.imageIcon);
        int[] images = {R.drawable.months, R.drawable.colors, R.drawable.seasons, R.drawable.calendar, R.drawable.october };


        //drawable = ResourcesCompat.getDrawable(res, R.drawable.color, null);
        score = 0;
        currentQIndex = 0;
        //imageIC.setImageDrawable(drawable);

        q1 = new Question(getString(R.string.Q1), true);
        q2 = new Question(getString(R.string.Q2), false);
        q3 = new Question(getString(R.string.Q3), true);
        q4 = new Question(getString(R.string.Q4), false);
        q0 = new Question(getString(R.string.Q0), true);


        questions = new Question[5];
        questions[0] = q0;
        questions[1] = q1;
        questions[2] = q2;
        questions[3] = q3;
        questions[4] = q4;
        question.setText(questions[currentQIndex].getQuestionText());


        imageIC.setImageResource(images[currentQIndex]);





        trueButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String text;
                Context context = getApplicationContext();
                if (questions[currentQIndex].isCorrectAns() == true) {
                    text = getString(R.string.correctToast);
                    score += 1;
                } else
                    text = getString(R.string.WrongToast);

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
                    text = getString(R.string.CorrectToast);
                    score += 1;
                } else
                    text = getString(R.string.WrongToast);
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
                    imageIC.setImageResource(images[currentQIndex]);
                } else {

                    Intent intent = new Intent(MainActivity.this, ScoreActivity.class);
                    intent.putExtra(getString(R.string.scorelabel), score);
                    startActivity(intent);
                }
            }
        });

    }
    }



