package com.example.ucgpadmin.opportunity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.example.ucgpadmin.MainActivity;
import com.example.ucgpadmin.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class JobOfferActivity extends AppCompatActivity {


    AutoCompleteTextView job_track;
    EditText job_offer_name;
    EditText job_offer_details;

    Button add_job_offer_Button;

    DatabaseReference db;

    ArrayList trackArray = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_offer);


        job_offer_name = (EditText) findViewById(R.id.job_offer_name);
        job_offer_details = (EditText) findViewById(R.id.job_offer_details);

        ArrayAdapter<String> field_Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, trackArray);
        job_track = (AutoCompleteTextView) findViewById(R.id.job_track);
        job_track.setThreshold(1);
        job_track.setAdapter(field_Adapter);
        db = FirebaseDatabase.getInstance().getReference();

        add_job_offer_Button = findViewById(R.id.add_job_offer_Button);
        add_job_offer_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String jobTrack = job_track.getText().toString().trim();
                String jobName = job_offer_name.getText().toString().trim();

                String eventDetials = job_offer_details.getText().toString().trim();

                db.child("Job_offer").child(jobTrack).child("job_offer_name").setValue(jobName);
                db.child("Job_offer").child(jobTrack).child("job_offer_details").setValue(eventDetials);

                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
                finish();

            }
        });

    }
}
