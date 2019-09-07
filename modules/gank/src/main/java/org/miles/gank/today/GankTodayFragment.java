package org.miles.gank.today;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.miles.kaiyan.R;
import org.miles.kaiyan.databinding.GankTodayFragmentBinding;
import org.miles.lib.mvvm.BaseViewModelFragment;

public class GankTodayFragment
        extends BaseViewModelFragment<GankTodayFragmentBinding, GankTodayFragmentModel> {
    @Override
    protected int getLayoutId() {
        return R.layout.gank_today_fragment;
    }

    @Override
    protected Class<GankTodayFragmentModel> getViewModelClass() {
        return GankTodayFragmentModel.class;
    }

    public static GankTodayFragment newInstance() {
        GankTodayFragment fragment = new GankTodayFragment();
        Bundle params = new Bundle();
        // TODO: 19.9.7 add params
        fragment.setArguments(params);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
        initObservers();
    }

    private void initViews() {

    }

    private void initObservers() {

    }
}
