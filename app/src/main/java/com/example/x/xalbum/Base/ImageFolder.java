package com.example.x.xalbum.Base;

import java.io.File;
import java.util.ArrayList;

public class ImageFolder {

    private long mCount;
    private String mTitle;
    private String mPath;
    private ArrayList<ImageData> mImageDataArrayList;

    public ImageFolder(){

    }

    public ArrayList<ImageData> getmImageDataArrayList() {
        return mImageDataArrayList;
    }

    public void setmImageDataArrayList(ArrayList<ImageData> mImageDataArrayList) {
        this.mImageDataArrayList = mImageDataArrayList;
    }

    public String getmPath() {
        return mPath;
    }

    public void setmPath(String mPath) {
        this.mPath = mPath;
    }



    public long getmCount() {
        return mCount;
    }

    public void setmCount(long mCount) {
        this.mCount = mCount;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }
}
