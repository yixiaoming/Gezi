package org.miles.gank;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import org.miles.gank.category.GankCategoryFragment;
import org.miles.kaiyan.R;

public class ModuleActivity extends AppCompatActivity {

    private GankCategoryFragment mGankCategoryFragment;
    private String mGankFragmentTag = GankCategoryFragment.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_activity);
        mGankCategoryFragment = (GankCategoryFragment) getSupportFragmentManager().findFragmentByTag(mGankFragmentTag);
        if (mGankCategoryFragment == null) {
            mGankCategoryFragment = GankCategoryFragment.newInstance();
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.contentView, mGankCategoryFragment, mGankFragmentTag);
        transaction.commit();
    }
}
