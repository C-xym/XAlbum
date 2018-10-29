package com.example.x.xalbum;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.x.xalbum.Base.ImageData;


public class ImageActivity extends AppCompatActivity {//del

    private ImageData mImageData;
    private ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        ImageView imageView=findViewById(R.id.photo);

        //TextView textView=findViewById(R.id.temp);

        mImageData=(ImageData) getIntent().getSerializableExtra("PhotoFile");

        //textView.setText(mImageData.getmTitle());

        Glide.with(this).load(mImageData.getmPath()).into(imageView);
    }
}
