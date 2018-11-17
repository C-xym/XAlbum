package com.example.x.xalbum.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.x.xalbum.Base.ImageData;
import com.example.x.xalbum.ImageActivity;
import com.example.x.xalbum.ImagePagerActivity;
import com.example.x.xalbum.R;

import java.util.ArrayList;

public class AlbumAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<ImageData> mFiles;
    private LayoutInflater mLayoutInflater;

    public AlbumAdapter(Context context, ArrayList<ImageData> File){
        this.mContext=context;
        mFiles=File;
        mLayoutInflater=LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mFiles.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView=  mLayoutInflater.inflate(R.layout.image_item,null,false);
            holder = new ViewHolder();
            holder.img = (ImageView) convertView.findViewById(R.id.img);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ImageData mImageDate=mFiles.get(position);

        final int pos=position;


        Glide.with(mContext).load(mImageDate.getmPath()).into(holder.img);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ImagePagerActivity.class);
                intent.putExtra("PhotoFile", mFiles);//pager
                intent.putExtra("pos",pos);
                mContext.startActivity(intent);
            }
        });

        return convertView;
    }

    //holder持有者
    private static class ViewHolder {
        ImageView img;
    }
}
