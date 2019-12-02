package com.example.ucgpadmin.corporate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ucgpadmin.MainActivity;
import com.example.ucgpadmin.R;

public class CorporateActivity extends AppCompatActivity {
   Button addCorporate,viewCorporate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corporate);

        addCorporate = (Button)findViewById(R.id.add_corporate);
        viewCorporate = (Button)findViewById(R.id.view_corporate);

        addCorporate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CorporateActivity.this, AddCorporateActivity.class));
            }
        });


        viewCorporate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(MainActivity.this, ViewC.class));

            }
        });
    }
}
