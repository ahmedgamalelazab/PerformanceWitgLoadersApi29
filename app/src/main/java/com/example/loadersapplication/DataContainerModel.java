package com.example.loadersapplication;

public class DataContainerModel {
    //we will fetch some data from out api so we have to preset the stuff here
    private String mMag;
    private String mPlae;
    private String mURL;
    private long mTime;
    //end of the private data section

    //section of constructors
    public DataContainerModel(String mag, String place, String url, long Time) {
        mMag = mag;
        mPlae = place;
        mURL = url;
        mTime = Time;
    }

    ;
    //end of constructors section


    //section of getters  , i don't need setters by the way
    public String getMagStrength() {
        return mMag;
    }

    ;

    public String getPlace() {
        return mPlae;
    }

    ;

    public String getURL() {
        return mURL;
    }

    ;

    public long getTime() {
        return mTime;
    }

    ;
    //end of the getters section

};
