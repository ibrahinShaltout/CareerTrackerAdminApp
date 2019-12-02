package com.example.ucgpadmin.field;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ucgpadmin.R;
import com.example.ucgpadmin.university.college.AddSpecializationActivity;

public class FieldActivity extends AppCompatActivity {

    Button  add_field_Button ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_field);
        add_field_Button =(Button) findViewById(R.id.add_New_field_Button);
        add_field_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), AddFieldActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
