package com.example.internship_task_1;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class   photo {
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("url_s")
    @Expose
    private String url_s;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl_s() {
        return url_s;
    }

    public void setUrl_s(String url_s) {
        this.url_s = url_s;
    }

    @Override
    public String toString() {
        return "photo{" +
                "title='" + title + '\'' +
                ", url_s='" + url_s + '\'' +
                '}';
    }
}
