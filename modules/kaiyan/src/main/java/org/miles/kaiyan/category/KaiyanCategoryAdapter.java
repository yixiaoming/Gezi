package org.miles.kaiyan.category;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import org.miles.kaiyan.R;
import org.miles.kaiyan.databinding.KaiyanVideoItemLayoutBinding;
import org.miles.lib.data.kaiyan.entity.KaiyanVideoItem;

import java.util.ArrayList;
import java.util.List;

public class KaiyanCategoryAdapter
        extends RecyclerView.Adapter<KaiyanCategoryAdapter.KaiyanCategoryViewHolder> {

    private List<KaiyanVideoItem> mKaiyanVideoItems = new ArrayList<>();

    public void setDatas(List<KaiyanVideoItem> kaiyanVideoItems) {
        mKaiyanVideoItems.clear();
        mKaiyanVideoItems.addAll(kaiyanVideoItems);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public KaiyanCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        KaiyanVideoItemLayoutBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.kaiyan_video_item_layout, parent, false);
        return new KaiyanCategoryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull KaiyanCategoryViewHolder holder, int position) {
        holder.bind(mKaiyanVideoItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mKaiyanVideoItems.size();
    }

    class KaiyanCategoryViewHolder extends RecyclerView.ViewHolder {

        private KaiyanVideoItemLayoutBinding mBinding;

        public KaiyanCategoryViewHolder(@NonNull KaiyanVideoItemLayoutBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(KaiyanVideoItem kaiyanVideoItem) {
            mBinding.title.setText(kaiyanVideoItem.data.title);
        }
    }
}
