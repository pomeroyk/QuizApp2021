package com.example.quizapp2021;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ScoreActivity extends AppCompatActivity {
    TextView scoreTV;
    Intent incomingIntent;
    int score;
    Button skipBTN;
    Button enterBTN;


    FirebaseDatabase database;
    DatabaseReference myRef;
    EditText nameET;
    String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        //database = FirebaseDatabase.getInstance();
        //myRef = database.getReference("name");
        skipBTN = (Button) findViewById(R.id.SKIPButton);
        enterBTN = (Button) findViewById(R.id.EnterButton);
        nameET = (EditText)findViewById(R.id.NameET);

        scoreTV = (TextView) findViewById(R.id.scoreTV);
        incomingIntent = getIntent();
        score = incomingIntent.getIntExtra("scoreLBL", 0);//0 is the default value, in case it can't find the real value.

        scoreTV.setText("Score: " + score); //setText is expecting a string!!

        skipBTN.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

                Intent intent = new Intent(ScoreActivity.this, HighScoreActivity.class);
                intent.putExtra(getString(R.string.scorelabel), score);
                startActivity(intent);
            }
        });
        nameET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameET.setText("");
            }
        });
        enterBTN.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

                name = nameET.getText().toString();
                Intent intent = new Intent(ScoreActivity.this, HighScoreActivity.class);
                intent.putExtra(getString(R.string.scorelabel), score);
                startActivity(intent);
            }
        });




    }




}