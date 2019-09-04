package org.miles.lib.mvvm;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseRecyclerViewHolder<V extends ViewDataBinding, D>
        extends RecyclerView.ViewHolder {

    protected V mView;

    private BaseRecyclerViewHolder(@NonNull V view) {
        super(view.getRoot());
        mView = view;
    }

    protected BaseRecyclerViewHolder(@NonNull ViewGroup parent, @LayoutRes int layoutId) {
        this((V) DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                layoutId, parent, false));
    }

    public abstract void bind(D data);
}