package org.miles.gank.main;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import org.miles.kaiyan.R;
import org.miles.kaiyan.databinding.GankFragmentBinding;
import org.miles.lib.data.gank.entity.GankCategoryEntity;
import org.miles.lib.mvvm.BaseViewModelFragment;

import java.util.List;

public class GankFragment
        extends BaseViewModelFragment<GankFragmentBinding, GankFragmentModel> {

    private GankTabPagerAdapter mGankTabPagerAdapter;

    public static GankFragment newInstance() {
        GankFragment fragment = new GankFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.gank_fragment;
    }

    @Override
    protected Class<GankFragmentModel> getViewModelClass() {
        return GankFragmentModel.class;
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
                new Observer<List<GankCategoryEntity>>() {
                    @Override
                    public void onChanged(List<GankCategoryEntity> gankCategoryEntities) {
                        mGankTabPagerAdapter.setDatas(gankCategoryEntities);
                    }
                });
    }
}
