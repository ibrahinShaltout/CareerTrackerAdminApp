package com.example.ucgpadmin.academy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

import com.example.ucgpadmin.R;
import com.example.ucgpadmin.tracks.AddTrackActivity;
import com.example.ucgpadmin.tracks.AddTrackCollegeActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

public class AddAcademyActivity extends AppCompatActivity {

    StorageReference storageReference;
    DatabaseReference db;

    AcademyDataClass academyDataClass = new AcademyDataClass();

    EditText academy_name,
            academy_overview,
            academy_website,
            academy_phone,
            academy_type,
            academy_headquarters,
            academy_branches,
            academy_foundation,
            academy_location,
            academy_industry,
            academy_specialities;

    Button add_corporate_Button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_academy);

        academy_name = (EditText) findViewById(R.id.academy_name);
        academy_overview = (EditText) findViewById(R.id.academy_overview);
        academy_website = (EditText) findViewById(R.id.academy_website);
        academy_phone = (EditText) findViewById(R.id.academy_phone);
        academy_type = (EditText) findViewById(R.id.academy_type);
        academy_headquarters = (EditText) findViewById(R.id.academy_headquarters);
        academy_branches = (EditText) findViewById(R.id.academy_branches);
        academy_foundation = (EditText) findViewById(R.id.academy_foundation);
        academy_location = (EditText) findViewById(R.id.academy_location);
        academy_industry = (EditText) findViewById(R.id.academy_industry);
        academy_specialities = (EditText) findViewById(R.id.academy_specialities);


        db = FirebaseDatabase.getInstance().getReference().child("Academies").child("Academies_List");


        add_corporate_Button = findViewById(R.id.add_Academy_Button);
        add_corporate_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                String arrayHours = acd_course_hours.getText().toString();
//                final String arrayHourSp[] = arrayHours.split(",");
//                ArrayList academyHourList = new ArrayList<String>(Arrays.asList(arrayHourSp));
//                String arrayLevels = acd_course_levels.getText().toString();
//                final String arrayLevelSp[] = arrayLevels.split(",");
//                ArrayList academyLevelList = new ArrayList<String>(Arrays.asList(arrayLevelSp));

                String academyName = academy_name.getText().toString().trim();
                String academyOverview = academy_overview.getText().toString().trim();
                String academyWebsite = academy_website.getText().toString().trim();
                String academyPhone = academy_phone.getText().toString().trim();
                String academyType = academy_type.getText().toString().trim();
                String academyHeadquarters = academy_headquarters.getText().toString().trim();
                String academyBranches = academy_branches.getText().toString().trim();
                String academyFoundation = academy_foundation.getText().toString().trim();
                String academyLocation = academy_location.getText().toString().trim();
                String academyIndustry = academy_industry.getText().toString().trim();
                String academySpecialities = academy_specialities.getText().toString().trim();

                academyDataClass.setAcademyName(academyName);
                academyDataClass.setAcademyWebsite(academyWebsite);
                academyDataClass.setAcademyPhone(academyPhone);
                academyDataClass.setAcademyType(academyType);
                academyDataClass.setAcademyHeadquarters(academyHeadquarters);
                academyDataClass.setAcademyBranches(academyBranches);
                academyDataClass.setAcademyFoundation(academyFoundation);
                academyDataClass.setAcademyLocation(academyLocation);
                academyDataClass.setAcademyIndustry(academyIndustry);
                academyDataClass.setAcademySpecialities(academySpecialities);
                academyDataClass.setAcademyOverview(academyOverview);

                String Academy_id = db.push().getKey();

                db.child(Academy_id).child("academy_Name").setValue(academyDataClass.academyName);
                db.child(Academy_id).child("academy_Overview").setValue(academyDataClass.academyOverview);
                db.child(Academy_id).child("academy_Website").setValue(academyDataClass.academyWebsite);
                db.child(Academy_id).child("academy_Phone").setValue(academyDataClass.academyPhone);
                db.child(Academy_id).child("academy_Type").setValue(academyDataClass.academyType);
                db.child(Academy_id).child("academy_Headquarters").setValue(academyDataClass.academyHeadquarters);
                db.child(Academy_id).child("academy_Branches").setValue(academyDataClass.academyBranches);
                db.child(Academy_id).child("academy_Foundation").setValue(academyDataClass.academyFoundation);
                db.child(Academy_id).child("academy_Location").setValue(academyDataClass.academyLocation);
                db.child(Academy_id).child("academy_Industry").setValue(academyDataClass.academyIndustry);
                db.child(Academy_id).child("academy_Specialities").setValue(academyDataClass.academySpecialities);

                Intent intent = new Intent(getBaseContext(), AddAcademyCourseActivity.class);
                intent.putExtra("EXTRA_Academy_ID", Academy_id);
                startActivity(intent);
                finish();


            }
        });
    }
}
