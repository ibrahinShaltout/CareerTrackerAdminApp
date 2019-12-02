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

public class AddEventActivity extends AppCompatActivity {


    AutoCompleteTextView event_track;
    EditText event_name;
    EditText event_detials;

    Button add_event_Button;

    DatabaseReference db;

    ArrayList trackArray = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);


        event_name = (EditText) findViewById(R.id.event_name);
        event_detials = (EditText) findViewById(R.id.event_detials);

        ArrayAdapter<String> field_Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, trackArray);
        event_track = (AutoCompleteTextView) findViewById(R.id.event_track);
        event_track.setThreshold(1);
        event_track.setAdapter(field_Adapter);
        db = FirebaseDatabase.getInstance().getReference();

        add_event_Button = findViewById(R.id.add_event_Button);
        add_event_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String eventTrack = event_track.getText().toString().trim();
                String eventName = event_name.getText().toString().trim();

                String eventDetials = event_detials.getText().toString().trim();

                db.child("Event").child(eventTrack).child("event_name").setValue(eventName);
                db.child("Event").child(eventTrack).child("event_detials").setValue(eventDetials);

                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }
}
