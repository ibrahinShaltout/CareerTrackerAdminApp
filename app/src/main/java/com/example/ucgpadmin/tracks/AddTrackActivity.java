package com.example.ucgpadmin.tracks;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;

import com.example.ucgpadmin.R;
import com.example.ucgpadmin.dataclass.FieldDataClass;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import id.zelory.compressor.Compressor;
import xyz.hasnat.sweettoast.SweetToast;

public class AddTrackActivity extends AppCompatActivity {

    DatabaseReference db;
    DatabaseReference db1;

    TrackDataClass trackDataClass = new TrackDataClass();

    AutoCompleteTextView track_Field;
    MultiAutoCompleteTextView track_hashtag;
    EditText track_name, track_bio, track_desc, track_rate;

    ArrayList hashtagArray = new ArrayList<>();
    ArrayList fieldArray = new ArrayList<>();
    Button add_Track_Button;
    Button add_track_photo;
    String track_id;

// pick picture
    private final static int GALLERY_PICK_CODE = 1;
    private ProgressDialog progressDialog;
    Bitmap thumb_Bitmap = null;
    private StorageReference mTrackImgStorageRef;
    private StorageReference thumb_image_ref;
    private String Track_download_url;
    private String Track_thumb_download_url;
    private ImageView new_track_image;
    private DatabaseReference getUserDatabaseReference;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_track);

        db = FirebaseDatabase.getInstance().getReference();
        track_id = db.push().getKey();
        progressDialog = new ProgressDialog(this);
        mTrackImgStorageRef = FirebaseStorage.getInstance().getReference().child("Track_image");
        thumb_image_ref = FirebaseStorage.getInstance().getReference().child("thumb_image_Post");
        new_track_image = (ImageView) findViewById(R.id.track_photo);
        getUserDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Posts").child(track_id);
        getUserDatabaseReference.keepSynced(true); // for offline



        track_name = (EditText) findViewById(R.id.track_name);
        track_bio = (EditText) findViewById(R.id.track_bio);
        track_desc = (EditText) findViewById(R.id.track_desc);
        track_rate = (EditText) findViewById(R.id.track_rate);
        track_Field = (AutoCompleteTextView) findViewById(R.id.track_Field);

        add_Track_Button = (Button) findViewById(R.id.add_Track_Button);
        add_track_photo = (Button) findViewById(R.id.add_track_photo);
        /** Change profile photo from GALLERY */
        add_track_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // open gallery
                Intent galleryIntent = new Intent();
                galleryIntent.setType("image/*");
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(galleryIntent, GALLERY_PICK_CODE);
            }
        });




        ArrayAdapter<String> hashtagAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, hashtagArray);
        track_hashtag = (MultiAutoCompleteTextView) findViewById(R.id.track_hashtag);
        track_hashtag.setThreshold(1);
        track_hashtag.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        track_hashtag.setAdapter(hashtagAdapter);

        ArrayAdapter<String> field_Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, fieldArray);
        track_Field = (AutoCompleteTextView) findViewById(R.id.track_Field);
        track_Field.setThreshold(1);
        track_Field.setAdapter(field_Adapter);

        db = FirebaseDatabase.getInstance().getReference().child("Tracks");
        db1 = FirebaseDatabase.getInstance().getReference();

        add_Track_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String arrayHashtag = track_hashtag.getText().toString();
                final String arrayHashtagSp[] = arrayHashtag.split(",");
                ArrayList trackHashtagList = new ArrayList<String>(Arrays.asList(arrayHashtagSp));

                String trackName = track_name.getText().toString().trim();
                String trackBio = track_bio.getText().toString().trim();
                String trackDesc = track_desc.getText().toString().trim();
                String trackRate = track_rate.getText().toString().trim();
                String trackField = track_Field.getText().toString().trim();

                trackDataClass.setTrackName(trackName);
                trackDataClass.setTrackBio(trackBio);
                trackDataClass.setTrackDesc(trackDesc);
                trackDataClass.setTrackRate(trackRate);
                trackDataClass.setTrackHashtagList(trackHashtagList);
                trackDataClass.setTrackField(trackField);
