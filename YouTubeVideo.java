package com.example.myapp;

import com.google.gson.annotations.SerializedName;

public class YouTubeVideo {
    private String title, url, id;

    public YouTubeVideo(){
        this.title = title;
        this.url = url;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getid() {
        return id;
    }

    public void setid(String id) {
        this.id = id;
    }

}

