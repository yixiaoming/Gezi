package org.miles.gank.today;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import org.miles.gank.R;
import org.miles.gank.databinding.GankTodayFragmentBinding;
import org.miles.lib.mvvm.BaseViewModelFragment;

import java.util.List;

public class GankTodayFragment
        extends BaseViewModelFragment<GankTodayFragmentBinding, GankTodayFragmentModel> {

    private GankTodayPagerAdapter mGankTodayPagerAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.gank_today_fragment;
    }

    @Override
    protected Class<GankTodayFragmentModel> getViewModelClass() {
        return GankTodayFragmentModel.class;
    }

    public static GankTodayFragment newInstance() {
        GankTodayFragment fragment = new GankTodayFragment();
        Bundle params = new Bundle();
        // TODO: 19.9.7 add params
        fragment.setArguments(params);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
        initObservers();
        mModel.initDatas();
    }

    private void initViews() {
        if (mGankTodayPagerAdapter == null) {
            mGankTodayPagerAdapter = new GankTodayPagerAdapter(getFragmentManager());
        }
        mView.todayViewpager.setAdapter(mGankTodayPagerAdapter);
        mView.tablayout.setupWithViewPager(mView.todayViewpager);
    }

    private void initObservers() {
        mModel.getCategories().observe(this,
                new Observer<List<String>>() {
                    @Override
                    public void onChanged(List<String> categories) {
                        mGankTodayPagerAdapter.setDatas(categories);
                    }
                });
    }
}
