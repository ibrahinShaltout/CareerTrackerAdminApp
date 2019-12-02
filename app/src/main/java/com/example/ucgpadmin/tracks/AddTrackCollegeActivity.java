package com.example.ucgpadmin.tracks;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

import com.example.ucgpadmin.MainActivity;
import com.example.ucgpadmin.R;
import com.example.ucgpadmin.dataclass.SpecializationDataClass;
import com.example.ucgpadmin.dataclass.UniversityDataClass;
import com.example.ucgpadmin.university.AddUniversity2Activity;
import com.example.ucgpadmin.university.college.CollegeDataClass;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;

public class AddTrackCollegeActivity extends AppCompatActivity {

    TextView textview2;
    AutoCompleteTextView track_college,track_college_specialization;
    Button add_College_to_Track_Button,Skip_College_to_Track_Button;

    DatabaseReference db1;
    ArrayList collegesName = new ArrayList<>();
    ArrayList SpecializationName = new ArrayList<>();

    UniversityDataClass universityDataClass = new UniversityDataClass();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_track_college);
        final String track_Id = getIntent().getStringExtra("EXTRA_Track_ID");
        final String track_Field = getIntent().getStringExtra("EXTRA_Track_Field");

        textview2 =(TextView)findViewById(R.id.textview2);

        Skip_College_to_Track_Button =(Button)findViewById(R.id.Skip_College_to_Track_Button);

        textview2.setVisibility(View.GONE);
        Skip_College_to_Track_Button.setVisibility(View.GONE);

        ArrayAdapter<String> track_college_Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, collegesName);
        track_college = (AutoCompleteTextView) findViewById(R.id.Track_college);
        track_college.setThreshold(1);
        track_college.setAdapter(track_college_Adapter);


        ArrayAdapter<String> Specialization_Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, SpecializationName);
        track_college_specialization = (AutoCompleteTextView) findViewById(R.id.track_college_specialization);
        track_college_specialization.setThreshold(1);
        track_college_specialization.setAdapter(Specialization_Adapter);

        add_College_to_Track_Button =(Button)findViewById(R.id.add_College_to_Track_Button);
        add_College_to_Track_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String array_Track_college = track_college.getText().toString();
                String array_college_specialization = track_college_specialization.getText().toString();

                final String array_college_specialization_Sp[] = array_college_specialization.split(",");
                ArrayList college_specialization_arrayList = new ArrayList<String>(Arrays.asList(array_college_specialization_Sp));
                universityDataClass.setCollege_specialization(college_specialization_arrayList);


                String college_id = db1.push().getKey();

                db1.child("Tracks").child(track_Field).child(track_Id).child("Track_Colleges")
                        .child(college_id).child("Name").setValue(array_Track_college);

                db1.child("Tracks").child(track_Field).child(track_Id).child("Track_Colleges")
                        .child(college_id).child("Colleges_Specialization").setValue(array_college_specialization);
                college_id = "i";

                textview2.setVisibility(View.VISIBLE);
                Skip_College_to_Track_Button.setVisibility(View.VISIBLE);
                track_college.setText("");
                track_college_specialization.setText("");

            }
        });

        Skip_College_to_Track_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), AddTrackPathActivity.class);
                intent.putExtra("EXTRA_Track_ID", track_Id);
                intent.putExtra("EXTRA_Track_Field", track_Field);
                startActivity(intent);
                finish();
            }
        });

        db1 = FirebaseDatabase.getInstance().getReference();

        db1.child("Universities").child("Colleges_List").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                fetchCollageName(dataSnapshot);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        db1.child("Universities").child("Specialization_List").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                fetchSpecializationName(dataSnapshot);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void fetchCollageName(DataSnapshot dataSnapshot) {
        String s = null;
        CollegeDataClass collegeDataClass = null;
        Iterable<DataSnapshot> list = dataSnapshot.getChildren();
        for (DataSnapshot x : list) {
            collegeDataClass = x.getValue(CollegeDataClass.class);
            collegesName.add(collegeDataClass.getCollege_Name());
            s = x.getKey();

        }
    }

    private void fetchSpecializationName(DataSnapshot dataSnapshot) {
        String s = null;
        SpecializationDataClass specializationDataClass = null;
        Iterable<DataSnapshot> list = dataSnapshot.getChildren();
        for (DataSnapshot x : list) {
            specializationDataClass = x.getValue(SpecializationDataClass.class);
            SpecializationName.add(specializationDataClass.getSpecializationName());
            s = x.getKey();

        }
    }


}
