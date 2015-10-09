package com.bawanj.fresconews.model;


import java.io.Serializable;

public class GalleryItem implements Serializable {

    // personal information
    private String mFirstName;
    private String mLastName;
    private String mAvatarUrl;
    // opinions

    private String mAddress;
    private String mHeaderUrl; // image
    private String mTime;
    private String mCaption;


    public GalleryItem(String firstName, String lastName, String avatarUrl
            , String address, String time, String headerUrl, String caption){


        mFirstName= firstName;
        mLastName = lastName;
        mAvatarUrl = avatarUrl;

        mAddress = address;
        mTime= time;
        mHeaderUrl = headerUrl; // image
        mCaption=caption;
    }



    public String getAvatarUrl() {
        return mAvatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        mAvatarUrl = avatarUrl;
    }

    public String getCaption() {
        return mCaption;
    }

    public void setCaption(String caption) {
        mCaption = caption;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public String getHeaderUrl() {
        return mHeaderUrl;
    }

    public void setHeaderUrl(String headerUrl) {
        mHeaderUrl = headerUrl;
    }

    public String getTime() {
        return mTime;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public void setTime(String time) {
        mTime = time;
    }
}
