package com.example.ucgpadmin.opportunity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ucgpadmin.MainActivity;
import com.example.ucgpadmin.R;
import com.example.ucgpadmin.tracks.TrackActivity;
import com.example.ucgpadmin.university.UniversityActivity;

public class OpportunityActivity extends AppCompatActivity {

    Button event ,jobOffer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opportunity);

        event = (Button)findViewById(R.id.event_screen);
        jobOffer = (Button)findViewById(R.id.jobOffer_screen);

        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OpportunityActivity.this, AddEventActivity.class));
            }
        });

        jobOffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OpportunityActivity.this, JobOfferActivity.class));
            }
        });
    }
}
