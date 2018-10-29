package com.example.x.xalbum.Base;



import java.io.Serializable;

public class ImageData implements Serializable {

    private String mName;
    private String mTitle;
    private String mPath;
    private String mSize;
    private String mAddTime;
    private String mModifiedTime;
    private String mHeight;
    private String mWidth;

    private String mLabel;//图片识别标签


    public ImageData(){

        mLabel="photo";//del


    }

    public String getmLabel() {
        return mLabel;
    }

    public void setmLabel(String mLabel) {//
        this.mLabel = mLabel;
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
