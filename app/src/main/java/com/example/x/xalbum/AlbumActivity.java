package com.example.x.xalbum;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.GridView;

import com.example.x.xalbum.Adapter.AlbumAdapter;
import com.example.x.xalbum.Base.ImageData;

import java.util.ArrayList;

public class AlbumActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        View decor = this.getWindow().getDecorView();
        decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        GridView gridView=(GridView)findViewById(R.id.img_group);

        ArrayList<ImageData> mFile = (ArrayList<ImageData>) getIntent().getSerializableExtra("ImageFiles");

        AlbumAdapter mAlbumAdapter = new AlbumAdapter(AlbumActivity.this, mFile);
        gridView.setAdapter(mAlbumAdapter);
        mAlbumAdapter.notifyDataSetChanged();
    }
}
