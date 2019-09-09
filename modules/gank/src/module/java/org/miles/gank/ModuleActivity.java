package org.miles.gank;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class ModuleActivity extends AppCompatActivity {

    private GankFragment mGankFragment;
    private String mGankFragmentTag = GankFragment.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_activity);
        mGankFragment = (GankFragment) getSupportFragmentManager()
                .findFragmentByTag(mGankFragmentTag);
        if (mGankFragment == null) {
            mGankFragment = GankFragment.newInstance();
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.contentView, mGankFragment, mGankFragmentTag);
        transaction.commit();
    }
}
