package com.example.x.xalbum.Base;



import android.util.Log;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class ImageData implements Serializable {

    private String mName;
    private String mTitle;
    private String mPath;
    private String mSize;
    private String mAddTime;
    private String mModifiedTime;
    private String mHeight;
    private String mWidth;


    public ImageData(){

    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmPath() {
        return mPath;
    }

    public void setmPath(String mPath) {
        this.mPath = mPath;
    }

    public String getmSize() {
        return mSize;
    }

    public void setmSize(String mSize) {
        this.mSize = mSize;
    }

    public String getmAddTime() {
        return mAddTime;
    }

    public void setmAddTime(String mAddTime) {
        this.mAddTime = mAddTime;
    }

    public String getmModifiedTime() {
        return mModifiedTime;
    }

    public void setmModifiedTime(String mModifiedTime) {
        this.mModifiedTime = mModifiedTime;

        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM", Locale.CHINA);
        String dateStr = dateformat.format(System.currentTimeMillis());
        Log.i("----------------------a",dateStr);

    }

    public String getmHeight() {
        return mHeight;
    }

    public void setmHeight(String mHeight) {
        this.mHeight = mHeight;
    }

    public String getmWidth() {
        return mWidth;
    }

    public void setmWidth(String mWidth) {
        this.mWidth = mWidth;
    }

}
