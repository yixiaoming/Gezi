package org.miles.gank.main;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import org.miles.gank.itemlist.GankTabFragment;
import org.miles.lib.data.gank.entity.GankCategoryEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GankTabPagerAdapter extends FragmentPagerAdapter {

    private List<GankCategoryEntity> mGankCategories;
    private List<GankTabFragment> mGankTabFragments;

    public GankTabPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
        mGankCategories = new ArrayList<>();
        mGankTabFragments = new ArrayList<>();
    }

    public void setDatas(List<GankCategoryEntity> gankCategories) {
        mGankCategories.clear();
        mGankTabFragments.clear();

        mGankCategories.addAll(gankCategories);
        mGankTabFragments.addAll(Collections.<GankTabFragment>nCopies(mGankCategories.size(), null));
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (mGankTabFragments.get(position) == null) {
            GankCategoryEntity entity = mGankCategories.get(position);
            GankTabFragment fragment = GankTabFragment.newInstance(entity);
            mGankTabFragments.add(position, fragment);
        }
        return mGankTabFragments.get(position);
    }

    @Override
    public int getCount() {
        return mGankCategories.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mGankCategories.get(position).name;
    }
}
