package com.example.x.xalbum;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import com.example.x.xalbum.Adapter.AlbumAdapter;
import com.example.x.xalbum.Base.ImageData;

import java.util.ArrayList;

public class AlbumActivity extends AppCompatActivity {

    private ArrayList<ImageData> mFile;
    private AlbumAdapter mAlbumAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        GridView gridView=(GridView)findViewById(R.id.img_group);

        mFile= (ArrayList<ImageData>) getIntent().getSerializableExtra("ImageFiles");

        mAlbumAdapter =new AlbumAdapter(AlbumActivity.this,mFile);

        gridView.setAdapter(mAlbumAdapter);
        mAlbumAdapter.notifyDataSetChanged();
    }
}
