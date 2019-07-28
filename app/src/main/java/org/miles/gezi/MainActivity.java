package org.miles.gezi;

import android.os.Bundle;

import org.miles.gezi.databinding.ActivityMainBinding;
import org.miles.mvvm.BaseVewModelActivity;

public class MainActivity extends BaseVewModelActivity<ActivityMainBinding, MainViewModel> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initViews();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected Class<MainViewModel> getViewModelClass() {
        return MainViewModel.class;
    }

    private void initViews() {

    }
}
