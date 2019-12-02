package com.example.ucgpadmin.tracks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ucgpadmin.R;
import com.example.ucgpadmin.academy.AcademyActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddTrackPathActivity extends AppCompatActivity {

    EditText Station_1_Overview,
            For_1A, A1_Station_Overview, learning_outcomes_A1,
            For_1B, B1_Station_Overview, learning_outcomes_B1,
            For_1C, C1_Station_Overview, learning_outcomes_C1,
            For_1D, D1_Station_Overview, learning_outcomes_D1,

    Station_2_Overview,
            For_2A, A2_Station_Overview, learning_outcomes_A2,
            For_2B, B2_Station_Overview, learning_outcomes_B2,
            For_2C, C2_Station_Overview, learning_outcomes_C2,
            For_2D, D2_Station_Overview, learning_outcomes_D2,

    Station_3_Overview,
            For_3A, A3_Station_Overview, learning_outcomes_A3,
            For_3B, B3_Station_Overview, learning_outcomes_B3,
            For_3C, C3_Station_Overview, learning_outcomes_C3,
            For_3D, D3_Station_Overview, learning_outcomes_D3;

    Button next, Skip_Path_Button;

    TextView textView_4;
    DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_track_path);
        final String track_Id = getIntent().getStringExtra("EXTRA_Track_ID");
        final String track_Field = getIntent().getStringExtra("EXTRA_Track_Field");


        textView_4 = (TextView) findViewById(R.id.textView_4);

        Skip_Path_Button = (Button) findViewById(R.id.Skip_Path_Button);

        textView_4.setVisibility(View.GONE);
        Skip_Path_Button.setVisibility(View.GONE);


        Station_1_Overview = (EditText) findViewById(R.id.Station_1_Overview);

        For_1A = (EditText) findViewById(R.id.For_1A);
        A1_Station_Overview = (EditText) findViewById(R.id.A1_Station_Overview);
        learning_outcomes_A1 = (EditText) findViewById(R.id.learning_outcomes_A1);

        For_1B = (EditText) findViewById(R.id.For_1B);
        B1_Station_Overview = (EditText) findViewById(R.id.B1_Station_Overview);
        learning_outcomes_B1 = (EditText) findViewById(R.id.learning_outcomes_B1);

        For_1C = (EditText) findViewById(R.id.For_1C);
        C1_Station_Overview = (EditText) findViewById(R.id.C1_Station_Overview);
        learning_outcomes_C1 = (EditText) findViewById(R.id.learning_outcomes_C1);

        For_1D = (EditText) findViewById(R.id.For_1D);
        D1_Station_Overview = (EditText) findViewById(R.id.D1_Station_Overview);
        learning_outcomes_D1 = (EditText) findViewById(R.id.learning_outcomes_D1);


        Station_2_Overview = (EditText) findViewById(R.id.Station_2_Overview);

        For_2A = (EditText) findViewById(R.id.For_2A);
        A2_Station_Overview = (EditText) findViewById(R.id.A2_Station_Overview);
        learning_outcomes_A2 = (EditText) findViewById(R.id.learning_outcomes_A2);

        For_2B = (EditText) findViewById(R.id.For_2B);
        B2_Station_Overview = (EditText) findViewById(R.id.B2_Station_Overview);
        learning_outcomes_B2 = (EditText) findViewById(R.id.learning_outcomes_B2);

        For_2C = (EditText) findViewById(R.id.For_2C);
        C2_Station_Overview = (EditText) findViewById(R.id.C2_Station_Overview);
        learning_outcomes_C2 = (EditText) findViewById(R.id.learning_outcomes_C2);

        For_2D = (EditText) findViewById(R.id.For_2D);
        D2_Station_Overview = (EditText) findViewById(R.id.D2_Station_Overview);
        learning_outcomes_D2 = (EditText) findViewById(R.id.learning_outcomes_D2);


        Station_3_Overview = (EditText) findViewById(R.id.Station_3_Overview);

        For_3A = (EditText) findViewById(R.id.For_3A);
        A3_Station_Overview = (EditText) findViewById(R.id.A3_Station_Overview);
        learning_outcomes_A3 = (EditText) findViewById(R.id.learning_outcomes_A3);

        For_3B = (EditText) findViewById(R.id.For_3B);
        B3_Station_Overview = (EditText) findViewById(R.id.B3_Station_Overview);
        learning_outcomes_B3 = (EditText) findViewById(R.id.learning_outcomes_B3);

        For_3C = (EditText) findViewById(R.id.For_3C);
        C3_Station_Overview = (EditText) findViewById(R.id.C3_Station_Overview);
        learning_outcomes_C3 = (EditText) findViewById(R.id.learning_outcomes_C3);

        For_3D = (EditText) findViewById(R.id.For_3D);
        D3_Station_Overview = (EditText) findViewById(R.id.D3_Station_Overview);
        learning_outcomes_D3 = (EditText) findViewById(R.id.learning_outcomes_D3);

        db = FirebaseDatabase.getInstance().getReference().child("Tracks").child(track_Field).child(track_Id);


        next = (Button) findViewById(R.id.add_path_Button);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String Station_1_Overview1 = Station_1_Overview.getText().toString().trim();
                String For_1A1 = For_1A.getText().toString().trim();
                String A1_Station_Overview1 = A1_Station_Overview.getText().toString().trim();
                String learning_outcomes_A11 = learning_outcomes_A1.getText().toString().trim();
                String For_1B1 = For_1B.getText().toString().trim();
                String B1_Station_Overview1 = B1_Station_Overview.getText().toString().trim();
                String learning_outcomes_B11 = learning_outcomes_B1.getText().toString().trim();
                String For_1C1 = For_1C.getText().toString().trim();
                String C1_Station_Overview1 = C1_Station_Overview.getText().toString().trim();
                String learning_outcomes_C11 = learning_outcomes_C1.getText().toString().trim();
                String For_1D1 = For_1D.getText().toString().trim();
                String D1_Station_Overview1 = D1_Station_Overview.getText().toString().trim();
                String learning_outcomes_D11 = learning_outcomes_D1.getText().toString().trim();


                String Station_2_Overview1 = Station_2_Overview.getText().toString().trim();
                String For_2A1 = For_2A.getText().toString().trim();
                String A2_Station_Overview1 = A2_Station_Overview.getText().toString().trim();
                String learning_outcomes_A21 = learning_outcomes_A2.getText().toString().trim();
                String For_2B1 = For_2B.getText().toString().trim();
                String B2_Station_Overview1 = B2_Station_Overview.getText().toString().trim();
                String learning_outcomes_B21 = learning_outcomes_B2.getText().toString().trim();
                String For_2C1 = For_2C.getText().toString().trim();
                String C2_Station_Overview1 = C2_Station_Overview.getText().toString().trim();
                String learning_outcomes_C21 = learning_outcomes_C2.getText().toString().trim();
                String For_2D1 = For_2D.getText().toString().trim();
                String D2_Station_Overview1 = D2_Station_Overview.getText().toString().trim();
                String learning_outcomes_D21 = learning_outcomes_D2.getText().toString().trim();

                String Station_3_Overview1 = Station_3_Overview.getText().toString().trim();
                String For_3A1 = For_2A.getText().toString().trim();
                String A3_Station_Overview1 = A3_Station_Overview.getText().toString().trim();
                String learning_outcomes_A31 = learning_outcomes_A3.getText().toString().trim();
                String For_3B1 = For_2B.getText().toString().trim();
                String B3_Station_Overview1 = B3_Station_Overview.getText().toString().trim();
                String learning_outcomes_B31 = learning_outcomes_B3.getText().toString().trim();
                String For_3C1 = For_2C.getText().toString().trim();
                String C3_Station_Overview1 = C3_Station_Overview.getText().toString().trim();
                String learning_outcomes_C31 = learning_outcomes_C3.getText().toString().trim();
                String For_3D1 = For_3D.getText().toString().trim();
                String D3_Station_Overview1 = D3_Station_Overview.getText().toString().trim();
                String learning_outcomes_D31 = learning_outcomes_D3.getText().toString().trim();

                String path_id = db.push().getKey();
                db.child("Paths_List").child(path_id).child("Station_1_Overview1").setValue(Station_1_Overview1);
                db.child("Paths_List").child(path_id).child("For_1A1").setValue(For_1A1);
                db.child("Paths_List").child(path_id).child("A1_Station_Overview1").setValue(A1_Station_Overview1);
                db.child("Paths_List").child(path_id).child("learning_outcomes_A11").setValue(learning_outcomes_A11);
                db.child("Paths_List").child(path_id).child("For_1B1").setValue(For_1B1);
                db.child("Paths_List").child(path_id).child("B1_Station_Overview1").setValue(B1_Station_Overview1);
                db.child("Paths_List").child(path_id).child("learning_outcomes_B11").setValue(learning_outcomes_B11);
                db.child("Paths_List").child(path_id).child("For_1C1").setValue(For_1C1);
                db.child("Paths_List").child(path_id).child("C1_Station_Overview1").setValue(C1_Station_Overview1);
                db.child("Paths_List").child(path_id).child("learning_outcomes_C11").setValue(learning_outcomes_C11);
                db.child("Paths_List").child(path_id).child("For_1D1").setValue(For_1D1);
                db.child("Paths_List").child(path_id).child("D1_Station_Overview1").setValue(D1_Station_Overview1);
                db.child("Paths_List").child(path_id).child("learning_outcomes_D11").setValue(learning_outcomes_D11);


                db.child("Paths_List").child(path_id).child("Station_2_Overview1").setValue(Station_2_Overview1);
                db.child("Paths_List").child(path_id).child("For_2A1").setValue(For_2A1);
                db.child("Paths_List").child(path_id).child("A2_Station_Overview1").setValue(A2_Station_Overview1);
                db.child("Paths_List").child(path_id).child("learning_outcomes_A21").setValue(learning_outcomes_A21);
                db.child("Paths_List").child(path_id).child("For_2B1").setValue(For_2B1);
                db.child("Paths_List").child(path_id).child("B2_Station_Overview1").setValue(B2_Station_Overview1);
                db.child("Paths_List").child(path_id).child("learning_outcomes_B21").setValue(learning_outcomes_B21);
                db.child("Paths_List").child(path_id).child("For_2C1").setValue(For_2C1);
                db.child("Paths_List").child(path_id).child("C2_Station_Overview1").setValue(C2_Station_Overview1);
                db.child("Paths_List").child(path_id).child("learning_outcomes_C21").setValue(learning_outcomes_C21);
                db.child("Paths_List").child(path_id).child("For_2D1").setValue(For_2D1);
                db.child("Paths_List").child(path_id).child("D2_Station_Overview1").setValue(D2_Station_Overview1);
                db.child("Paths_List").child(path_id).child("learning_outcomes_D21").setValue(learning_outcomes_D21);


                db.child("Paths_List").child(path_id).child("Station_3_Overview1").setValue(Station_3_Overview1);
                db.child("Paths_List").child(path_id).child("For_3A1").setValue(For_3A1);
                db.child("Paths_List").child(path_id).child("A3_Station_Overview1").setValue(A3_Station_Overview1);
                db.child("Paths_List").child(path_id).child("learning_outcomes_A31").setValue(learning_outcomes_A31);
                db.child("Paths_List").child(path_id).child("For_3B1").setValue(For_3B1);
                db.child("Paths_List").child(path_id).child("B3_Station_Overview1").setValue(B3_Station_Overview1);
                db.child("Paths_List").child(path_id).child("learning_outcomes_B31").setValue(learning_outcomes_B31);
                db.child("Paths_List").child(path_id).child("For_3C1").setValue(For_3C1);
                db.child("Paths_List").child(path_id).child("C3_Station_Overview1").setValue(C3_Station_Overview1);
                db.child("Paths_List").child(path_id).child("learning_outcomes_C31").setValue(learning_outcomes_C31);
                db.child("Paths_List").child(path_id).child("For_3D1").setValue(For_3D1);
                db.child("Paths_List").child(path_id).child("D3_Station_Overview1").setValue(D3_Station_Overview1);
                db.child("Paths_List").child(path_id).child("learning_outcomes_D31").setValue(learning_outcomes_D31);

                path_id = "i";
                textView_4.setVisibility(View.VISIBLE);
                Skip_Path_Button.setVisibility(View.VISIBLE);


                Station_1_Overview.setText("");
                For_1A.setText("");
                A1_Station_Overview.setText("");
                learning_outcomes_A1.setText("");
                For_1B.setText("");
                B1_Station_Overview.setText("");
                learning_outcomes_B1.setText("");
                C1_Station_Overview.setText("");
                For_1C.setText("");
                learning_outcomes_C1.setText("");
                D1_Station_Overview.setText("");
                learning_outcomes_D1.setText("");
                For_1D.setText("");

                Station_2_Overview.setText("");
                For_2A.setText("");
                A2_Station_Overview.setText("");
                learning_outcomes_A2.setText("");
                For_2B.setText("");
                B2_Station_Overview.setText("");
                learning_outcomes_B2.setText("");
                C2_Station_Overview.setText("");
                For_2C.setText("");
                learning_outcomes_C2.setText("");
                D2_Station_Overview.setText("");
                learning_outcomes_D2.setText("");
                For_2D.setText("");

                Station_3_Overview.setText("");
                For_3A.setText("");
                A3_Station_Overview.setText("");
                learning_outcomes_A3.setText("");
                For_3B.setText("");
                B3_Station_Overview.setText("");
                learning_outcomes_B3.setText("");
                C3_Station_Overview.setText("");
                For_3C.setText("");
                learning_outcomes_C3.setText("");
                D3_Station_Overview.setText("");
                learning_outcomes_D3.setText("");
                For_3D.setText("");


            }
        });

        Skip_Path_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), AddTrack_JobTitleActivity.class);
                intent.putExtra("EXTRA_Track_ID", track_Id);
                intent.putExtra("EXTRA_Track_Field", track_Field);
                startActivity(intent);
                finish();
            }
        });
    }
}
