package org.miles.gank.common;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.target.Target;

import org.miles.gank.R;
import org.miles.gank.data.entity.GankTodayItemEntity;
import org.miles.gank.databinding.GankItemBigImgViewBinding;
import org.miles.gank.databinding.GankItemOneImgViewBinding;
import org.miles.lib.glide.GlideApp;
import org.miles.lib.glide.GlideRequestListener;
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
                .error(R.drawable.place_holder_img)
                .fitCenter()
                .into(mView.img);
    }
}
