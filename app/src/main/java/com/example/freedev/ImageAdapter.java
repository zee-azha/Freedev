package com.example.freedev;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter <ImageAdapter.ImageViewHolder>{
    @NonNull
    private Context mContex;
    private List<Porto> mPorto;
    public ImageAdapter(Context context,List<Porto> porto){
        mContex =context;
        mPorto = porto;
    }
    @Override
        public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContex).inflate(R.layout.porto_item,parent,false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        Porto portoCurrent = mPorto.get(position);
        holder.textViewTitle.setText(portoCurrent.getTitle());
        Picasso.with(mContex)
                .load(portoCurrent.getPhoto())
                .placeholder(R.mipmap.ic_launcher_round)
                .fit()
                .centerCrop()
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mPorto.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewTitle;
        public ImageView imageView;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
            imageView = itemView.findViewById(R.id.image_view);
        }
    }
}
