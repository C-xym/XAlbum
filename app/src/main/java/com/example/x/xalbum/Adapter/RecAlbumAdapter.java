package com.example.x.xalbum.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.x.xalbum.Base.ImageData;
import com.example.x.xalbum.ImagePagerActivity;
import com.example.x.xalbum.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RecAlbumAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int TYPE_IMG=0;
    public static final int TYPE_DATE=1;

    private ArrayList<ImageData> mFiles;
    private String FolderName;
    private Context mContext;

    public RecAlbumAdapter(Context context,ArrayList<ImageData> list,String folderName){
        mContext=context;
        mFiles=list;
        FolderName=folderName;
    }

    @Override
    public int getItemViewType(int position) {
        return mFiles.get(position).getTYPE();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==TYPE_IMG)
        {
             View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item, parent, false);
             return new ImgHolder(v);
        }
        else
        {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.date_item, parent, false);
            return new DateHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ImgHolder)
        {
            Glide.with(mContext).load(mFiles.get(position).getmPath()).into(((ImgHolder) holder).imageView);
            final int pos=mFiles.get(position).getPos();
            ((ImgHolder) holder).imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, ImagePagerActivity.class);
                    intent.putExtra("FolderName",FolderName);
                    intent.putExtra("pos",pos);
                    mContext.startActivity(intent);
                }
            });
        }
        else if(holder instanceof DateHolder)
        {
            ((DateHolder) holder).textView.setText(mFiles.get(position).getmDateYM());
        }

    }

    @Override
    public int getItemCount() {
        return mFiles.size();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager layoutManager=recyclerView.getLayoutManager();
        if(layoutManager instanceof GridLayoutManager){
            final GridLayoutManager manager=((GridLayoutManager)recyclerView.getLayoutManager());
            manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if(getItemViewType(position)==TYPE_DATE)
                        return manager.getSpanCount();
                    return manager.getSpanCount()/3;
                }
            });
        }
    }

    class DateHolder extends RecyclerView.ViewHolder{

        TextView textView;
         public DateHolder(@NonNull View itemView) {
             super(itemView);
             textView=(TextView)itemView.findViewById(R.id.item_date);
         }
     }

     class ImgHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        public ImgHolder(@NonNull View itemView) {
            super(itemView);
            imageView=(ImageView)itemView.findViewById(R.id.img);
        }
    }
}
