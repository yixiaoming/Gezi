package org.miles.gank.today.todaylist;

import android.view.ViewGroup;

import androidx.annotation.NonNull;

import org.miles.gank.data.entity.GankTodayItemEntity;
import org.miles.gank.databinding.GankItemNoImgViewBinding;
import org.miles.lib.mvvm.BaseRecyclerViewHolder;

public class GankItemNoImageHolder
        extends BaseRecyclerViewHolder<GankItemNoImgViewBinding, GankTodayItemEntity> {
    protected GankItemNoImageHolder(@NonNull ViewGroup parent, int layoutId) {
        super(parent, layoutId);
    }

    @Override
    public void bind(GankTodayItemEntity data) {
        mView.desc.setText(data.desc);
        mView.author.setText(data.who);
        mView.date.setText(data.createdAt);
    }
}
