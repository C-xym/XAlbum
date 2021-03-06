package com.example.x.xalbum.Base;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class ImageScanResult {

    private static ImageScanResult imageScanResult;

    private final static String[] IMAGE_PROJECTION = new String[]{
            MediaStore.Images.Media.DATA,//图片路径
            MediaStore.Images.Media.DISPLAY_NAME,//图片文件名，包括后缀名
            MediaStore.Images.Media.TITLE,//图片文件名，不包含后缀

            MediaStore.Images.Media.DATE_ADDED,//添加时间
            MediaStore.Images.Media.DATE_MODIFIED,//最后修改时间
            MediaStore.Images.Media.HEIGHT,//The height of the image/video in pixels.
            MediaStore.Images.Media.WIDTH,//
            MediaStore.Images.Media.SIZE,//size of file in bytes
    };

    private Context mContext;
    private ArrayList<ImageFolder> mAlbumList;//文件夹,路径表

    public static ImageScanResult get(Context context)
    {
        if(imageScanResult==null)
        {
            imageScanResult=new ImageScanResult(context);
        }
        return imageScanResult;
    }

    public ArrayList<ImageFolder> getAlbumList(){
        return mAlbumList;
    }

    public ArrayList<ImageData> getFolderList(String fname)
    {
        for(ImageFolder c:mAlbumList)
        {
            if(fname.equals(c.getmTitle()))
            {
                return c.getmImageDataArrayList();
            }
        }
        return null;
    }

    public ArrayList<ImageData> getFolderList_date(String fname)
    {
        ArrayList<ImageData> list=new ArrayList<>();
        String mDate;
        int Pos=0;
        for(ImageFolder c:mAlbumList)
        {
            if(fname.equals(c.getmTitle()))
            {
                ArrayList<ImageData> arrayList=c.getmImageDataArrayList();
                mDate=arrayList.get(0).getmDateYM();

                ImageData imageData=new ImageData(mDate);
                list.add(imageData);

                for(ImageData x:arrayList)
                {
                    if(mDate.equals(x.getmDateYM()))
                    {
                        x.setPos(Pos);
                        Pos++;
                        list.add(x);
                    }
                    else
                    {
                        mDate=x.getmDateYM();
                        imageData=new ImageData(mDate);
                        list.add(imageData);
                        x.setPos(Pos);
                        Pos++;
                        list.add(x);
                    }
                }
                return list;
            }
        }
        return null;
    }

    private ImageScanResult(Context context){
        this.mContext=context;
        ImageScan();
    }

    private void ImageScan(){
        Cursor cursor=null;
        try{
            cursor=mContext.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    IMAGE_PROJECTION,
                    MediaStore.Images.Media.MIME_TYPE + "=? or "
                            + MediaStore.Images.Media.MIME_TYPE + "=?",
                    new String[] { "image/jpeg", "image/png" },
                    null);
            if(cursor!=null){
                int n=IMAGE_PROJECTION.length;
                int dataColumnIndex[]=new int[n];
                for(int i=0;i<n;i++){
                    dataColumnIndex[i] = cursor.getColumnIndex(IMAGE_PROJECTION[i]);
                }

                ArrayList<File> FolderList=new ArrayList<>();
                ArrayList<ImageFolder> albumFolderList = new ArrayList<>();
                HashMap<String, ArrayList<ImageData>> albumImageListMap = new HashMap<>();
                while (cursor.moveToNext()){//读取每一项

                    File imageFile = new File(cursor.getString(dataColumnIndex[0]));//图片文件

                    ImageData mImageData=new ImageData();

                    mImageData.setmPath(cursor.getString(dataColumnIndex[0]));
                    mImageData.setmName(cursor.getString(dataColumnIndex[1]));
                    mImageData.setmTitle(cursor.getString(dataColumnIndex[2]));
                    mImageData.setmAddTime(cursor.getLong(dataColumnIndex[3]));
                    mImageData.setmModifiedTime(cursor.getLong(dataColumnIndex[4]));

                    mImageData.setmHeight(cursor.getInt(dataColumnIndex[5]));
                    mImageData.setmWidth(cursor.getInt(dataColumnIndex[6]));
                    mImageData.setmSize(cursor.getInt(dataColumnIndex[7]));

                    File albumFolder = imageFile.getParentFile();//图片目录
                    String albumPath = albumFolder.getAbsolutePath();

                    if (!FolderList.contains(albumFolder)) {
                        FolderList.add(albumFolder);
                    }

                    ArrayList<ImageData> albumImageFiles = albumImageListMap.get(albumPath);
                    if (albumImageFiles == null) {
                        albumImageFiles = new ArrayList<>();
                        albumImageListMap.put(albumPath, albumImageFiles);
                    }
                    albumImageFiles.add(mImageData);//添加到对应的相册目录下面
                }

                for(File C:FolderList){
                    ImageFolder mImageFoler=new ImageFolder();
                    mImageFoler.setmTitle(C.getName());
                    mImageFoler.setmPath(C.getPath());
                    albumFolderList.add(mImageFoler);
                }

                Set<String> keySet = albumImageListMap.keySet();
                for (String key : keySet) {//对图片目录下所有的图片文件做排序
                    ArrayList<ImageData> albumImageList = albumImageListMap.get(key);
                    sortByFileLastModified(albumImageList);
                }

                for(ImageFolder C:albumFolderList){
                    C.setmCount(albumImageListMap.get(C.getmPath()).size());
                    C.setmImageDataArrayList(albumImageListMap.get(C.getmPath()));
                }
                sortByFileCount(albumFolderList);//对图片目录做排序

                mAlbumList=albumFolderList;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(cursor!=null){
                cursor.close();//关闭Cursor
            }
        }
    }

    private void sortByFileLastModified(List<ImageData> files) {
        Collections.sort(files, new Comparator<ImageData>() {
            @Override
            public int compare(ImageData o1, ImageData o2) {
                if (o1.getmModifiedTime() > o2.getmModifiedTime()) {
                    return -1;
                } else if (o1.getmModifiedTime() < o2.getmModifiedTime()) {
                    return 1;
                }
                return 0;
            }
        });
    }

    //数量大小排序
    private void sortByFileCount(List<ImageFolder> files) {
        Collections.sort(files, new Comparator<ImageFolder>() {
            @Override
            public int compare(ImageFolder o1, ImageFolder o2) {
                if (o1.getmCount() > o2.getmCount()) {
                    return -1;
                } else if (o1.getmCount()<o2.getmCount()) {
                    return 1;
                }
                return 0;
            }
        });
    }
}
