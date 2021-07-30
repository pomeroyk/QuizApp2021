package com.example.quizapp2021;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;

public class ScoreActivity extends AppCompatActivity {
    TextView leaderboard;
    Intent incomingIntent;
    int score;
    Button EmailBTN;
    Button enterBTN;
    Button NextBTN;
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
        myRef = database.getReference("leaderboard");
        //database = FirebaseDatabase.getInstance().getReference("players");

        NextBTN = (Button) findViewById(R.id.NextButton);
        EmailBTN = (Button) findViewById(R.id.EmailButton);
        enterBTN = (Button) findViewById(R.id.EnterButton);
        nameET = (EditText) findViewById(R.id.NameET);
        leaderboard = (TextView) findViewById(R.id.displayScoreTV);

        incomingIntent = getIntent();
        score = incomingIntent.getIntExtra("scoreLBL", 0);//0 is the default value, in case it can't find the real value.


        EmailBTN.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String[] addresses = new String[]{"km2d23@yahoo.com"};
                String subject = getString(R.string.subject);
                composeEmail(addresses, subject);
                //look up how to add a body to the email.
            }
        });

        NextBTN.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(ScoreActivity.this, EndActivity.class);
                //intent.putExtra("myLeaderBoard", myRef.toString());
                startActivity(intent);
            }
        });


        nameET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameET.setText("");
            }
        });
        enterBTN.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                name = nameET.getText().toString();
                //testers.add(new Player(name,score));
                //Collections.sort(testers);
                //Get an automatically generated "key" for the player
                Player p = new Player(name, score);
                String key = myRef.push().getKey();
                myRef.child(key).setValue(p);
                //myRef.push().setValue(new Player(name, score));

                //Intent intent = new Intent(ScoreActivity.this, HighScoreActivity.class);
                //intent.putExtra("tester", testers.toString());
                //startActivity(intent);

            }


        });

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                Log.d(TAG, "HERE ");
                for(int i = testers.size()-1; i >=0; i--)
                {
                    testers.remove(i);
                }
                for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                    Player p = childSnapshot.getValue((Player.class));
                    testers.add(p);

                }
                Collections.sort(testers, Collections.reverseOrder());
                Log.d(TAG, "Leaderboard is: " + testers.toString().replace('[', ' ').replace(']', ' ').trim());
                String stuff="";
                leaderboard.setText("");
                for(int i = 0; i < testers.size(); i++)
                {
                    stuff+=testers.get(i).toString() + "\n";
                }
                leaderboard.setText(stuff);
                        //testers.toString().replace('[', ' ').replace(']', ' ').trim());

                //Log.d(TAG, "Leaderboard is: " + testers.toString());
                //Intent intent = new Intent(ScoreActivity.this, HighScoreActivity.class);
                //intent.putExtra("myLeaderBoard", testers);
                //startActivity(intent);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
            }

        });
    }
public void composeEmail (String[]addresses, String subject){
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        if (intent.resolveActivity(getPackageManager()) != null) {
        startActivity(intent);
        }
        }



    }




