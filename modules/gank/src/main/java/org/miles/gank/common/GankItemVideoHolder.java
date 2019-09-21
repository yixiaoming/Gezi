package org.miles.gank.common;

import android.view.ViewGroup;

import androidx.annotation.NonNull;

import org.miles.gank.data.entity.GankTodayItemEntity;
import org.miles.gank.databinding.GankItemNoImgViewBinding;
import org.miles.gank.databinding.GankItemVideoViewBinding;
import org.miles.lib.glide.GlideApp;
import org.miles.lib.mvvm.BaseRecyclerViewHolder;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

public class GankItemVideoHolder
        extends BaseRecyclerViewHolder<GankItemVideoViewBinding, GankTodayItemEntity> {
    protected GankItemVideoHolder(@NonNull ViewGroup parent, int layoutId) {
        super(parent, layoutId);
    }

    @Override
    public void bind(GankTodayItemEntity data) {
        mView.author.setText(data.who);
        mView.date.setText(data.createdAt);

        mView.video.setUp(data.url, data.desc);
        mView.video.setKeepScreenOn(false);
    }
}