//

                db.child(trackDataClass.trackField).child(track_id).child("track_Name").setValue(trackDataClass.trackName);
                db.child(trackDataClass.trackField).child(track_id).child("track_Bio").setValue(trackDataClass.trackBio);
                db.child(trackDataClass.trackField).child(track_id).child("track_Desc").setValue(trackDataClass.trackDesc);
                db.child(trackDataClass.trackField).child(track_id).child("track_Rate").setValue(trackDataClass.trackRate);
                db.child(trackDataClass.trackField).child(track_id).child("track_Field").setValue(trackDataClass.trackField);
                db.child(trackDataClass.trackField).child(track_id).child("track_Hashtag_List").setValue(trackDataClass.trackHashtagList);
                db.child(trackDataClass.trackField).child(track_id).child("TrackID").setValue(track_id);

                Intent intent = new Intent(getBaseContext(), AddTrackCollegeActivity.class);
                intent.putExtra("EXTRA_Track_ID", track_id);
                intent.putExtra("EXTRA_Track_Field", trackDataClass.trackField);
                startActivity(intent);
                finish();
            }
        });

        db1.child("Fields").child("Fields_List").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                fetchFieldName(dataSnapshot);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        /** Cropping image functionality
         * Library Link- https://github.com/ArthurHub/Android-Image-Cropper
         * */
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_PICK_CODE && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            // start picker to get image for cropping and then use the image in cropping activity
            CropImage.activity()
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(4, 3)
                    .start(this);
        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);

            if (resultCode == RESULT_OK) {
                progressDialog.setMessage("Please wait...");
                progressDialog.show();

                final Uri resultUri = result.getUri();

                File thumb_filePath_Uri = new File(resultUri.getPath());


                /**
                 * compress image using compressor library
                 * link - https://github.com/zetbaitsu/Compressor
                 * */
                try {
                    thumb_Bitmap = new Compressor(this)
                            .setMaxWidth(390)
                            .setMaxHeight(250)
                            .setQuality(45)
                            .compressToBitmap(thumb_filePath_Uri);
                } catch (IOException e) {
                    e.printStackTrace();
                }


                // firebase storage for uploading the cropped image
                final StorageReference filePath = mTrackImgStorageRef.child(track_id + ".jpg");

                UploadTask uploadTask = filePath.putFile(resultUri);
                Task<Uri> uriTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        if (!task.isSuccessful()) {
                            SweetToast.error(AddTrackActivity.this, "Post Photo Error: " + task.getException().getMessage());
                            //throw task.getException();
                        }
                        Track_download_url = filePath.getDownloadUrl().toString();
                        return filePath.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()) {
                            //Toasty.info(SettingsActivity.this, "Your profile photo is uploaded successfully.", Toast.LENGTH_SHORT).show();
                            // retrieve the stored image as profile photo
                            Track_download_url = task.getResult().toString();
                            Log.e("tag", "profile url: " + Track_download_url);

                            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                            thumb_Bitmap.compress(Bitmap.CompressFormat.JPEG, 45, outputStream);
                            final byte[] thumb_byte = outputStream.toByteArray();

                            // firebase storage for uploading the cropped and compressed image
                            final StorageReference thumb_filePath = thumb_image_ref.child(track_id + "jpg");
                            UploadTask thumb_uploadTask = thumb_filePath.putBytes(thumb_byte);

                            Task<Uri> thumbUriTask = thumb_uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                                @Override
                                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                    if (!task.isSuccessful()) {
                                        SweetToast.error(AddTrackActivity.this, "Thumb Image Error: " + task.getException().getMessage());
                                    }
                                    Track_thumb_download_url = thumb_filePath.getDownloadUrl().toString();
                                    return thumb_filePath.getDownloadUrl();
                                }
                            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    Track_thumb_download_url = task.getResult().toString();
                                    Log.e("tag", "thumb url: " + Track_thumb_download_url);
                                    if (task.isSuccessful()) {
                                        Log.e("tag", "thumb Post updated");

                                        HashMap<String, Object> update_user_data = new HashMap<>();
                                        update_user_data.put("user_image_Post", Track_download_url);
                                        update_user_data.put("user_thumb_image", Track_thumb_download_url);


                                        Picasso.get()
                                                .load(Track_download_url)
                                                .into(new_track_image);


                                        getUserDatabaseReference.updateChildren(new HashMap<String, Object>(update_user_data))
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {
                                                        Log.e("tag", "thumb Post updated");
                                                        progressDialog.dismiss();
                                                    }
                                                }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Log.e("tag", "for thumb Post: " + e.getMessage());
                                                progressDialog.dismiss();
                                            }
                                        });
                                    }

                                }
                            });

                        }

                    }
                });

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                //Exception error = result.getError();
                // handling more event
                SweetToast.info(AddTrackActivity.this, "Image cropping failed.");
            }
        }

    }
}


