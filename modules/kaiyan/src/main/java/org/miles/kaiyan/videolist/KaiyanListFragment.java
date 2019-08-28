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

    public static final String PARAM_CATEGORY_ID = "id";
    public static final String PARAM_CATEGORY_NAME = "name";

    private long mCategoryId;
    private String mCategoryName;

    private KaiyanListRecyclerAdapter mKaiyanVideoListAdapter;

    public static KaiyanListFragment newInstance(KaiyanCategory category) {
        KaiyanListFragment fragment = new KaiyanListFragment();
        fragment.setArguments(new Bundle());
        fragment.getArguments().putLong(PARAM_CATEGORY_ID, category.id);
        fragment.getArguments().putString(PARAM_CATEGORY_NAME, category.name);
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
        mCategoryId = getArguments().getLong(PARAM_CATEGORY_ID);
        mCategoryName = getArguments().getString(PARAM_CATEGORY_NAME);
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
                mKaiyanVideoListAdapter.setDatas(kaiyanVideoItems);
            }
        });

        mModel.initDatas();
    }
}
