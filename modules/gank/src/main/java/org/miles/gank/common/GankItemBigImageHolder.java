package org.miles.gank.common;

import android.view.ViewGroup;

import androidx.annotation.NonNull;

import org.miles.gank.data.entity.GankTodayItemEntity;
import org.miles.gank.databinding.GankItemBigImgViewBinding;
import org.miles.gank.databinding.GankItemOneImgViewBinding;
import org.miles.lib.glide.GlideApp;
import org.miles.lib.mvvm.BaseRecyclerViewHolder;

public class GankItemBigImageHolder
        extends BaseRecyclerViewHolder<GankItemBigImgViewBinding, GankTodayItemEntity> {
    protected GankItemBigImageHolder(@NonNull ViewGroup parent, int layoutId) {
        super(parent, layoutId);
    }

    @Override
    public void bind(GankTodayItemEntity data) {
        mView.author.setText(data.who);
        mView.date.setText(data.createdAt);
        GlideApp.with(mView.getRoot())
                .load(data.url)
                .fitCenter()
                .into(mView.img);
    }
}
