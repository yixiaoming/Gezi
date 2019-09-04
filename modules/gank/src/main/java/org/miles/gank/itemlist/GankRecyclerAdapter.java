package org.miles.gank.itemlist;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.miles.kaiyan.R;
import org.miles.kaiyan.databinding.GankItemLayoutBinding;
import org.miles.lib.data.gank.entity.GankEntity;
import org.miles.lib.mvvm.BaseRecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

public class GankRecyclerAdapter
        extends RecyclerView.Adapter<GankRecyclerAdapter.GankViewHolder> {

    private List<GankEntity> mDatas = new ArrayList<>();

    public void setDatas(List<GankEntity> datas) {
        mDatas.clear();
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GankViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GankViewHolder(parent, R.layout.gank_item_layout);
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
            extends BaseRecyclerViewHolder<GankItemLayoutBinding, GankEntity> {

        public GankViewHolder(@NonNull ViewGroup parent, int layoutId) {
            super(parent, layoutId);
        }

        @Override
        public void bind(GankEntity data) {
            mView.title.setText(data.desc);
        }
    }
}
