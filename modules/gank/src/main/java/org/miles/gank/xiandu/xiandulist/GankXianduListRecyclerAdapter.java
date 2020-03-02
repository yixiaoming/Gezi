package org.miles.gank.xiandu.xiandulist;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.miles.gank.R;
import org.miles.gank.data.entity.GankCategoryItemEntity;
import org.miles.gank.databinding.GankXianduItemOneImgViewBinding;
import org.miles.lib.glide.GlideApp;
import org.miles.lib.mvvm.BaseRecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

public class GankXianduListRecyclerAdapter
        extends RecyclerView.Adapter<BaseRecyclerViewHolder> {

    public static final int ITEMVIEW_TYPE_ONE_IMG = 1;

    private List<GankCategoryItemEntity> mDatas = new ArrayList<>();
    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(GankXianduListRecyclerAdapter.OnItemClickListener listener){
        mOnItemClickListener = listener;
    }

    public abstract static class OnItemClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            onItemClicked((GankCategoryItemEntity) view.getTag());
        }

        public abstract void onItemClicked(GankCategoryItemEntity entity);
    }

    @NonNull
    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case ITEMVIEW_TYPE_ONE_IMG:
                return new GankCategoryItemOneImageHolder(parent, R.layout.gank_xiandu_item_one_img_view);
        }
        throw new IllegalArgumentException("Wrong item view type!");
    }

    @Override
    public void onBindViewHolder(@NonNull BaseRecyclerViewHolder holder, int position) {
        holder.bind(mDatas.get(position));
        holder.itemView.setTag(mDatas.get(position));
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(mOnItemClickListener);
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public int getItemViewType(int position) {
        return ITEMVIEW_TYPE_ONE_IMG;
    }

    public void setDatas(List<GankCategoryItemEntity> datas) {
        mDatas.clear();
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }

    static class GankCategoryItemOneImageHolder
            extends BaseRecyclerViewHolder<GankXianduItemOneImgViewBinding, GankCategoryItemEntity> {
        protected GankCategoryItemOneImageHolder(@NonNull ViewGroup parent, int layoutId) {
            super(parent, layoutId);
        }

        @Override
        public void bind(GankCategoryItemEntity data) {
            mView.desc.setText(data.title);
            mView.author.setText(data.site.name);
            mView.date.setText(data.publishedAt);
            GlideApp.with(mView.getRoot())
                    .load(data.cover)
                    .error(R.drawable.place_holder_img)
                    .fitCenter()
                    .into(mView.img);
        }
    }
}
