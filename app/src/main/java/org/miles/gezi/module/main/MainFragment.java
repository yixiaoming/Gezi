package org.miles.gezi.module.main;

import org.miles.gezi.R;
import org.miles.gezi.databinding.ActivityMainBinding;
import org.miles.lib.mvvm.BaseViewModelFragment;

public class MainFragment extends BaseViewModelFragment<ActivityMainBinding, MainViewModel> {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected Class<MainViewModel> getViewModelClass() {
        return MainViewModel.class;
    }
}
