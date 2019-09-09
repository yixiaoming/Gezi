package org.miles.gank.today.todaylist;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import org.miles.gank.R;
import org.miles.gank.databinding.GankTodayListFragmentBinding;
import org.miles.lib.data.gank.entity.GankTodayItemEntity;
import org.miles.lib.mvvm.BaseViewModelFragment;

import java.util.List;

public class GankTodayListFragment
        extends BaseViewModelFragment<GankTodayListFragmentBinding, GankTodayListFragmentModel> {

    public static final String PARAM_CATEGORY = "param_category";

    private String mGankCategory;
    private GankTodayListAdapter mGankTodayListAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.gank_today_list_fragment;
    }

    @Override
    protected Class<GankTodayListFragmentModel> getViewModelClass() {
        return GankTodayListFragmentModel.class;
    }

    public static GankTodayListFragment newInstance(String category) {
        GankTodayListFragment fragment = new GankTodayListFragment();
        Bundle params = new Bundle();
        params.putString(PARAM_CATEGORY, category);
        fragment.setArguments(params);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGankCategory = getArguments().getString(PARAM_CATEGORY, "");
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
        initObservers();
        mModel.initDatas(mGankCategory);
    }

    private void initViews() {
        if (mGankTodayListAdapter == null) {
            mGankTodayListAdapter = new GankTodayListAdapter();
        }
        mView.recyclerview.setAdapter(mGankTodayListAdapter);
        mView.recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void initObservers() {
        mModel.getGankTodayItemEntities()
                .observe(this, new Observer<List<GankTodayItemEntity>>() {
                    @Override
                    public void onChanged(List<GankTodayItemEntity> gankTodayItemEntities) {
                        if (gankTodayItemEntities != null && gankTodayItemEntities.size() > 0) {
                            mGankTodayListAdapter.setDatas(gankTodayItemEntities);
                        }

                    }
                });
    }
}
