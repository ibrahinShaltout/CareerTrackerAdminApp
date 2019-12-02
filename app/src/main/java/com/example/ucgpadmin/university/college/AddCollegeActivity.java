package com.example.ucgpadmin.university.college;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;

import com.example.ucgpadmin.MainActivity;
import com.example.ucgpadmin.R;
import com.example.ucgpadmin.dataclass.SpecializationDataClass;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;

public class AddCollegeActivity extends AppCompatActivity {

    EditText college_name;
    EditText college_Overview;

    MultiAutoCompleteTextView college_Specialization;
    Button add_college_Button;

    DatabaseReference db;
    DatabaseReference get_db;
    CollegeDataClass collegeDataClass = new CollegeDataClass();

    ArrayList specializationDataClasses = new ArrayList<>();

//    String[] fieldsArray = {"Field", "Field2", "Field3", "Field4", "Field5", "Field6", "Field7", "Field8",};
//    String[] depArray = {"Department", "Department", "Department", "Department", "Department", "Department"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_college);

        college_name = (EditText) findViewById(R.id.college_name);
        college_Overview = (EditText) findViewById(R.id.college_field_overview);

//        ArrayAdapter<String> CollegeFieldAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, fieldsArray);
//        College_Name = (AutoCompleteTextView) findViewById(R.id.college_field);
//        College_Name.setThreshold(1);
//        College_Name.setAdapter(CollegeFieldAdapter);

        ArrayAdapter<String> specializationAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, specializationDataClasses);
        college_Specialization = (MultiAutoCompleteTextView) findViewById(R.id.college_specialization);
        college_Specialization.setThreshold(1);
        college_Specialization.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        college_Specialization.setAdapter(specializationAdapter);

        db = FirebaseDatabase.getInstance().getReference().child("Universities").child("Colleges_List").push();
        get_db = FirebaseDatabase.getInstance().getReference().child("Universities").child("Specialization_List");

        add_college_Button = findViewById(R.id.add_college_Button);
        add_college_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String CollegeFieldName = college_name.getText().toString().trim();
                String collegeFieldOverview = college_Overview.getText().toString().trim();

                String arrayDep = college_Specialization.getText().toString();
                final String arrayDepSp[] = arrayDep.split(",");
                ArrayList CollegeSpecializationList = new ArrayList<String>(Arrays.asList(arrayDepSp));


                collegeDataClass.setCollege_Overview(collegeFieldOverview);
                collegeDataClass.setCollege_Specialization_List(CollegeSpecializationList);
                collegeDataClass.setCollege_Name(CollegeFieldName);

                db.child("college_Name").setValue(collegeDataClass.college_Name);
                db.child("college_Overview").setValue(collegeDataClass.college_Overview);
                db.child("college_Specialization_List").setValue(collegeDataClass.college_Specialization_List);


                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
                finish();

            }
        });


        get_db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                fetchSpecializationData(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void fetchSpecializationData(DataSnapshot dataSnapshot) {
        SpecializationDataClass specializationDataClass = null;
        Iterable<DataSnapshot> list = dataSnapshot.getChildren();
        for (DataSnapshot x : list) {
            specializationDataClass = x.getValue(SpecializationDataClass.class);
            specializationDataClasses.add(specializationDataClass.getSpecializationName());
        }

    }
}
