package org.miles.kaiyan.videolist;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import org.miles.kaiyan.R;
import org.miles.kaiyan.databinding.KaiyanCategoryFragmentBinding;
import org.miles.lib.data.kaiyan.entity.KaiyanCategory;
import org.miles.lib.data.kaiyan.entity.KaiyanVideoItem;
import org.miles.lib.mvvm.BaseViewModelFragment;

import java.util.List;

public class KaiyanListFragment
        extends BaseViewModelFragment<KaiyanCategoryFragmentBinding, KaiyanListFragmentModel> {

    public static final String TAG = "KaiyanListFragment";

    public static final String PARAM_CATEGORY_ID = "id";

    private long mCategoryId;

    private KaiyanListRecyclerAdapter mKaiyanVideoListAdapter;

    public static KaiyanListFragment newInstance(KaiyanCategory category) {
        KaiyanListFragment fragment = new KaiyanListFragment();
        fragment.setArguments(new Bundle());
        fragment.getArguments().putLong(PARAM_CATEGORY_ID, category.id);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.kaiyan_category_fragment;
    }

    @Override
    protected Class<KaiyanListFragmentModel> getViewModelClass() {
        return KaiyanListFragmentModel.class;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assert getArguments() != null;
        mCategoryId = getArguments().getLong(PARAM_CATEGORY_ID);
        mModel.setCategoryId(mCategoryId);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mKaiyanVideoListAdapter == null) {
            mKaiyanVideoListAdapter = new KaiyanListRecyclerAdapter();
        }
        mBinding.recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        mBinding.recyclerview.setAdapter(mKaiyanVideoListAdapter);
        mModel.getKaiyanVideoDatas().observe(this, new Observer<List<KaiyanVideoItem>>() {
            @Override
            public void onChanged(List<KaiyanVideoItem> kaiyanVideoItems) {
                if (kaiyanVideoItems == null || kaiyanVideoItems.size() == 0) {
                    mBinding.emptyView.setVisibility(View.VISIBLE);
                    mBinding.recyclerview.setVisibility(View.GONE);
                    return;
                }
                mBinding.emptyView.setVisibility(View.GONE);
                mBinding.recyclerview.setVisibility(View.VISIBLE);
                mKaiyanVideoListAdapter.setDatas(kaiyanVideoItems);
            }
        });

        mModel.initDatas();
    }
}
