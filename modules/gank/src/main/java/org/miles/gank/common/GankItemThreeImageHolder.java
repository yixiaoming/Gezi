package org.miles.gank.common;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.target.Target;

import org.miles.gank.R;
import org.miles.gank.data.entity.GankTodayItemEntity;
import org.miles.gank.databinding.GankItemOneImgViewBinding;
import org.miles.gank.databinding.GankItemThreeImgViewBinding;
import org.miles.lib.glide.GlideApp;
import org.miles.lib.glide.GlideRequestListener;
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
                .error(R.drawable.place_holder_img)
                .fitCenter()
                .into(mView.img1);
        GlideApp.with(mView.getRoot())
                .load(data.images.get(1))
                .error(R.drawable.place_holder_img)
                .fitCenter()
                .into(mView.img2);
        GlideApp.with(mView.getRoot())
                .load(data.images.get(2))
                .error(R.drawable.place_holder_img)
                .fitCenter()
                .into(mView.img3);
    }
}
