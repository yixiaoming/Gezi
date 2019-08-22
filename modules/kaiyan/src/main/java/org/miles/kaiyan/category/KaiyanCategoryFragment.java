package org.miles.kaiyan.category;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import org.miles.kaiyan.R;
import org.miles.kaiyan.databinding.KaiyanCategoryDetailFragmentBinding;
import org.miles.lib.data.kaiyan.entity.KaiyanCategory;
import org.miles.lib.data.kaiyan.entity.KaiyanVideoItem;
import org.miles.lib.mvvm.BaseViewModelFragment;

import java.util.List;

public class KaiyanCategoryFragment
        extends BaseViewModelFragment<KaiyanCategoryDetailFragmentBinding, KaiyanCategoryFragmentModel> {

    public static final String PARAM_CATEGORY_ID = "id";
    public static final String PARAM_CATEGORY_NAME = "name";

    private long mCategoryId;
    private String mCategoryName;

    private KaiyanCategoryAdapter mKaiyanCategoryAdapter;

    public static KaiyanCategoryFragment newInstance(KaiyanCategory category) {
        KaiyanCategoryFragment fragment = new KaiyanCategoryFragment();
        fragment.setArguments(new Bundle());
        fragment.getArguments().putLong(PARAM_CATEGORY_ID, category.id);
        fragment.getArguments().putString(PARAM_CATEGORY_NAME, category.name);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.kaiyan_category_detail_fragment;
    }

    @Override
    protected Class<KaiyanCategoryFragmentModel> getViewModelClass() {
        return KaiyanCategoryFragmentModel.class;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCategoryId = getArguments().getLong(PARAM_CATEGORY_ID);
        mCategoryName = getArguments().getString(PARAM_CATEGORY_NAME);
        mModel.setCategoryId(mCategoryId);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mKaiyanCategoryAdapter == null) {
            mKaiyanCategoryAdapter = new KaiyanCategoryAdapter();
        }
        mBinding.recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        mBinding.recyclerview.setAdapter(mKaiyanCategoryAdapter);
        mModel.getKaiyanVideoDatas().observe(this, new Observer<List<KaiyanVideoItem>>() {
            @Override
            public void onChanged(List<KaiyanVideoItem> kaiyanVideoItems) {
                mKaiyanCategoryAdapter.setDatas(kaiyanVideoItems);
            }
        });

        mModel.initDatas();
    }
}
