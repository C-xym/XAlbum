package com.example.x.xalbum.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.x.xalbum.AlbumActivity;
import com.example.x.xalbum.Base.ImageData;
import com.example.x.xalbum.Base.ImageFolder;
import com.example.x.xalbum.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainAdapter extends BaseAdapter {

    private ArrayList<ImageFolder> mAlbumList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public MainAdapter(Context context, List<ImageFolder> AlbumList){
        this.mContext=context;
        this.mAlbumList= (ArrayList<ImageFolder>) AlbumList;
        mLayoutInflater=LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        if(mAlbumList==null){
            return 0;
        }else {
            return mAlbumList.size();
        }
    }

    @Override
    public Object getItem(int position) {
        return mAlbumList.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView=  mLayoutInflater.inflate(R.layout.menu_item,null,false);
            holder = new ViewHolder();
            holder.ivAlbumCover = (ImageView) convertView.findViewById(R.id.item_img);
            holder.tvDirectoryName = (TextView) convertView.findViewById(R.id.item_name);
            holder.tvCount=(TextView)convertView.findViewById(R.id.item_count);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final ArrayList<ImageData> mAlbum=mAlbumList.get(position).getmImageDataArrayList();
        String folderName = mAlbumList.get(position).getmTitle();
        String folderCount= String.valueOf(mAlbumList.get(position).getmCount());

        Glide.with(mContext).load(mAlbum.get(0).getmPath()).into(holder.ivAlbumCover);
        holder.tvDirectoryName.setText(folderName);
        holder.tvCount.setText(folderCount);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, AlbumActivity.class);
                intent.putExtra("ImageFiles",mAlbum);//final
                mContext.startActivity(intent);
            }
        });
        return convertView;
    }

    //holder持有者 类
    private static class ViewHolder {
        ImageView ivAlbumCover;
        TextView tvDirectoryName;
        TextView tvCount;
    }
}
