package com.example.ucgpadmin.field;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ucgpadmin.MainActivity;
import com.example.ucgpadmin.R;
import com.example.ucgpadmin.dataclass.FieldDataClass;
import com.example.ucgpadmin.dataclass.SpecializationDataClass;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddFieldActivity extends AppCompatActivity {

    EditText field_overview;
    EditText field_name;

    Button add_field_Button;

    DatabaseReference db;
    FieldDataClass fieldDataClass = new FieldDataClass();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_field);

        field_name = (EditText) findViewById(R.id.field_name);
        field_overview = (EditText) findViewById(R.id.field_overview);

        db = FirebaseDatabase.getInstance().getReference().child("Fields").child("Fields_List").push();

        add_field_Button = findViewById(R.id.add_field_Button);
        add_field_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String specializationName = field_name.getText().toString().trim();

                String collegeFieldOverview = field_overview.getText().toString().trim();

                fieldDataClass.setField_Name(specializationName);
                fieldDataClass.setField_Overview(collegeFieldOverview);


                db.child("field_Name").setValue(fieldDataClass.getField_Name());
                db.child("field_Overview").setValue(fieldDataClass.getField_Overview());

                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
                finish();

            }
        });


    }
}
