package org.miles.gank.today.todaylist;

import android.view.ViewGroup;

import androidx.annotation.NonNull;

import org.miles.gank.data.entity.GankTodayItemEntity;
import org.miles.gank.databinding.GankItemOneImgViewBinding;
import org.miles.gank.databinding.GankItemThreeImgViewBinding;
import org.miles.lib.glide.GlideApp;
import org.miles.lib.mvvm.BaseRecyclerViewHolder;

public class GankItemThreeImageHolder
        extends BaseRecyclerViewHolder<GankItemThreeImgViewBinding, GankTodayItemEntity> {
    protected GankItemThreeImageHolder(@NonNull ViewGroup parent, int layoutId) {
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
                .into(mView.img1);
        GlideApp.with(mView.getRoot())
                .load(data.images.get(1))
                .fitCenter()
                .into(mView.img2);
        GlideApp.with(mView.getRoot())
                .load(data.images.get(2))
                .fitCenter()
                .into(mView.img3);
    }
}
