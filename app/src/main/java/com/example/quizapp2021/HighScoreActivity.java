package com.example.quizapp2021;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HighScoreActivity extends AppCompatActivity {
    Button emailButton;
    TextView highScoreTV;
    FirebaseDatabase database;
    DatabaseReference myRef;
    Intent incomingIntent;
    String myLeaderBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        //myRef = database.getReference("leaderboard");
        emailButton = (Button) findViewById(R.id.emailBTN);
        highScoreTV = (TextView) findViewById(R.id.scoreTV);
        incomingIntent = getIntent();
        myLeaderBoard = incomingIntent.getStringExtra("myLeaderBoard");//0 is the default value, in case it can't find the real value.

        // Read from the database

        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                String[] addresses = new String[]{"km2d23@yahoo.com"};
                String subject = getString(R.string.subject);
                composeEmail(addresses, subject);
                //look up how to add a body to the email.
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