package org.miles.gank.xiandu.xiandulist;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import org.miles.kaiyan.R;
import org.miles.kaiyan.databinding.GankCategoryListFragmentBinding;
import org.miles.lib.data.gank.entity.GankCategoryItemEntity;
import org.miles.lib.data.gank.entity.GankSecondCategoryEntity;
import org.miles.lib.mvvm.BaseViewModelFragment;

import java.util.List;

public class GankXianduListFragment
        extends BaseViewModelFragment<GankCategoryListFragmentBinding, GankXianduListFragmentModel> {

    public static final String PARAM_CATEGORY = "param_category_type";
    private String mCategory;
    private GankXianduListRecyclerAdapter mGankXianduListRecyclerAdapter;

    public static GankXianduListFragment newInstance(String category) {
        GankXianduListFragment fragment = new GankXianduListFragment();
        Bundle params = new Bundle();
        params.putString(PARAM_CATEGORY, category);
        fragment.setArguments(params);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.gank_category_list_fragment;
    }

    @Override
    protected Class<GankXianduListFragmentModel> getViewModelClass() {
        return GankXianduListFragmentModel.class;
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
        if (mGankXianduListRecyclerAdapter == null) {
            mGankXianduListRecyclerAdapter = new GankXianduListRecyclerAdapter();
        }
        mView.recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        mView.recyclerview.setAdapter(mGankXianduListRecyclerAdapter);
    }

    private void initObservers() {
        mModel.getSecondCategories().observe(this,
                new Observer<List<GankSecondCategoryEntity>>() {
                    @Override
                    public void onChanged(List<GankSecondCategoryEntity> gankSecondCategoryEntities) {
                        if (gankSecondCategoryEntities != null && gankSecondCategoryEntities.size() > 0) {
                            mView.scrollTabs.setVisibility(View.VISIBLE);
                            for (final GankSecondCategoryEntity entity : gankSecondCategoryEntities) {
                                TextView textView = new TextView(getContext());
                                textView.setText(entity.title);
                                ViewGroup.MarginLayoutParams params =
                                        new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                textView.setPadding(20, 10, 20, 10);
                                textView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        mModel.loadCategoryList(entity.categoryId, 1);
                                    }
                                });
                                mView.scrollTabs.addView(textView, params);
                            }
                            mModel.loadCategoryList(gankSecondCategoryEntities.get(0).categoryId, 1);
                        } else {
                            mView.scrollTabs.setVisibility(View.GONE);
                        }
                    }
                });

        mModel.getGankEntities().observe(
                this, new Observer<List<GankCategoryItemEntity>>() {
                    @Override
                    public void onChanged(List<GankCategoryItemEntity> gankEntities) {
                        if (gankEntities == null || gankEntities.size() == 0) {
                            mView.emptyView.setVisibility(View.VISIBLE);
                            mView.recyclerview.setVisibility(View.GONE);
                        } else {
                            mView.emptyView.setVisibility(View.GONE);
                            mView.recyclerview.setVisibility(View.VISIBLE);
                            mGankXianduListRecyclerAdapter.setDatas(gankEntities);
                        }
                    }
                }
        );
    }
}