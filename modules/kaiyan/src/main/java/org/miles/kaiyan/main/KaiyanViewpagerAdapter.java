package org.miles.kaiyan.main;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import org.miles.kaiyan.videolist.KaiyanListFragment;
import org.miles.lib.data.kaiyan.entity.KaiyanCategory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KaiyanViewpagerAdapter extends FragmentPagerAdapter {

    private List<KaiyanCategory> mKaiyanCategories = new ArrayList<>();
    private List<Fragment> mFragments = new ArrayList<>();

    public KaiyanViewpagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = mFragments.get(position);
        if (fragment == null) {
            fragment = KaiyanListFragment.newInstance(mKaiyanCategories.get(position));
            mFragments.add(fragment);
        }
        return fragment;
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
        mKaiyanCategories.clear();
        mFragments.clear();

        mKaiyanCategories.addAll(kaiyanCategories);
        mFragments.addAll(Collections.<Fragment>nCopies(kaiyanCategories.size(), null));
        notifyDataSetChanged();
    }
}