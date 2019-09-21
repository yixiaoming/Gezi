package org.miles.gank.common;

import android.text.TextUtils;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.miles.gank.R;
import org.miles.gank.data.entity.GankTodayItemEntity;
import org.miles.lib.mvvm.BaseRecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

public class GankRecyclerAdapter extends RecyclerView.Adapter<BaseRecyclerViewHolder> {

    public static final int ITEMVIEW_TYPE_ONE_IMG = 1;
    public static final int ITEMVIEW_TYPE_NO_IMG = 2;
    public static final int ITEMVIEW_TYPE_THREE_IMG = 3;
    public static final int ITEMVIEW_TYPE_BIG_IMG = 4;
    public static final int ITEMVIEW_TYPE_VIDEO = 5;

    private List<GankTodayItemEntity> mDatas = new ArrayList<>();

    @NonNull
    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case ITEMVIEW_TYPE_NO_IMG:
                return new GankItemNoImageHolder(parent, R.layout.gank_item_no_img_view);
            case ITEMVIEW_TYPE_ONE_IMG:
                return new GankItemOneImageHolder(parent, R.layout.gank_item_one_img_view);
            case ITEMVIEW_TYPE_THREE_IMG:
                return new GankItemThreeImageHolder(parent, R.layout.gank_item_three_img_view);
            case ITEMVIEW_TYPE_BIG_IMG:
                return new GankItemBigImageHolder(parent, R.layout.gank_item_big_img_view);
            case ITEMVIEW_TYPE_VIDEO:
                return new GankItemVideoHolder(parent, R.layout.gank_item_video_view);
        }
        throw new IllegalArgumentException("Wrong item view type!");
    }

    @Override
    public void onBindViewHolder(@NonNull BaseRecyclerViewHolder holder, int position) {
        holder.bind(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public int getItemViewType(int position) {
        GankTodayItemEntity entity = mDatas.get(position);
        if (TextUtils.equals(entity.type, Constrants.CATEGORY_FULI)) {
            return ITEMVIEW_TYPE_BIG_IMG;
        }
        if (TextUtils.equals(entity.type, Constrants.CATEGORY_VIDEO)) {
            return ITEMVIEW_TYPE_VIDEO;
        }

        if (entity.images == null || entity.images.size() == 0) {
            return ITEMVIEW_TYPE_NO_IMG;
        } else if (entity.images.size() < 3) {
            return ITEMVIEW_TYPE_ONE_IMG;
        } else {
            return ITEMVIEW_TYPE_THREE_IMG;
        }
    }

    public void setDatas(List<GankTodayItemEntity> datas) {
        mDatas.clear();
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }
}
