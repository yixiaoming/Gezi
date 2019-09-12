package org.miles.kaiyan.main;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import org.miles.kaiyan.videolist.KaiyanListFragment;
import org.miles.kaiyan.data.entity.KaiyanCategory;

import java.util.ArrayList;
import java.util.List;

public class KaiyanPagerAdapter extends FragmentStatePagerAdapter {

    private List<KaiyanCategory> mDatas = new ArrayList<>();
    private List<Fragment> mFragments = new ArrayList<>();

    public KaiyanPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position >= mFragments.size() || mFragments.get(position) == null) {
            Fragment fragment = KaiyanListFragment.newInstance(mDatas.get(position));
            mFragments.add(fragment);
            return fragment;
        }
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mDatas.get(position).name;
    }

    public void setDatas(List<KaiyanCategory> kaiyanCategories) {
        mDatas = kaiyanCategories;
        mFragments.clear();

        notifyDataSetChanged();
    }
}