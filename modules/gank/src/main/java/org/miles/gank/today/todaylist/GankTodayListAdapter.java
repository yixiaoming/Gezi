package org.miles.gank.today.todaylist;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.miles.gank.R;
import org.miles.gank.databinding.GankTodayItemLayoutBinding;
import org.miles.gank.data.entity.GankTodayItemEntity;
import org.miles.lib.mvvm.BaseRecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

public class GankTodayListAdapter extends RecyclerView.Adapter<GankTodayListAdapter.GankItemEntityHolder> {

    private List<GankTodayItemEntity> mDatas = new ArrayList<>();

    @NonNull
    @Override
    public GankItemEntityHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GankItemEntityHolder(parent, R.layout.gank_today_item_layout);
    }

    @Override
    public void onBindViewHolder(@NonNull GankItemEntityHolder holder, int position) {
        holder.bind(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void setDatas(List<GankTodayItemEntity> datas) {
        mDatas.clear();
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }

    class GankItemEntityHolder
            extends BaseRecyclerViewHolder<GankTodayItemLayoutBinding, GankTodayItemEntity> {

        protected GankItemEntityHolder(@NonNull ViewGroup parent, int layoutId) {
            super(parent, layoutId);
        }

        @Override
        public void bind(GankTodayItemEntity data) {
            mView.title.setText(data.desc);
        }
    }
}
