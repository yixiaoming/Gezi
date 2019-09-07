package org.miles.gank.category;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import org.miles.gank.category.categorylist.GankCategoryListFragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GankTabPagerAdapter extends FragmentPagerAdapter {

    private List<String> mGankCategories;
    private List<GankCategoryListFragment> mGankCategoryListFragments;

    public GankTabPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
        mGankCategories = new ArrayList<>();
        mGankCategoryListFragments = new ArrayList<>();
    }

    public void setDatas(List<String> gankCategories) {
        mGankCategories.clear();
        mGankCategoryListFragments.clear();

        mGankCategories.addAll(gankCategories);
        mGankCategoryListFragments.addAll(Collections.<GankCategoryListFragment>nCopies(mGankCategories.size(), null));
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (mGankCategoryListFragments.get(position) == null) {
            String category = mGankCategories.get(position);
            GankCategoryListFragment fragment = GankCategoryListFragment.newInstance(category);
            mGankCategoryListFragments.add(position, fragment);
        }
        return mGankCategoryListFragments.get(position);
    }

    @Override
    public int getCount() {
        return mGankCategories.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mGankCategories.get(position);
    }
}
