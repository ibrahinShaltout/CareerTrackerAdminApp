package com.example.ucgpadmin.university;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.example.ucgpadmin.R;
import java.util.ArrayList;

public class UniversityAdapter{
//    Context c;
//    ArrayList<UniversityDataClass> univertsityDataClasses;
//
//    public UniversityAdapter(Context c, ArrayList<UniversityDataClass> univertsityDataClasses) {
//        this.c = c;
//        this.univertsityDataClasses = univertsityDataClasses;
//    }
//
//    @Override
//    public int getCount() {
//        return univertsityDataClasses.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return univertsityDataClasses.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        if(convertView==null)
//        {
//            convertView= LayoutInflater.from(c).inflate(R.layout.university_item,parent,false);
//        }
//
//        TextView universityName= (TextView) convertView.findViewById(R.id.University_name_item);
//        TextView universityFiled= (TextView) convertView.findViewById(R.id.University_field_item);
//        TextView universityBio= (TextView) convertView.findViewById(R.id.University_bio_item);
//        TextView universityHashtage= (TextView) convertView.findViewById(R.id.hashtages_University_item);
//        TextView universityMoreInformation= (TextView) convertView.findViewById(R.id.University_more_Info_item);
//
//        final UniversityDataClass s= univertsityDataClasses.get(position);
//
////        StringBuilder value = new StringBuilder();
////        for (String x:s.getUniversityhashtage()) {
////            value.append(x).append(" ");
////        }
//
//        universityName.setText(s.getUniversityName());
////        universityFiled.setText(s.getTrackFiled());
//        universityBio.setText(s.getUniversityBio());
////        universityHashtage.setText(value);
//        universityMoreInformation.setText(s.getUniversityMoreInfo());
//
//        //ONITECLICK
//        convertView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(c,s.getUniversityName(),Toast.LENGTH_SHORT).show();
//            }
//        });
//        return convertView;
//    }

}
