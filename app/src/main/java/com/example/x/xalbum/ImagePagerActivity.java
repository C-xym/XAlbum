package com.example.x.xalbum;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.x.xalbum.Base.ImageData;

import java.util.ArrayList;

public class ImagePagerActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private PagerAdapter mPreviewPagerAdapter;
    private ArrayList<ImageData> mImageDataArrayList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);



        mImageDataArrayList= (ArrayList<ImageData>) getIntent().getSerializableExtra("PhotoFile");

        mViewPager=(ViewPager)findViewById(R.id.activity_image_view_pager);
        mPreviewPagerAdapter = new PreviewPagerAdapter();
        mViewPager.setAdapter(mPreviewPagerAdapter);

    }
    private class PreviewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            if (mImageDataArrayList == null) {
                return 0;
            }
            return mImageDataArrayList.size();
        }


        @Override
        public boolean isViewFromObject(View view, Object object) {
            //PhotoView galleryPhotoView = (PhotoView) view.findViewById(R.id.iv_show_image);
            //galleryPhotoView.setScale(1.0f);//让图片在滑动过程中恢复回缩放操作前原图大小
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View galleryItemView = View.inflate(ImagePagerActivity.this, R.layout.activity_image, null);

            ImageView imageView=galleryItemView.findViewById(R.id.photo);
            ImageData mImageData=mImageDataArrayList.get(position);

            TextView textView=galleryItemView.findViewById(R.id.photo_label);
            textView.setText(mImageData.getmLabel());

            Glide.with(ImagePagerActivity.this).load(mImageData.getmPath()).into(imageView);

            container.addView(galleryItemView);
            return galleryItemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}


