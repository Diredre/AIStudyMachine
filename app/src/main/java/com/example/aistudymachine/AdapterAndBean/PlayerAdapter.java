package com.example.aistudymachine.AdapterAndBean;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.aistudymachine.widget.MyJzvdStd;

import cn.jzvd.JzvdStd;
import com.example.aistudymachine.R;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.ViewHolder> {

    private List<PlayerBean> urlList;
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private MyJzvdStd itemplay_videoplayer;
        private TextView itemplay_tv_auther, itemplay_tv_con;

        public ViewHolder(View itemView) {
            super(itemView);
            itemplay_videoplayer = itemView.findViewById(R.id.itemplay_videoplayer);
            itemplay_tv_auther = itemView.findViewById(R.id.itemplay_tv_auther);
            itemplay_tv_con = itemView.findViewById(R.id.itemplay_tv_con);
        }
    }

    public PlayerAdapter(Context context, List<PlayerBean> urlList) {
        this.context = context;
        this.urlList = urlList;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video_play,
                parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull PlayerAdapter.ViewHolder holder, int position) {
        PlayerBean url = urlList.get(position % urlList.size());
        //????????????+??????
        holder.itemplay_tv_con.setText(url.getCon());
        holder.itemplay_tv_auther.setText(url.getAuther());
        //????????????
        /*Glide.with(holder.itemplay_videoplayer.getContext())
                .load(url.getImageUrl())
                .into(holder.itemplay_videoplayer.thumbImageView);*/
        holder.itemplay_videoplayer.thumbImageView.setImageBitmap(getBitmapFormUrl(url.getVideoUrl()));
        //??????
        holder.itemplay_videoplayer.WIFI_TIP_DIALOG_SHOWED = true;          //?????????????????????WIFIDialog??????
        holder.itemplay_videoplayer.setUp(url.getVideoUrl(), "", JzvdStd.SCREEN_NORMAL);
        holder.itemplay_videoplayer.setVideoImageDisplayType(1);
        holder.itemplay_videoplayer.thumbImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        holder.itemplay_videoplayer.startButton.performClick();             //????????????
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }


    /**
     * ??????????????????
     */
    public Bitmap getBitmapFormUrl(String url) {
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        retriever.setDataSource(url);
        //getFrameAtTime()--->???setDataSource()???????????????????????? ?????????????????????????????????????????????????????????????????????
        // ????????????????????????????????????????????????????????????????????????????????????
        Bitmap bitmap = retriever.getFrameAtTime();
        retriever.release();
        return bitmap;
    }
}