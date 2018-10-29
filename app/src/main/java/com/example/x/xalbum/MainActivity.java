package com.example.x.xalbum;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.example.x.xalbum.Adapter.MainAdapter;
import com.example.x.xalbum.Base.ImageFolder;
import com.example.x.xalbum.Base.ImageScanResult;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private MainAdapter mainAdapter;
    private List<ImageFolder> mAlbumList;//文件夹
    private ListView ItemView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ItemView =(ListView)findViewById(R.id.item_view);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1 );
        }else{
            openAlbum();
        }//权限
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {//权限
        switch (requestCode){
            case 1:
                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    openAlbum();
                }else{
                    Toast.makeText(this,"You denied the permission",Toast.LENGTH_SHORT).show();//没有权限
                }
                break;
            default:
        }
    }

    private void openAlbum(){
        ImageScanResult imageScanResult=new ImageScanResult(this);
        mAlbumList=imageScanResult.getAlbumList();
        mainAdapter =new MainAdapter(MainActivity.this,mAlbumList);

        ItemView.setAdapter(mainAdapter);
        mainAdapter.notifyDataSetChanged();
    }
}
