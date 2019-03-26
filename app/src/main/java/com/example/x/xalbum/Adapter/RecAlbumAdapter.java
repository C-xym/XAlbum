package com.example.x.xalbum.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;

import com.bumptech.glide.Glide;
import com.example.x.xalbum.Base.ImageData;
import com.example.x.xalbum.ImagePagerActivity;
import com.example.x.xalbum.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecAlbumAdapter extends RecyclerView.Adapter<RecAlbumAdapter.albumHoder> {

    private ArrayList<ImageData> mFiles;
    private Context mContext;

    public RecAlbumAdapter(Context context,ArrayList<ImageData> list){
        mContext=context;
        mFiles=list;
    }


    @NonNull
    @Override
    public albumHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item, parent, false);
        return new albumHoder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull albumHoder holder, final int position) {
        Glide.with(mContext).load(mFiles.get(position).getmPath()).into(holder.imageView);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ImagePagerActivity.class);
                intent.putExtra("PhotoFile", mFiles);//pager
                intent.putExtra("pos",position);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mFiles.size();
    }

    public static class albumHoder extends RecyclerView.ViewHolder{

        ImageView imageView;
        public albumHoder(@NonNull View itemView) {
            super(itemView);
            imageView=(ImageView)itemView.findViewById(R.id.img);
        }
    }
}
