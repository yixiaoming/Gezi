package org.miles.kaiyan.main;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import org.miles.kaiyan.R;
import org.miles.kaiyan.databinding.KaiyanFragmentBinding;
import org.miles.lib.data.kaiyan.entity.KaiyanCategory;
import org.miles.lib.mvvm.BaseViewModelFragment;

import java.util.List;

public class KaiyanFragment
        extends BaseViewModelFragment<KaiyanFragmentBinding, KaiyanFragmentModel> {

    private static final String TAG = "KaiyanFragment";

    private KaiyanViewpagerAdapter mViewPagerAdapter;

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
            mViewPagerAdapter = new KaiyanViewpagerAdapter(getFragmentManager());
        }
        mBinding.tablayout.setupWithViewPager(mBinding.viewpager);
        mBinding.viewpager.setAdapter(mViewPagerAdapter);
        mModel.getKaiyanCategories().observe(this, new Observer<List<KaiyanCategory>>() {
            @Override
            public void onChanged(List<KaiyanCategory> kaiyanCategories) {
                if (kaiyanCategories == null || kaiyanCategories.size() == 0) {
                    mBinding.emptyView.setVisibility(View.VISIBLE);
                    mBinding.viewpager.setVisibility(View.GONE);
                    return;
                }
                mBinding.emptyView.setVisibility(View.GONE);
                mBinding.viewpager.setVisibility(View.VISIBLE);
                mViewPagerAdapter.setDatas(kaiyanCategories);
            }
        });

        mModel.initDatas();
    }
}
