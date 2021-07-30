package com.example.quizapp2021;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class ScoreActivity extends AppCompatActivity {
    TextView leaderboard;
    Intent incomingIntent;
    int score;
    Button skipBTN;
    Button enterBTN;
    ArrayList<Player> testers = new ArrayList<Player>();


    FirebaseDatabase database;
    DatabaseReference myRef;
    EditText nameET;
    String name;
    String TAG = "AAA";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        database = FirebaseDatabase.getInstance("https://quizapp2021-4405a-default-rtdb.firebaseio.com/");
        //database = FirebaseDatabase.getInstance().getReference("players");
        myRef = database.getReference("leaderboard");
        skipBTN = (Button) findViewById(R.id.SKIPButton);
        enterBTN = (Button) findViewById(R.id.EnterButton);
        nameET = (EditText)findViewById(R.id.NameET);


        leaderboard = (TextView) findViewById(R.id.scoreTV);
        incomingIntent = getIntent();
        score = incomingIntent.getIntExtra("scoreLBL", 0);//0 is the default value, in case it can't find the real value.

        leaderboard.setText("Score: " + score); //setText is expecting a string!!

        skipBTN.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

                Intent intent = new Intent(ScoreActivity.this, HighScoreActivity.class);
                intent.putExtra("myLeaderBoard", myRef.toString());
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
                testers.add(new Player(name,score));
                Collections.sort(testers);
                myRef.setValue(testers);
                Log.d(TAG, "Leaderboard is: " + testers.toString());
                Intent intent = new Intent(ScoreActivity.this, HighScoreActivity.class);
                intent.putExtra("myLeaderBoard", myRef.toString());
                startActivity(intent);

            }
        });
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                ArrayList<Player> testers = new ArrayList<Player>();
                //String value = Objects.requireNonNull(dataSnapshot.getValue()).toString();
                //for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                  //  testers.add((Player) childSnapshot.getValue());}



                Log.d(TAG, "Leaderboard is: " + testers.toString());
                Intent intent = new Intent(ScoreActivity.this, HighScoreActivity.class);
                intent.putExtra("myLeaderBoard", testers.toString());
                startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });






    }




}