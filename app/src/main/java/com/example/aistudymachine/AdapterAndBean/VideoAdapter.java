package com.example.aistudymachine.AdapterAndBean;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.aistudymachine.Acts.VideoPlayActivity;
import com.example.aistudymachine.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder>{

    private List<PlayerBean> videoBeanList;
    private Context context;
    private boolean isLike = false;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        CardView item_video_cv;
        TextView userId, title, con;
        ImageView userIcon, like;

        public ViewHolder(View v){
            super(v);
            this.item_video_cv = v.findViewById(R.id.item_video_cv);
            this.userId = v.findViewById(R.id.item_video_tv_name);
            this.userIcon = v.findViewById(R.id.item_video_iv_cover);
            this.title = v.findViewById(R.id.item_video_tv_title);
            this.like = v.findViewById(R.id.item_video_iv_like);
            this.con = v.findViewById(R.id.item_video_tv_con);
        }
    }

    public VideoAdapter(Context context, List<PlayerBean> videoBeanList) {
        this.context = context;
        this.videoBeanList = videoBeanList;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);

        viewHolder.item_video_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = viewHolder.getAdapterPosition() + 1;
                //Toast.makeText(v.getContext(), "第" + pos + "位", Toast.LENGTH_SHORT).show();
                //Intent intent = new Intent(v.getContext(), VideoPlayActivity.class);
                //context.startActivity(intent);
            }
        });
        viewHolder.like.setColorFilter(Color.parseColor("#8A8A8A"));
        viewHolder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isLike){
                    isLike = false;
                    viewHolder.like.setColorFilter(Color.parseColor("#8A8A8A"));
                }else {
                    isLike = true;
                    viewHolder.like.setColorFilter(Color.parseColor("#FF5C5C"));
                }
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull VideoAdapter.ViewHolder holder, int position) {
        PlayerBean videoBean = videoBeanList.get(position);
        holder.userId.setText(videoBean.getAuther());
        holder.title.setText(videoBean.getAuther());
        holder.con.setText(videoBean.getCon());
        Glide.with(context)
                .load(videoBean.getImageUrl())
                .into(holder.userIcon);
    }

    @Override
    public int getItemCount() {
        return videoBeanList.size();
    }
}
