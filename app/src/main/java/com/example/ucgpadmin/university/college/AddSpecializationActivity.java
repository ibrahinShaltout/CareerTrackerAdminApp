package com.example.ucgpadmin.university.college;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ucgpadmin.MainActivity;
import com.example.ucgpadmin.R;
import com.example.ucgpadmin.dataclass.SpecializationDataClass;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddSpecializationActivity extends AppCompatActivity {


    EditText specialization_overview;
    EditText specialization_name;

    Button add_specialization_Button;

    DatabaseReference db;
    SpecializationDataClass specializationDataClass = new SpecializationDataClass();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_specialization);

        specialization_overview = (EditText) findViewById(R.id.specialization_overview);
        specialization_name = (EditText) findViewById(R.id.specialization_name);

        db = FirebaseDatabase.getInstance().getReference().child("Universities").child("Specialization_List").push();

        add_specialization_Button = findViewById(R.id.add_specialization_Button);
        add_specialization_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String specializationName = specialization_name.getText().toString().trim();

                String collegeFieldOverview = specialization_overview.getText().toString().trim();

                specializationDataClass.setSpecializationOverview(collegeFieldOverview);
                specializationDataClass.setSpecializationName(specializationName);


                db.child("specializationOverview").setValue(specializationDataClass.getSpecializationOverview());
                db.child("specializationName").setValue(specializationDataClass.getSpecializationName());

                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
                finish();

            }
        });


    }
}
