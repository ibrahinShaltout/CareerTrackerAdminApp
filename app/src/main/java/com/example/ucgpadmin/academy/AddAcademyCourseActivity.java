package com.example.ucgpadmin.academy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import com.example.ucgpadmin.R;
import com.example.ucgpadmin.dataclass.SpecializationDataClass;
import com.example.ucgpadmin.dataclass.UniversityDataClass;
import com.example.ucgpadmin.tracks.AddTrackPathActivity;
import com.example.ucgpadmin.university.college.CollegeDataClass;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;

public class AddAcademyCourseActivity extends AppCompatActivity {

    TextView textView_3;
    AutoCompleteTextView Academy_Course, Course_Academy_specialization;
    Button add_Course_to_Academy_Button, Skip_Course_to_Academy_Button;

    DatabaseReference db1;
    ArrayList coursesName = new ArrayList<>();
    ArrayList courseSpecializationName = new ArrayList<>();

    UniversityDataClass universityDataClass = new UniversityDataClass();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course_academy);
        final String Academy_Id = getIntent().getStringExtra("EXTRA_Academy_ID");

        textView_3 = (TextView) findViewById(R.id.textView_3);

        Skip_Course_to_Academy_Button = (Button) findViewById(R.id.Skip_Course_to_Academy_Button);

        textView_3.setVisibility(View.GONE);
        Skip_Course_to_Academy_Button.setVisibility(View.GONE);

        ArrayAdapter<String> course_academy_Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, coursesName);
        Academy_Course = (AutoCompleteTextView) findViewById(R.id.Academy_Course);
        Academy_Course.setThreshold(1);
        Academy_Course.setAdapter(course_academy_Adapter);


        ArrayAdapter<String> course_Specialization_Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, courseSpecializationName);
        Course_Academy_specialization = (AutoCompleteTextView) findViewById(R.id.Course_Academy_specialization);
        Course_Academy_specialization.setThreshold(1);
        Course_Academy_specialization.setAdapter(course_Specialization_Adapter);


        db1 = FirebaseDatabase.getInstance().getReference();

        db1.child("Academies").child("Courses").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                fetchCoursesName(dataSnapshot);

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





        add_Course_to_Academy_Button = (Button) findViewById(R.id.add_Course_to_Academy_Button);
        add_Course_to_Academy_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String array_Academy_Courses = Academy_Course.getText().toString();
                String array_Courses_specialization = Course_Academy_specialization.getText().toString();


                String Course_id = db1.push().getKey();

                db1.child("Academies").child("Academies_List").child(Academy_Id).child("Academy_Courses_List")
                        .child(Course_id).child("Course_Name").setValue(array_Academy_Courses);

                db1.child("Academies").child("Academies_List").child(Academy_Id).child("Academy_Courses_List")
                        .child(Course_id).child("Course_Specialization").setValue(array_Courses_specialization);
                Course_id = "i";

                textView_3.setVisibility(View.VISIBLE);
                Skip_Course_to_Academy_Button.setVisibility(View.VISIBLE);
                Academy_Course.setText("");
                Course_Academy_specialization.setText("");

            }
        });

        Skip_Course_to_Academy_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), AcademyActivity.class);
//                intent.putExtra("EXTRA_Track_ID", Academy_Id);
                startActivity(intent);
                finish();
            }
        });

      }

    private void fetchCoursesName(DataSnapshot dataSnapshot) {
        String s = null;
        CourseDataClass courseDataClass = null;
        Iterable<DataSnapshot> list = dataSnapshot.getChildren();
        for (DataSnapshot x : list) {
            courseDataClass = x.getValue(CourseDataClass.class);
            coursesName.add(courseDataClass.getCourse_name());
            s = x.getKey();

        }
    }

    private void fetchSpecializationName(DataSnapshot dataSnapshot) {
        String s = null;
        SpecializationDataClass specializationDataClass = null;
        Iterable<DataSnapshot> list = dataSnapshot.getChildren();
        for (DataSnapshot x : list) {
            specializationDataClass = x.getValue(SpecializationDataClass.class);
            courseSpecializationName.add(specializationDataClass.getSpecializationName());
            s = x.getKey();

        }
    }


}
