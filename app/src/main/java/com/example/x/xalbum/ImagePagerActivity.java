package com.example.x.xalbum;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ActionBarContextView;

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
    private int Pos;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);




        mImageDataArrayList= (ArrayList<ImageData>) getIntent().getSerializableExtra("PhotoFile");
        Pos=(int)getIntent().getSerializableExtra("pos");


        mViewPager=(ViewPager)findViewById(R.id.activity_image_view_pager);
        mPreviewPagerAdapter = new PreviewPagerAdapter();
        mViewPager.setAdapter(mPreviewPagerAdapter);

        mViewPager.setCurrentItem(Pos);

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


