package org.miles.gank.category;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import org.miles.kaiyan.R;
import org.miles.kaiyan.databinding.GankCategoryFragmentBinding;
import org.miles.kaiyan.databinding.GankFragmentBinding;
import org.miles.lib.data.gank.entity.GankSecondCategoryEntity;
import org.miles.lib.mvvm.BaseViewModelFragment;

import java.util.List;

public class GankCategoryFragment
        extends BaseViewModelFragment<GankCategoryFragmentBinding, GankCategoryFragmentModel> {

    private GankTabPagerAdapter mGankTabPagerAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.gank_category_fragment;
    }

    @Override
    protected Class<GankCategoryFragmentModel> getViewModelClass() {
        return GankCategoryFragmentModel.class;
    }

    public static GankCategoryFragment newInstance() {
        GankCategoryFragment fragment = new GankCategoryFragment();
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
        mModel.getGankCategoryes().observe(this,
                new Observer<List<GankSecondCategoryEntity>>() {
                    @Override
                    public void onChanged(List<GankSecondCategoryEntity> gankSecondCategoryEntities) {
                        mGankTabPagerAdapter.setDatas(gankSecondCategoryEntities);
                    }
                });
    }
}
