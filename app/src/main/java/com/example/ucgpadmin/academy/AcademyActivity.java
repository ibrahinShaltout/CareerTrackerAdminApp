package com.example.ucgpadmin.academy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ucgpadmin.MainActivity;
import com.example.ucgpadmin.R;

public class AcademyActivity extends AppCompatActivity {

    Button addAcademy,viewAcademy,add_Course;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academy);

        addAcademy = (Button)findViewById(R.id.add_academy);
        viewAcademy = (Button)findViewById(R.id.view_academy);
        add_Course = (Button)findViewById(R.id.add_Course);

        addAcademy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AcademyActivity.this, AddAcademyActivity.class));
            }
        });
        viewAcademy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(MainActivity.this, ViewC.class));
            }
        });
        add_Course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AcademyActivity.this, AddCourseActivity.class));
            }
        });
    }


}
