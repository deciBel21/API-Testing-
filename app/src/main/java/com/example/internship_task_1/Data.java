package com.example.internship_task_1;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Data {
    @SerializedName("photo")
    @Expose
    private ArrayList <photo> photo;

    public ArrayList<photo> getPhoto() {
        return photo;
    }

    public void setPhoto(ArrayList<photo> photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Data{" +
                "photo=" + photo +
                '}';
    }
}
