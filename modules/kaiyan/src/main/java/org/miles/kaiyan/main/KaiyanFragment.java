package org.miles.kaiyan.main;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Observer;

import org.miles.kaiyan.R;
import org.miles.kaiyan.databinding.KaiyanListFragmentBinding;
import org.miles.kaiyan.category.KaiyanCategoryFragment;
import org.miles.lib.data.kaiyan.entity.KaiyanCategory;
import org.miles.lib.mvvm.BaseViewModelFragment;

import java.util.ArrayList;
import java.util.List;

public class KaiyanFragment
        extends BaseViewModelFragment<KaiyanListFragmentBinding, KaiyanFragmentModel> {

    private static final String TAG = "KaiyanFragment";

    private ViewPagerAdapter mViewPagerAdapter;

    public static KaiyanFragment newInstance() {
        KaiyanFragment fragment = new KaiyanFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.kaiyan_list_fragment;
    }

    @Override
    protected Class<KaiyanFragmentModel> getViewModelClass() {
        return KaiyanFragmentModel.class;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mViewPagerAdapter == null) {
            mViewPagerAdapter = new ViewPagerAdapter(getFragmentManager());
        }
        mBinding.tablayout.setupWithViewPager(mBinding.viewpager);
        mBinding.viewpager.setAdapter(mViewPagerAdapter);
        mModel.getKaiyanCategories().observe(this, new Observer<List<KaiyanCategory>>() {
            @Override
            public void onChanged(List<KaiyanCategory> kaiyanCategories) {
                mViewPagerAdapter.setDatas(kaiyanCategories);
            }
        });

        mModel.initDatas();
    }

    public static class ViewPagerAdapter extends FragmentPagerAdapter {

        private List<KaiyanCategory> mKaiyanCategories = new ArrayList<>();
        private List<Fragment> mFragments;

        public ViewPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mKaiyanCategories.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return mKaiyanCategories.get(position).name;
        }

        public void setDatas(List<KaiyanCategory> kaiyanCategories) {
            mKaiyanCategories = kaiyanCategories;
            mFragments = new ArrayList<>();
            for (KaiyanCategory category : mKaiyanCategories) {
                mFragments.add(KaiyanCategoryFragment.newInstance(category));
            }
            notifyDataSetChanged();
        }
    }
}
