package org.miles.gank.itemlist;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import org.miles.kaiyan.R;
import org.miles.kaiyan.databinding.GankTabFragmentBinding;
import org.miles.lib.data.gank.entity.GankEntity;
import org.miles.lib.mvvm.BaseViewModelFragment;

import java.util.List;

public class GankTabFragment
        extends BaseViewModelFragment<GankTabFragmentBinding, GankTabFragmentModel> {

    public static final String PARAM_CATEGORY = "param_category_type";
    private String mCategory;
    private GankRecyclerAdapter mGankRecyclerAdapter;

    public static GankTabFragment newInstance(String category) {
        GankTabFragment fragment = new GankTabFragment();
        Bundle params = new Bundle();
        params.putString(PARAM_CATEGORY, category);
        fragment.setArguments(params);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.gank_tab_fragment;
    }

    @Override
    protected Class<GankTabFragmentModel> getViewModelClass() {
        return GankTabFragmentModel.class;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle params = getArguments();
        if (params != null) {
            mCategory = params.getString(PARAM_CATEGORY);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
        initObservers();
        mModel.initDatas(mCategory);
    }

    private void initViews() {
        if (mGankRecyclerAdapter == null) {
            mGankRecyclerAdapter = new GankRecyclerAdapter();
        }
        mView.recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        mView.recyclerview.setAdapter(mGankRecyclerAdapter);
    }

    private void initObservers() {
        mModel.getGankEntities().observe(
                this, new Observer<List<GankEntity>>() {
                    @Override
                    public void onChanged(List<GankEntity> gankEntities) {
                        if (gankEntities == null || gankEntities.size() == 0) {
                            // TODO: 19.9.5 显示空view
                        } else {
                            mGankRecyclerAdapter.setDatas(gankEntities);
                        }
                    }
                }
        );
    }
}
