package org.miles.gank.xiandu.xiandulist;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import org.miles.kaiyan.R;
import org.miles.kaiyan.databinding.GankCategoryListFragmentBinding;
import org.miles.lib.data.gank.entity.GankCategoryItemEntity;
import org.miles.lib.mvvm.BaseViewModelFragment;

import java.util.List;

public class GankXianduListFragment
        extends BaseViewModelFragment<GankCategoryListFragmentBinding, GankXianduListFragmentModel> {

    public static final String PARAM_CATEGORY = "param_category_type";
    private String mCategory;
    private GankXianduListRecyclerAdapter mGankXianduListRecyclerAdapter;

    public static GankXianduListFragment newInstance(String category) {
        GankXianduListFragment fragment = new GankXianduListFragment();
        Bundle params = new Bundle();
        params.putString(PARAM_CATEGORY, category);
        fragment.setArguments(params);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.gank_category_list_fragment;
    }

    @Override
    protected Class<GankXianduListFragmentModel> getViewModelClass() {
        return GankXianduListFragmentModel.class;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle params = getArguments();
        if (params != null) {
            mCategory = params.getString(PARAM_CATEGORY);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
        initObservers();
        mModel.initDatas(mCategory, 1);
    }

    private void initViews() {
        if (mGankXianduListRecyclerAdapter == null) {
            mGankXianduListRecyclerAdapter = new GankXianduListRecyclerAdapter();
        }
        mView.recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        mView.recyclerview.setAdapter(mGankXianduListRecyclerAdapter);
    }

    private void initObservers() {
        mModel.getGankEntities().observe(
                this, new Observer<List<GankCategoryItemEntity>>() {
                    @Override
                    public void onChanged(List<GankCategoryItemEntity> gankEntities) {
                        if (gankEntities == null || gankEntities.size() == 0) {
                            // TODO: 19.9.5 显示空view
                        } else {
                            mGankXianduListRecyclerAdapter.setDatas(gankEntities);
                        }
                    }
                }
        );
    }
}
