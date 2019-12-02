package com.example.ucgpadmin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ucgpadmin.academy.AcademyActivity;
import com.example.ucgpadmin.academy.AddAcademyActivity;
import com.example.ucgpadmin.corporate.CorporateActivity;
import com.example.ucgpadmin.field.FieldActivity;
import com.example.ucgpadmin.opportunity.OpportunityActivity;
import com.example.ucgpadmin.tracks.TrackActivity;
import com.example.ucgpadmin.university.UniversityActivity;
import com.example.ucgpadmin.university.college.AddCollegeActivity;
import com.example.ucgpadmin.corporate.AddCorporateActivity;
import com.example.ucgpadmin.tracks.AddTrackActivity;
import com.example.ucgpadmin.university.AddUniversityActivity;

public class MainActivity extends AppCompatActivity {



    Button Track ,University , corporate , Academy, field ,opportunity ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Track = (Button)findViewById(R.id.track_screen);
        University = (Button)findViewById(R.id.university_screen);
        corporate = (Button)findViewById(R.id.corporate_screen);
        Academy = (Button)findViewById(R.id.academy_screen);
        field = (Button)findViewById(R.id.field_screen);
        opportunity = (Button)findViewById(R.id.opportunity_screen);


        Track.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TrackActivity.class));
            }
        });

        University.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, UniversityActivity.class));
            }
        });

        corporate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CorporateActivity.class));
            }
        });

        Academy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AcademyActivity.class));
            }
        });

        field.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FieldActivity.class));
            }
        });

        opportunity.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(MainActivity.this, OpportunityActivity.class));
                    }
                });







    }
}
