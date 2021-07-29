package com.example.quizapp2021;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HighScoreActivity extends AppCompatActivity {
    Button emailButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        emailButton = (Button) findViewById(R.id.emailBTN);


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