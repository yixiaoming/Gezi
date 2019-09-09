package org.miles.gank.xiandu;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import org.miles.gank.R;
import org.miles.gank.databinding.GankCategoryFragmentBinding;
import org.miles.lib.data.gank.entity.GankFirstCategoryEntity;
import org.miles.lib.mvvm.BaseViewModelFragment;

import java.util.List;

public class GankXianduFragment
        extends BaseViewModelFragment<GankCategoryFragmentBinding, GankXianduFragmentModel> {

    private GankTabPagerAdapter mGankTabPagerAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.gank_category_fragment;
    }

    @Override
    protected Class<GankXianduFragmentModel> getViewModelClass() {
        return GankXianduFragmentModel.class;
    }

    public static GankXianduFragment newInstance() {
        GankXianduFragment fragment = new GankXianduFragment();
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mGankTabPagerAdapter == null) {
            mGankTabPagerAdapter = new GankTabPagerAdapter(getFragmentManager());
        }
        initViews();
        initObservers();
        mModel.initDatas();
    }

    private void initViews() {
        mView.tablayout.setupWithViewPager(mView.viewapger);
        mView.viewapger.setAdapter(mGankTabPagerAdapter);
    }

    private void initObservers() {
        mModel.getGankFirstCategories().observe(this,
                new Observer<List<GankFirstCategoryEntity>>() {
                    @Override
                    public void onChanged(List<GankFirstCategoryEntity> gankFirstCategoryEntities) {
                        mGankTabPagerAdapter.setDatas(gankFirstCategoryEntities);
                    }
                });
//        mModel.getGankCategoryes().observe(this,
//                new Observer<List<GankSecondCategoryEntity>>() {
//                    @Override
//                    public void onChanged(List<GankSecondCategoryEntity> gankSecondCategoryEntities) {
//                        mGankTabPagerAdapter.setDatas(gankSecondCategoryEntities);
//                    }
//                });
    }
}
