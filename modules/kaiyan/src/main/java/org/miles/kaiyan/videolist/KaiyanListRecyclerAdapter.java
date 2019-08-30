package org.miles.kaiyan.videolist;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.miles.kaiyan.R;
import org.miles.kaiyan.databinding.KaiyanVideoItemLayoutBinding;
import org.miles.lib.data.kaiyan.entity.KaiyanVideoItem;
import org.miles.lib.mvvm.BaseRecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

public class KaiyanListRecyclerAdapter
        extends RecyclerView.Adapter<KaiyanListRecyclerAdapter.KaiyanCategoryViewHolder> {

    private List<KaiyanVideoItem> mKaiyanVideoItems = new ArrayList<>();

    public void setDatas(List<KaiyanVideoItem> kaiyanVideoItems) {
        mKaiyanVideoItems.clear();
        mKaiyanVideoItems.addAll(kaiyanVideoItems);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public KaiyanCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new KaiyanCategoryViewHolder(parent, R.layout.kaiyan_video_item_layout);
    }

    @Override
    public void onBindViewHolder(@NonNull KaiyanCategoryViewHolder holder, int position) {
        holder.bind(mKaiyanVideoItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mKaiyanVideoItems.size();
    }

    class KaiyanCategoryViewHolder
            extends BaseRecyclerViewHolder<KaiyanVideoItemLayoutBinding, KaiyanVideoItem> {

        public KaiyanCategoryViewHolder(@NonNull ViewGroup parent, int layoutId) {
            super(parent, layoutId);
        }

        @Override
        public void bind(KaiyanVideoItem data) {
            mView.title.setText(data.data.title);
        }
    }
}
