package org.miles.gank.today;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import org.miles.gank.today.todaylist.GankTodayListFragment;

import java.util.ArrayList;
import java.util.List;

public class GankTodayPagerAdapter extends FragmentPagerAdapter {

    private List<String> mDatas = new ArrayList<>();
    private List<Fragment> mFragments = new ArrayList<>();

    public GankTodayPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position >= mFragments.size() || mFragments.get(position) == null) {
            fragment = GankTodayListFragment.newInstance(mDatas.get(position));
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mDatas.get(position);
    }

    public void setDatas(List<String> categories) {
        mDatas.clear();
        mFragments.clear();

        mDatas.addAll(categories);
        notifyDataSetChanged();
    }
}
