package org.miles.gank.today.todaylist;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import org.miles.gank.R;
import org.miles.gank.common.GankRecyclerAdapter;
import org.miles.gank.data.entity.GankTodayItemEntity;
import org.miles.gank.databinding.GankTodayListFragmentBinding;
import org.miles.lib.mvvm.BaseViewModelFragment;
import org.miles.webview.WebViewActivity;

import java.util.List;

public class GankTodayListFragment
        extends BaseViewModelFragment<GankTodayListFragmentBinding, GankTodayListFragmentModel> {

    public static final String PARAM_CATEGORY = "param_category";

    private String mGankCategory;
    private GankRecyclerAdapter mGankRecyclerAdapter;

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
        mModel.setGankCategory(mGankCategory);
        mModel.initDatas();
    }

    private void initViews() {
        if (mGankRecyclerAdapter == null) {
            mGankRecyclerAdapter = new GankRecyclerAdapter();
            mGankRecyclerAdapter.setOnItemClickListener(new GankRecyclerAdapter.OnItemClickListener() {
                @Override
                public void onItemClicked(GankTodayItemEntity entity) {
                    WebViewActivity.openWebviewWithUrl(getContext(), entity.url);
                }
            });
        }
        mView.recyclerview.setAdapter(mGankRecyclerAdapter);
        mView.recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));

        mView.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mModel.refresh();
            }
        });
    }

    private void initObservers() {
        mModel.getGankTodayItemEntities()
                .observe(this, new Observer<List<GankTodayItemEntity>>() {
                    @Override
                    public void onChanged(List<GankTodayItemEntity> gankTodayItemEntities) {
                        if (gankTodayItemEntities != null && gankTodayItemEntities.size() > 0) {
                            mGankRecyclerAdapter.setDatas(gankTodayItemEntities);
                        }
                        mView.swipeRefreshLayout.setRefreshing(false);
                    }
                });
    }
}
