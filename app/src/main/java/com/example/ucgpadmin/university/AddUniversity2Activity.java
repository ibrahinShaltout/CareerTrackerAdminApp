package com.example.ucgpadmin.university;

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
import com.example.ucgpadmin.university.college.CollegeDataClass;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;

public class AddUniversity2Activity extends AppCompatActivity {


    TextView notificationCollege;

    AutoCompleteTextView university_college;
    MultiAutoCompleteTextView college_specialization;

    Button add_College_to_University_Button;
    Button skip;

    String[] fldDepArray = {"Department", "Department", "Department", "Department", "Department", "Department",};

    DatabaseReference db1;
    ArrayList collegesName = new ArrayList<>();
    ArrayList SpecializationName = new ArrayList<>();

    UniversityDataClass universityDataClass = new UniversityDataClass();
    CollegeDataClass CollegeDataClass = new CollegeDataClass();
    SpecializationDataClass specializationDataClass = new SpecializationDataClass();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_university2);

        final String university_Id = getIntent().getStringExtra("EXTRA_University_ID");


        notificationCollege = (TextView) findViewById(R.id.textview1);
        notificationCollege.setVisibility(View.GONE);
        skip = (Button) findViewById(R.id.Skip_College_to_University_Button);

        ArrayAdapter<String> university_college_Adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, collegesName);
        university_college = (AutoCompleteTextView) findViewById(R.id.university_college);
        university_college.setThreshold(1);
        university_college.setAdapter(university_college_Adapter);


        ArrayAdapter<String> Specialization_Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, SpecializationName);
        college_specialization = (MultiAutoCompleteTextView) findViewById(R.id.university_specialization);
        college_specialization.setThreshold(1);
        college_specialization.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        college_specialization.setAdapter(Specialization_Adapter);

        add_College_to_University_Button = findViewById(R.id.add_College_to_University_Button);
        add_College_to_University_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String array_college_specialization = university_college.getText().toString();

                final String array_college_specialization_Sp[] = array_college_specialization.split(",");
                ArrayList college_specialization_arrayList = new ArrayList<String>(Arrays.asList(array_college_specialization_Sp));
                universityDataClass.setCollege_specialization(college_specialization_arrayList);


                String college_id = db1.push().getKey();

                db1.child("Universities").child("Universities_List").child(university_Id).child("University_Colleges")
                        .child(college_id).child("Name").setValue(array_college_specialization);

                db1.child("Universities").child("Universities_List").child(university_Id).child("University_Colleges")
                        .child(college_id).child("Colleges_Specialization").setValue(universityDataClass.getCollege_specialization());
                college_id = "i";

                notificationCollege.setVisibility(View.VISIBLE);
                university_college.setText("");
                college_specialization.setText("");

            }
        });

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddUniversity2Activity.this, MainActivity.class));
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
