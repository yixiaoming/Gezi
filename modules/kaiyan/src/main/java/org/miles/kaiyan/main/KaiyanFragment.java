package org.miles.kaiyan.main;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import org.miles.kaiyan.R;
import org.miles.kaiyan.databinding.KaiyanFragmentBinding;
import org.miles.kaiyan.data.entity.KaiyanCategory;
import org.miles.lib.mvvm.BaseViewModelFragment;

import java.util.List;

public class KaiyanFragment
        extends BaseViewModelFragment<KaiyanFragmentBinding, KaiyanFragmentModel> {

    private static final String TAG = "KaiyanFragment";

    private KaiyanPagerAdapter mViewPagerAdapter;

    public static KaiyanFragment newInstance() {
        KaiyanFragment fragment = new KaiyanFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.kaiyan_fragment;
    }

    @Override
    protected Class<KaiyanFragmentModel> getViewModelClass() {
        return KaiyanFragmentModel.class;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mViewPagerAdapter == null) {
            mViewPagerAdapter = new KaiyanPagerAdapter(getFragmentManager());
        }
        mView.tablayout.setupWithViewPager(mView.kaiyanViewpager);
        mView.kaiyanViewpager.setAdapter(mViewPagerAdapter);
        mModel.getKaiyanCategories().observe(this, new Observer<List<KaiyanCategory>>() {
            @Override
            public void onChanged(List<KaiyanCategory> kaiyanCategories) {
                if (kaiyanCategories == null || kaiyanCategories.size() == 0) {
                    mView.emptyView.setVisibility(View.VISIBLE);
                    mView.kaiyanViewpager.setVisibility(View.GONE);
                    return;
                }
                mView.emptyView.setVisibility(View.GONE);
                mView.kaiyanViewpager.setVisibility(View.VISIBLE);
                mViewPagerAdapter.setDatas(kaiyanCategories);
            }
        });

        mModel.initDatas();
    }
}
