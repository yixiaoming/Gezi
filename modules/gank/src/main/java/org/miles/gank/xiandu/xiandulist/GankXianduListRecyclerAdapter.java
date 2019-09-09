package org.miles.gank.xiandu.xiandulist;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.miles.gank.R;
import org.miles.gank.databinding.GankCategoryItemLayoutBinding;
import org.miles.lib.data.gank.entity.GankCategoryItemEntity;
import org.miles.lib.mvvm.BaseRecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

public class GankXianduListRecyclerAdapter
        extends RecyclerView.Adapter<GankXianduListRecyclerAdapter.GankViewHolder> {

    private List<GankCategoryItemEntity> mDatas = new ArrayList<>();

    public void setDatas(List<GankCategoryItemEntity> datas) {
        mDatas.clear();
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GankViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GankViewHolder(parent, R.layout.gank_category_item_layout);
    }

    @Override
    public void onBindViewHolder(@NonNull GankViewHolder holder, int position) {
        holder.bind(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class GankViewHolder
            extends BaseRecyclerViewHolder<GankCategoryItemLayoutBinding, GankCategoryItemEntity> {

        public GankViewHolder(@NonNull ViewGroup parent, int layoutId) {
            super(parent, layoutId);
        }

        @Override
        public void bind(GankCategoryItemEntity data) {
            mView.title.setText(data.title);
        }
    }
}
