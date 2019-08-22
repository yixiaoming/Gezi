package org.miles.lib.mvvm;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseRecyclerViewHolder<B extends ViewDataBinding, D>
        extends RecyclerView.ViewHolder {

    protected B mBinding;

    protected BaseRecyclerViewHolder(@NonNull B binding) {
        super(binding.getRoot());
        mBinding = binding;
    }

    protected BaseRecyclerViewHolder(@NonNull ViewGroup parent, @LayoutRes int layoutId) {
        this((B) DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                layoutId, parent, false));
    }

    public abstract void bind(D data);
}