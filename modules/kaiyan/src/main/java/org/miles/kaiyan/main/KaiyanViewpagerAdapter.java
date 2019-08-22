package org.miles.kaiyan.main;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import org.miles.kaiyan.category.KaiyanCategoryFragment;
import org.miles.lib.data.kaiyan.entity.KaiyanCategory;

import java.util.ArrayList;
import java.util.List;

public class KaiyanViewpagerAdapter extends FragmentPagerAdapter {

    private List<KaiyanCategory> mKaiyanCategories = new ArrayList<>();
    private List<Fragment> mFragments;

    public KaiyanViewpagerAdapter(@NonNull FragmentManager fm) {
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