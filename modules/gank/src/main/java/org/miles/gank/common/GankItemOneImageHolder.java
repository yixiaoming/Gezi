package org.miles.gank.common;

import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;

import org.miles.gank.data.entity.GankTodayItemEntity;
import org.miles.gank.databinding.GankItemNoImgViewBinding;
import org.miles.gank.databinding.GankItemOneImgViewBinding;
import org.miles.lib.glide.GlideApp;
import org.miles.lib.mvvm.BaseRecyclerViewHolder;

public class GankItemOneImageHolder
        extends BaseRecyclerViewHolder<GankItemOneImgViewBinding, GankTodayItemEntity> {
    protected GankItemOneImageHolder(@NonNull ViewGroup parent, int layoutId) {
        super(parent, layoutId);
    }

    @Override
    public void bind(GankTodayItemEntity data) {
        mView.desc.setText(data.desc);
        mView.author.setText(data.who);
        mView.date.setText(data.createdAt);
        GlideApp.with(mView.getRoot())
                .load(data.images.get(0))
                .fitCenter()
                .into(mView.img);
    }
}
