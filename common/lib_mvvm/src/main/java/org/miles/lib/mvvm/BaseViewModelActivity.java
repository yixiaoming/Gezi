package org.miles.lib.mvvm;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

public abstract class BaseViewModelActivity<V extends ViewDataBinding, M extends ViewModel>
        extends AppCompatActivity {

    protected V mBinding;
    protected M mModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, getLayoutId());
        mModel = ViewModelProviders.of(this).get(getViewModelClass());
    }

    protected abstract int getLayoutId();

    protected abstract Class<M> getViewModelClass();
}
