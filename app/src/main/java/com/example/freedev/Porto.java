package com.example.freedev;

public class Porto {
    private String mTitle;
    private String mPhoto;
    public Porto() {
        //empty constructor needed
    }
    public Porto(String title, String photo) {
        if (title.trim().equals("")) {
            title = "No Title";
        }
        mTitle = title;
        mPhoto = photo;
    }
    public String getTitle() {

        return mTitle;
    }
    public void setTitle(String title) {
        mTitle = title;
    }
    public String getPhoto() {
        return mPhoto;
    }
    public void setPhoto(String photo) {
        mPhoto = photo;
    }
}


