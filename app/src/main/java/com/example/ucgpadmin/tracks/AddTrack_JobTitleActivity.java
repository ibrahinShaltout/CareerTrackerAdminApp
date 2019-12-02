package com.example.ucgpadmin.tracks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ucgpadmin.MainActivity;
import com.example.ucgpadmin.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddTrack_JobTitleActivity extends AppCompatActivity {

    EditText track_job_title,track_Job_desc;
    Button add_Track_job_Button;
    DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_track__job_title);

        final String track_Id = getIntent().getStringExtra("EXTRA_Track_ID");
        final String track_Field = getIntent().getStringExtra("EXTRA_Track_Field");


        track_job_title=(EditText)findViewById(R.id.track_job_title);
        track_Job_desc=(EditText)findViewById(R.id.track_Job_desc);
        add_Track_job_Button=(Button) findViewById(R.id.add_Track_job_Button);

        db = FirebaseDatabase.getInstance().getReference().child("Tracks").child(track_Field).child(track_Id);

        add_Track_job_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String job_title = track_job_title.getText().toString();
                String Job_desc = track_Job_desc.getText().toString();

                String path_id = db.push().getKey();
                db.child("Job_Information").child("Job_Title").setValue(job_title);
                db.child("Job_Information").child("Job_Description").setValue(Job_desc);

                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
                finish();

            }
        });


    }
}
