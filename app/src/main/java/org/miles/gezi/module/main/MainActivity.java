package org.miles.gezi.module.main;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.miles.gezi.R;
import org.miles.gezi.databinding.ActivityMainBinding;
import org.miles.lib.mvvm.BaseViewModelActivity;

public class MainActivity extends BaseViewModelActivity<ActivityMainBinding, MainViewModel>
        implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
        initObservers();
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
        mView.bottomNavigation.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return mModel.showFragment(item.getItemId(), R.id.main_content, getSupportFragmentManager());
    }

    private void initObservers() {

    }
}
