package com.example.ucgpadmin.university;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ucgpadmin.R;
import com.example.ucgpadmin.dataclass.UniversityDataClass;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class AddUniversityActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 71;
    StorageReference storageReference;
    DatabaseReference db;

    private Uri filePath;
    UniversityDataClass universityDataClass = new UniversityDataClass();

    private EditText university_name, university_website,
            university_phone, university_type,
            university_headquarters, university_overview,
            university_branches, University_bio, university_foundation, university_location;

    private Button university_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_university);

        storageReference = FirebaseStorage.getInstance().getReference();

        university_name = (EditText) findViewById(R.id.university_name);
        university_website = (EditText) findViewById(R.id.university_website);
        university_phone = (EditText) findViewById(R.id.university_phone);
        university_type = (EditText) findViewById(R.id.university_type);
        university_headquarters = (EditText) findViewById(R.id.university_headquarters);
        university_overview = (EditText) findViewById(R.id.university_overview);
        university_branches = (EditText) findViewById(R.id.university_branches);
        University_bio = (EditText) findViewById(R.id.University_bio);
        university_foundation = (EditText) findViewById(R.id.university_foundation);
        university_location = (EditText) findViewById(R.id.university_location);


        db = FirebaseDatabase.getInstance().getReference();

        university_image = findViewById(R.id.university_image);
        university_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                chooseImage()
//                uploadImage();

            }
        });

        Button add_University_Button = findViewById(R.id.add_University_Button);
        add_University_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String university_id = db.push().getKey();
                String universityName = university_name.getText().toString().trim();
                String universityWebsite = university_website.getText().toString().trim();
                String universityPhone = university_phone.getText().toString().trim();
                String universityType = university_type.getText().toString().trim();
                String universityHeadquarters = university_headquarters.getText().toString().trim();
                String universityOverview = university_overview.getText().toString().trim();
                String universityBranches = university_branches.getText().toString().trim();
                String UniversityBio = University_bio.getText().toString().trim();
                String universityFoundation = university_foundation.getText().toString().trim();
                String universityLocation = university_location.getText().toString().trim();

                universityDataClass.setUniversityName(universityName);
                universityDataClass.setUniversityWebsite(universityWebsite);
                universityDataClass.setUniversityPhone(universityPhone);
                universityDataClass.setUniversityType(universityType);
                universityDataClass.setUniversityHeadquarters(universityHeadquarters);
                universityDataClass.setUniversityOverview(universityOverview);
                universityDataClass.setUniversityBranches(universityBranches);
                universityDataClass.setUniversityBio(UniversityBio);
                universityDataClass.setUniversityFoundation(universityFoundation);
                universityDataClass.setUniversityLocation(universityLocation);
                universityDataClass.setUniversityID(university_id);

                db.child("Universities").child("Universities_List").child(university_id).setValue(universityDataClass);

                Intent intent = new Intent(getBaseContext(), AddUniversity2Activity.class);
                intent.putExtra("EXTRA_University_ID", university_id);
                startActivity(intent);

            }
        });
    }

}
