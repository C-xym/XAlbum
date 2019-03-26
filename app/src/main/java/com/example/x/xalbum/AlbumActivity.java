package com.example.x.xalbum;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.GridView;

import com.example.x.xalbum.Adapter.AlbumAdapter;
import com.example.x.xalbum.Adapter.RecAlbumAdapter;
import com.example.x.xalbum.Base.ImageData;

import java.util.ArrayList;

public class AlbumActivity extends AppCompatActivity {

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        View decor = this.getWindow().getDecorView();
        decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        ArrayList<ImageData> mFile = (ArrayList<ImageData>) getIntent().getSerializableExtra("ImageFiles");


        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.rec);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3, OrientationHelper.VERTICAL,false));
        recyclerView.setAdapter(new RecAlbumAdapter(AlbumActivity.this,mFile));


    }
}
