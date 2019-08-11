package org.miles.gezi;

import android.os.Bundle;
import android.view.View;

import org.miles.lib.room.AppConfig;
import org.miles.gezi.databinding.ActivityMainBinding;
import org.miles.lib.mvvm.BaseVewModelActivity;

public class MainActivity extends BaseVewModelActivity<ActivityMainBinding, MainViewModel> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppConfig.init(getApplicationContext());
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
        mBinding.insertUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mModel.insertUser();
            }
        });
        mBinding.showUsersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mModel.showUsers();
            }
        });
        mBinding.showUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mModel.showUser();
            }
        });
    }
}
