package com.example.ucgpadmin.dataclass;

import java.util.ArrayList;

public class UniversityDataClass {

    private String universityName;
    private String universityID;
    private String universityWebsite;
    private String universityPhone;
    private String universityType;
    private String universityHeadquarters;
    private String universityOverview;
    private String universityBranches;
    private String UniversityBio;
    private String universityFoundation;
    private String universityLocation;
    public String imageURL;

//    private String universityFieldOverview;
    private String universityCollegeField;
    private ArrayList college_specialization;


    public String getUniversityID() {
        return universityID;
    }

    public void setUniversityID(String universityID) {
        this.universityID = universityID;
    }

    public String getUniversityCollegeField() {
        return universityCollegeField;
    }

    public void setUniversityCollegeField(String universityCollegeField) {
        this.universityCollegeField = universityCollegeField;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }


    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public String getUniversityWebsite() {
        return universityWebsite;
    }

    public void setUniversityWebsite(String universityWebsite) {
        this.universityWebsite = universityWebsite;
    }

    public String getUniversityPhone() {
        return universityPhone;
    }

    public void setUniversityPhone(String universityPhone) {
        this.universityPhone = universityPhone;
    }

    public String getUniversityType() {
        return universityType;
    }

    public void setUniversityType(String universityType) {
        this.universityType = universityType;
    }

    public String getUniversityHeadquarters() {
        return universityHeadquarters;
    }

    public void setUniversityHeadquarters(String universityHeadquarters) {
        this.universityHeadquarters = universityHeadquarters;
    }

    public String getUniversityOverview() {
        return universityOverview;
    }

    public void setUniversityOverview(String universityOverview) {
        this.universityOverview = universityOverview;
    }

//    public String getUniversityFieldOverview() {
//        return universityFieldOverview;
//    }
//
//    public void setUniversityFieldOverview(String universityFieldOverview) {
//        this.universityFieldOverview = universityFieldOverview;
//    }

    public String getUniversityBranches() {
        return universityBranches;
    }

    public void setUniversityBranches(String universityBranches) {
        this.universityBranches = universityBranches;
    }

    public String getUniversityBio() {
        return UniversityBio;
    }

    public void setUniversityBio(String universityBio) {
        UniversityBio = universityBio;
    }

    public String getUniversityFoundation() {
        return universityFoundation;
    }

    public void setUniversityFoundation(String universityFoundation) {
        this.universityFoundation = universityFoundation;
    }

    public String getUniversityLocation() {
        return universityLocation;
    }

    public void setUniversityLocation(String universityLocation) {
        this.universityLocation = universityLocation;
    }

    public ArrayList getCollege_specialization() {
        return college_specialization;
    }

    public void setCollege_specialization(ArrayList college_specialization) {
        this.college_specialization = college_specialization;
    }


}
