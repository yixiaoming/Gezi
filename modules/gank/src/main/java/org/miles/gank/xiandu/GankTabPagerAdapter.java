package org.miles.gank.xiandu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import org.miles.gank.xiandu.xiandulist.GankXianduListFragment;
import org.miles.lib.data.gank.entity.GankSecondCategoryEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GankTabPagerAdapter extends FragmentPagerAdapter {

    private List<GankSecondCategoryEntity> mGankCategories;
    private List<GankXianduListFragment> mGankXianduListFragments;

    public GankTabPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
        mGankCategories = new ArrayList<>();
        mGankXianduListFragments = new ArrayList<>();
    }

    public void setDatas(List<GankSecondCategoryEntity> gankCategories) {
        mGankCategories.clear();
        mGankXianduListFragments.clear();

        mGankCategories.addAll(gankCategories);
        mGankXianduListFragments.addAll(Collections.<GankXianduListFragment>nCopies(mGankCategories.size(), null));
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (mGankXianduListFragments.get(position) == null) {
            GankSecondCategoryEntity entity = mGankCategories.get(position);
            GankXianduListFragment fragment = GankXianduListFragment.newInstance(entity.categoryId);
            mGankXianduListFragments.add(position, fragment);
        }
        return mGankXianduListFragments.get(position);
    }

    @Override
    public int getCount() {
        return mGankCategories.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mGankCategories.get(position).title;
    }
}
