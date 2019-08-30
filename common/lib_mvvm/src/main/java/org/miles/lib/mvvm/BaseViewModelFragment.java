package org.miles.lib.mvvm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

public abstract class BaseViewModelFragment<V extends ViewDataBinding, M extends ViewModel>
        extends Fragment {

    protected V mView;
    protected M mModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mModel = ViewModelProviders.of(this).get(getViewModelClass());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        return mView.getRoot();
    }

    protected abstract int getLayoutId();

    protected abstract Class<M> getViewModelClass();
}
