package org.miles.kaiyan.videolist;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.miles.kaiyan.R;
import org.miles.kaiyan.databinding.KaiyanVideoItemLayoutBinding;
import org.miles.kaiyan.data.entity.KaiyanVideoItem;
import org.miles.lib.glide.GlideApp;
import org.miles.lib.mvvm.BaseRecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

public class KaiyanListRecyclerAdapter
        extends RecyclerView.Adapter<KaiyanListRecyclerAdapter.KaiyanCategoryViewHolder> {

    private List<KaiyanVideoItem> mDatas = new ArrayList<>();

    public void setDatas(List<KaiyanVideoItem> kaiyanVideoItems) {
        mDatas = kaiyanVideoItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public KaiyanCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new KaiyanCategoryViewHolder(parent, R.layout.kaiyan_video_item_layout);
    }

    @Override
    public void onBindViewHolder(@NonNull KaiyanCategoryViewHolder holder, int position) {
        holder.bind(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class KaiyanCategoryViewHolder
            extends BaseRecyclerViewHolder<KaiyanVideoItemLayoutBinding, KaiyanVideoItem> {

        public KaiyanCategoryViewHolder(@NonNull ViewGroup parent, int layoutId) {
            super(parent, layoutId);
        }

        @Override
        public void bind(KaiyanVideoItem item) {
            GlideApp.with(mView.getRoot())
                    .load(item.data.author.icon)
                    .error(R.drawable.place_holder_img)
                    .fitCenter()
                    .into(mView.authorLogo);
            mView.video.setUp(item.data.playUrl, item.data.title);
            GlideApp.with(mView.getRoot())
                    .load(item.data.cover.detail)
                    .error(R.drawable.place_holder_img)
                    .fitCenter()
                    .into(mView.video.ivThumb);
            mView.author.setText(item.data.author.name);
            mView.date.setText(item.data.date);
        }
    }
}
