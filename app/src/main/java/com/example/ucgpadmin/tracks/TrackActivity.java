package com.example.ucgpadmin.tracks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ucgpadmin.MainActivity;
import com.example.ucgpadmin.R;

public class TrackActivity extends AppCompatActivity {
    Button addTrack, viewTrack;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);


        addTrack = (Button) findViewById(R.id.add_track);
        viewTrack = (Button) findViewById(R.id.view_track);

        addTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TrackActivity.this, AddTrackActivity.class));
            }
        });

    }
}
