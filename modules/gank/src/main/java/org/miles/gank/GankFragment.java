package org.miles.gank;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import org.miles.gank.databinding.GankFragmentBinding;
import org.miles.gank.xiandu.GankXianduFragment;
import org.miles.gank.today.GankTodayFragment;
import org.miles.lib.mvvm.BaseViewModelFragment;

public class GankFragment
        extends BaseViewModelFragment<GankFragmentBinding, GankFragmentModel> {

    private static final String TODAY_FRAGMENT_TAG = "today_fragment";
    private static final String CATEGORY_FRAGMENT_TAG = "category_fragment";

    private Fragment mCurrentFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.gank_fragment;
    }

    @Override
    protected Class<GankFragmentModel> getViewModelClass() {
        return GankFragmentModel.class;
    }

    public static GankFragment newInstance() {
        GankFragment fragment = new GankFragment();
        Bundle params = new Bundle();
        fragment.setArguments(params);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
        initObservers();
        showFragment(TODAY_FRAGMENT_TAG);
    }

    private void initViews() {
        mView.todayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFragment(TODAY_FRAGMENT_TAG);
            }
        });

        mView.categoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFragment(CATEGORY_FRAGMENT_TAG);
            }
        });
    }

    private void initObservers() {

    }

    private void showFragment(String fragmentTag) {
        if (getFragmentManager() == null) return;
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        Fragment willShowFragment = getFragmentManager().findFragmentByTag(fragmentTag);
        if (willShowFragment != null) {
            if (mCurrentFragment != null) {
                transaction.hide(mCurrentFragment);
            }
            transaction.show(willShowFragment);
            transaction.commit();
            mCurrentFragment = willShowFragment;
            return;
        }
        if (TODAY_FRAGMENT_TAG.equals(fragmentTag)) {
            willShowFragment = GankTodayFragment.newInstance();
            transaction.add(R.id.gank_fragment_content, willShowFragment
                    , TODAY_FRAGMENT_TAG);
        } else if (CATEGORY_FRAGMENT_TAG.equals(fragmentTag)) {
            willShowFragment = GankXianduFragment.newInstance();
            transaction.add(R.id.gank_fragment_content, willShowFragment
                    , CATEGORY_FRAGMENT_TAG);
        } else {
            throw new IllegalArgumentException(fragmentTag + " not found!");
        }
        if (mCurrentFragment != null) {
            transaction.hide(mCurrentFragment);
        }
        transaction.show(willShowFragment);
        transaction.commit();
        mCurrentFragment = willShowFragment;
    }
}
