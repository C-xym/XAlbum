package com.example.x.xalbum.Base;



import android.util.Log;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ImageData implements Serializable {

    private String mName;
    private String mTitle;
    private String mPath;

    private int mSize;
    private long mAddTime;
    private long mModifiedTime;
    private int mHeight;
    private int mWidth;

    private String AddDate;
    private String mDateYM;

    private int TYPE;
    public static final int TYPE_IMG=0;
    public static final int TYPE_DATE=1;
    private int pos;

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public ImageData(){
        TYPE=TYPE_IMG;
    }

    public ImageData(String date)
    {
        this.mDateYM=date;
        this.TYPE=TYPE_DATE;
    }


    public int getTYPE() {
        return TYPE;
    }

    public void setTYPE() {
        this.TYPE = TYPE_DATE;
    }

    public void setmDateYM(String mDateYM) {
        this.mDateYM = mDateYM;
    }

    public String getAddDate() {
        return AddDate;
    }

    public String getmDateYM() {
        return mDateYM;
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

    public int getmSize() {
        return mSize;
    }

    public void setmSize(int mSize) {
        this.mSize = mSize;
    }

    public long getmAddTime() {
        return mAddTime;
    }

    public void setmAddTime(long mAddTime) {
        this.mAddTime = mAddTime;

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:MM", Locale.CHINA);
        this.AddDate=format.format(mAddTime*1000);

        this.mDateYM=AddDate.substring(0,7);
        Log.i("--------ym",mDateYM);
    }

    public long getmModifiedTime() {
        return mModifiedTime;
    }

    public void setmModifiedTime(long mModifiedTime) {
        this.mModifiedTime = mModifiedTime;
    }

    public int getmHeight() {
        return mHeight;
    }

    public void setmHeight(int mHeight) {
        this.mHeight = mHeight;
    }

    public int getmWidth() {
        return mWidth;
    }

    public void setmWidth(int mWidth) {
        this.mWidth = mWidth;
    }

}
