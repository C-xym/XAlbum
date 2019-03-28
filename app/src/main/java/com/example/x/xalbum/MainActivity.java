package com.example.x.xalbum;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ListView;
import android.widget.Toast;


import com.example.x.xalbum.Adapter.MainAdapter;
import com.example.x.xalbum.Base.ImageFolder;
import com.example.x.xalbum.Base.ImageScanResult;
import com.example.x.xalbum.Base.PermissionSetting;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private ListView ItemView;

    private String[] Permissions=new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View decor = this.getWindow().getDecorView();
        decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);///View.SYSTEM_UI_FLAG_LAYOUT_STABLE   View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        ItemView =(ListView)findViewById(R.id.item_view);

        checkPermission();
    }

    private void checkPermission(){
        List<String> mPermissionList = new ArrayList<>();
        for (String Permission : Permissions) {
            if (ContextCompat.checkSelfPermission(MainActivity.this, Permission) != PackageManager.PERMISSION_GRANTED) {
                mPermissionList.add(Permission);
            }
        }
        if (!mPermissionList.isEmpty()) {
            String[] permissions = mPermissionList.toArray(new String[0]);//将List转为数组
            ActivityCompat.requestPermissions(MainActivity.this, permissions, 1);
        }
        else {
            openAlbum();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    openAlbum();
                }else{
                    Toast.makeText(this,"You denied the permission",Toast.LENGTH_SHORT).show();//没有权限
                    PermissionSetting mPermissionSetting=new PermissionSetting(MainActivity.this);
                    mPermissionSetting.start();//跳转应用权限设置界面
                }
                break;
            default:
        }
    }

    private void openAlbum(){

        List<ImageFolder> mAlbumList = ImageScanResult.get(this).getAlbumList();
        MainAdapter mainAdapter = new MainAdapter(MainActivity.this, mAlbumList);

        ItemView.setAdapter(mainAdapter);
        mainAdapter.notifyDataSetChanged();
    }
}
