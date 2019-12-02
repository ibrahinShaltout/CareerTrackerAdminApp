package com.example.ucgpadmin.corporate;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

import com.example.ucgpadmin.R;
import com.example.ucgpadmin.university.AddUniversityActivity;
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

public class AddCorporateActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 71;
    StorageReference storageReference;
    DatabaseReference db;
    private Uri filePath;
    CorporateDataClass corporateDataClass=new CorporateDataClass();

    EditText corporate_name, corporate_phone, corporate_website, corporate_type,
            corporate_headquarters, corporate_branches, corporate_foundation,
            corporate_location, corporate_industry, corporate_size,
            corporate_overview, corporate_dep_overview;
    Button corporate_image, add_dep_button,add_corporate_Button;
    MultiAutoCompleteTextView corporate_dep,corporate_jobs;
    String[] crpDepArray ={"Department","Department","Department","Department","Department","Department"};
    String[] crpJobArray = {"Job","Job","Job","Job","Job","Job","Job","Job","Job","Job","Job","Job"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_corprete);
        storageReference =  FirebaseStorage.getInstance().getReference();
        db = FirebaseDatabase.getInstance().getReference().child("Corporates").push();


        corporate_name = (EditText) findViewById(R.id.corporate_name);
        corporate_phone = (EditText) findViewById(R.id.corporate_phone);
        corporate_website = (EditText) findViewById(R.id.corporate_website);
        corporate_type = (EditText) findViewById(R.id.corporate_type);
        corporate_headquarters = (EditText) findViewById(R.id.corporate_headquarters);
        corporate_branches = (EditText) findViewById(R.id.corporate_branches);
        corporate_foundation = (EditText) findViewById(R.id.corporate_foundation);
        corporate_location = (EditText) findViewById(R.id.corporate_location);
        corporate_industry = (EditText) findViewById(R.id.corporate_industry);
        corporate_size = (EditText) findViewById(R.id.corporate_size);
//        corporate_overview = (EditText) findViewById(R.id.corporate_overview);
        corporate_dep_overview = (EditText) findViewById(R.id.corporate_dep_overview);

        ArrayAdapter<String> ClgFldAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, crpDepArray);
        corporate_dep = (MultiAutoCompleteTextView) findViewById(R.id.corporate_dep);
        corporate_dep.setThreshold(1);
        corporate_dep.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        corporate_dep.setAdapter(ClgFldAdapter);

        ArrayAdapter<String> fieldAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, crpJobArray);
        corporate_jobs = (MultiAutoCompleteTextView) findViewById(R.id.corporate_jobs);
        corporate_jobs.setThreshold(1);
        corporate_jobs.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        corporate_jobs.setAdapter(fieldAdapter);

//        corporate_image = findViewById(R.id.corporate_image);
        corporate_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }


        });


        add_corporate_Button=findViewById(R.id.add_corporate_Button);
        add_corporate_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String corporateName = corporate_name.getText().toString().trim();
                String corporatePhone = corporate_phone.getText().toString().trim();
                String corporateWebsite = corporate_website.getText().toString().trim();
                String corporateType = corporate_type.getText().toString().trim();
                String corporateHeadquarters = corporate_headquarters.getText().toString().trim();
                String corporateBranches = corporate_branches.getText().toString().trim();
                String corporateFoundation = corporate_foundation.getText().toString().trim();
                String corporateLocation = corporate_location.getText().toString().trim();
                String corporateIndustry = corporate_industry.getText().toString().trim();
                String corporateSize = corporate_size.getText().toString().trim();
                String corporateOverview = corporate_overview.getText().toString().trim();
                String corporateDepOverview = corporate_dep_overview.getText().toString().trim();


                String arrayDep = corporate_dep.getText().toString();
                final String arrayDepSp[] = arrayDep.split(",");
                ArrayList crpDepartmentList = new ArrayList<String>(Arrays.asList(arrayDepSp));

                String arrayCrpJob = corporate_jobs.getText().toString();
                final String arrayCrpJobSp[] = arrayCrpJob.split(",");
                ArrayList crpJobList = new ArrayList<String>(Arrays.asList(arrayCrpJobSp));

                corporateDataClass.setCorporateName(corporateName);
                corporateDataClass.setCorporatePhone(corporatePhone);
                corporateDataClass.setCorporateWebsite(corporateWebsite);
                corporateDataClass.setCorporateType(corporateType);
                corporateDataClass.setCorporateHeadquarters(corporateHeadquarters);
                corporateDataClass.setCorporateBranches(corporateBranches);
                corporateDataClass.setCorporateFoundation(corporateFoundation);
                corporateDataClass.setCorporateLocation(corporateLocation);
                corporateDataClass.setCorporateIndustry(corporateIndustry);
                corporateDataClass.setCorporateSize(corporateSize);
                corporateDataClass.setCorporateOverview(corporateOverview);
                corporateDataClass.setCorporateDepOverview(corporateDepOverview);
                corporateDataClass.setCrpDepartmentList(crpDepartmentList);
                corporateDataClass.setCrpJobList(crpJobList);

                db.child("corporateName").setValue(corporateDataClass.corporateName);
                db.child("corporatePhone").setValue(corporateDataClass.corporatePhone);
                db.child("corporateWebsite").setValue(corporateDataClass.corporateWebsite);
                db.child("corporateType").setValue(corporateDataClass.corporateType);
                db.child("corporateHeadquarters").setValue(corporateDataClass.corporateHeadquarters);
                db.child("corporateBranches").setValue(corporateDataClass.corporateBranches);
                db.child("corporateFoundation").setValue(corporateDataClass.corporateFoundation);
                db.child("corporateLocation").setValue(corporateDataClass.corporateLocation);
                db.child("corporateIndustry").setValue(corporateDataClass.corporateIndustry);
                db.child("corporateSize").setValue(corporateDataClass.corporateSize);
                db.child("corporateOverview").setValue(corporateDataClass.corporateOverview);
                db.child("corporateDepOverview").setValue(corporateDataClass.corporateDepOverview);
                db.child("crpDepartmentList").setValue(corporateDataClass.crpDepartmentList);
                db.child("crpJobList").setValue(corporateDataClass.crpJobList);
                final String key = db.getKey();
                uploadImage(key);

            }
        });
    }
    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void uploadImage(String postKey) {

        if (filePath != null) {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            String imageID = UUID.randomUUID().toString();
            StorageReference ref = storageReference.child("images/" + imageID);
            corporateDataClass.setImageURL(filePath.toString());

            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            progressDialog.dismiss();
                            Toast.makeText(AddCorporateActivity.this, "Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(AddCorporateActivity.this, "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded " + (int) progress + "%");
                        }
                    });
        }
    }
}
