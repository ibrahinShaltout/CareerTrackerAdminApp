package com.example.ucgpadmin.university;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.ucgpadmin.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ViewUniversityActivity extends AppCompatActivity {

//    DatabaseReference db2;
//    FirebaseHelperUniversity helper2;
//    UniversityAdapter universityAdapter;
//    ListView lvUni;
//    ArrayList<UniversityDataClass> universityDataClasses = new ArrayList<>();
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_view_university);
//
//        //INITIALIZE FIREBASE DB
//        lvUni = (ListView) findViewById(R.id.lvUniversity);
//        db2 = FirebaseDatabase.getInstance().getReference();
//        helper2 = new FirebaseHelperUniversity(db2);
//
//
//        //ADAPTER
//        universityAdapter = new UniversityAdapter(this, universityDataClasses);
//        lvUni.setAdapter(universityAdapter);
//
//        //RETRIEVE
//        db2.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                fetchData(dataSnapshot);
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//                fetchData(dataSnapshot);
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//                fetchData(dataSnapshot);
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//                fetchData(dataSnapshot);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//    }
//
//    private void fetchData(DataSnapshot dataSnapshot) {
//        UniversityDataClass universityDataClass = null;
//        Iterable<DataSnapshot> list = dataSnapshot.getChildren();
//        for (DataSnapshot x : list) {
//            universityDataClass = x.getValue(UniversityDataClass.class);
//            universityDataClasses.add(universityDataClass);
//        }
//        universityAdapter.notifyDataSetChanged();
//    }

}
