package org.miles.gank.itemlist;

import org.miles.kaiyan.R;
import org.miles.kaiyan.databinding.GankTabFragmentBinding;
import org.miles.lib.data.gank.entity.GankCategoryEntity;
import org.miles.lib.mvvm.BaseViewModelFragment;

public class GankTabFragment
        extends BaseViewModelFragment<GankTabFragmentBinding, GankTabFragmentModel> {

    public static GankTabFragment newInstance(GankCategoryEntity entity) {
        GankTabFragment fragment = new GankTabFragment();
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
}
