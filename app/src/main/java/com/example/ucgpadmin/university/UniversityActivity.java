package com.example.ucgpadmin.university;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.example.ucgpadmin.MainActivity;
import com.example.ucgpadmin.R;
import com.example.ucgpadmin.university.college.AddCollegeActivity;
import com.example.ucgpadmin.university.college.AddSpecializationActivity;

public class UniversityActivity extends AppCompatActivity {
    Button addUniversity, viewUniversity, add_new_college,add_new_specialization;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_university);
        addUniversity = (Button) findViewById(R.id.add_University);
        viewUniversity = (Button) findViewById(R.id.view_University);
        add_new_specialization = (Button) findViewById(R.id.add_new_specialization);
        add_new_college = (Button) findViewById(R.id.add_new_college);

        addUniversity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UniversityActivity.this, AddUniversityActivity.class));
            }
        });
        viewUniversity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, AddCollegeActivity.class));
            }
        });

        add_new_specialization = (Button) findViewById(R.id.add_new_specialization);


        add_new_college.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UniversityActivity.this, AddCollegeActivity.class));
            }
        });

        add_new_specialization.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UniversityActivity.this, AddSpecializationActivity.class));
            }
        });
    }
}
