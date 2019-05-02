package com.example.menurecommendation;

import java.util.List;

public class CuisineData {

    private int CuisineID;
    private String CuisineName;
    private String CuisineImage;

    // constructor

    public CuisineData(){}

    public CuisineData(int ID, String name, String image){
        CuisineID = ID;
        CuisineName = name;
        CuisineImage = image;
    }

    // properties

    public int getCuisineID() {
        return CuisineID;
    }

    public void setCuisineID(int cuisineID) {
        this.CuisineID = cuisineID;
    }

    public String getCuisineName() {
        return CuisineName;
    }

    public void setCuisineName(String cuisineName) { this.CuisineName = cuisineName; }

    public String getCuisineImage() {
        return CuisineImage;
    }

    public void setCuisineImage(String cuisineImage) {
        CuisineImage = cuisineImage;
    }
}
