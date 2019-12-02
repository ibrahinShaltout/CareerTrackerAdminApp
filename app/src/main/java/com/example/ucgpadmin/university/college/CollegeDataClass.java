package com.example.ucgpadmin.university.college;

import java.util.ArrayList;

public class CollegeDataClass {
    String college_Overview;
    String college_Name;
    ArrayList <String> college_Specialization_List;

    public String getCollege_Overview() {
        return college_Overview;
    }

    public void setCollege_Overview(String college_Overview) {
        this.college_Overview = college_Overview;
    }

    public String getCollege_Name() {
        return college_Name;
    }

    public void setCollege_Name(String college_Name) {
        this.college_Name = college_Name;
    }

    public ArrayList<String> getCollege_Specialization_List() {
        return college_Specialization_List;
    }

    public void setCollege_Specialization_List(ArrayList<String> college_Specialization_List) {
        this.college_Specialization_List = college_Specialization_List;
    }
}
