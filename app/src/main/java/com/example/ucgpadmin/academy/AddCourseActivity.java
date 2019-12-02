package com.example.ucgpadmin.academy;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.ucgpadmin.R;
import com.example.ucgpadmin.dataclass.FieldDataClass;
import com.example.ucgpadmin.tracks.AddTrack_JobTitleActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AddCourseActivity extends AppCompatActivity {
    AutoCompleteTextView course_Field;
    EditText course_name, course_overview, course_Link, course_hours, course_price;
    private RadioGroup radioGenderGroup;
    private RadioButton radioGenderButton;
    Button add_new_course_Button;

    ArrayList fieldArray = new ArrayList<>();

    DatabaseReference db;
    DatabaseReference db1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);


        course_name = (EditText) findViewById(R.id.course_name);
        course_overview = (EditText) findViewById(R.id.course_overview);
        course_Link = (EditText) findViewById(R.id.course_Link);
        course_hours = (EditText) findViewById(R.id.course_hours);
        course_price = (EditText) findViewById(R.id.course_price);


        ArrayAdapter<String> course_field_Adapter = new ArrayAdapter<String>(AddCourseActivity.this, android.R.layout.simple_dropdown_item_1line, fieldArray);
        course_Field = (AutoCompleteTextView) findViewById(R.id.course_Topic);
        course_Field.setThreshold(1);
        course_Field.setAdapter(course_field_Adapter);


        radioGenderGroup = (RadioGroup) findViewById(R.id.radioGenderGroup);
        add_new_course_Button = (Button) findViewById(R.id.add_new_course_Button);
        db = FirebaseDatabase.getInstance().getReference().child("Academies").child("Courses");
        db1 = FirebaseDatabase.getInstance().getReference();


        db1.child("Fields").child("Fields_List").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                fetchFieldName(dataSnapshot);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        add_new_course_Button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // get selected radio button from radioGroup
                int selectedId = radioGenderGroup.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                radioGenderButton = (RadioButton) findViewById(selectedId);

//                Toast.makeText(AddCourseActivity.this,
//                        radioGenderButton.getText(), Toast.LENGTH_SHORT).show();


                String course_name1 = course_name.getText().toString().trim();
                String course_overview1 = course_overview.getText().toString().trim();
                String course_Link1 = course_Link.getText().toString().trim();
                String course_hours1 = course_hours.getText().toString().trim();
                String course_price1 = course_price.getText().toString().trim();
                String course_Field1 = course_Field.getText().toString().trim();






                String college_id = db.push().getKey();

                db.child(college_id).child("course_name").setValue(course_name1);
                db.child(college_id).child("course_overview").setValue(course_overview1);
                db.child(college_id).child("course_Link").setValue(course_Link1);
                db.child(college_id).child("course_hours").setValue(course_hours1);
                db.child(college_id).child("course_price").setValue(course_price1);
                db.child(college_id).child("course_Field").setValue(course_Field1);


                String Radio =radioGenderButton.getText().toString();
                if (Radio.equals("Yes") )
                {

                    db.child(college_id).child("course_Certification").setValue(Radio);

                }else if (Radio.equals("No"))
                {
                    db.child(college_id).child("course_Certification").setValue(Radio);
                }

                Intent intent = new Intent(getBaseContext(), AcademyActivity.class);
                startActivity(intent);
                finish();
            }

        });

    }

    private void fetchFieldName(DataSnapshot dataSnapshot) {
        FieldDataClass fieldDataClass = null;
        Iterable<DataSnapshot> list = dataSnapshot.getChildren();
        for (DataSnapshot x : list) {
            fieldDataClass = x.getValue(FieldDataClass.class);
            fieldArray.add(fieldDataClass.getField_Name());

        }
    }


}
