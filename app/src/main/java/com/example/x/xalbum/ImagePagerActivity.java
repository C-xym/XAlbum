package com.example.x.xalbum;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.x.xalbum.Base.ImageData;
import com.example.x.xalbum.Base.ImageScanResult;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.ArrayList;

public class ImagePagerActivity extends AppCompatActivity {

    private ArrayList<ImageData> mImageDataArrayList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);

        View decor = this.getWindow().getDecorView();
        decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        String fname=(String)getIntent().getSerializableExtra("FolderName");
        int pos = (int) getIntent().getSerializableExtra("pos");

        mImageDataArrayList= ImageScanResult.get(this).getFolderList(fname);

        ViewPager mViewPager = (ViewPager) findViewById(R.id.activity_image_view_pager);
        PagerAdapter mPreviewPagerAdapter = new PreviewPagerAdapter();
        mViewPager.setAdapter(mPreviewPagerAdapter);

        mViewPager.setCurrentItem(pos);
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
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            //PhotoView galleryPhotoView = (PhotoView) view.findViewById(R.id.iv_show_image);
            //galleryPhotoView.setScale(1.0f);//让图片在滑动过程中恢复回缩放操作前原图大小
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {

            View galleryItemView = View.inflate(ImagePagerActivity.this, R.layout.activity_image, null);

            PhotoView imageView=galleryItemView.findViewById(R.id.photo);
            ImageData mImageData=mImageDataArrayList.get(position);

            Glide.with(ImagePagerActivity.this).load(mImageData.getmPath()).into(imageView);

            container.addView(galleryItemView);
            return galleryItemView;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }
    }
}


