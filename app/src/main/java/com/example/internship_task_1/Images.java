package com.example.internship_task_1;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Images {
    @SerializedName("photos")
    @Expose
   private Data photos;

    public Data getPhotos() {
        return photos;
    }

    public void setPhotos(Data photos) {
        this.photos = photos;
    }

    @Override
    public String toString() {
        return "Images{" +
                "photos=" + photos +
                '}';
    }
}
