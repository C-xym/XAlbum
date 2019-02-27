package com.example.x.xalbum;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.ListView;
import android.widget.Toast;


import com.example.x.xalbum.Adapter.MainAdapter;
import com.example.x.xalbum.Base.ImageFolder;
import com.example.x.xalbum.Base.ImageScanResult;
import com.example.x.xalbum.Base.PermissionSetting;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private MainAdapter mainAdapter;
    private List<ImageFolder> mAlbumList;//文件夹
    private ListView ItemView;

    private PermissionSetting mPermissionSetting;

    private String[] Permissions=new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ItemView =(ListView)findViewById(R.id.item_view);


        /*
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1 );
        }else{
            openAlbum();
        }//权限
        */
        checkPermission();
    }

    private void checkPermission(){
        List<String> mPermissionList = new ArrayList<>();
        for (int i = 0; i < Permissions.length; i++) {
            if (ContextCompat.checkSelfPermission(MainActivity.this, Permissions[i]) != PackageManager.PERMISSION_GRANTED) {
                mPermissionList.add(Permissions[i]);
            }
        }
        if (!mPermissionList.isEmpty()) {
            String[] permissions = mPermissionList.toArray(new String[mPermissionList.size()]);//将List转为数组
            ActivityCompat.requestPermissions(MainActivity.this, permissions, 1);
        }
        else {
            openAlbum();
        }
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
